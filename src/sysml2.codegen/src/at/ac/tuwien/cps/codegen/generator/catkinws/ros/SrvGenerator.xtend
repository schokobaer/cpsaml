package at.ac.tuwien.cps.codegen.generator.catkinws.ros

import at.ac.tuwien.cps.codegen.model.Action
import at.ac.tuwien.cps.codegen.util.FileUtils

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*
import org.omg.sysml.lang.sysml.Usage
import at.ac.tuwien.cps.codegen.util.CpsUtil

class SrvGenerator {
	
	def generate(String path, Action action) {
		FileUtils.write('''«path»/msg/«action.name.toClazz».srv''', generateContent(action))
	}
	
	private def dataType(Usage usage) {
		val t = CpsUtil.dataType(usage)
		
		if (t === null) {
			return "???";
		} else if ("int".equals(t)) {
			return "Int32"
		} else if ("double".equals(t)) {
			return "Float32"
		} else if ("boolean".equals(t)) {
			return "Bool"
		} else if ("String".equals(t)) {
			return "String"
		}
		return "???"
	}
	
	private def generateContent(Action action)
	'''
	«FOR p: action.params»
	«dataType(p)» «p.name.toVar»
	«ENDFOR»
	---
	bool success
	'''
	
}