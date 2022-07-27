package «pkg».cockpit.rest;

import «pkg».cockpit.dto.web.cps.CpsDto;
import «pkg».cockpit.dto.web.log.LogDto;
import «pkg».cockpit.log.CpsLogEntry;
import «pkg».cockpit.log.CpsLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LogRestController {

    @Autowired
    private CpsLogService service;

    @GetMapping("/api/log/{monitor}/{id}")
    public List<LogDto> getLogs(@PathVariable String monitor, @PathVariable String id) {
        return service.getLogs(monitor, id).stream()
                .map(log -> new LogDto(monitor, id, log.getTimestamp(), log.getEvent().name(), log.getMsg()))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/log/{monitor}/{id}/{timestamp}")
    public CpsDto getLogInstanceData(@PathVariable String monitor, @PathVariable String id, @PathVariable String timestamp) {
        return service.getLogCpsDto(monitor, id, LocalDateTime.parse(timestamp));
    }
}
