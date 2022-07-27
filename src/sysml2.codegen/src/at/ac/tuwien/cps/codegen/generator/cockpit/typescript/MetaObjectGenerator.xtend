package at.ac.tuwien.cps.codegen.generator.cockpit.typescript

import at.ac.tuwien.cps.codegen.mapper.SysMLMapper
import at.ac.tuwien.cps.codegen.model.Action
import at.ac.tuwien.cps.codegen.model.CpsSystem
import at.ac.tuwien.cps.codegen.model.Field
import at.ac.tuwien.cps.codegen.util.CpsUtil
import at.ac.tuwien.cps.codegen.util.FileUtils
import at.ac.tuwien.cps.codegen.util.SysMLReflectionUtil
import org.omg.sysml.lang.sysml.AttributeDefinition
import org.omg.sysml.lang.sysml.Usage

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*

class MetaObjectGenerator {
	
	SysMLMapper mapper = new SysMLMapper()
		
	/**
	 * path is the root path to the project.
	 */
	def generate(String path, CpsSystem system) {
		FileUtils.write(path + "/src/main/webapp/src/metaobject/MetaObject.ts", generateCode(system))
	}
	
	private def primitiveDataType(Usage usg) {
		val t = CpsUtil.dataType(usg)
		if (t === null) {
			return null
		}
		
		if ("int".equals(t)) {
			return "INT"
		} else if ("double".equals(t)) {
			return "FLOAT"
		} else if ("boolean".equals(t)) {
			return "BOOL"
		} else if ("String".equals(t)) {
			return "STRING"
		}
		return null
	}
		
	private def getProperty(Field f)
	'''
	{
	    name: "«f.name.toVar»",
	    type: "«IF CpsUtil.dataType(f.usage) === null»complex«ELSE»primitive«ENDIF»",
	    «IF CpsUtil.dataType(f.usage) !== null»dataType: "«primitiveDataType(f.usage)»"«ELSE»properties: [
	    	«FOR sf: mapper.mapAttr(f.type as AttributeDefinition).fields SEPARATOR ','»
	    	«getProperty(sf)»
	    	«ENDFOR»
	    ]«ENDIF»«getBounds(f)»
	}
	'''
	
	private def getService(Action a)
	'''
	{
        name: '«a.name.toVar»',
        parameters: [
        	«FOR p: a.params SEPARATOR ','»
            {
                name: '«p.name.toVar»',
                optional: false,
                type: "«primitiveDataType(p)»"
            }
            «ENDFOR»
        ]
    }
	'''
	
	private def getBounds(Field field) {
		val bounds = CpsUtil.getBounds(field.type)
		if (bounds === null) {
			return ''
		}
		return ''',
		bounds: {
			min: «bounds.key»,
			max: «bounds.value»
		}'''
	}
	
	private def generateCode(CpsSystem system)
	'''
	import {CPS} from "../dto/Cps";
	import {CpsComponent, Property} from "./MetaObjectModel";
	
	export const metaDataMap: Map<CPS, CpsComponent> = new Map<CPS, CpsComponent>()
	
	«FOR cps: system.cps»
	const «cps.name.toVar»Struct: Array<Property> = [
	    {
	        name: "id",
	        type: "primitive",
	        dataType: "STRING"
	    },
	    «FOR state: cps.states SEPARATOR ',\n'»
	    {
	        name: "«state.name.toVar»",
	        type: "enum",
	        literals: [ «FOR v: SysMLReflectionUtil.getStateValues(state.usage) SEPARATOR ', '»'«v.toConstant»'«ENDFOR» ]
	    }«ENDFOR»
		«IF !cps.states.empty»,«ENDIF»
	    «FOR f: cps.fields SEPARATOR ','»
	    «getProperty(f)»
	    «ENDFOR»
	    «IF !cps.fields.empty»,«ENDIF»
	    «FOR p: cps.parts.filter[it.composite] SEPARATOR ','»
	    {
	        name: '«p.name.toVar»',
	        type: "complex",
	        properties: [
	            «FOR f: mapper.map(p.type).fields SEPARATOR ','»
	            «getProperty(f)»
	            «ENDFOR»
	        ]
	    }«ENDFOR»
	]
	«ENDFOR»
	
	// ITS
	metaDataMap.set("«system.name.toConstant»", {
	    services: [
	    	«FOR svc: system.systemwideDt.actions SEPARATOR ','»
	    	«getService(svc)»
	    	«ENDFOR»
	    ],
	    properties: [
	        «FOR cps: system.systemwideDt.parts.filter[it.composite].filter[CpsUtil.isCps(it.type)] SEPARATOR ',\n' AFTER '\n'»
	        {
	            name: '«cps.name.toVar»',
	            «IF cps.list»list: true,«ENDIF»
	            type: "complex",
	            properties: «cps.type.name.toVar»Struct
	        }«ENDFOR»
	    ]
	})
	
	«FOR cps: system.cps»
	metaDataMap.set("«cps.name.toConstant»", {
	    services: [
	        «FOR svc: cps.actions SEPARATOR ','»
	        «getService(svc)»
	        «ENDFOR»
	    ],
	    properties: «cps.name.toVar»Struct
	})
	«ENDFOR»
	'''
}