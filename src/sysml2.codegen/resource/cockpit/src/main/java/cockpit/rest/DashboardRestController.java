package «pkg».cockpit.rest;

import «pkg».cockpit.dto.web.monitor.DashboardDto;
import «pkg».cockpit.mapper.DashboardMapper;
import «pkg».cockpit.model.monitor.Dashboard;
import «pkg».cockpit.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class DashboardRestController {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private DashboardMapper mapper;

    @GetMapping("/api/dashboard")
    public Map<String, String> getDashboards() {
        return dashboardService.getAll().stream()
                .collect(Collectors.toMap(Dashboard::getId, Dashboard::getName));
    }

    @GetMapping("/api/dashboard/{id}")
    public DashboardDto getDashboard(@PathVariable String id) {
        return mapper.map(dashboardService.get(id));
    }

    @PostMapping("/api/dashboard")
    public String create(@RequestBody DashboardDto monitor) {
        return dashboardService.create(mapper.map(monitor));
    }

    @PutMapping("/api/dashboard/{id}")
    public void update(@PathVariable String id, @RequestBody DashboardDto monitor) {
        monitor.setId(id);
        dashboardService.update(mapper.map(monitor));
    }

    @DeleteMapping("/api/dashboard/{id}")
    public void delete(@PathVariable String id) {
        dashboardService.delete(id);
    }


}
