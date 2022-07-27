package «pkg».cockpit.model.monitor;

import «pkg».cockpit.model.monitor.logic.Comparator;

public class Threshold {
    private Comparator comparator;
    private Object value;

    public Threshold() { }

    public Threshold(Comparator comparator, Object value) {
        this.comparator = comparator;
        this.value = value;
    }

    public Comparator getComparator() {
        return comparator;
    }

    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
