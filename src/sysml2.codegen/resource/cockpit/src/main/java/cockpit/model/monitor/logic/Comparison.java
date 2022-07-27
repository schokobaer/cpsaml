package «pkg».cockpit.model.monitor.logic;

import «pkg».cockpit.model.monitor.PathFieldMapping;

import java.util.List;

public class Comparison implements Logical {
    private List<PathFieldMapping> path;
    private Comparator comparator;
    private Object value;

    public Comparison() {
    }

    public Comparison(List<PathFieldMapping> path, Comparator comparator, Object value) {
        this.path = path;
        this.comparator = comparator;
        this.value = value;
    }

    public List<PathFieldMapping> getPath() {
        return path;
    }

    public void setPath(List<PathFieldMapping> path) {
        this.path = path;
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