package «pkg».cockpit.service;

import «pkg».cockpit.dto.web.alarm.AlarmDto;
import «pkg».cockpit.dto.web.cps.CpsDto;
import «pkg».cockpit.dto.web.log.LogDto;
import «pkg».cockpit.model.monitor.Cps;
import «pkg».cockpit.repo.DashboardRepo;
import «pkg».cockpit.util.ReflectionUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class WebSocketNotifyService {

    @Autowired
    private SimpMessageSendingOperations simp;

    @Autowired
    private ObjectMapper jsonMapper;

    public void update(String dashboardId, CpsDto dto) {
        try {
            String json = jsonMapper.writeValueAsString(dto);
            simp.convertAndSend("/event/cps/" + dashboardId + "/" + dto.getId() + "/update", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void offline(String dashboardId, Cps cps, String instanceId) {
        try {
            CpsDto dto = new CpsDto();
            dto.setCps(cps.name());
            dto.setId(instanceId);
            String json = jsonMapper.writeValueAsString(dto);
            simp.convertAndSend("/event/cps/" + dashboardId + "/" + instanceId + "/offline", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void alarm(AlarmDto alarm) {
        try {
            String json = jsonMapper.writeValueAsString(alarm);
            simp.convertAndSend("/event/alarm", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void log(LogDto log) {
        try {
            String json = jsonMapper.writeValueAsString(log);
            simp.convertAndSend("/event/cps/" + log.getMonitor() + "/" + log.getId() + "/log", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
