package com.example.its.its.cockpit.model.monitor;

import com.example.its.its.cockpit.model.monitor.logic.LogicalExpression;
import com.example.its.its.cockpit.util.Variant;

public class CriticalMonitor extends Monitor {

    private String onEvent;
    private Variant<LogicalExpression, String> expression;

    public String getOnEvent() {
        return onEvent;
    }

    public void setOnEvent(String onEvent) {
        this.onEvent = onEvent;
    }

    public Variant<LogicalExpression, String> getExpression() {
        return expression;
    }

    public void setExpression(Variant<LogicalExpression, String> expression) {
        this.expression = expression;
    }
}
