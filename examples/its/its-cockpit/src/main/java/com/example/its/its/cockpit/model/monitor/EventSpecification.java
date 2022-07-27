package com.example.its.its.cockpit.model.monitor;

import com.example.its.its.cockpit.model.monitor.logic.LogicalExpression;
import com.example.its.its.cockpit.util.Tuple;
import com.example.its.its.cockpit.util.Variant;

public class EventSpecification {
    private String name;
    private Variant<Tuple<LogicalExpression, LogicalExpression>, String> expression;

    public EventSpecification() { }

    public EventSpecification(String name, LogicalExpression before, LogicalExpression after) {
        this.name = name;
        this.expression = Variant.of(new Tuple<>(before, after), null);
    }

    public EventSpecification(String name, String custom) {
        this.name = name;
        this.expression = Variant.of(null, custom);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Variant<Tuple<LogicalExpression, LogicalExpression>, String> getExpression() {
        return expression;
    }

    public void setExpression(Variant<Tuple<LogicalExpression, LogicalExpression>, String> expression) {
        this.expression = expression;
    }
}
