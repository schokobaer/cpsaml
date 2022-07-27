package at.ac.tuwien.cps.codegen.generator.catkinws.ros

import at.ac.tuwien.cps.codegen.util.FileUtils
import at.ac.tuwien.cps.codegen.model.Part

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*
import at.ac.tuwien.cps.codegen.util.SysMLReflectionUtil
import at.ac.tuwien.cps.codegen.util.CpsUtil
import java.util.ArrayList
import at.ac.tuwien.cps.codegen.model.Member
import at.ac.tuwien.cps.codegen.model.StateField
import at.ac.tuwien.cps.codegen.model.Field

class ComponentClientGenerator {
	
	def generate(String path, String pkgName, Part part) {
		FileUtils.write('''«path»/scripts/component/client/«part.name.toLow»_client.py''', generateCode(pkgName, part))
	}
	
	/**
	 * Retkurns a map of field.name -> isObject
	 */
	private def getMembers(Part part) {
		val fields = part.fields.map[it.name.toVar -> (RosUtil.primitiveType(it) == null)]
		val states = part.states.map[it.name.toVar -> false]
		
		val members = new ArrayList()
		members.addAll(fields)
		members.addAll(states)
				
		return members
	}
	
	
	private def generateCode(String pkgName, Part part)
	'''
	import rospy
	«FOR m: RosUtil.stdMsgs(part) BEFORE 'from std_msgs.msg import ' SEPARATOR ', '»«m»«ENDFOR»
	«FOR m: RosUtil.complexMsgs(part) BEFORE '''from «pkgName».msg import ''' SEPARATOR ', '»«m»«ENDFOR»
	«FOR m: RosUtil.stdSrvs(part) BEFORE 'from std_srvs.srv import ' SEPARATOR ', '»«m»«ENDFOR»
	«FOR m: RosUtil.complexSrvs(part) BEFORE '''from «pkgName».srv import ''' SEPARATOR ', '»«m»«ENDFOR»
	
	class «part.name.toClazz»Client:
	    def __init__(self, name = "«part.name.toLow»"«FOR f: part.fields», on«f.name.toClazz»Changed = None«ENDFOR»«FOR s: part.states», on«s.name.toClazz»Changed = None«ENDFOR»):
	        self._name = name
	        
	        # properties
	        «FOR f: part.fields»
	        self.«f.name.toVar» = «RosUtil.defaultValue(f)»
	        «ENDFOR»
	        «FOR s: part.states»
	        self.«s.name.toVar» = «s.type.name.toClazz».«SysMLReflectionUtil.getDefaultStateValue(s.usage).toConstant»
	        «ENDFOR»
	        
	        # event handler
	        «FOR f: part.fields»
	        self.on«f.name.toClazz»Changed = on«f.name.toClazz»Changed
	        «ENDFOR»
	        «FOR s: part.states»
	        self.on«s.name.toClazz»Changed = on«s.name.toClazz»Changed
	        «ENDFOR»
	
	        # services:
	        «FOR a: part.actions»
	        self.«a.name.toVar» = None
	        «ENDFOR»
	    
	    def init_ros(self):
	        # services
	        «FOR a: part.actions»
	        rospy.wait_for_service(self._name + '/«a.name.toLow»')
	        self.«a.name.toVar» = rospy.ServiceProxy(self._name + '/«a.name.toLow»', «RosUtil.srvType(a)»)
	        «ENDFOR»
	
	        # topics
	        «FOR f: part.fields»
	        rospy.Subscriber(self._name + '/«f.name.toLow»', «RosUtil.msgType(f)», self.handle_«f.name.toVar»)
	        «ENDFOR»
	        «FOR s: part.states»
	        rospy.Subscriber(self._name + '/«s.name.toLow»', String, self.handle_«s.name.toVar»)
	        «ENDFOR»	
	
	    «FOR f: part.fields»
	    def handle_«f.name.toVar»(self, data):
	        if «IF RosUtil.primitiveType(f) === null»«FOR sf: RosUtil.complexFieldToPart(f).fields SEPARATOR ' or '»self.«f.name.toVar».«sf.name.toVar» != data.«sf.name.toVar»«ENDFOR»«ELSE»self.«f.name.toVar» != data.data«ENDIF»:
	            self.«f.name.toVar» = data.data
	            if self.on«f.name.toClazz»Changed != None:
	                self.on«f.name.toClazz»Changed(self.«f.name.toVar»)
	    
	    «ENDFOR»
	    «FOR s: part.states»
	    def handle_«s.name.toVar»(self, data):
	        if self.«s.name.toVar» != data.data:
	            self.«s.name.toVar» = data.data
	            if self.on«s.name.toClazz»Changed != None:
	                self.on«s.name.toClazz»Changed(self.«s.name.toVar»)
	    
	    «ENDFOR»
	
	        
	    def to_map(self):
	        return «RosUtil.toMapString(part, 'self', 1)»
	'''
}