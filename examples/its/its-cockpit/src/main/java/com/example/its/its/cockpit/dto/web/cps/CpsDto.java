package com.example.its.its.cockpit.dto.web.cps;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CpsDto {
    private String id;
    private String cps;
    private List<Pair<?>> data;
    private Map<String, Boolean> criticals;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCps() {
        return cps;
    }

    public void setCps(String cps) {
        this.cps = cps;
    }

    public List<Pair<?>> getData() {
        return data;
    }

    public void setData(List<Pair<?>> data) {
        this.data = data;
    }

    public Map<String, Boolean> getCriticals() {
        return criticals;
    }

    public void setCriticals(Map<String, Boolean> criticals) {
        this.criticals = criticals;
    }
}
