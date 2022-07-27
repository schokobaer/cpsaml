package com.example.its.its.cockpit.dto.web.monitor;

import com.example.its.its.cockpit.dto.web.monitor.logic.LogicalExpressionDto;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CriticalMonitorDto extends MonitorDto {

    private String onEvent;
    private LogicalExpressionDto expression;
    private String custom;

    public String getOnEvent() {
        return onEvent;
    }

    public void setOnEvent(String onEvent) {
        this.onEvent = onEvent;
    }

    public LogicalExpressionDto getExpression() {
        return expression;
    }

    public void setExpression(LogicalExpressionDto expression) {
        this.expression = expression;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }
}
