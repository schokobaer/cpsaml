package at.ac.tuwien.cps.codegen.generator.systemwidedt.dto

import at.ac.tuwien.cps.codegen.mapper.SysMLMapper
import at.ac.tuwien.cps.codegen.model.CpsSystem
import at.ac.tuwien.cps.codegen.model.Field
import at.ac.tuwien.cps.codegen.model.Part
import at.ac.tuwien.cps.codegen.util.CpsUtil
import at.ac.tuwien.cps.codegen.util.FileUtils
import java.util.ArrayList
import java.util.List
import org.omg.sysml.lang.sysml.AttributeDefinition

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*

class DtoGenerator {
	
	SysMLMapper mapper = new SysMLMapper();
	
	def generate(String path, String pkg, Part part, boolean singleton) {
		FileUtils.write('''«path»/dto/«part.name.toLow»/«part.name.toClazz»Dto.java''', generateCode(pkg, part.name.toLow, part, List.of(), true, singleton))
		generateNested(path, pkg, part.name.toLow, part)
	}
	
	def generate(String path, String pkg, CpsSystem system) {
		FileUtils.write('''«path»/dto/«system.systemwideDt.name.toLow»/«system.systemwideDt.name.toClazz»Dto.java''', generateCode(pkg, system.name.toLow, system.systemwideDt, system.cps, false))
		generateNested(path, pkg, l(system.name), system.systemwideDt)
	}
	
	/**
	 * Used for Cps. So the part argument must be a CPS Part definition!
	 */
	private def generateNested(String path, String pkg, String subPkg, Part part) {
		part.parts
			.filter[it.composite]
			.filter[CpsUtil.dataType(it.usage) === null && !CpsUtil.isCps(it.type)]
			.forEach[
				val sub = mapper.map(it.type)
				generateNestedRec(path, pkg, subPkg, sub)
			]
		part.fields
			.filter[CpsUtil.dataType(it.usage) === null && it.type instanceof AttributeDefinition]
			.forEach[
				val sub = mapper.mapAttr(AttributeDefinition.cast(it.type))
				generateNestedRec(path, pkg, subPkg, sub)
			]
	}
	
	private def generateNestedRec(String path, String pkg, String subPkg, Part part) {
		FileUtils.write('''«path»/dto/«subPkg»/«part.name.toClazz»Dto.java''', generateCode(pkg, subPkg, part, List.of(), false))
		part.fields
			.filter[CpsUtil.dataType(it.usage) === null && it.type instanceof AttributeDefinition]
			.forEach[
				val sub = mapper.mapAttr(AttributeDefinition.cast(it.type))
				generateNestedRec(path, pkg, subPkg, sub)
			]
	}
	
	private def boolean needsList(Part part) {
		return !part.fields.filter[it.list].empty || !part.states.filter[it.list].empty || !part.parts.filter[it.composite].filter[it.list].empty
	}
	
	private def String getType(Field f) {
		val t = CpsUtil.dataType(f.usage)
		if (t !== null) {
			return t
		}
		
		return '''«f.usage.name.toClazz»Dto'''
	} 
	
	private def getMembers(Part part) {
		val fields = part.fields.map[it.name.toVar -> '''«IF it.list»List<«ENDIF»«getType(it)»«IF it.list»>«ENDIF»''']
		val states = part.states.map[it.name.toVar -> '''«IF it.list»List<«ENDIF»String«IF it.list»>«ENDIF»''']
		val parts = part.parts.filter[it.composite].map[it.name.toVar -> '''«IF it.list»List<«ENDIF»«it.type.name.toClazz»Dto«IF it.list»>«ENDIF»''']
		
		val members = new ArrayList()
		members.addAll(fields)
		members.addAll(states)
		members.addAll(parts)
				
		return members
	}
	
	private def generateCode(String pkg, String subPkg, Part part, List<Part> cps, boolean isCps) {
		return generateCode(pkg, subPkg, part, cps, isCps, false)
	}
	
	private def generateCode(String pkg, String subPkg, Part part, List<Part> cps, boolean isCps, boolean singleton)
	'''
	package «pkg».systemwidedt.dto.«subPkg»;
	
	«FOR cp: cps»
	import «pkg».systemwidedt.dto.«cp.name.toLow».«cp.name.toClazz»Dto;
	«ENDFOR»
	«IF needsList(part)»
	import java.util.List;
	«ENDIF»
	
	public class «part.name.toClazz»Dto {
		
		«IF isCps && !singleton»
		private String id;
		«ENDIF»
		«FOR f: getMembers(part)»
		private «f.value» «f.key»;
		«ENDFOR»
		
		public «part.name.toClazz»Dto() {
		}
		
		public «part.name.toClazz»Dto(«FOR f: getMembers(part) SEPARATOR ', '»«f.value» «f.key»«ENDFOR») {
			«FOR f: getMembers(part)»
			this.«f.key» = «f.key»;
			«ENDFOR»
		}
	
		«IF isCps && !singleton»
		public String getId() {
			return id;
		}
		
		public void setId(String id) {
			this.id = id;
		}
		
		«ENDIF»
		«FOR f: getMembers(part)»
		public «f.value» get«f.key.toClazz»() {
			return «f.key»;
		}
		
		public void set«f.key.toClazz»(«f.value» «f.key») {
			this.«f.key» = «f.key»;
		}
		
		«ENDFOR»
		
		@Override
		public String toString() {
			return "«part.name.toClazz»Dto {"
			    «FOR f: getMembers(part) SEPARATOR ' + ", "'»
			    + "«f.key»=" + «f.key»
			    «ENDFOR»
				+ "}";
		}
	}
	'''
	
}