package at.ac.tuwien.cps.codegen.generator.systemwidedt.config

import at.ac.tuwien.cps.codegen.model.CpsSystem
import at.ac.tuwien.cps.codegen.util.FileUtils

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*

class RabbitConfigGenerator {
	 
	 final String fileName = '''/config/RabbitConfig.java'''
	 	 
	def generate(String path, String pkg, CpsSystem system) {
        FileUtils.write(path + fileName, generateCode(pkg, system))
	}
	 
	def generateCode(String pkg, CpsSystem system)
	'''
	package «pkg».systemwidedt.config;
	
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
	
	    public static final String ACTION_«system.name.toConstant»_QUEUE = "action«system.name.toLow»";
	    public static final String ACTION_«system.name.toConstant»_ROUTING_KEY = "action.«system.name.toLow»";
	
	    «FOR cps: system.cps»
	    public static final String DATA_«cps.name.toConstant»_QUEUE = "«cps.name.toLow»data«system.name.toLowerCase»";
	    public static final String DATA_«cps.name.toConstant»_ROUTING_KEY = "data.«cps.name.toLow»";
	    public static final String ACTION_«system.name.toConstant»_«cps.name.toConstant»_ROUTING_KEY = "action.«system.name.toLow».«cps.name.toLow»";
	    public static final String ACTION_«system.name.toConstant»_«cps.name.toConstant»_QUEUE = "action«system.name.toLow»«cps.name.toLow»";
	    «ENDFOR»
	
	
	    @Bean(ACTION_EXHANGER)
	    public TopicExchange actionExchange() {
	        return new TopicExchange(ACTION_EXHANGER, false, false);
	    }
	
	    @Bean(DATA_EXCHANGER)
	    public TopicExchange dataExchange() {
	        return new TopicExchange(DATA_EXCHANGER, false, false);
	    }
		 
	    @Bean(ACTION_«f(system.name)»_QUEUE)
	    public Queue action«system.name.toClazz»Queue() {
	        return new Queue(ACTION_«system.name.toConstant»_QUEUE, false, true, true);
	    }
	    
		@Bean
	    public Binding action«system.name.toClazz»Binding(@Qualifier(ACTION_«system.name.toConstant»_QUEUE) Queue queue, @Qualifier(ACTION_EXHANGER) TopicExchange exchange) {
	        return BindingBuilder.bind(queue).to(exchange).with(ACTION_«system.name.toConstant»_ROUTING_KEY);
	    }
		 
	    «FOR cps: system.cps»
	    @Bean(DATA_«cps.name.toConstant»_QUEUE)
	    public Queue data«cps.name.toClazz»Queue() {
	        return new Queue(DATA_«cps.name.toConstant»_QUEUE, false, true, true);
	    }
	    
	    @Bean(ACTION_«system.name.toConstant»_«cps.name.toConstant»_QUEUE)
	    public Queue action«system.name.toClazz»«cps.name.toClazz»Queue() {
	        return new Queue(ACTION_«system.name.toConstant»_«cps.name.toConstant»_QUEUE, false, true, true);
	    }
	    
	    @Bean
	    public Binding data«cps.name.toClazz»Binding(@Qualifier(DATA_«cps.name.toConstant»_QUEUE) Queue queue, @Qualifier(DATA_EXCHANGER) TopicExchange exchange) {
	        return BindingBuilder.bind(queue).to(exchange).with(DATA_«cps.name.toConstant»_ROUTING_KEY);
	    }
	    
	    @Bean
	    public Binding action«system.name.toClazz»«cps.name.toClazz»Binding(@Qualifier(ACTION_«system.name.toConstant»_«cps.name.toConstant»_QUEUE) Queue queue, @Qualifier(ACTION_EXHANGER) TopicExchange exchange) {
	        return BindingBuilder.bind(queue).to(exchange).with(ACTION_«system.name.toConstant»_«cps.name.toConstant»_ROUTING_KEY);
	    }
		«ENDFOR»
	
	    @Bean
	    public Jackson2JsonMessageConverter messageConverter() {
	        return new Jackson2JsonMessageConverter();
	    }
	
	}
	'''
}