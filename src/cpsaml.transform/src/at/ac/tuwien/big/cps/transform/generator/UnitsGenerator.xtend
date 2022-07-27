package at.ac.tuwien.big.cps.transform.generator

import cpsaml.Cps
import static extension at.ac.tuwien.big.cps.transform.util.NamingUtil.*
import at.ac.tuwien.big.cps.transform.util.FileUtils
import cpsaml.Sensor

class UnitsGenerator {
	
	
	def generate(String path, Cps cps) {
		FileUtils.write('''«path»/«cps.name.toClazz»Units.sysml''', generateCode(cps))
	}
	
	private def generateCode(Cps cps)
	'''
	package «cps.name.toName»Units {
		«FOR r: cps.resources»
		attribute def «r.name.toName» { }
		«ENDFOR»
		
		«FOR r: cps.hardware.filter(Sensor).flatMap[it.resources]»
		attribute def «r.name.toName» { }
		«ENDFOR»
	}
	'''
}