package «pkg».cockpit.rest;

import «pkg».cockpit.dto.web.cps.CpsDto;
import «pkg».cockpit.model.monitor.Cps;
import «pkg».cockpit.repo.DtoRepo;
import «pkg».cockpit.service.ActionService;
import «pkg».cockpit.service.CpsService;
import «pkg».cockpit.util.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class CpsRestController {

    @Autowired
    private CpsService cpsService;

    @Autowired
    private ActionService actionService;

    @Autowired
    private DtoRepo dtoRepo;

    @GetMapping("/api/cps/{cps}")
    public List<String> getAvailable(@PathVariable String cps) {
        return cpsService.getAll(Cps.valueOf(cps.toUpperCase())).stream()
                .map(r -> ReflectionUtils.resolveId(r))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/cps/{dashboard}/{id}")
    public CpsDto getDto(@PathVariable String dashboard, @PathVariable String id) {
        return dtoRepo.get(dashboard, id);
    }

    @PostMapping("/api/cps/{cps}/{id}/{action}")
    public void invokeAction(@PathVariable String cps, @PathVariable String id, @PathVariable String action,
                             @RequestBody Map<String, Object> args, @RequestHeader String dashboard) {
        actionService.execute(Cps.valueOf(cps.toUpperCase()), id, action, args, dashboard);
    }
}
