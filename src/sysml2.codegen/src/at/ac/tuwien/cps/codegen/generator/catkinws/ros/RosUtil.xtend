package at.ac.tuwien.cps.codegen.generator.catkinws.ros

import at.ac.tuwien.cps.codegen.model.Part
import at.ac.tuwien.cps.codegen.util.CpsUtil
import org.omg.sysml.lang.sysml.AttributeDefinition
import at.ac.tuwien.cps.codegen.mapper.SysMLMapper
import at.ac.tuwien.cps.codegen.model.Field

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*
import at.ac.tuwien.cps.codegen.model.Action
import org.omg.sysml.lang.sysml.Usage
import at.ac.tuwien.cps.codegen.model.StateField
import java.util.ArrayList
import at.ac.tuwien.cps.codegen.model.PartField
import at.ac.tuwien.cps.codegen.model.Member
import java.util.List
import java.util.HashSet

class RosUtil {
	
	static SysMLMapper mapper = new SysMLMapper()
	
	/**
	 * Returns all Messages from all ros nodes from the CPS
	 */
	static def getMsgs(Part part) {
		return getNodes(part)
			.flatMap[it.fields]
			.filter[CpsUtil.dataType(it.usage) === null && it.type instanceof AttributeDefinition]
			.map[mapper.mapAttr(it.type as AttributeDefinition)]
	}
	
	/**
	 * Returns all Services from all ros nodes from the CPS, that have more than 1 parameter
	 */
	static def getSrvs(Part part) {
		return getNodes(part)
			.flatMap[it.actions]
			.filter[it.params.size > 1]
	}
	
	/**
	 * Returns all RosNodes from the cps
	 */
	static def getNodes(Part part) {
		return part.parts
			.filter[CpsUtil.isRosNode(it.type)]
			.map[mapper.map(it.type)]
			.toSet
	}
	
	static def primitiveType(Field f) {
		val t = CpsUtil.dataType(f.usage)
		
		if (t === null) {
			return null
		}
		
		if ("int".equals(t)) {
			return "int32"
		} else if ("double".equals(t)) {
			return "float32"
		} else if ("boolean".equals(t)) {
			return "bool"
		} else if ("String".equals(t)) {
			return "string"
		}
		
		return null
	}
	
	static def msgType(Field f) {
		val t = CpsUtil.dataType(f.usage)
		
		if (t === null) {
			return f.usage.name.toClazz
		} else if ("int".equals(t)) {
			return "Int32"
		} else if ("double".equals(t)) {
			return "Float32"
		} else if ("boolean".equals(t)) {
			return "Bool"
		} else if ("String".equals(t)) {
			return "String"
		}
		
		throw new RuntimeException("unknown datatype")
	}
	
	static def complexFieldToPart(Field f) {
		return mapper.mapAttr(f.type as AttributeDefinition)
	}
	
	static def srvType(Action a) {
		if (a.params.empty) {
			return "Trigger"
		} else if (a.params.size > 1) {
			return a.name.toClazz + "Srv"
		}
		
		val t = CpsUtil.dataType(a.params.get(0))
		
		if (t === null) {
			throw new RuntimeException("unknown datatype")
		} else if ("int".equals(t)) {
			return "IntSrv"
		} else if ("double".equals(t)) {
			return "FloatSrv"
		} else if ("boolean".equals(t)) {
			return "SetBool"
		} else if ("String".equals(t)) {
			return "StringSrv"
		}
		
		throw new RuntimeException("unknown datatype") 
	}
	
	static def defaultValue(Field f) {
		var t = CpsUtil.dataType(f.usage)
		
		if (t === null) {
			val t2 = mapper.mapAttr(f.type as AttributeDefinition)
			return '''[«FOR sf: t2.fields SEPARATOR ', '»«defaultValue(sf)»«ENDFOR»]'''
		}
		
		if ("int".equals(t)) {
			return "0"
		} else if ("double".equals(t)) {
			return "0.0"
		} else if ("boolean".equals(t)) {
			return "False"
		} else if ("String".equals(t)) {
			return '""'
		}
		
		throw new RuntimeException("unknown datatype")
	}
	
	static def toMapString(Part p, String prefix, int indent) {
		val members = new ArrayList<Member>
		members.addAll(p.fields)
		members.addAll(p.states)
		members.addAll(p.parts)
		return '''{
			«FOR f: members SEPARATOR ','»
            «FOR i: 1..indent»    «ENDFOR»«toMapString(f, prefix, indent + 1)»
            «ENDFOR»«FOR i: 1..<indent»    «ENDFOR»}'''
	}
	
	static def toMapString(Member m, String prefix, int indent) {
		var res = "'" + m.name.toVar + "': "
		
		if (m instanceof StateField) {
			res += '''«prefix».«m.name.toVar»'''
		} else if (m instanceof Field) {
			if (RosUtil.primitiveType(m as Field) !== null) {
				res += '''«prefix».«m.name.toVar»'''
			} else {
				val p = RosUtil.complexFieldToPart(m)
				res += toMapString(p, '''«prefix».«m.name.toVar»''', indent)
			}
		} else if (m instanceof PartField) {
			res += '''«prefix».«m.name.toVar».to_map()'''
		}
		
		return res
	}
	
	static def stdMsgs(Part part) {
		val set = new HashSet<String>
		val primitives = List.of('Int32', 'Float32', 'Bool', 'String')
		part.fields
			.map[RosUtil.msgType(it)]
			.filter[primitives.contains(it)]
			.forEach[set.add(it)]
		if (!part.states.empty) {
			set.add("String")
		}
		return set
	}
	
	static def complexMsgs(Part part) {
		val primitives = List.of('Int32', 'Float32', 'Bool', 'String')
		return part.fields
			.map[RosUtil.msgType(it)]
			.filter[!primitives.contains(it)]
			.toSet
	}
	
	static def stdSrvs(Part part) {
		val primitives = List.of('Trigger', 'SetBool')
		return part.actions
			.map[RosUtil.srvType(it)]
			.filter[primitives.contains(it)]
			.toSet
	}
	
	static def complexSrvs(Part part) {
		val primitives = List.of('Trigger', 'SetBool')
		return part.actions
			.map[RosUtil.srvType(it)]
			.filter[!primitives.contains(it)]
			.toSet
	}
}