package at.ac.tuwien.big.cps.transform.generator

import at.ac.tuwien.big.cps.transform.util.FileUtils
import cpsaml.Actuator
import cpsaml.Hardware
import cpsaml.Sensor

import static extension at.ac.tuwien.big.cps.transform.util.NamingUtil.*
import static extension at.ac.tuwien.big.cps.transform.util.SysMLUtils.*

class HardwareGenerator {
		
	def generate(String path, Hardware hw) {
		FileUtils.write('''«path»/«hw.name.toClazz».sysml''', generateCode(hw))
	}
		
	private def dispatch generateCode(Sensor sensor)
	'''
	package «sensor.name.toName» {
			
			private import Cps::RosNodeAnnotation;
			
	        «FOR r: sensor.resources»
	        «IF r.description.notEmpty»
	        /**
	         * «r.description.toComment»
	         */
	        «ENDIF»
	        «r.generateDef»
			«ENDFOR»
			
		    «IF sensor.description.notEmpty»
		    /**
		     * «sensor.description.toComment»
		     */
			«ENDIF»
			part def «sensor.name.toName» {
				@RosNodeAnnotation;
				
		        «FOR p: sensor.resources»
		        «p.generateUsage»
		        «ENDFOR»
			}
		}
	'''
	
	private def dispatch generateCode(Actuator actuator)
	'''
	package «actuator.name.toName» {
			
			private import Cps::RosNodeAnnotation;
			
		    «FOR a: actuator.commands»
		    «IF a.description.notEmpty»
		    /**
		     * «a.description.toComment»
		     */
		    «ENDIF»
		    «a.generateDef»
		    
		    «ENDFOR»
		    
		    «IF actuator.description.notEmpty»
		    /**
		     * «actuator.description.toComment»
		     */
			«ENDIF»
			part def «actuator.name.toName» {
				@RosNodeAnnotation;
						        
				«FOR a: actuator.commands»
				«a.generatePerform»
				«ENDFOR»
			}
		}
	'''
}