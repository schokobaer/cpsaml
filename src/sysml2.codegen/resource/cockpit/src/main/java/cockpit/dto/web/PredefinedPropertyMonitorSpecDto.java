package «pkg».cockpit.dto.web;

import «pkg».cockpit.model.monitor.predefined.Bounds;
import «pkg».cockpit.model.monitor.predefined.DataType;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PredefinedPropertyMonitorSpecDto {

    private String name;
    private String representation;
    private DataType returnType;
    private Bounds bounds;

    public PredefinedPropertyMonitorSpecDto() { }

    public PredefinedPropertyMonitorSpecDto(String name, String representation, DataType returnType, Bounds bounds) {
        this.name = name;
        this.representation = representation;
        this.returnType = returnType;
        this.bounds = bounds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    public DataType getReturnType() {
        return returnType;
    }

    public void setReturnType(DataType returnType) {
        this.returnType = returnType;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }
}
