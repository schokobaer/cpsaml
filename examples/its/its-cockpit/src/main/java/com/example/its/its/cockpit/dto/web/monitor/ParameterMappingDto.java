package com.example.its.its.cockpit.dto.web.monitor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "__type__")
@JsonSubTypes({
        @JsonSubTypes.Type(value = InputParameterMappingDto.class, name = "input"),
        @JsonSubTypes.Type(value = FixedValueParameterMappingDto.class, name = "fixed")
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class ParameterMappingDto {
    private String parameter;

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
