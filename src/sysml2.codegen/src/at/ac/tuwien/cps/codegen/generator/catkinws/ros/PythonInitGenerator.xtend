package at.ac.tuwien.cps.codegen.generator.catkinws.ros

import at.ac.tuwien.cps.codegen.model.Part
import at.ac.tuwien.cps.codegen.util.FileUtils

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*

class PythonInitGenerator {
	
	def generate(String path, Part part) {
		FileUtils.write('''«path»/scripts/component/__init__.py''', generateComponentNodeInitContent(part))
		FileUtils.write('''«path»/scripts/component/client/__init__.py''', generateComponentClientInitContent(part))
	}
	
	 
	private def generateComponentNodeInitContent(Part part)
	'''
	«FOR n: RosUtil.getNodes(part)»
	from «n.name.toLow»_base import Base«n.name.toClazz»«FOR s: n.states», «s.type.name.toClazz»«ENDFOR»
	«ENDFOR»
	from «part.name.toLow»_dt_base import Base«part.name.toClazz»«FOR s: part.states», «s.type.name.toClazz»«ENDFOR»
	'''
	
	private def generateComponentClientInitContent(Part part)
	'''
	«FOR n: RosUtil.getNodes(part)»
	from «n.name.toLow»_client import «n.name.toClazz»Client
	«ENDFOR»
	'''
}