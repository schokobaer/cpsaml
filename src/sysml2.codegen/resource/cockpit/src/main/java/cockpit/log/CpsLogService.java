package «pkg».cockpit.log;

import «pkg».cockpit.dto.web.cps.CpsDto;
import «pkg».cockpit.dto.web.log.LogDto;
import «pkg».cockpit.repo.DtoRepo;
import «pkg».cockpit.service.DashboardService;
import «pkg».cockpit.service.WebSocketNotifyService;
import «pkg».cockpit.util.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class CpsLogService {

    private static final int MAX_LOGS = 256;
    // Monitor -> Id -> [CpsLogEntry]
    private Map<String, Map<String, List<CpsLogEntry>>> logs = new HashMap<>();

    @Autowired
    private WebSocketNotifyService notifyService;

    @Autowired
    private DtoRepo dtoRepo;

    public void log(String monitorId, String id, CpsLogEvent event, String msg) {
        CpsDto dto = dtoRepo.get(monitorId, id);
        log(monitorId, dto, event, msg);
    }

    public void log(String monitorId, CpsDto dto, CpsLogEvent event, String msg) {
        String id = dto.getId();
        if (!logs.containsKey(monitorId)) {
            logs.put(monitorId, new HashMap<>());
        }
        Map<String, List<CpsLogEntry>> cpsLogs = logs.get(monitorId);
        if (!cpsLogs.containsKey(id)) {
            cpsLogs.put(id, Collections.synchronizedList(new LinkedList<>()));
        }
        List<CpsLogEntry> instanceLogs = cpsLogs.get(id);
        CpsLogEntry entry = new CpsLogEntry(event, msg, dto);
        instanceLogs.add(entry);
        notifyService.log(new LogDto(monitorId, id, entry.getTimestamp(), entry.getEvent().name(), entry.getMsg()));
        adjust(instanceLogs);
    }

    private void adjust(List<CpsLogEntry> instanceLogs) {
        while (instanceLogs.size() > MAX_LOGS) {
            instanceLogs.remove(0);
        }
    }

    public List<CpsLogEntry> getLogs(String monitorId, String id) {
        if (logs.containsKey(monitorId) && logs.get(monitorId).containsKey(id)) {
            return logs.get(monitorId).get(id);
        }
        return List.of();
    }

    public CpsDto getLogCpsDto(String dashboardId, String id, LocalDateTime timestamp) {
        return getLogs(dashboardId, id).stream()
                .filter(l -> l.getTimestamp().equals(timestamp))
                .map(l -> l.getDto())
                .findAny()
                .orElse(null);
    }

}
