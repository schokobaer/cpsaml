package «pkg».cockpit.dto.web.monitor;

import java.util.List;

public class ActionDto {
    private String name;
    private String service;
    private List<ParameterMappingDto> params;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public List<ParameterMappingDto> getParams() {
        return params;
    }

    public void setParams(List<ParameterMappingDto> params) {
        this.params = params;
    }
}
