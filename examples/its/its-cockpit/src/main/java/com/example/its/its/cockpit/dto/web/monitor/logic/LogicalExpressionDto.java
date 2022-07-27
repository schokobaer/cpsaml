package com.example.its.its.cockpit.dto.web.monitor.logic;

import java.util.List;

public class LogicalExpressionDto implements LogicalDto {
    private List<LogicalDto> expressions;
    private String operator;
    private boolean not;

    public List<LogicalDto> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<LogicalDto> expressions) {
        this.expressions = expressions;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public boolean isNot() {
        return not;
    }

    public void setNot(boolean not) {
        this.not = not;
    }
}
