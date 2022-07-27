package com.example.its.its.systemwidedt.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitConfig.class);

    public static final String DATA_EXCHANGER = "data";
    public static final String ACTION_EXHANGER = "action";

    public static final String ACTION_ITS_QUEUE = "actionits";
    public static final String ACTION_ITS_ROUTING_KEY = "action.its";

    public static final String DATA_ROOMBA_QUEUE = "roombadataits";
    public static final String DATA_ROOMBA_ROUTING_KEY = "data.roomba";
    public static final String ACTION_ITS_ROOMBA_ROUTING_KEY = "action.its.roomba";
    public static final String ACTION_ITS_ROOMBA_QUEUE = "actionitsroomba";


    @Bean(ACTION_EXHANGER)
    public TopicExchange actionExchange() {
        return new TopicExchange(ACTION_EXHANGER, false, false);
    }

    @Bean(DATA_EXCHANGER)
    public TopicExchange dataExchange() {
        return new TopicExchange(DATA_EXCHANGER, false, false);
    }
	 
    @Bean(ACTION_ITS_QUEUE)
    public Queue actionITSQueue() {
        return new Queue(ACTION_ITS_QUEUE, false, true, true);
    }
    
	@Bean
    public Binding actionITSBinding(@Qualifier(ACTION_ITS_QUEUE) Queue queue, @Qualifier(ACTION_EXHANGER) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ACTION_ITS_ROUTING_KEY);
    }
	 
    @Bean(DATA_ROOMBA_QUEUE)
    public Queue dataRoombaQueue() {
        return new Queue(DATA_ROOMBA_QUEUE, false, true, true);
    }
    
    @Bean(ACTION_ITS_ROOMBA_QUEUE)
    public Queue actionITSRoombaQueue() {
        return new Queue(ACTION_ITS_ROOMBA_QUEUE, false, true, true);
    }
    
    @Bean
    public Binding dataRoombaBinding(@Qualifier(DATA_ROOMBA_QUEUE) Queue queue, @Qualifier(DATA_EXCHANGER) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DATA_ROOMBA_ROUTING_KEY);
    }
    
    @Bean
    public Binding actionITSRoombaBinding(@Qualifier(ACTION_ITS_ROOMBA_QUEUE) Queue queue, @Qualifier(ACTION_EXHANGER) TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ACTION_ITS_ROOMBA_ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
