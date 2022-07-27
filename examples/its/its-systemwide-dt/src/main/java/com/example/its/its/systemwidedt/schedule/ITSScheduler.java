package com.example.its.its.systemwidedt.schedule;

import com.example.its.its.systemwidedt.dto.its.ITSDto;
import com.example.its.its.systemwidedt.repo.RoombaRepo;
import com.example.its.its.systemwidedt.service.ITSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@Component
public class ITSScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ITSScheduler.class);

	@Autowired
	private RoombaRepo roombaRepo;

    @Autowired
    private ITSService iTSService;

    @Scheduled(fixedRate = 200L)
    public void update() {

        final ZonedDateTime offlineThreshold = ZonedDateTime.now().minusSeconds(5);
        
        ITSDto dto = new ITSDto();
        dto.setRoombas(roombaRepo.getAll().stream()
                .filter(p -> p.right.isAfter(offlineThreshold))
                .map(p -> p.left)
                .collect(Collectors.toList())
        );
        LOGGER.info("Online roombas: {}", dto.getRoombas());
        
        iTSService.publish(dto);
    }
}
