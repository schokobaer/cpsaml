package com.example.its.its.cockpit.dto.web.monitor;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardDto {
    private String id;
    private String name;
    private String cps;
    private String instance;
    private String notificationType;
    private List<ActionDto> actions;
    private List<MonitorDto> monitors;
    private List<EventSpecificationDto> eventSpecifications;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCps() {
        return cps;
    }

    public void setCps(String cps) {
        this.cps = cps;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public List<ActionDto> getActions() {
        return actions;
    }

    public void setActions(List<ActionDto> actions) {
        this.actions = actions;
    }

    public List<MonitorDto> getMonitors() {
        return monitors;
    }

    public void setMonitors(List<MonitorDto> monitors) {
        this.monitors = monitors;
    }

    public List<EventSpecificationDto> getEventSpecifications() {
        return eventSpecifications;
    }

    public void setEventSpecifications(List<EventSpecificationDto> eventSpecifications) {
        this.eventSpecifications = eventSpecifications;
    }
}
