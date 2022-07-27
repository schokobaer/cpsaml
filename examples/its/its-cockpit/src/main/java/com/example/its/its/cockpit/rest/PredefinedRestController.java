package com.example.its.its.cockpit.rest;

import com.example.its.its.cockpit.dto.web.PredefinedPropertyMonitorSpecDto;
import com.example.its.its.cockpit.model.monitor.Cps;
import com.example.its.its.cockpit.model.monitor.predefined.DataType;
import com.example.its.its.cockpit.model.monitor.predefined.PrimitiveType;
import com.example.its.its.cockpit.service.PredefinedMonitorProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PredefinedRestController {

    @Autowired
    private PredefinedMonitorProviderService predefinedMonitorProviderService;

    @GetMapping("/api/predefined/monitor/property")
    public List<PredefinedPropertyMonitorSpecDto> getPropertyObjectives(@RequestParam Cps cps) {
        return predefinedMonitorProviderService.getPropertyMonitors(cps).entrySet().stream()
                .map(kv -> new PredefinedPropertyMonitorSpecDto(
                        kv.getKey(),
                        kv.getValue().getRepresentation().name(),
                        kv.getValue().getReturnType(), kv.getValue().getBounds()
                ))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/predefined/monitor/critical")
    public List<String> getCriticals(@RequestParam Cps cps) {
        return predefinedMonitorProviderService.getCriticalMonitors(cps);
    }

    @GetMapping("/api/predefined/eventspec")
    public List<String> getEventSpecifications(@RequestParam Cps cps) {
        return predefinedMonitorProviderService.getEventSpecifications(cps);
    }

    @GetMapping("/api/predefined/severity")
    public List<String> getSeverities(@RequestParam boolean list, @RequestParam PrimitiveType type, @RequestParam Cps cps) {
        return predefinedMonitorProviderService.getSeverities(new DataType(type, list), cps);
    }

}
