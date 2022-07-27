package at.ac.tuwien.cps.codegen.generator.systemwidedt.service

import at.ac.tuwien.cps.codegen.util.FileUtils
import at.ac.tuwien.cps.codegen.model.CpsSystem
import at.ac.tuwien.cps.codegen.model.Part
import at.ac.tuwien.cps.codegen.util.CpsUtil

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*

class SystemwideServiceGenerator {
	
	def generate(String path, String pkg, CpsSystem system) {
		FileUtils.write('''«path»/service/«system.name.toClazz»Service.java''', generateCode(pkg, system.systemwideDt))
	}
	
	def generateCode(String pkg, Part system)
	'''
	package «pkg».systemwidedt.service;
	
	import «pkg».systemwidedt.config.RabbitConfig;
	import «pkg».systemwidedt.dto.«system.name.toLow».«system.name.toClazz»Dto;
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.amqp.rabbit.core.RabbitTemplate;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	
	@Service
	public class «system.name.toClazz»Service {
	
	    private static final Logger LOGGER = LoggerFactory.getLogger(«system.name.toClazz»Service.class);
	    public static final String DATA_«system.name.toConstant»_ROUTING_KEY = "data.«system.name.toLow»";
	    
	    @Autowired
	    private RabbitTemplate rabbitTemplate;
	    
		«FOR a: system.actions»
		public void «a.name.toVar»(«FOR p: a.params SEPARATOR ', '»«CpsUtil.dataType(p)» «p.name.toVar»«ENDFOR») {
			// TODO: Generated
		}
		
		«ENDFOR»		
	    public void publish(«system.name.toClazz»Dto dto) {
	        LOGGER.info("publishing «system.name»");
	        rabbitTemplate.convertAndSend(RabbitConfig.DATA_EXCHANGER, DATA_«system.name.toConstant»_ROUTING_KEY, dto);
	    }
	}
	'''
}