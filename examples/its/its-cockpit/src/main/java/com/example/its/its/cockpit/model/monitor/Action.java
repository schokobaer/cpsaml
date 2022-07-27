package com.example.its.its.cockpit.model.monitor;

import java.util.List;

public class Action {
    private String name;
    private String service;
    private List<ParameterMapping> params;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public List<ParameterMapping> getParams() {
        return params;
    }

    public void setParams(List<ParameterMapping> params) {
        this.params = params;
    }
}
