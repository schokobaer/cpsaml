package at.ac.tuwien.cps.codegen.generator.catkinws.ros

import at.ac.tuwien.cps.codegen.util.FileUtils
import at.ac.tuwien.cps.codegen.model.Part

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*
import at.ac.tuwien.cps.codegen.util.SysMLReflectionUtil
import at.ac.tuwien.cps.codegen.util.CpsUtil
import java.util.ArrayList
import org.omg.sysml.lang.sysml.Usage

class DtBaseGenerator {
	
	def generate(String path, Part part, boolean singleton) {
		FileUtils.write('''«path»/scripts/component/«part.name.toLow»_dt_base.py''', generateCode(part, singleton).newLineFix)
	}
	
	/**
	 * Retkurns a map of field.name -> isObject
	 */
	private def getMembers(Part part) {
		val fields = part.fields.map[it.name.toVar -> false]
		val states = part.states.map[it.name.toVar -> false]
		val parts = part.parts.map[it.name.toVar -> CpsUtil.isRosNode(it.type)]
		
		val members = new ArrayList()
		members.addAll(fields)
		members.addAll(states)
		members.addAll(parts)
				
		return members
	}
	
	def converter(Usage usage, String varName) {
		var t = CpsUtil.dataType(usage)
		
		if (t === null) {
			return varName
		}
		
		if ("int".equals(t)) {
			return '''int(«varName»)'''
		} else if ("double".equals(t)) {
			return '''float(«varName»)'''
		} else if ("boolean".equals(t)) {
			return '''«varName» == 'True' '''
		} else if ("String".equals(t)) {
			return '''«varName».replace('"', '')'''
		}
		
		return '''«varName».replace('"', '')'''
	}
	
