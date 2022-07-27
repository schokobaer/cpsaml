package at.ac.tuwien.cps.codegen.generator.systemwidedt.service

import at.ac.tuwien.cps.codegen.model.CpsSystem
import at.ac.tuwien.cps.codegen.util.FileUtils
import at.ac.tuwien.cps.codegen.model.Part
import at.ac.tuwien.cps.codegen.util.CpsUtil

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*

class CpsServiceGenerator {
	
	def generate(String path, String pkg, CpsSystem system) {
		system.cps.forEach[
			val singleton = CpsUtil.isSingletonCps(system, it)
			FileUtils.write('''«path»/service/«it.name.toClazz»Service.java''', generateCode(pkg, it, singleton))
		]
	}
	
	def generateCode(String pkg, Part cps, boolean singleton)
	'''
	package «pkg».systemwidedt.service;
	
	import «pkg».systemwidedt.config.RabbitConfig;
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
	public class «cps.name.toClazz»Service {
	
	    private static final Logger LOGGER = LoggerFactory.getLogger(«cps.name.toClazz»Service.class);
	    private static final String «cps.name.toConstant»_ACTION_ROUTING_KEY = «IF singleton»"action.«cps.name.toLow»"«ELSE»"action."«ENDIF»;
	
	    @Autowired
	    private RabbitTemplate rabbitTemplate;
	
	    @Autowired
	    private ObjectMapper objectMapper;
		
		«FOR a: cps.actions»
		public void «a.name.toVar»(«IF !singleton»String id«ENDIF»«IF !a.params.empty && !singleton», «ENDIF»«FOR p: a.params SEPARATOR ', '»«CpsUtil.dataType(p)» «p.name.toVar»«ENDFOR») {
			LOGGER.info("Sending «a.name» message to «cps.name» "«IF !singleton» + id«ENDIF»);
			Map<String, Object> map = new HashMap<>();
			«FOR p: a.params»
			map.put("«p.name.toVar»", «p.name.toVar»);
			«ENDFOR»
			try {
				String json = objectMapper.writeValueAsString(map);
				rabbitTemplate.convertAndSend(RabbitConfig.ACTION_EXHANGER, «cps.name.toConstant»_ACTION_ROUTING_KEY«IF !singleton» + id«ENDIF», json, m -> {
				    m.getMessageProperties().getHeaders().put("action", "«a.name.toVar»");
					return m;
				});
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
		«ENDFOR»
	}
	'''
}