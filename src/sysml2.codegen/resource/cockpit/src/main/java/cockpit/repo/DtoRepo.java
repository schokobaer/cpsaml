package «pkg».cockpit.repo;

import «pkg».cockpit.dto.web.cps.CpsDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Component
public class DtoRepo {

    private Map<String, Map<String, CpsDto>> data = new HashMap<>();

    public void put(String dashboardId, CpsDto dto) {
        if (!data.containsKey(dashboardId)) {
            data.put(dashboardId, new HashMap<>());
        }
        data.get(dashboardId).put(dto.getId(), dto);
    }

    public CpsDto get(String dashboardId, String id) {
        if (!data.containsKey(dashboardId)) {
            return null;
        }
        return data.get(dashboardId).get(id);
    }
}
