package at.ac.tuwien.cps.codegen.generator.catkinws

import at.ac.tuwien.cps.codegen.model.Part
import at.ac.tuwien.cps.codegen.util.FileUtils

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*

class StartupScriptGenerator {
	
	def generate(String path, Part part, boolean singleton) {
		FileUtils.write('''«path»/«part.name.toLow».sh''', generateContent(part, singleton).newLineFix)
	}
	
	private def generateContent(Part part, boolean singleton)
	'''
	#!/bin/bash
	/opt/ros/kinetic/setup.bash
	source devel/setup.bash
	echo "setup finished"
	roscore > /dev/null &
	sleep 3
	echo "roscore started"
	rosparam set /rabbit_host "$1"
	echo "rabbit_host param set to $1"
	roslaunch «part.name.toLow» «part.name.toLow».launch«IF !singleton» «part.name.toLow»_id:=$2«ENDIF»
	'''
	
}