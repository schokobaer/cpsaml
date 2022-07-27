package at.ac.tuwien.cps.codegen.generator.cockpit.java

import at.ac.tuwien.cps.codegen.util.FileUtils
import at.ac.tuwien.cps.codegen.model.CpsSystem

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*

class CpsEnumGenerator {
		
	def generate(String path, String pkg, CpsSystem system) {
	    FileUtils.write('''«path»/model/monitor/Cps.java''', generateCode(pkg, system))
	}
	
	def generateCode(String pkg, CpsSystem system)
	'''
	package «pkg».cockpit.model.monitor;
	
	import «pkg».cockpit.model.cps.«system.name.toLow».«system.name.toClazz»;
	«FOR cps: system.cps»
	import «pkg».cockpit.model.cps.«cps.name.toLow».«cps.name.toClazz»;
	«ENDFOR»
	
	public enum Cps {
	    «system.name.toConstant»«FOR cps: system.cps», «cps.name.toConstant»«ENDFOR»;
	
	    public Class<?> getType() {
	    	if (this == «system.name.toConstant») {
	    		return «system.name.toClazz».class;
	    	} «FOR cps: system.cps» else if (this == «cps.name.toConstant») {
	    		return «cps.name.toClazz».class;
	    	}«ENDFOR»
	        return null;
	    }
	}
	'''
}