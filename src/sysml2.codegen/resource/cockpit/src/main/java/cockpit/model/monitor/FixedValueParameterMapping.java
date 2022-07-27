package «pkg».cockpit.model.monitor;

public class FixedValueParameterMapping<T> extends ParameterMapping {
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
