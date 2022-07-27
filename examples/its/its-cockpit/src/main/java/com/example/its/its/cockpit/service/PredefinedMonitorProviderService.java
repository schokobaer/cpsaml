package com.example.its.its.cockpit.service;
import com.example.its.its.cockpit.model.monitor.Cps;
import com.example.its.its.cockpit.model.monitor.Representation;
import com.example.its.its.cockpit.model.monitor.predefined.*;
import com.example.its.its.cockpit.model.monitor.predefined.Bounds;
import com.example.its.its.cockpit.util.Tuple;
import at.ac.tuwien.big.cps.annotationprocessor.annotation.*;
import at.ac.tuwien.big.cps.annotationprocessor.model.SeverityLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PredefinedMonitorProviderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PredefinedMonitorProviderService.class);

    private Map<String, PredefinedSeveritySpec> severities = new HashMap<>();
    private Map<Class<?>, Map<String, PredefinedValueMonitorSpec>> propertyMonitors = new HashMap<>();
    private Map<Class<?>, Map<String, Tuple<Method, Object>>> criticalMonitors = new HashMap<>();
    private Map<Class<?>, Map<String, Tuple<Method, Object>>> eventRules = new HashMap<>();

    /**
     * Helper
     */

    private DataType getReturnDataType(Method method) {
        if (List.class.isAssignableFrom(method.getReturnType())) {
            Class listType = (Class) ((ParameterizedType) method.getGenericReturnType()).getActualTypeArguments()[0];
            return new DataType(PrimitiveType.fromClass(listType), true);
        }
        return new DataType(PrimitiveType.fromClass(method.getReturnType()));
    }

    private DataType getParameterDataType(Method method, int index) {
        if (List.class.isAssignableFrom(method.getParameters()[index].getType())) {
            Class listType = (Class) ((ParameterizedType) method.getGenericParameterTypes()[index]).getActualTypeArguments()[0];
            return new DataType(PrimitiveType.fromClass(listType), true);
        }
        return new DataType(PrimitiveType.fromClass(method.getParameters()[index].getType()));
    }

    private Bounds getBounds(Method method) {
        if (!method.isAnnotationPresent(at.ac.tuwien.big.cps.annotationprocessor.annotation.Bounds.class)) {
            return null;
        }
        at.ac.tuwien.big.cps.annotationprocessor.annotation.Bounds annotation = method.getAnnotation(at.ac.tuwien.big.cps.annotationprocessor.annotation.Bounds.class);
        DataType dataType = getReturnDataType(method);
        if (dataType.isList() || !dataType.getType().isNumber()) {
            LOGGER.error("@Bounds is only allowed for method with a numerical return type");
            System.exit(1);
        }
        if (annotation.min() >= annotation.max()) {
            LOGGER.error("@Bounds: min must be smaller than max");
            System.exit(1);
        }
        return new Bounds(annotation.min(), annotation.max());
    }

    /**
     * REGISTER
     */

    @Autowired
    private void register(ApplicationContext ctx) {
        for (String bean: ctx.getBeanNamesForAnnotation(MonitorProvider.class)) {
            Object component = ctx.getBean(bean);
            for (Method method : component.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(PropertyMonitor.class)) {
                    registerPropertyMonitor(method, component);
                } else if (method.isAnnotationPresent(CriticalMonitor.class)) {
                    registerCriticalMonitor(method, component);
                } else if (method.isAnnotationPresent(Severity.class)) {
                    registerSeverity(method, component);
                } else if (method.isAnnotationPresent(Event.class)) {
                    registerEventSpecification(method, component);
                }
            }
        }
    }

    private void registerPropertyMonitor(Method method, Object component) {

        if (method.getParameters().length != 1) {
            LOGGER.error("@PropertyObjective methods allow exactly one parameter");
            System.exit(1);
        }

        try {
            DataType dataType = getReturnDataType(method);
            Bounds bounds = getBounds(method);
            Representation representation = bounds != null ? Representation.RANGE :
                    (!dataType.isList() && dataType.getType().isNumber()) ? Representation.GRAPH :
                            Representation.TEXT;

            Class<?> type = method.getParameters()[0].getType();
            if (!propertyMonitors.containsKey(type)) {
                propertyMonitors.put(type, new HashMap<>());
            }
            String name = method.getAnnotation(PropertyMonitor.class).value();
            if ("".equals(name)) {
                name = method.getName();
            }
            propertyMonitors.get(type).put(name, new PredefinedValueMonitorSpec(name, method, component, representation, dataType, bounds));
            LOGGER.info("Registered @PropertyObjective {} for class {}", name, type.getName());
        } catch (Exception e) {
            LOGGER.error("@PropertyObjective methods must return a primitive type or a list of primitives");
            System.exit(1);
        }
    }

    private void registerCriticalMonitor(Method method, Object component) {
        if (method.getParameters().length != 1) {
            LOGGER.error("@CriticalObjecte methods allow exactly one parameter");
            System.exit(1);
        }
        if (!method.getReturnType().equals(boolean.class)) {
            LOGGER.error("@CriticalMonitor methods need to return boolean");
            System.exit(1);
        }

        Class<?> type = method.getParameters()[0].getType();
        if (!criticalMonitors.containsKey(type)) {
            criticalMonitors.put(type, new HashMap<>());
        }
        String name = method.getAnnotation(CriticalMonitor.class).value();
        if ("".equals(name)) {
            name = method.getName();
        }
        criticalMonitors.get(type).put(name, new Tuple<>(method, component));
        LOGGER.info("Registered @CriticalMonitor {} for class {}", name, type.getName());
    }

    private void registerSeverity(Method method, Object component) {
        if (!method.getReturnType().equals(SeverityLevel.class)) {
            LOGGER.error("@Severity methods need to have {} as return type", SeverityLevel.class.getName());
            System.exit(1);
        }
        if (method.getParameters().length == 0 || method.getParameters().length > 2) {
            LOGGER.error("@Severity methods need to have exactly one or exactly two parameters");
            System.exit(1);
        }
        try {
            DataType dataType = null;
            Class<?> clazz = null;
            try {
                dataType = getParameterDataType(method, 0);
                if (method.getParameters().length == 2) {
                    clazz = method.getParameters()[1].getType();
                }
            } catch (IllegalArgumentException e) {
                dataType = getParameterDataType(method, 1);
                if (method.getParameters().length == 2) {
                    clazz = method.getParameters()[0].getType();
                }
            }
            String name = method.getAnnotation(Severity.class).value();
            if ("".equals(name)) {
                name = method.getName();
            }
            if (severities.containsKey(name)) {
                LOGGER.error("@Severity names need to be unique: {}" + name);
                System.exit(1);
            }
            severities.put(name, new PredefinedSeveritySpec(name, method, component, dataType, clazz));
            LOGGER.info("Registered @Severity {} with argument {}", name, dataType);
        } catch (Exception e) {
            LOGGER.error("@Severity methods need to have a primitive parameter type or a list of primitives");
            System.exit(1);
        }
    }

    private void registerEventSpecification(Method method, Object component) {
        if (method.getParameters().length != 2 || !method.getParameters()[0].getType().equals(method.getParameters()[1].getType())) {
            LOGGER.error("@EventSpecification methods allow exactly two parameter of the same  type");
            System.exit(1);
        }
        if (!method.getReturnType().equals(boolean.class)) {
            LOGGER.error("@EventSpecification methods need to return boolean");
            System.exit(1);
        }

        Class<?> type = method.getParameters()[0].getType();
        if (!eventRules.containsKey(type)) {
            eventRules.put(type, new HashMap<>());
        }
        String name = method.getAnnotation(Event.class).value();
        if ("".equals(name)) {
            name = method.getName();
        }
        eventRules.get(type).put(name, new Tuple<>(method, component));
        LOGGER.info("Registered @EventSpecification {} for class {}", name, type.getName());
    }

    /**
     * INVOKER
     */

    public boolean invokeCriticalMonitor(Object instance, String objective) throws InvocationTargetException, IllegalAccessException {
        Tuple<Method, Object> pair = criticalMonitors.get(instance.getClass()).get(objective);
        return (boolean) pair.getFirst().invoke(pair.getSecond(), instance);
    }

    public Object invokePropertyMonitor(Object instance, String objective) throws InvocationTargetException, IllegalAccessException {
        PredefinedValueMonitorSpec spec = propertyMonitors.get(instance.getClass()).get(objective);
        return spec.getMethod().invoke(spec.getBean(), instance);
    }

    public SeverityLevel invokeSeverity(Object value, String severity, Object instance) throws InvocationTargetException, IllegalAccessException {
        PredefinedSeveritySpec spec = severities.get(severity);
        if (spec.getClazz() != null) {
            if (spec.getMethod().getParameters()[0].getType().equals(spec.getClazz())) {
                return (SeverityLevel) spec.getMethod().invoke(spec.getBean(), instance, value);
            }
            return (SeverityLevel) spec.getMethod().invoke(spec.getBean(), value, instance);
        }
        return (SeverityLevel) spec.getMethod().invoke(spec.getBean(), value);
    }

    public boolean invokeEventSpecification(Object old, Object current, String objective) throws InvocationTargetException, IllegalAccessException {
        Tuple<Method, Object> pair = eventRules.get(old.getClass()).get(objective);
        return (boolean) pair.getFirst().invoke(pair.getSecond(), old, current);
    }



    /**
     * GETTER
     */

    /**
     * @Generated
     * @param cps
     * @return
     */
    public Map<String, PredefinedValueMonitorSpec> getPropertyMonitors(Cps cps) {
        return propertyMonitors.containsKey(cps.getType()) ? propertyMonitors.get(cps.getType()) : Map.of();
    }

    /**
     * @Generated
     * @param cps
     * @return
     */
    public List<String> getCriticalMonitors(Cps cps) {
        if (!criticalMonitors.containsKey(cps.getType())) {
            return List.of();
        }
        return criticalMonitors.get(cps.getType()).entrySet().stream()
                .map(kv -> kv.getKey())
                .collect(Collectors.toList());
    }

    public List<String> getSeverities(DataType type, Cps cps) {

        return severities.values().stream()
                .filter(spec -> spec.getInputType().equals(type))
                .filter(spec -> cps == null || spec.getClazz() == null || spec.getClazz().equals(cps.getType()))
                .map(spec -> spec.getName())
                .collect(Collectors.toList());
    }

    public List<String> getEventSpecifications(Cps cps) {
        if (!eventRules.containsKey(cps.getType())) {
            return List.of();
        }
        return eventRules.get(cps.getType()).entrySet().stream()
                .map(kv -> kv.getKey())
                .collect(Collectors.toList());
    }

}
