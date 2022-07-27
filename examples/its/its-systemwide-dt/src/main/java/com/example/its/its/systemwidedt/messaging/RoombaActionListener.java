package com.example.its.its.systemwidedt.messaging;
	
import com.example.its.its.systemwidedt.config.RabbitConfig;
import com.example.its.its.systemwidedt.service.RoombaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RoombaActionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoombaActionListener.class);

    @Autowired
    private RoombaService roombaService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitConfig.ACTION_ITS_ROOMBA_QUEUE)
    public void listen(String data, @Header String action, @Header String instance_id) throws JsonProcessingException {
        Map<String, ?> args = objectMapper.readValue(data, Map.class);
        
        if ("drive".equals(action)) {
            roombaService.drive(instance_id, Integer.parseInt(args.get("speed").toString()));
        } else if ("turn".equals(action)) {
            roombaService.turn(instance_id, Double.parseDouble(args.get("angle").toString()));
        } else if ("stop".equals(action)) {
            roombaService.stop(instance_id);
        } else if ("stack".equals(action)) {
            roombaService.stack(instance_id, Integer.parseInt(args.get("speed").toString()), Double.parseDouble(args.get("time").toString()), Double.parseDouble(args.get("angle").toString()));
        } else if ("start".equals(action)) {
            roombaService.start(instance_id);
        } else if ("clear".equals(action)) {
            roombaService.clear(instance_id);
        } else if ("dock".equals(action)) {
            roombaService.dock(instance_id);
        } else if ("navigate".equals(action)) {
            roombaService.navigate(instance_id, args.get("target").toString());
        } else {
            LOGGER.warn("Unknown action {} for Roomba {}", action, instance_id);
        }
    }

}
