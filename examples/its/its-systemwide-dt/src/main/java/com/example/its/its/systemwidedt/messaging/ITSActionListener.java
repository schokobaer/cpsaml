package com.example.its.its.systemwidedt.messaging;
	
import com.example.its.its.systemwidedt.config.RabbitConfig;
import com.example.its.its.systemwidedt.service.ITSService;
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
public class ITSActionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ITSActionListener.class);

    @Autowired
    private ITSService iTSService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitConfig.ACTION_ITS_QUEUE)
    public void listen(String data, @Header String action) throws JsonProcessingException {
        Map<String, ?> args = objectMapper.readValue(data, Map.class);
        
        if ("request".equals(action)) {
            iTSService.request(args.get("target").toString());
        } else {
            LOGGER.warn("Unknown action {} for ITS ", action);
        }
    }

}
