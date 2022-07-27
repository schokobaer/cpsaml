package «pkg».cockpit.dto.web.monitor;

import «pkg».cockpit.dto.web.PredefinedPropertyMonitorSpecDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PropertyMonitorDto extends MonitorDto {

    private List<Object> path;
    private PredefinedPropertyMonitorSpecDto custom;
    private String representation;
    private SeverityDto severity;
    private String customSeverity;
    private Boolean alarmOnFalseValue = null;

    public PropertyMonitorDto() { }

    public List<Object> getPath() {
        return path;
    }

    public void setPath(List<Object> path) {
        this.path = path;
    }

    public PredefinedPropertyMonitorSpecDto getCustom() {
        return custom;
    }

    public void setCustom(PredefinedPropertyMonitorSpecDto custom) {
        this.custom = custom;
    }

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    public SeverityDto getSeverity() {
        return severity;
    }

    public void setSeverity(SeverityDto severity) {
        this.severity = severity;
    }

    public String getCustomSeverity() {
        return customSeverity;
    }

    public void setCustomSeverity(String customSeverity) {
        this.customSeverity = customSeverity;
    }

    public Boolean getAlarmOnFalseValue() {
        return alarmOnFalseValue;
    }

    public void setAlarmOnFalseValue(Boolean alarmOnFalseValue) {
        this.alarmOnFalseValue = alarmOnFalseValue;
    }
}
