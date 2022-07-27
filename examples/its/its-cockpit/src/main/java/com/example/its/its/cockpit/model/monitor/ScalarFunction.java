package com.example.its.its.cockpit.model.monitor;

public enum ScalarFunction {
    COUNT, FIRST, LAST, EMPTY, AVG, MIN, MAX, SUM;

    public boolean isIndexFunction() {
        return this == FIRST || this == LAST;
    }

    public boolean isDeadEndAggregation() {
        return this == COUNT || this == EMPTY;
    }

    public boolean isSubPathAggregation() {
        return this == AVG || this == MIN || this == MAX || this == SUM;
    }
}
