package «pkg».cockpit.dto.web.alarm;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlarmDto {
    private String monitor;
    private String instance;
    private String context;
    private AlarmType type;
    private boolean active;

    public AlarmDto(String monitor, String instance, String context, AlarmType type, boolean active) {
        this.monitor = monitor;
        this.instance = instance;
        this.context = context;
        this.type = type;
        this.active = active;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public AlarmType getType() {
        return type;
    }

    public void setType(AlarmType type) {
        this.type = type;
    }

    public String asKey() {
        return monitor + "_" + context + "_" + instance;
    }
}
