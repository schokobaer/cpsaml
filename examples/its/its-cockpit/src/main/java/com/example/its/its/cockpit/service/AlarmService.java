package com.example.its.its.cockpit.service;

import com.example.its.its.cockpit.dto.web.alarm.AlarmDto;
import com.example.its.its.cockpit.dto.web.cps.CpsDto;
import com.example.its.its.cockpit.log.CpsLogEvent;
import com.example.its.its.cockpit.log.CpsLogService;
import com.example.its.its.cockpit.model.monitor.NotificationType;
import com.example.its.its.cockpit.repo.DashboardRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Point for reporting alarms.
 */
@Service
public class AlarmService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmService.class);

    @Autowired
    private WebSocketNotifyService notifyService;

    @Autowired
    private CpsLogService logRepo;

    @Autowired
    private DashboardRepo repo;

    @Autowired(required = false)
    private AlarmSenderService alarmSenderService;

    private Map<String, AlarmDto> activeAlarms = new HashMap<>();

    public void report(AlarmDto alarm, CpsDto dto) {
        String key = alarm.asKey();
        if (!alarm.isActive() && activeAlarms.containsKey(key)) {
            activeAlarms.remove(key);
            notifyService.alarm(alarm);
            logRepo.log(
                    alarm.getMonitor(),
                    dto,
                    CpsLogEvent.RESOLVE,
                    alarm.getContext() + " resolved"
            );
            //LOGGER.info("Rescinded alarm {} for instance {}", alarm.getCritical(), alarm.getInstance());
        } else if (alarm.isActive() && !activeAlarms.containsKey(key)) {
            activeAlarms.put(key, alarm);
            notifyService.alarm(alarm);
            logRepo.log(
                    alarm.getMonitor(),
                    dto,
                    CpsLogEvent.ALARM,
                    alarm.getContext() + " assumption failed"
            );
            //LOGGER.info("New alarm {} for instance {}", alarm.getCritical(), alarm.getInstance());
        }
    }

    private void send(AlarmDto alarm) {
        NotificationType type = repo.get(alarm.getMonitor()).getNotificationType();
        if (type == NotificationType.NONE || alarmSenderService == null) {
            return;
        } else if (type == NotificationType.EMAIL) {
            alarmSenderService.email(alarm);
        } else if (type == NotificationType.SMS) {
            alarmSenderService.sms(alarm);
        }
    }

}
