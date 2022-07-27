package at.ac.tuwien.cps.codegen.generator.cockpit.typescript

import at.ac.tuwien.cps.codegen.util.FileUtils
import at.ac.tuwien.cps.codegen.model.CpsSystem

//import static at.ac.tuwien.cps.codegen.util.NamingUtil.f

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*

class CpsTsEnumGenerator {
	
	
	/**
	 * path is the root path to the project.
	 */
	def generate(String path, CpsSystem system) {
		FileUtils.write(path + "/src/main/webapp/src/dto/Cps.ts", generateCode(system))
	}
	
	def generateCode(CpsSystem system)
	'''
	/*DS*/
	export type CPS = '«f(system.name)»'«FOR cps: system.cps» | '«cps.name.toConstant»'«ENDFOR»
	export const CPS_VALUES: ReadonlyArray<CPS> = ['«f(system.name)»'«FOR cps: system.cps», '«cps.name.toConstant»'«ENDFOR»]
	'''
}