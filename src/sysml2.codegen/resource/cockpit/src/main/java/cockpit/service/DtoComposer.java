package «pkg».cockpit.service;

import «pkg».cockpit.dto.web.alarm.AlarmDto;
import «pkg».cockpit.dto.web.alarm.AlarmType;
import «pkg».cockpit.dto.web.cps.CpsDto;
import «pkg».cockpit.dto.web.cps.Pair;
import «pkg».cockpit.model.monitor.*;
import «pkg».cockpit.model.monitor.predefined.Bounds;
import «pkg».cockpit.model.monitor.logic.Comparison;
import «pkg».cockpit.repo.CpsRepo;
import «pkg».cockpit.repo.DashboardRepo;
import «pkg».cockpit.repo.DtoRepo;
import «pkg».cockpit.util.ReflectionUtils;
import «pkg».cockpit.util.Tuple;
import at.ac.tuwien.big.cps.annotationprocessor.model.SeverityLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static «pkg».cockpit.util.Utils.runCatching;

@Component
public class DtoComposer {

    @Autowired
    private CpsRepo cpsRepo;

    @Autowired
    private LogicalEvaluator evaluator;

    @Autowired
    private PredefinedMonitorProviderService objectiveProvider;

    @Autowired
    private DtoRepo dtoRepo;


    private Object getAggregatedValue(AggregationMonitor objective, Cps cps) {

        Stream stream = cpsRepo.getAll(cps).stream()
                .filter(elem -> evaluator.evaluate(elem, objective.getFilter()));

        if (objective.getAggregation() == Aggregation.COUNT) {
            return stream.count();
        }

        DoubleStream ds = stream.map(elem -> objective.getSource().map(
                path -> runCatching(() -> evaluator.resolvePathRec(elem, path)),
                custom -> runCatching(() -> objectiveProvider.invokePropertyMonitor(elem, custom.getName()))
        )).mapToDouble(elem -> Double.parseDouble(elem.toString()));

        if (objective.getAggregation() == Aggregation.AVG) {
            return ds.average().orElse(0);
        } else if (objective.getAggregation() == Aggregation.MIN) {
            return ds.min();
        } else if (objective.getAggregation() == Aggregation.MAX) {
            return ds.max();
        } else if (objective.getAggregation() == Aggregation.SUM) {
            return ds.sum();
        }
        throw new RuntimeException("Invalid aggregation function: " + objective.getAggregation());
    }

    private SeverityLevel getExtra(Object value, PropertyMonitor objective, Object instance) {
        if (objective.getSeverity() == null) {
            return null;
        }
        if (objective.getSeverity().isB()) {
            try {
                return objectiveProvider.invokeSeverity(value, objective.getSeverity().getB(), instance);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
        }
        Severity severity = objective.getSeverity().getA();
        if (evaluator.evaluate(value, new Comparison(
                List.of(),
                severity.getGreen().getComparator(),
                severity.getGreen().getValue()
        ))) {
            return SeverityLevel.GREEN;
        } else if (severity.getOrange() != null && evaluator.evaluate(value, new Comparison(
                List.of(),
                severity.getOrange().getComparator(),
                severity.getOrange().getValue()
        ))) {
            return SeverityLevel.ORANGE;
        } else {
            return SeverityLevel.RED;
        }
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

    public List<Pair<?>> composeData(Dashboard dashboard, Object instance) {
        return dashboard.getMonitors().stream()
                .filter(PropertyMonitor.class::isInstance)
                .map(PropertyMonitor.class::cast)
                .map(m -> evaluateMonitor(m, instance, dashboard))
                .collect(Collectors.toList());
    }

    private <T> Pair<T> evaluateMonitor(PropertyMonitor propertyMonitor, Object instance, Dashboard dashboard) {
        Pair pair = new Pair();
        pair.setKey(propertyMonitor.getName());

        T value;
        if (propertyMonitor instanceof AggregationMonitor) {
            value = (T) getAggregatedValue((AggregationMonitor) propertyMonitor, ReflectionUtils.resolveCps(instance));
        } else {
            value = (T) propertyMonitor.getSource().map(
                    path -> runCatching(() -> evaluator.resolvePathRec(instance, path)),
                    custom -> runCatching(() -> objectiveProvider.invokePropertyMonitor(instance, custom.getName()))
            );
        }

        if (propertyMonitor.getRepresentation() == Representation.GRAPH) {
            CpsDto old = dtoRepo.get(dashboard.getId(), ReflectionUtils.resolveId(instance));
            List<Tuple<Integer, T>> history = new ArrayList<>();
            if (old != null) {
                history = old.getData().stream()
                        .filter(p -> p.getKey().equals(propertyMonitor.getName()))
                        .map(p -> (List<Tuple<Integer, T>>) p.getValue())
                        .findAny()
                        .orElse(new ArrayList<>());
                history = history.stream().map(t -> new Tuple<>(t.getFirst() + 1, t.getSecond())).collect(Collectors.toList());
                if (history.size() > 20) {
                    history.remove(0);
                }
            }
            history.add(new Tuple<>(0, value));
            pair.setValue(history);
        } else {
            pair.setValue(value);
        }

        SeverityLevel severityLevel = getExtra(value, propertyMonitor, instance);
        if (severityLevel != null) {
            pair.setExtra(severityLevel.name());
        }
        return pair;
    }

    public Map<String, Boolean> composeCriticals(Dashboard dashboard, Object instance, Set<String> events) {
        return dashboard.getMonitors().stream()
                .filter(CriticalMonitor.class::isInstance)
                .map(CriticalMonitor.class::cast)
                .collect(Collectors.toMap(
                        c -> c.getName(),
                        c -> evaluateCritical(instance, c, events)
                ));
    }

    private Boolean evaluateCritical(Object instance, CriticalMonitor monitor, Set<String> events) {
        if (monitor.getOnEvent() != null && !events.contains(monitor.getOnEvent())) {
            return true;
        }
        return monitor.getExpression().map(
                exp -> runCatching(() -> evaluator.evaluate(instance, exp), false),
                cust -> runCatching(() -> objectiveProvider.invokeCriticalMonitor(instance, cust), false)
        );
    }
}
