package com.example.its.its.systemwidedt.messaging;

import com.example.its.its.systemwidedt.config.RabbitConfig;
import com.example.its.its.systemwidedt.dto.roomba.RoombaDto;
import com.example.its.its.systemwidedt.repo.RoombaRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class RoombaDataListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoombaDataListener.class);

    @Autowired
    private RoombaRepo repo;

    @RabbitListener(queues = RabbitConfig.DATA_ROOMBA_QUEUE)
    public void listen(RoombaDto roomba, @Header String roomba_id) {
        LOGGER.info("Received Roomba data updatefor " + roomba_id, roomba);
        roomba.setId(roomba_id);
        repo.put(roomba);
    }

}
