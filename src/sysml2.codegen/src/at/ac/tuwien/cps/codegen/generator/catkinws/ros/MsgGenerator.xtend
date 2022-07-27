package at.ac.tuwien.cps.codegen.generator.catkinws.ros

import at.ac.tuwien.cps.codegen.util.FileUtils
import at.ac.tuwien.cps.codegen.model.Part

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*

class MsgGenerator {
	
	def generate(String path, Part part) {
		FileUtils.write('''«path»/msg/«part.name.toClazz».msg''', generateContent(part))
	}
	
	private def generateContent(Part part)
	'''
	«FOR f: part.fields»
	«RosUtil.primitiveType(f)» «f.name.toVar»
	«ENDFOR»
	'''
}