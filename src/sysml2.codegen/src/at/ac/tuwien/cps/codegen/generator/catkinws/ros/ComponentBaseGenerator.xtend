package at.ac.tuwien.cps.codegen.generator.catkinws.ros

import at.ac.tuwien.cps.codegen.model.Part
import at.ac.tuwien.cps.codegen.util.FileUtils
import at.ac.tuwien.cps.codegen.util.SysMLReflectionUtil

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*
import at.ac.tuwien.cps.codegen.mapper.SysMLMapper
import java.util.List

class ComponentBaseGenerator {
	
	SysMLMapper mapper = new SysMLMapper()
	
	def generate(String path, String pkgName, Part part) {
		FileUtils.write('''«path»/scripts/component/«part.name.toLow»_base.py''', generateCode(pkgName, part))
	}
	
	private def generateCode(String pkgName, Part part)
	'''
	#!/usr/bin/env python
	# license removed for brevity
	
	import rospy
	«FOR m: RosUtil.stdMsgs(part) BEFORE 'from std_msgs.msg import ' SEPARATOR ', '»«m»«ENDFOR»
	«FOR m: RosUtil.complexMsgs(part) BEFORE '''from «pkgName».msg import ''' SEPARATOR ', '»«m»«ENDFOR»
	«FOR m: RosUtil.stdSrvs(part) BEFORE 'from std_srvs.srv import ' SEPARATOR ', '»«m», «m»Response«ENDFOR»
	«FOR m: RosUtil.complexSrvs(part) BEFORE '''from «pkgName».srv import ''' SEPARATOR ', '»«m», «m»Response«ENDFOR»
	«FOR p: part.parts.filter[!it.composite] BEFORE 'from client import ' SEPARATOR ', '»«p.type.name.toClazz»Client«ENDFOR»
	
	class Base«part.name.toClazz»:
	    def __init__(self):
	    	«FOR f: part.fields»
	    	self.«f.name.toVar» = «RosUtil.defaultValue(f)»
	    	«ENDFOR»
	    	«FOR s: part.states»
	    	self.«s.name.toVar» = «s.type.name.toClazz».«SysMLReflectionUtil.getDefaultStateValue(s.usage).toConstant»
	        «ENDFOR»
	        «FOR p: part.parts.filter[!it.composite]»
	        self.«p.name.toVar» = «p.type.name.toClazz»Client("«p.type.name.toLow»", «FOR pf: mapper.map(p.type).fields SEPARATOR ', '»on«pf.name.toClazz»Changed=self.handle_«p.name.toLow»_«pf.name.toLow»«ENDFOR»)
	    	«ENDFOR»
	        
	        «FOR f: part.fields»
	        self.«f.name.toLow»Pub = None
	        «ENDFOR»
	        «FOR s: part.states»
	        self.«s.name.toLow»Pub = None
	        «ENDFOR»
	
	    def fetch(self):
	        pass
		
	    «FOR p: part.parts.filter[!it.composite]»
	    «FOR pf: mapper.map(p.type).fields»
	    def handle_«p.name.toLow»_«pf.name.toLow»(self, «pf.name.toVar»):
	        pass
	    
		«ENDFOR»
		«ENDFOR»
		
	    «FOR a: part.actions»
	    def handle_«a.name.toVar»(self, req):
	    	return «RosUtil.srvType(a)»Response(«IF List.of("Trigger", "SetBool").exists[it.equals(RosUtil.srvType(a))]»True, ""«ELSE»True«ENDIF»)
	    
		«ENDFOR»
	
	    def init_ros(self):
	        rospy.loginfo("init ros ...")
	        «FOR f: part.fields»
	        self.«f.name.toVar»Pub = rospy.Publisher('«f.name.toVar»', «RosUtil.msgType(f)», queue_size=10)
	        «ENDFOR»
	        «FOR s: part.states»
	        self.«s.name.toVar»Pub = rospy.Publisher('«s.name.toVar»', String, queue_size=10)
	        «ENDFOR»
	        
	        rospy.init_node('«part.name.toLow»')
	        
	        «FOR a: part.actions»
	        rospy.Service('«a.name.toLow»', «RosUtil.srvType(a)», self.handle_«a.name.toVar»)
	        «ENDFOR»
	        
	        rospy.loginfo("init ros finished")
	
	
	    def spin(self):
	        rate = rospy.Rate(10) # 10hz
	        while not rospy.is_shutdown():
	            # Read bot data
	            self.fetch()
	            
	            # publish
	            «FOR f: part.fields»
	            ««« # self.speedPub.publish(«FOR x: 0..2»«ENDFOR»self.speed[0], self.speed[1]) // Todo generate destructing if no primitive »»»
	            self.«f.name.toVar»Pub.publish(self.«f.name.toVar»)
	            «ENDFOR»
	            rospy.loginfo("«FOR f: part.fields SEPARATOR ' / '»«f.name.toVar»: {}«ENDFOR»".format(«FOR f: part.fields SEPARATOR ', '»self.«f.name.toVar»«ENDFOR»))
	
	            rate.sleep()
	
	'''
	
}