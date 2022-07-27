package «pkg».cockpit.dto.web.monitor.logic;

public class ThresholdDto {
    private String comparator;
    private Object value;

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
