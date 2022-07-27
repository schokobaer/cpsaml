package at.ac.tuwien.cps.codegen.generator.catkinws

import at.ac.tuwien.cps.codegen.model.Part
import at.ac.tuwien.cps.codegen.util.FileUtils

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*

class DockerfileGenerator {
	
	def generate(String path, Part part, boolean singleton) {
		FileUtils.write('''«path»/docker/«part.name.toLow»/Dockerfile''', generateContent(part, singleton))
	}
	
	private def generateContent(Part part, boolean singleton)
	'''
	FROM ros:kinetic-robot
	
	# installs
	RUN /bin/bash -c 'apt-get update -y; apt-get install python2.7 -y; apt-get install python-pika -y; apt-get install python-enum -y'
	
	# create catkin workspace
	RUN ["mkdir", "-p", "/catkin_ws/src"]
	WORKDIR /catkin_ws
	
	# add package
	ADD ./src/«part.name.toLow» /catkin_ws/src
	ADD ./«part.name.toLow».sh /catkin_ws/«part.name.toLow».sh
	
	# build workspace
	RUN /bin/bash -c '. /opt/ros/kinetic/setup.bash; cd /catkin_ws; catkin_make'
		
	# set env variables
	ENV rabbit_host 'localhost'
	«IF !singleton»ENV «part.name.toLow»_id '«part.name.toLow»0'«ENDIF»
	
	# start launcher	
	ENTRYPOINT /bin/bash -c '. /catkin_ws/«part.name.toLow».sh ${rabbit_host}«IF !singleton» ${«part.name.toLow»_id}«ENDIF»'
	'''
}