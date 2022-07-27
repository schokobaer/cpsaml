package at.ac.tuwien.cps.codegen.generator.cockpit

import at.ac.tuwien.cps.codegen.generator.cockpit.java.CpsEnumGenerator
import at.ac.tuwien.cps.codegen.generator.cockpit.java.DataListenerGenerator
import at.ac.tuwien.cps.codegen.generator.cockpit.java.ModelDtoGenerator
import at.ac.tuwien.cps.codegen.generator.cockpit.typescript.MetaObjectGenerator
import at.ac.tuwien.cps.codegen.model.CpsSystem
import at.ac.tuwien.cps.codegen.processor.TemplateFileProcessor
import at.ac.tuwien.cps.codegen.util.FileUtils
import java.io.File

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*
import at.ac.tuwien.cps.codegen.generator.cockpit.typescript.CpsTsEnumGenerator
import at.ac.tuwien.cps.codegen.util.CpsUtil

class CockpitProjectGenerator {
	
	def generate(String path, String groupId, CpsSystem system) {
		
		// create project root
		val root = new File(getTarget(path, system))
		root.mkdirs()
		
		// clone static project from resources; generate pom
		val pkg = groupId + "." + system.name.toLow
		val pkgPath = copyProject(root.absolutePath, pkg, system)
		
				
		// generate dynamic source files
		// Typescript
		new CpsTsEnumGenerator().generate(root.absolutePath, system)
		new MetaObjectGenerator().generate(root.absolutePath, system)
		
		// Java
		new CpsEnumGenerator().generate(pkgPath, pkg, system)
		new DataListenerGenerator().generate(pkgPath, pkg, system)
				
		val dtoGenerator = new ModelDtoGenerator()
		dtoGenerator.generate(pkgPath, pkg, system)
		system.cps.forEach[
			dtoGenerator.generate(pkgPath, pkg, it, CpsUtil.isSingletonCps(system, it))
		]
	}
	
	def clean(String path, CpsSystem system) {
		FileUtils.deleteDirectory(getTarget(path, system))
	}
	
	private def getTarget(String path, CpsSystem system) {
		return new File(path).absolutePath + '''/«system.name.toLow»-cockpit'''
	}
	
	private def String copyProject(String root, String pkg, CpsSystem system) {
		val processor = new TemplateFileProcessor()
			.addFileExtensionSupport(".java")
			.addFileExtensionSupport(".xml")
			.addFileExtensionSupport("Dockerfile")
			.setExpression("groupId", pkg)
			.setExpression("pkg", pkg)
			.setExpression("system.name.toLow", system.name.toLow)
			.setExpression("system.name.toConstant", system.name.toConstant)
			.setExpression("system.name.toClazz", system.name.toClazz)
			.setExpression("system.name.toVar", system.name.toVar)
		
		
			
		val resRoot = "resource/cockpit"
		
		processor.deepCopy('''«resRoot»/.mvn''', root, false)
		processor.deepCopy('''«resRoot»/.gitignore''', root, false)
		processor.deepCopy('''«resRoot»/mvnw''', root, false)
		processor.deepCopy('''«resRoot»/mvnw.cmd''', root, false)
		processor.deepCopy('''«resRoot»/Dockerfile''', root, false)
		processor.deepCopy('''«resRoot»/pom.xml''', root, false)
		
		
		FileUtils.mkdir('''«root»/src/test/java''')
		
		val pkgFile = new File(root + "/src/main/java/" + pkg.replace(".", "/") + "/cockpit")
		pkgFile.mkdirs
		processor.deepCopy('''«resRoot»/src/main/resources''', '''«root»/src/main''', false)
		processor.deepCopy('''«resRoot»/src/main/webapp''', '''«root»/src/main''', false)
		
		processor.deepCopy('''«resRoot»/src/main/java/cockpit''', pkgFile.absolutePath, true)
		
		return pkgFile.absolutePath
	}
	
}