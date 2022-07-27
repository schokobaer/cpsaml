package at.ac.tuwien.big.cps.transform.generator

import at.ac.tuwien.big.cps.transform.util.FileUtils
import cpsaml.Cps
import cpsaml.MultiCps
import java.util.List

import static extension at.ac.tuwien.big.cps.transform.util.NamingUtil.*
import static extension at.ac.tuwien.big.cps.transform.util.SysMLUtils.*

class CpsGenerator {
	
	def generate(String path, Cps cps) {
		FileUtils.write('''«path»/«cps.name.toPath»/«cps.name.toClazz».sysml''', generateCode(cps))
	}
	
	private def generateCode(Cps cps)
	'''
	package «cps.name.toName» {
		
		private import Cps::CpsAnnotation;
		«FOR p: cps.hardware»
		private import «p.name.toName»::«p.name.toName»;
		«ENDFOR»
		
	    «FOR r: cps.resources»
	    «IF r.description.notEmpty»
	    /**
	     * «r.description.toComment»
	     */
	    «ENDIF»
	    «r.generateDef»
	    «ENDFOR»
		
	    «FOR a: cps.commands»
	    «IF a.description.notEmpty»
	    /**
	     * «a.description.notEmpty ? a.description.toComment : a.name»
	     */
	    «ENDIF»
	    «a.generateDef»
	    
	    «ENDFOR»
		
	    «IF cps.description.notEmpty»
	    /**
	     * «cps.description.toComment»
	     */
	    «ENDIF»
		part def «cps.name.toName» {
			@CpsAnnotation { mobile = «String.valueOf(cps instanceof MultiCps && (cps as MultiCps).instances.empty)»; }
			
	        «FOR p: cps.resources»
	        «p.generateUsage»
	        «ENDFOR»
	
			«FOR p: cps.hardware»
			«FOR i: p.instances.empty ? List.of(p.name) : p.instances»
			part «i.toVar»: «p.name.toName»;
			«ENDFOR»
			«ENDFOR»
			
			«FOR a: cps.commands»
			«a.generatePerform»
			«ENDFOR»
		}
	}
	'''
	
}