package com.example.its.its.cockpit.listener;

import com.example.its.its.cockpit.config.RabbitConfig;
import com.example.its.its.cockpit.model.cps.its.ITS;
import com.example.its.its.cockpit.model.monitor.Cps;
import com.example.its.its.cockpit.service.CpsService;
import com.example.its.its.cockpit.util.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ITSDataListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ITSDataListener.class);

    @Autowired
    private CpsService cpsService;

    @RabbitListener(queues = RabbitConfig.DATA_ITS_QUEUE)
    public void listen(ITS iTS) {

        // offline
        Set<String> roombasIds = iTS.getRoombas().stream().map(r -> r.getId()).collect(Collectors.toSet());
        cpsService.getAll(Cps.ROOMBA).stream()
                .filter(r -> !roombasIds.contains(ReflectionUtils.resolveId(r)))
                .forEach(r -> cpsService.offline(r));

		// Roomba
		iTS.getRoombas().forEach(r -> {
			cpsService.update(r);
		});
        

        cpsService.update(iTS);
    }

}
