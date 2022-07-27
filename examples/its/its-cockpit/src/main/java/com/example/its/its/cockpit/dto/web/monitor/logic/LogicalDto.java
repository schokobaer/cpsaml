package com.example.its.its.cockpit.dto.web.monitor.logic;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "__type__")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ComparisonDto.class, name = "comp"),
        @JsonSubTypes.Type(value = LogicalExpressionDto.class, name = "exp")
})
public interface LogicalDto {
}