	private def generateCode(Part part, boolean singleton)
	'''
	#!/usr/bin/env python
	# license removed for brevity
	
	«IF !singleton»
	import sys
	import random
	«ENDIF»
	import pika	
	import rospy
	from threading import Thread
	import json
	from client import «FOR node: part.parts.filter[CpsUtil.isRosNode(it.type)] SEPARATOR ', '»«node.type.name.toClazz»Client«ENDFOR»
	
	«FOR sd: part.states»
	class «sd.type.name.toClazz»:
	    «FOR s: SysMLReflectionUtil.getStateValues(sd.usage)»
	    «s.toConstant» = '«s.toConstant»'
	    «ENDFOR»
	
	    @staticmethod
	    def values():
	        return [
	            «FOR s: SysMLReflectionUtil.getStateValues(sd.usage) SEPARATOR ','»
	            «sd.type.name.toClazz».«s.toConstant»
    			«ENDFOR»
	        ]
	
	«ENDFOR»
	
	
	class Base«part.name.toClazz»:
	    def __init__(self):
	    	«FOR f: part.fields»
	    	self.«f.name.toVar» = «RosUtil.defaultValue(f)»
	    	«ENDFOR»
	        «FOR sd: part.states»
	        self.«sd.name.toVar» = «sd.type.name.toClazz».«SysMLReflectionUtil.getDefaultStateValue(sd.usage).toConstant»
	        «ENDFOR»
	        self.rabbit = RabbitClient(self)
	        «FOR node: part.parts.filter[CpsUtil.isRosNode(it.type)]»
	        self.«node.name.toVar» = «node.type.name.toClazz»Client("«node.name.toLow»")
	        «ENDFOR»
	        
	    «FOR a: part.actions»
	    def handle_«a.name.toVar»(self«FOR p: a.params», «p.name.toVar»«ENDFOR»):
	        pass
	    
	    «ENDFOR»
	
	    def to_map(self):
	    	return «RosUtil.toMapString(part, "self", 1)»
		
	    def init_ros(self):
	        rospy.init_node('«part.name.toLow»_dt')
			
	        «FOR node: part.parts.filter[CpsUtil.isRosNode(it.type)]»
	        # «node.name»
	        rospy.loginfo("init «node.name» ...")
	        self.«node.name.toVar».init_ros()
	        rospy.loginfo("init «node.name» finished")
	        
	        «ENDFOR»
	        	
	        rospy.loginfo("init ros finished")
	
	
	    def spin(self):
	        rate = rospy.Rate(10) # 10hz
	        while not rospy.is_shutdown():
	
	            # publish the «part.name» to rabbit
	            «part.name.toVar»_msg = self.to_map()
	            msg_json = json.dumps(«part.name.toVar»_msg)
	
	            self.rabbit.publish(msg_json)
	            rospy.loginfo("{}".format(msg_json))
	
	            rate.sleep()
	
	class RabbitClient:
	    def __init__(self, «part.name.toVar»):
	        self.«part.name.toVar» = «part.name.toVar»
	        self.rabbit_channel_out = None
	        self.data_exchanger = 'data'
	        self.data_routingkey = 'data.«part.name.toLow»'
	        «IF !singleton»
	        self.«part.name.toVar»_id = "«part.name.toLow»-{}".format(random.randint(10000, 99999))
	        if len(sys.argv) > 1:
	            self.«part.name.toVar»_id = sys.argv[1]
	        «ENDIF»
	        self.action_exchanger = "action"
	        self.action_routingkey = «IF singleton»"action.«part.name.toLow»"«ELSE»"action.{}".format(self.«part.name.toVar»_id)«ENDIF»
	
	    def init_rabbit(self):
	        rospy.loginfo("init rabbit ...")
	
	        rabbit_host = rospy.get_param('/rabbit_host', 'localhost')
	        rospy.loginfo("rabbit_host={}".format(rabbit_host))
	        connection1 = pika.BlockingConnection(pika.ConnectionParameters(host=rabbit_host))
	        connection2 = pika.BlockingConnection(pika.ConnectionParameters(host=rabbit_host))
	
	        # data channel data
	        data_channel = connection1.channel()
	        data_channel.exchange_declare(exchange=self.data_exchanger, durable=False, exchange_type='topic')
	
	        # action channel action
	        action_channel = connection2.channel()
	        action_channel.exchange_declare(exchange=self.action_exchanger, exchange_type='topic')
	        action_queue = action_channel.queue_declare(«IF singleton»'«part.name.toLow»'«ELSE»self.«part.name.toVar»_id«ENDIF», exclusive=True)
	        action_queue_name = action_queue.method.queue
	        action_channel.queue_bind(exchange=self.action_exchanger, queue=action_queue_name, routing_key=self.action_routingkey)
	        # action_channel.basic_consume(queue=action_queue_name, on_message_callback=self.handle_rabbit_msg, auto_ack=True)
	        action_channel.basic_consume(self.handle_rabbit_msg, action_queue_name, True)
	
	        rabbitworker = Thread(target=self.rabbit_bg_task, name='RabbitMsgHandler', args=(action_channel,))
	        rabbitworker.daemon = True
	        rabbitworker.start()
	
	        rospy.loginfo("init rabbit finished")
	        self.data_channel = data_channel
	
	    def publish(self, msg_json):
	        self.data_channel.basic_publish(
	            exchange=self.data_exchanger,
	            routing_key=self.data_routingkey,
	            «IF singleton»
	            properties=pika.BasicProperties(),
	            «ELSE»
	            properties=pika.BasicProperties(
	                headers={'«part.name.toLow»_id': self.«part.name.toVar»_id}
	            ),
	            «ENDIF»
	            body=msg_json)
	
	    def handle_rabbit_msg(self, ch, method, properties, body):
	        rospy.loginfo("received a rabbit message: {}".format(body))
	        action = properties.headers['action']
	        args = json.loads(json.loads(body))
	        «FOR a: part.actions»
	        if action == '«a.name.toVar»':
	            «FOR p: a.params»
	            «p.name.toVar» = args["«p.name.toVar»"]
	            «ENDFOR»
	            self.«part.name.toVar».handle_«a.name.toVar»(«FOR p: a.params SEPARATOR ', '»«p.name.toVar»«ENDFOR»)
	        «ENDFOR»
	
	    def rabbit_bg_task(self, channel):
	        channel.start_consuming()
	'''
	
}