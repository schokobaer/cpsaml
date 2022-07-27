package at.ac.tuwien.cps.codegen.generator.cockpit.java

import at.ac.tuwien.cps.codegen.model.CpsSystem

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*
import at.ac.tuwien.cps.codegen.util.FileUtils
import at.ac.tuwien.cps.codegen.util.CpsUtil

class DataListenerGenerator {
		
	def generate(String path, String pkg, CpsSystem system) {
	    FileUtils.write('''«path»/listener/«system.name.toClazz»DataListener.java''', generateCode(pkg, system))
	}
	
	def generateCode(String pkg, CpsSystem system)
	'''
	package «pkg».cockpit.listener;
	
	import «pkg».cockpit.config.RabbitConfig;
	import «pkg».cockpit.model.cps.«system.name.toLow».«system.name.toClazz»;
	import «pkg».cockpit.model.monitor.Cps;
	import «pkg».cockpit.service.CpsService;
	import «pkg».cockpit.util.ReflectionUtils;
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.amqp.rabbit.annotation.RabbitListener;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Component;
	
	import java.util.Set;
	import java.util.stream.Collectors;
	
	@Component
	public class «system.name.toClazz»DataListener {
	
	    private static final Logger LOGGER = LoggerFactory.getLogger(«system.name.toClazz»DataListener.class);
	
	    @Autowired
	    private CpsService cpsService;
	
	    @RabbitListener(queues = RabbitConfig.DATA_«system.name.toConstant»_QUEUE)
	    public void listen(«system.name.toClazz» «system.name.toVar») {
	
	        // offline
	        «FOR cps: system.systemwideDt.parts.filter[it.list && CpsUtil.isCps(it.type)]»
	        Set<String> «cps.name.toVar»Ids = «system.name.toVar».get«cps.name.toClazz»().stream().map(r -> r.getId()).collect(Collectors.toSet());
	        cpsService.getAll(Cps.«cps.type.name.toConstant»).stream()
	                .filter(r -> !«cps.name.toVar»Ids.contains(ReflectionUtils.resolveId(r)))
	                .forEach(r -> cpsService.offline(r));
	        «ENDFOR»
	        «FOR cps: system.systemwideDt.parts.filter[!it.list && CpsUtil.isCps(it.type)]»
	        if («system.name.toVar».get«cps.name.toClazz»() == null && cpsService.getAll(Cps.«cps.type.name.toConstant»).stream()
	                .filter(r -> "«cps.name.toLow»".equals(ReflectionUtils.resolveId(r)))
	                .count() > 0) {
	            cpsService.offline(cpsService.getAll(Cps.«cps.type.name.toConstant»).stream()
	                    .filter(r -> "«cps.name.toLow»".equals(ReflectionUtils.resolveId(r)))
	                    .findFirst()
	                    .get());
	        }
		    «ENDFOR»
	
			«FOR cps: system.systemwideDt.parts.filter[it.list && CpsUtil.isCps(it.type)]»
			// «cps.type.name»
			«system.name.toVar».get«cps.name.toClazz»().forEach(r -> {
				cpsService.update(r);
			});
	        «ENDFOR»
	        
	        «FOR cps: system.systemwideDt.parts.filter[!it.list && CpsUtil.isCps(it.type)]»
	        // «cps.name»
	        if («system.name.toVar».get«cps.name.toClazz»() != null) {
	            cpsService.update(«system.name.toVar».get«cps.name.toClazz»());
	        }
	        
	        «ENDFOR»
	
	        cpsService.update(«system.name.toVar»);
	    }
	
	}
	'''
}