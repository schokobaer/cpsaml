package com.example.its.its.cockpit.dto.web.monitor.logic;

import java.util.List;

public class ComparisonDto implements LogicalDto{
    private List<Object> path;
    private String comparator;
    private Object value;

    public List<Object> getPath() {
        return path;
    }

    public void setPath(List<Object> path) {
        this.path = path;
    }

    public String getComparator() {
        return comparator;
    }

    public void setComparator(String comparator) {
        this.comparator = comparator;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
