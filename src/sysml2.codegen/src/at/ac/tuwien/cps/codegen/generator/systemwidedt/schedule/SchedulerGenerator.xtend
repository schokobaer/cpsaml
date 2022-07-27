package at.ac.tuwien.cps.codegen.generator.systemwidedt.schedule

import at.ac.tuwien.cps.codegen.model.CpsSystem
import at.ac.tuwien.cps.codegen.util.FileUtils

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*
import at.ac.tuwien.cps.codegen.generator.systemwidedt.dto.DtoGenerator
import at.ac.tuwien.cps.codegen.mapper.SysMLMapper
import at.ac.tuwien.cps.codegen.util.CpsUtil

class SchedulerGenerator {
	
	SysMLMapper mapper = new SysMLMapper()
	
	def generate(String path, String pkg, CpsSystem system) {
		FileUtils.write('''«path»/schedule/«system.name.toClazz»Scheduler.java''', generateCode(pkg, system))
	}
	
	def generateCode(String pkg, CpsSystem system)
	'''
	package «pkg».systemwidedt.schedule;
	
	import «pkg».systemwidedt.dto.«system.name.toLow».«system.name.toClazz»Dto;
	«FOR cps: system.cps»
	import «pkg».systemwidedt.repo.«cps.name.toClazz»Repo;
	«ENDFOR»
	import «pkg».systemwidedt.service.«system.name.toClazz»Service;
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.scheduling.annotation.Scheduled;
	import org.springframework.stereotype.Component;
	
	import java.time.ZonedDateTime;
	import java.util.stream.Collectors;
	
	@Component
	public class «system.name.toClazz»Scheduler {
	
	    private static final Logger LOGGER = LoggerFactory.getLogger(«system.name.toClazz»Scheduler.class);
	
		«FOR cps: system.cps»
		@Autowired
		private «cps.name.toClazz»Repo «cps.name.toVar»Repo;
	    «ENDFOR»
	
	    @Autowired
	    private «system.name.toClazz»Service «system.name.toVar»Service;
	
	    @Scheduled(fixedRate = 200L)
	    public void update() {
	
	        final ZonedDateTime offlineThreshold = ZonedDateTime.now().minusSeconds(5);
	        
	        «system.name.toClazz»Dto dto = new «system.name.toClazz»Dto();
	        «FOR f: system.systemwideDt.parts»
	        «val p = mapper.map(f.type)»
	        «IF CpsUtil.isSingletonCps(system, p)»
	        if (scaleRepo.get().left != null && scaleRepo.get().right.isAfter(offlineThreshold)) {
	            dto.setScale(scaleRepo.get().left);
	        }
            «ELSEIF f.list»
	        dto.set«f.name.toClazz»(«p.name.toVar»Repo.getAll().stream()
	                .filter(p -> p.right.isAfter(offlineThreshold))
	                .map(p -> p.left)
	                .collect(Collectors.toList())
	        );
	        «ELSE»
	        dto.set«f.name.toClazz»(«p.name.toVar»Repo.getAll().stream()
	                .filter(p -> p.right.isAfter(offlineThreshold) && "«f.name.toLow»".equals(p.left.getId()))
	                .map(p -> p.left)
	                .findAny()
	                .orElse(null)
	        );
	        «ENDIF»
	        LOGGER.info("Online «f.name»: {}", dto.get«f.name.toClazz»());
	        «ENDFOR»
	        
	        «system.name.toVar»Service.publish(dto);
	    }
	}
	'''
}