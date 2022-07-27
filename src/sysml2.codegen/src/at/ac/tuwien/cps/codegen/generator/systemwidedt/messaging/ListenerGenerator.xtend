package at.ac.tuwien.cps.codegen.generator.systemwidedt.messaging

import at.ac.tuwien.cps.codegen.model.Part
import at.ac.tuwien.cps.codegen.model.CpsSystem
import at.ac.tuwien.cps.codegen.util.FileUtils

import at.ac.tuwien.cps.codegen.util.CpsUtil

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*
import org.omg.sysml.lang.sysml.ReferenceUsage

class ListenerGenerator {
	
	
	def generate(String path, String pkg, CpsSystem system) {
		if (!system.systemwideDt.actions.empty) {
			FileUtils.write('''«path»/messaging/«system.systemwideDt.name.toClazz»ActionListener.java''', generateActionListenerCode(pkg, system.systemwideDt, system.name, false))
		}
		system.cps.forEach[
			val singleton = CpsUtil.isSingletonCps(system, it)
			if (!it.actions.empty) {
				FileUtils.write('''«path»/messaging/«it.name.toClazz»ActionListener.java''', generateActionListenerCode(pkg, it, system.name, !singleton))
			}
			FileUtils.write('''«path»/messaging/«it.name.toClazz»DataListener.java''', generateDataListenerCode(pkg, it, singleton))
		]
	}
	
	private def argumentParser(ReferenceUsage p, String content) {
		val wrapper = CpsUtil.dataTypeWrapper(p);
		
		if ("Integer".equals(wrapper)) {
			return '''Integer.parseInt(«content».toString())'''
		} else if ("Double".equals(wrapper)) {
			return '''Double.parseDouble(«content».toString())'''
		} else if ("Boolean".equals(wrapper)) {
			return '''Boolean.parseBoolean(«content».toString())'''
		}
		
		return '''«content».toString()''';
	}
	
	private def String generateActionListenerCode(String pkg, Part part, String systemName, boolean isCps)
	'''
	package «pkg».systemwidedt.messaging;
		
	import «pkg».systemwidedt.config.RabbitConfig;
	import «pkg».systemwidedt.service.«part.name.toClazz»Service;
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
	public class «part.name.toClazz»ActionListener {
	
	    private static final Logger LOGGER = LoggerFactory.getLogger(«part.name.toClazz»ActionListener.class);
	
	    @Autowired
	    private «part.name.toClazz»Service «part.name.toVar»Service;
	
	    @Autowired
	    private ObjectMapper objectMapper;
	
	    @RabbitListener(queues = RabbitConfig.ACTION_«systemName.toConstant»_«IF isCps»«part.name.toConstant»_«ENDIF»QUEUE)
	    public void listen(String data, @Header String action«IF isCps», @Header String instance_id«ENDIF») throws JsonProcessingException {
	        Map<String, ?> args = objectMapper.readValue(data, Map.class);
	        
	        «FOR a: part.actions SEPARATOR ' else '»if ("«a.name.toVar»".equals(action)) {
	            «part.name.toVar»Service.«a.name.toVar»(«IF isCps»instance_id«IF !a.params.empty», «ENDIF»«ENDIF»«FOR p: a.params SEPARATOR ', '»«argumentParser(p, '''args.get("«p.name.toVar»")''')»«ENDFOR»);
	        }«ENDFOR» else {
	            LOGGER.warn("Unknown action {} for «part.name» «IF isCps»{}«ENDIF»", action«IF isCps», instance_id«ENDIF»);
	        }
	    }
	
	}
	'''
	
	private def String generateDataListenerCode(String pkg, Part part, boolean singleton)
	'''
	package «pkg».systemwidedt.messaging;
	
	import «pkg».systemwidedt.config.RabbitConfig;
	import «pkg».systemwidedt.dto.«part.name.toLow».«part.name.toClazz»Dto;
	import «pkg».systemwidedt.repo.«part.name.toClazz»Repo;
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.amqp.rabbit.annotation.RabbitListener;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.messaging.handler.annotation.Header;
	import org.springframework.stereotype.Component;
	
	@Component
	public class «part.name.toClazz»DataListener {
	
	    private static final Logger LOGGER = LoggerFactory.getLogger(«part.name.toClazz»DataListener.class);
	
	    @Autowired
	    private «part.name.toClazz»Repo repo;
	
	    @RabbitListener(queues = RabbitConfig.DATA_«part.name.toConstant»_QUEUE)
	    public void listen(«part.name.toClazz»Dto «part.name.toVar»«IF !singleton», @Header String «part.name.toLow»_id«ENDIF») {
	        LOGGER.info("Received «part.name» data update«IF singleton»" «ELSE»for " + «part.name.toLow»_id«ENDIF», «part.name.toVar»);
	        «IF !singleton»
	        «part.name.toVar».setId(«part.name.toLow»_id);
	        «ENDIF»
	        repo.put(«part.name.toVar»);
	    }
	
	}
	'''
}