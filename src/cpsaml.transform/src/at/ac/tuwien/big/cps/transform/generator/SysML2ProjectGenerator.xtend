package at.ac.tuwien.big.cps.transform.generator

import at.ac.tuwien.big.cps.transform.util.FileUtils
import cpsaml.CpsSystem
import java.io.File

import static extension at.ac.tuwien.big.cps.transform.util.NamingUtil.*

class SysML2ProjectGenerator {
	
	def generate(String path, CpsSystem sys) {
		val root = createProjectStructure(path, sys)
		
		new SysGenerator().generate(root, sys)
		val cpsGenerator = new CpsGenerator()
		val hardwareGenerator = new HardwareGenerator()
		//val unitsGenerator = new UnitsGenerator()
		
		sys.cps.forEach[ cps |
			cpsGenerator.generate(root, cps)
			
			cps.hardware.forEach[ hw |
				hardwareGenerator.generate('''«root»/«cps.name.toPath»''', hw)
			]
			
			//unitsGenerator.generate('''«root»/«cps.name.toPath»''', cps)
		]
	}
	
	def clean(String path, CpsSystem sys) {
		FileUtils.deleteDirectory(getTarget(path, sys))
	}
	
	private def getTarget(String path, CpsSystem sys) {
		return new File(path).absolutePath + '''/«sys.name.toPath».sysml'''
	}
	
	private def createProjectStructure(String path, CpsSystem sys) {
		val resRoot = "resource/sysml"
		val root = getTarget(path, sys)
		FileUtils.deepCopy(resRoot, root, true)
		return root + '/src'
	}
}