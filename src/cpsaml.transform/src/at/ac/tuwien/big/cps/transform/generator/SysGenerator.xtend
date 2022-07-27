package at.ac.tuwien.big.cps.transform.generator

import at.ac.tuwien.big.cps.transform.util.FileUtils
import cpsaml.CpsSystem

import static extension at.ac.tuwien.big.cps.transform.util.NamingUtil.*
import static extension at.ac.tuwien.big.cps.transform.util.SysMLUtils.*
import cpsaml.MultiCps
import cpsaml.SingletonCps

class SysGenerator {
	
	def generate(String path, CpsSystem sys) {
		FileUtils.write('''«path»/«sys.name.toClazz».sysml''', generateCode(sys))
	}
	
	private def generateCode(CpsSystem sys)
	'''
	package «sys.name.toName» {
	    
	    private import Cps::SystemAnnotation;
	    «FOR c: sys.cps»
	    private import «c.name.toName»::«c.name.toName»;
	    «ENDFOR»
	    
	    «FOR a: sys.commands»
	    «IF a.description.notEmpty»
	    /**
	     * «a.description.notEmpty ? a.description.toComment : a.name»
	     */
	    «ENDIF»
	    «a.generateDef»
	    
	    «ENDFOR»
	
		part def «sys.name.toName» {
			@SystemAnnotation;
			
			«FOR c: sys.cps.filter(SingletonCps)»
			part «c.name.toVar»: «c.name.toName»;
			«ENDFOR»
			«FOR c: sys.cps.filter(MultiCps).filter[it.instances.empty]»
			part «c.name.toVar.toPlural»: «c.name.toName»[*];
			«ENDFOR»
			«FOR c: sys.cps.filter(MultiCps).filter[!(it as MultiCps).instances.empty]»
			«FOR i: c.instances»
			part «i.toVar»: «c.name.toName»;
			«ENDFOR»
			«ENDFOR»
			
			
			«FOR a: sys.commands»
			«a.generatePerform»
			«ENDFOR»
		}
		
		
		part «sys.name.toVar»: «sys.name.toName»;
	}
	'''
}