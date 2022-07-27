package at.ac.tuwien.cps.codegen.generator.systemwidedt.repo

import at.ac.tuwien.cps.codegen.util.FileUtils
import at.ac.tuwien.cps.codegen.model.CpsSystem
import at.ac.tuwien.cps.codegen.model.Part

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*
import at.ac.tuwien.cps.codegen.util.CpsUtil

class RepoGenerator {
		 	 
	def generate(String path, String pkg, CpsSystem system) {
		system.cps.forEach[
			val singleton = CpsUtil.isSingletonCps(system, it)
			FileUtils.write('''«path»/repo/«it.name.toClazz»Repo.java''', generateCode(pkg, it, singleton))
		]
	}
	
	def generateCode(String pkg, Part part, boolean singleton)
	'''
	package «pkg».systemwidedt.repo;
	
	import «pkg».systemwidedt.dto.«part.name.toLow».«part.name.toClazz»Dto;
	import org.apache.commons.lang3.tuple.ImmutablePair;
	import org.springframework.stereotype.Repository;
	
	import java.time.ZonedDateTime;
	import java.util.Collection;
	import java.util.HashMap;
	import java.util.Map;
	
	@Repository
	public class «part.name.toClazz»Repo {
	
	    «IF singleton»
	    private «part.name.toClazz»Dto data;
	    private ZonedDateTime lastUpdate;
	    «ELSE»
	    private Map<String, ImmutablePair<«part.name.toClazz»Dto, ZonedDateTime>> data = new HashMap<>();
	    «ENDIF»
	
	    public void put(«part.name.toClazz»Dto «part.name.toVar») {
	        «IF singleton»
	        data = «part.name.toVar»;
	        lastUpdate = ZonedDateTime.now();
	    	«ELSE»
	        data.put(«part.name.toVar».getId(), new ImmutablePair<>(«part.name.toVar», ZonedDateTime.now()));
	        «ENDIF»
	    }
		
	    «IF singleton»
	    public ImmutablePair<«part.name.toClazz»Dto, ZonedDateTime> get() {
	        return new ImmutablePair<>(data, lastUpdate);
	    }
	    «ELSE»
	    public Collection<ImmutablePair<«part.name.toClazz»Dto, ZonedDateTime>> getAll() {
	        return data.values();
	    }
	    «ENDIF»
	}
	'''
}