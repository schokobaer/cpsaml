package «pkg».cockpit.model.monitor;

import «pkg».cockpit.model.monitor.predefined.PredefinedValueMonitorSpec;
import «pkg».cockpit.util.Variant;

import java.util.List;

public class PropertyMonitor extends Monitor {

    private Variant<List<PathFieldMapping>, PredefinedValueMonitorSpec> source;
    private Representation representation = Representation.TEXT;
    private Variant<Severity, String> severity;
    private Boolean alarmOnFalseValue = null;


    public Variant<List<PathFieldMapping>, PredefinedValueMonitorSpec> getSource() {
        return source;
    }

    public void setSource(Variant<List<PathFieldMapping>, PredefinedValueMonitorSpec> source) {
        this.source = source;
    }

    public Representation getRepresentation() {
        return representation;
    }

    public void setRepresentation(Representation representation) {
        this.representation = representation;
    }

    public Variant<Severity, String> getSeverity() {
        return severity;
    }

    public void setSeverity(Variant<Severity, String> severity) {
        this.severity = severity;
    }

    public Boolean getAlarmOnFalseValue() {
        return alarmOnFalseValue;
    }

    public void setAlarmOnFalseValue(Boolean alarmOnFalseValue) {
        this.alarmOnFalseValue = alarmOnFalseValue;
    }
}
