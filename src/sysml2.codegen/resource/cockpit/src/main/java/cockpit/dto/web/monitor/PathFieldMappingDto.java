package «pkg».cockpit.dto.web.monitor;

public class PathFieldMappingDto {
    private String field;
    private String function;

    public PathFieldMappingDto() { }

    public PathFieldMappingDto(String field, String function) {
        this.field = field;
        this.function = function;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
