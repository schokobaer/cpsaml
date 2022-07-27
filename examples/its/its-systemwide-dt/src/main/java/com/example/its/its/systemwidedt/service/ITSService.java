package com.example.its.its.systemwidedt.service;

import com.example.its.its.systemwidedt.config.RabbitConfig;
import com.example.its.its.systemwidedt.dto.its.ITSDto;
import com.example.its.its.systemwidedt.repo.RoombaRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ITSService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ITSService.class);
    public static final String DATA_ITS_ROUTING_KEY = "data.its";
    
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RoombaRepo roombaRepo;

    @Autowired
    private RoombaService roombaService;
    
	public void request(String target) {
        roombaRepo.getAll().stream()
                .filter(r -> r.getLeft().getState().equals("IDLE"))
                .sorted((a, b) -> a.getRight().compareTo(b.getRight()))
                .map(r -> r.getLeft())
                .findFirst()
                .ifPresent(r -> roombaService.navigate(r.getId(), target));
	}
	
    public void publish(ITSDto dto) {
        LOGGER.info("publishing ITS");
        rabbitTemplate.convertAndSend(RabbitConfig.DATA_EXCHANGER, DATA_ITS_ROUTING_KEY, dto);
    }
}
