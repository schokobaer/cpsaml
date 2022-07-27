package com.example.its.its.cockpit.service;


import static com.example.its.its.cockpit.util.Utils.runCatching;
import com.example.its.its.cockpit.log.CpsLogEvent;
import com.example.its.its.cockpit.log.CpsLogService;
import com.example.its.its.cockpit.model.monitor.Dashboard;
import com.example.its.its.cockpit.repo.CpsRepo;
import com.example.its.its.cockpit.util.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EventGenerator {

    @Autowired
    private LogicalEvaluator logicalEvaluator;

    @Autowired
    private PredefinedMonitorProviderService predefinedMonitorProviderService;

    @Autowired
    private CpsRepo cpsRepo;

    public Set<String> getOccurringEvents(Dashboard dashboard, Object instance) {
        Object old = cpsRepo.get(instance);

        if (old == null) {
            return Set.of();
        }
        return dashboard.getEventSpecifications().stream()
                .filter(es -> es.getExpression().map(
                        tuple -> runCatching(() -> logicalEvaluator.evaluate(old, tuple.getFirst()) && logicalEvaluator.evaluate(instance, tuple.getSecond()), false),
                        predefined -> runCatching(() -> predefinedMonitorProviderService.invokeEventSpecification(old, instance, predefined), false)
                ))
                .map(es -> es.getName())
                .collect(Collectors.toSet());
    }

}
