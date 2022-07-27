package at.ac.tuwien.cps.codegen.generator.cockpit.java

import at.ac.tuwien.cps.codegen.mapper.SysMLMapper
import at.ac.tuwien.cps.codegen.model.CpsSystem
import at.ac.tuwien.cps.codegen.model.Field
import at.ac.tuwien.cps.codegen.model.Part
import at.ac.tuwien.cps.codegen.util.FileUtils
import java.util.ArrayList
import java.util.List
import org.omg.sysml.lang.sysml.AttributeDefinition

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*
import static extension at.ac.tuwien.cps.codegen.util.CpsUtil.*
import at.ac.tuwien.cps.codegen.util.SysMLReflectionUtil
import at.ac.tuwien.cps.codegen.util.CpsUtil
import org.omg.sysml.lang.sysml.AnnotatingFeature
import org.omg.sysml.lang.sysml.MetadataFeature

class ModelDtoGenerator {
	
	SysMLMapper mapper = new SysMLMapper();
	
	def generate(String path, String pkg, Part part, boolean singleton) {
		FileUtils.write('''«path»/model/cps/«part.name.toLow»/«part.name.toClazz».java''', generateCode(pkg, part.name.toLow, part, List.of(), true, singleton))
		generateNested(path, pkg, l(part.name), part)
	}
	
	def generate(String path, String pkg, CpsSystem system) {
		FileUtils.write('''«path»/model/cps/«system.systemwideDt.name.toLow»/«system.systemwideDt.name.toClazz».java''', generateCode(pkg, system.name.toLow, system.systemwideDt, system.cps, true, false))
		generateNested(path, pkg, system.name.toLow, system.systemwideDt)
	}
	
	/**
	 * Used for Cps. So the part argument must be a CPS Part definition!
	 */
	private def generateNested(String path, String pkg, String subPkg, Part part) {
		part.parts
			.filter[it.composite]
			.filter[it.usage.dataType === null && !it.type.isCps]
			.forEach[
				val sub = mapper.map(it.type)
				generateNestedRec(path, pkg, subPkg, sub)
			]
		part.fields
			.filter[it.usage.dataType === null && it.type instanceof AttributeDefinition]
			.forEach[
				val sub = mapper.mapAttr(AttributeDefinition.cast(it.type))
				generateNestedRec(path, pkg, subPkg, sub)
			]
	}
	
	private def generateNestedRec(String path, String pkg, String subPkg, Part part) {
		FileUtils.write('''«path»/model/cps/«subPkg»/«part.name.toClazz».java''', generateCode(pkg, subPkg, part, List.of(), false, false))
		part.fields
			.filter[it.usage.dataType === null && it.type instanceof AttributeDefinition]
			.forEach[
				val sub = mapper.mapAttr(AttributeDefinition.cast(it.type))
				generateNestedRec(path, pkg, subPkg, sub)
			]
	}
	
	private def boolean needsList(Part part) {
		return !part.fields.filter[it.list].empty || !part.states.filter[it.list].empty || !part.parts.filter[it.composite].filter[it.list].empty
	}
	
	private def String getType(Field f) {
		val t = f.usage.dataType
		if (t !== null) {
			return t
		}
		
		return f.usage.name.toClazz
	}
	
	private def getMembers(Part part) {
		val fields = part.fields.map[it.name.toVar -> '''«IF it.list»List<«ENDIF»«getType(it)»«IF it.list»>«ENDIF»''']
		val states = part.states.map[it.name.toVar -> '''«IF it.list»List<«ENDIF»String«IF it.list»>«ENDIF»''']
		val parts = part.parts.filter[it.composite].map[it.name.toVar -> '''«IF it.list»List<«ENDIF»«it.type.name.toClazz»«IF it.list»>«ENDIF»''']
		
		val members = new ArrayList()
		members.addAll(fields)
		members.addAll(states)
		members.addAll(parts)
				
		return members
	}
	
	private def getBounds(Part part, String attrName) {
		val field = part.fields.findFirst[it.name.equals(attrName)]
		if (field === null) {
			return ''
		}
		val bounds = CpsUtil.getBounds(field.type)
		if (bounds === null) {
			return ''
		}
		return '''@Bounds(min = «bounds.key», max = «bounds.value») '''
	}
	
	private def hasBoundsField(Part part) {
		return part.fields.findFirst[SysMLReflectionUtil.isAnnotationPresent(it.type, CpsUtil.ANNO_BOUNDS)] !== null
	}
	
	
	private def generateCode(String pkg, String subPkg, Part part, List<Part> cps, boolean isCps, boolean singleton)
	'''
	package «pkg».cockpit.model.cps.«subPkg»;
	
	«IF isCps»
	import «pkg».cockpit.annotation.CpsDevice;
	import «pkg».cockpit.model.monitor.Cps;
	import at.ac.tuwien.big.cps.annotationprocessor.annotation.Id;
	«ENDIF»
	«FOR cp: cps»
	import «pkg».cockpit.model.cps.«cp.name.toLow».«cp.name.toClazz»;
	«ENDFOR»
	«IF needsList(part)»
	import java.util.List;
	«ENDIF»
	«IF hasBoundsField(part)»
	import at.ac.tuwien.big.cps.annotationprocessor.annotation.Bounds;
	«ENDIF»
	
	«IF isCps»
	@CpsDevice(Cps.«part.name.toConstant»)
	«ENDIF»
	public class «part.name.toClazz» {
		
		«IF isCps»
		@Id
		private String id«IF !cps.empty» = "«part.name.toConstant»"«ELSEIF singleton» = "«part.name.toLow»"«ENDIF»;
		«ENDIF»
		«FOR f: getMembers(part)»
		«getBounds(part, f.key)»
		private «f.value» «f.key»;
		«ENDFOR»
		
		public «part.name.toClazz»() {
		}
		
		public «part.name.toClazz»(«FOR f: getMembers(part) SEPARATOR ', '»«f.value» «f.key»«ENDFOR») {
			«FOR f: getMembers(part)»
			this.«f.key» = «f.key»;
			«ENDFOR»
		}
	
		«IF isCps»
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
		
		«IF isCps && cps.empty»
		@Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	
	        «part.name.toClazz» that = («part.name.toClazz») o;
	
	        return id != null ? id.equals(that.id) : that.id == null;
	    }
	
	    @Override
	    public int hashCode() {
	        return id != null ? id.hashCode() : 0;
	    }
	    «ENDIF»
	    
	    @Override
		public String toString() {
			return "«part.name.toClazz» {"
			    «FOR f: getMembers(part) SEPARATOR ' + ", "'»
			    + "«f.key»=" + «f.key»
			    «ENDFOR»
				+ "}";
		}
	}
	
	'''}