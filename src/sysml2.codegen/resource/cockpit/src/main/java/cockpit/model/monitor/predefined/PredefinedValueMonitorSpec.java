package «pkg».cockpit.model.monitor.predefined;

import «pkg».cockpit.model.monitor.Representation;

import java.lang.reflect.Method;

public class PredefinedValueMonitorSpec {
    private String name;
    private Method method;
    private Object bean;
    private Representation representation;
    private DataType returnType;
    private Bounds bounds;

    public PredefinedValueMonitorSpec(String name, Method method, Object bean, Representation representation, DataType returnType, Bounds bounds) {
        this.name = name;
        this.method = method;
        this.bean = bean;
        this.representation = representation;
        this.returnType = returnType;
        this.bounds = bounds;
    }

    public String getName() {
        return name;
    }

    public Method getMethod() {
        return method;
    }

    public Object getBean() {
        return bean;
    }

    public Representation getRepresentation() {
        return representation;
    }

    public DataType getReturnType() {
        return returnType;
    }

    public Bounds getBounds() {
        return bounds;
    }
}
