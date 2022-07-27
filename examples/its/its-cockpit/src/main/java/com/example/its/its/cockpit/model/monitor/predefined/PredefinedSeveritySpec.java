package com.example.its.its.cockpit.model.monitor.predefined;

import java.lang.reflect.Method;

public class PredefinedSeveritySpec {
    private String name;
    private Method method;
    private Object bean;
    private DataType inputType;
    private Class<?> clazz;

    public PredefinedSeveritySpec(String name, Method method, Object bean, DataType inputType, Class<?> clazz) {
        this.name = name;
        this.method = method;
        this.bean = bean;
        this.inputType = inputType;
        this.clazz = clazz;
    }

    public PredefinedSeveritySpec(String name, Method method, Object bean, DataType inputType) {
        this(name, method, bean, inputType, null);
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

    public DataType getInputType() {
        return inputType;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
