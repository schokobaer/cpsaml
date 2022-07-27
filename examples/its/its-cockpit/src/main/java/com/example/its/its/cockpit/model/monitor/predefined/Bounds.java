package com.example.its.its.cockpit.model.monitor.predefined;

public class Bounds {
    private double min;
    private double max;

    public Bounds(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}
