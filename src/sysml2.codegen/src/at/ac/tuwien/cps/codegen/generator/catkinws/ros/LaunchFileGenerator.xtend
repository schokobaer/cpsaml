package at.ac.tuwien.cps.codegen.generator.catkinws.ros

import at.ac.tuwien.cps.codegen.util.FileUtils
import at.ac.tuwien.cps.codegen.model.Part

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*
import at.ac.tuwien.cps.codegen.mapper.SysMLMapper
import at.ac.tuwien.cps.codegen.util.CpsUtil

class LaunchFileGenerator {

	SysMLMapper mapper = new SysMLMapper()

	def generate(String path, Part part, boolean singleton) {
		FileUtils.write('''«path»/launch/«part.name.toLow».launch''', generateContent(part, singleton))
	}
	
	/**
	 * Returns all RosNodes from the cps
	 */
	private def getNodes(Part part) {
		return part.parts
			.filter[CpsUtil.isRosNode(it.type)]
			.map[mapper.map(it.type)]
			.toSet
	}
	
	private def generateContent(Part part, boolean singleton)
	'''
	<launch>
	  «IF !singleton»<arg name="«part.name.toLow»_id" />«ENDIF»
	  <group ns="«part.name.toLow»1">
	    «FOR n: part.parts.filter[CpsUtil.isRosNode(it.type)]»
	    <node pkg="«part.name.toLow»" name="«n.name.toLow»" ns="«n.name.toLow»" type="«n.type.name.toLow».py" />
	    «ENDFOR»
	    <node pkg="«part.name.toLow»" name="«part.name.toLow»_dt" type="«part.name.toLow»_dt.py" «IF !singleton»args="$(arg «part.name.toLow»_id)" «ENDIF»output="screen" />
	  </group>
	
	</launch>
	'''
}