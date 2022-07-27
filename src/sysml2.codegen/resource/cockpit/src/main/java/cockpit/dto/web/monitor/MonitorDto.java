package «pkg».cockpit.dto.web.monitor;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "__type__")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PropertyMonitorDto.class, name = "prop"),
        @JsonSubTypes.Type(value = AggregationMonitorDto.class, name = "agg"),
        @JsonSubTypes.Type(value = CriticalMonitorDto.class, name = "critical")
})
public abstract class MonitorDto {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
