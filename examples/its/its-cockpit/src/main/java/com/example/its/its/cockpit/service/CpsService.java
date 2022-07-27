package com.example.its.its.cockpit.service;

import com.example.its.its.cockpit.dto.web.alarm.AlarmDto;
import com.example.its.its.cockpit.dto.web.alarm.AlarmType;
import com.example.its.its.cockpit.dto.web.cps.CpsDto;
import com.example.its.its.cockpit.dto.web.cps.Pair;
import com.example.its.its.cockpit.log.CpsLogEvent;
import com.example.its.its.cockpit.log.CpsLogService;
import com.example.its.its.cockpit.model.monitor.*;
import com.example.its.its.cockpit.model.monitor.predefined.Bounds;
import com.example.its.its.cockpit.repo.CpsRepo;
import com.example.its.its.cockpit.repo.DtoRepo;
import com.example.its.its.cockpit.util.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.its.its.cockpit.util.Utils.runCatching;

@Service
public class CpsService {

    @Autowired
    private CpsRepo repo;

    @Autowired
    private DtoRepo dtoRepo;

    @Autowired
    private CpsLogService logService;

    @Autowired
    private EventGenerator eventGenerator;

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private DtoComposer composer;

    @Autowired
    private AlarmService alarmService;

    @Autowired
    private WebSocketNotifyService webSocketNotifyService;

    public List<Object> getAll(Cps cps) {
        return repo.getAll(cps).stream().collect(Collectors.toList());
    }

    public void update(Object instance) {
        Cps cps = ReflectionUtils.resolveCps(instance);
        String id = ReflectionUtils.resolveId(instance);

        // online
        final boolean online = repo.get(instance) == null;

        dashboardService.getAll(instance).stream()
                .forEach(dashboard -> {
                    // Events
                    Set<String> events = eventGenerator.getOccurringEvents(dashboard, instance);

                    CpsDto dto = new CpsDto();
                    dto.setId(id);
                    dto.setCps(cps.name());

                    // Criticals
                    dto.setCriticals(composer.composeCriticals(dashboard, instance, events));

                    // data
                    dto.setData(composer.composeData(dashboard, instance));
                    this.validateData(dto, dashboard, instance);

                    // store
                    dtoRepo.put(dashboard.getId(), dto);

                    // publish
                    events.stream().forEach(e -> logService.log(dashboard.getId(), dto, CpsLogEvent.CUSTOM, e));
                    webSocketNotifyService.update(dashboard.getId(), dto);

                    // online
                    if (online) {
                        logService.log(
                                dashboard.getId(),
                                dto,
                                CpsLogEvent.ONLINE,
                                id + " went online"
                        );
                    }

                    dto.getCriticals().keySet().stream()
                            .filter(critical -> {
                                // Filter out criticals with onEvent that were positiv
                                CriticalMonitor moni = (CriticalMonitor) dashboard.getMonitors().stream()
                                        .filter(m -> m.getName().equals(critical))
                                        .findAny()
                                        .get();
                                if (moni.getOnEvent() != null && dto.getCriticals().get(critical)) {
                                    return false;
                                }
                                return true;
                            })
                            .forEach(critical -> alarmService.report(new AlarmDto(
                                    dashboard.getId(),
                                    id,
                                    critical,
                                    AlarmType.CRITICAL,
                                    !dto.getCriticals().get(critical)
                            ), dto));

                });

        repo.put(cps, instance);
    }

    public void offline(Object lastInstance) {
        dashboardService.getAll(lastInstance)
                .forEach(dashboard -> webSocketNotifyService.offline(
                        dashboard.getId(),
                        ReflectionUtils.resolveCps(lastInstance),
                        ReflectionUtils.resolveId(lastInstance)
                ));
    }

    private void validateData(CpsDto dto, Dashboard dashboard, Object instance) {
        List<Pair<?>> data = dto.getData();
        dashboard.getMonitors().stream()
                .filter(PropertyMonitor.class::isInstance)
                .map(PropertyMonitor.class::cast)
                .filter(m -> m.getAlarmOnFalseValue() != null && m.getAlarmOnFalseValue().booleanValue())
                .forEach(m -> {
                    Object value = data.stream().filter(p -> p.getKey().equals(m.getName())).findAny().get().getValue();
                    Bounds bounds = m.getSource().map(
                            path -> runCatching(() -> getBounds(instance, path)),
                            custom -> custom.getBounds()
                    );
                    if (bounds != null && value instanceof Comparable) {
                        Comparable comparable = (Comparable) value;
                        alarmService.report(new AlarmDto(
                                dashboard.getId(),
                                ReflectionUtils.resolveId(instance),
                                m.getName(),
                                AlarmType.OUTOFBOUNDS,
                                comparable.compareTo(bounds.getMin()) < 0 || comparable.compareTo(bounds.getMax()) > 0
                        ), dto);
                    }
                });
    }

    private Bounds getBounds(Object instance, List<PathFieldMapping> path) throws NoSuchFieldException, IllegalAccessException {
        Field field = null;
        Object ctx = instance;
        for (PathFieldMapping pfm: path) {
            field = ctx.getClass().getDeclaredField(pfm.getField());
            field.setAccessible(true);
            ctx = field.get(ctx);
        }

        if (field != null && field.isAnnotationPresent(at.ac.tuwien.big.cps.annotationprocessor.annotation.Bounds.class)) {
        	at.ac.tuwien.big.cps.annotationprocessor.annotation.Bounds anno = field.getAnnotation(at.ac.tuwien.big.cps.annotationprocessor.annotation.Bounds.class);
            return new Bounds(anno.min(), anno.max());
        }
        return null;
    }
}
