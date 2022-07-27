package «pkg».cockpit.dto.web.monitor;


public class InputParameterMappingDto extends ParameterMappingDto {
    private String type;
    private String defaultValue;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
