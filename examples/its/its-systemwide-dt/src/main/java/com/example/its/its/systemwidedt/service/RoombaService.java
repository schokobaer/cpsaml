package com.example.its.its.systemwidedt.service;

import com.example.its.its.systemwidedt.config.RabbitConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RoombaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoombaService.class);
    private static final String ROOMBA_ACTION_ROUTING_KEY = "action.";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;
	
	public void drive(String id, int speed) {
		LOGGER.info("Sending drive message to Roomba " + id);
		Map<String, Object> map = new HashMap<>();
		map.put("speed", speed);
		try {
			String json = objectMapper.writeValueAsString(map);
			rabbitTemplate.convertAndSend(RabbitConfig.ACTION_EXHANGER, ROOMBA_ACTION_ROUTING_KEY + id, json, m -> {
			    m.getMessageProperties().getHeaders().put("action", "drive");
				return m;
			});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	public void turn(String id, double angle) {
		LOGGER.info("Sending turn message to Roomba " + id);
		Map<String, Object> map = new HashMap<>();
		map.put("angle", angle);
		try {
			String json = objectMapper.writeValueAsString(map);
			rabbitTemplate.convertAndSend(RabbitConfig.ACTION_EXHANGER, ROOMBA_ACTION_ROUTING_KEY + id, json, m -> {
			    m.getMessageProperties().getHeaders().put("action", "turn");
				return m;
			});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	public void stop(String id) {
		LOGGER.info("Sending stop message to Roomba " + id);
		Map<String, Object> map = new HashMap<>();
		try {
			String json = objectMapper.writeValueAsString(map);
			rabbitTemplate.convertAndSend(RabbitConfig.ACTION_EXHANGER, ROOMBA_ACTION_ROUTING_KEY + id, json, m -> {
			    m.getMessageProperties().getHeaders().put("action", "stop");
				return m;
			});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	public void stack(String id, int speed, double time, double angle) {
		LOGGER.info("Sending stack message to Roomba " + id);
		Map<String, Object> map = new HashMap<>();
		map.put("speed", speed);
		map.put("time", time);
		map.put("angle", angle);
		try {
			String json = objectMapper.writeValueAsString(map);
			rabbitTemplate.convertAndSend(RabbitConfig.ACTION_EXHANGER, ROOMBA_ACTION_ROUTING_KEY + id, json, m -> {
			    m.getMessageProperties().getHeaders().put("action", "stack");
				return m;
			});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	public void start(String id) {
		LOGGER.info("Sending start message to Roomba " + id);
		Map<String, Object> map = new HashMap<>();
		try {
			String json = objectMapper.writeValueAsString(map);
			rabbitTemplate.convertAndSend(RabbitConfig.ACTION_EXHANGER, ROOMBA_ACTION_ROUTING_KEY + id, json, m -> {
			    m.getMessageProperties().getHeaders().put("action", "start");
				return m;
			});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	public void clear(String id) {
		LOGGER.info("Sending clear message to Roomba " + id);
		Map<String, Object> map = new HashMap<>();
		try {
			String json = objectMapper.writeValueAsString(map);
			rabbitTemplate.convertAndSend(RabbitConfig.ACTION_EXHANGER, ROOMBA_ACTION_ROUTING_KEY + id, json, m -> {
			    m.getMessageProperties().getHeaders().put("action", "clear");
				return m;
			});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	public void dock(String id) {
		LOGGER.info("Sending dock message to Roomba " + id);
		Map<String, Object> map = new HashMap<>();
		try {
			String json = objectMapper.writeValueAsString(map);
			rabbitTemplate.convertAndSend(RabbitConfig.ACTION_EXHANGER, ROOMBA_ACTION_ROUTING_KEY + id, json, m -> {
			    m.getMessageProperties().getHeaders().put("action", "dock");
				return m;
			});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	public void navigate(String id, String target) {
		LOGGER.info("Sending navigate message to Roomba " + id);
		Map<String, Object> map = new HashMap<>();
		map.put("target", target);
		try {
			String json = objectMapper.writeValueAsString(map);
			rabbitTemplate.convertAndSend(RabbitConfig.ACTION_EXHANGER, ROOMBA_ACTION_ROUTING_KEY + id, json, m -> {
			    m.getMessageProperties().getHeaders().put("action", "navigate");
				return m;
			});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
}
