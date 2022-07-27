package «pkg».cockpit.model.monitor;

import «pkg».cockpit.util.Variant;

public class PathFieldMapping {
    private String field;
    private Variant<ScalarFunction, VectorFunction> function;

    public PathFieldMapping() { }

    public PathFieldMapping(String field) {
        this.field = field;
    }

    public PathFieldMapping(String field, ScalarFunction function) {
        this.field = field;
        this.function = Variant.of(function, null);
    }

    public PathFieldMapping(String field, VectorFunction function) {
        this.field = field;
        this.function = Variant.of(null, function);
    }

    public PathFieldMapping(String field, Variant<ScalarFunction, VectorFunction> functionVariant) {
        this.field = field;
        this.function = functionVariant;
    }

    public String getField() {
        return field;
    }

    public Variant<ScalarFunction, VectorFunction> getFunction() {
        return function;
    }
}
