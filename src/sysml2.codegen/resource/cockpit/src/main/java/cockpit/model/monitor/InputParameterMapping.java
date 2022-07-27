package «pkg».cockpit.model.monitor;

public class InputParameterMapping<T> extends ParameterMapping {
    private InputType type;
    private T defaultValue;

    public InputType getType() {
        return type;
    }

    public void setType(InputType type) {
        this.type = type;
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(T defaultValue) {
        this.defaultValue = defaultValue;
    }
}
