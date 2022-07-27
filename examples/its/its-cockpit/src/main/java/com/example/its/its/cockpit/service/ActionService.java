package com.example.its.its.cockpit.service;

import com.example.its.its.cockpit.config.RabbitConfig;
import com.example.its.its.cockpit.log.CpsLogEvent;
import com.example.its.its.cockpit.log.CpsLogService;
import com.example.its.its.cockpit.model.monitor.Cps;
import com.example.its.its.cockpit.repo.CpsRepo;
import com.example.its.its.cockpit.util.ReflectionUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ActionService {

    @Autowired
    private CpsRepo repo;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CpsLogService logService;

    private static final String ACTION_ROUTING_KEY = "action.its";

    public void execute(Cps cps, String instanceId, String action, Map<String, Object> args, String dashboardId) {
        String routingKey = ACTION_ROUTING_KEY;
        if (cps.ordinal() > 0) {
            routingKey += "." + cps.name().toLowerCase();
        }

        try {
            String msg = objectMapper.writeValueAsString(args);
            rabbitTemplate.convertAndSend(RabbitConfig.ACTION_EXHANGER, routingKey, msg, m -> {
                Map<String, Object> headers = m.getMessageProperties().getHeaders();
                headers.put("action", action);
                headers.put("instance_id", instanceId);
                return m;
            });

            String logMsg = action + "(" +
                args.keySet().stream()
                        .map(key -> key + "=" + args.get(key))
                        .collect(Collectors.joining(",")) + ")";

            logService.log(dashboardId, instanceId, CpsLogEvent.ACTION, logMsg);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
