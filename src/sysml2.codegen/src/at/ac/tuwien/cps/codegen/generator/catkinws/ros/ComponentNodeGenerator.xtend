package at.ac.tuwien.cps.codegen.generator.catkinws.ros

import at.ac.tuwien.cps.codegen.model.Part

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*
import at.ac.tuwien.cps.codegen.util.FileUtils
import java.io.File

class ComponentNodeGenerator {
	
	/**
	 * Should only get generated, if there is no node implementation yet, so changes wont get lost.
	 */
	def generate(String path, Part part, boolean isDt) {
		val file = '''«path»/scripts/«part.name.toLow»«IF isDt»_dt«ENDIF».py'''
		if (!new File(file).exists) {
			FileUtils.write(file, generateCode(part, isDt).newLineFix)
		}
	}
	
	private def generateCode(Part part, boolean isDt)
	'''
	#!/usr/bin/env python
	# license removed for brevity
	
	import rospy
	from component import Base«part.name.toClazz»
	
	class «part.name.toClazz»(Base«part.name.toClazz»):
	    def __init__(self):
	        Base«part.name.toClazz».__init__(self)
	    
	    «IF !isDt && !part.fields.empty»
	    def fetch(self):
	        # TODO: IMPLEMENT
	        pass
	     
	    «ENDIF»
	
	    «FOR a: part.actions»
	    def handle_«a.name.toVar»(self«IF isDt»«FOR p: a.params», «p.name.toVar»«ENDFOR»«ELSE», req«ENDIF»):
	    	# TODO: IMPLEMENT
	    	pass
	    	
	    «ENDFOR»
		
	if __name__ == '__main__':
	    try:
	        «part.name.toVar» = «part.name.toClazz»()
	        «part.name.toVar».init_ros()
	        «IF isDt»
	        «part.name.toVar».rabbit.init_rabbit()
	        «ENDIF»
	        «part.name.toVar».spin()
	    except rospy.ROSInterruptException:
	        pass
	'''
	
}