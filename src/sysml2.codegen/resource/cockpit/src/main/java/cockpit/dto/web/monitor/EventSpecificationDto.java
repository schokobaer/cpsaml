package «pkg».cockpit.dto.web.monitor;

import «pkg».cockpit.dto.web.monitor.logic.LogicalExpressionDto;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventSpecificationDto {
    private String name;
    private LogicalExpressionDto before;
    private LogicalExpressionDto after;
    private String custom;

    public EventSpecificationDto() { }

    public EventSpecificationDto(String name, LogicalExpressionDto before, LogicalExpressionDto after) {
        this.name = name;
        this.before = before;
        this.after = after;
    }

    public EventSpecificationDto(String name, String custom) {
        this.name = name;
        this.custom = custom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LogicalExpressionDto getBefore() {
        return before;
    }

    public void setBefore(LogicalExpressionDto before) {
        this.before = before;
    }

    public LogicalExpressionDto getAfter() {
        return after;
    }

    public void setAfter(LogicalExpressionDto after) {
        this.after = after;
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }
}
