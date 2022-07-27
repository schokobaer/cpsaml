package at.ac.tuwien.cps.codegen.generator.systemwidedt

import at.ac.tuwien.cps.codegen.util.FileUtils
import java.io.File
import at.ac.tuwien.cps.codegen.model.CpsSystem
import at.ac.tuwien.cps.codegen.generator.systemwidedt.config.RabbitConfigGenerator
import at.ac.tuwien.cps.codegen.generator.systemwidedt.repo.RepoGenerator
import at.ac.tuwien.cps.codegen.generator.systemwidedt.schedule.SchedulerGenerator
import at.ac.tuwien.cps.codegen.generator.systemwidedt.service.SystemwideServiceGenerator
import at.ac.tuwien.cps.codegen.generator.systemwidedt.service.CpsServiceGenerator
import at.ac.tuwien.cps.codegen.generator.systemwidedt.messaging.ListenerGenerator
import at.ac.tuwien.cps.codegen.generator.systemwidedt.dto.DtoGenerator
import at.ac.tuwien.cps.codegen.processor.TemplateFileProcessor

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*
import at.ac.tuwien.cps.codegen.util.CpsUtil

class SystemwideDtProjectGenerator {
		
	def generate(String path, String groupId, CpsSystem system) {
		
		// create project root
		val root = new File(getTarget(path, system))
		root.mkdirs()
		
		// clone static project from resources; generate pom
		val pkg = groupId + "." + system.name.toLow
		val pkgPath = copyProject(root.absolutePath, pkg, system)
		
				
		// generate dynamic source files
		new RabbitConfigGenerator().generate(pkgPath, pkg, system)
		new RepoGenerator().generate(pkgPath, pkg, system)
		new SchedulerGenerator().generate(pkgPath, pkg, system)
		new SystemwideServiceGenerator().generate(pkgPath, pkg, system)
		new CpsServiceGenerator().generate(pkgPath, pkg, system)
		new ListenerGenerator().generate(pkgPath, pkg, system)
		
		val dtoGenerator = new DtoGenerator()
		dtoGenerator.generate(pkgPath, pkg, system)
		system.cps.forEach[
			val singleton = CpsUtil.isSingletonCps(system, it)
			dtoGenerator.generate(pkgPath, pkg, it, singleton)
		]
	}
	
	def clean(String path, CpsSystem system) {
		FileUtils.deleteDirectory(getTarget(path, system))
	}
	
	private def getTarget(String path, CpsSystem system) {
		return new File(path).absolutePath + '''/«system.name.toLow»-systemwide-dt'''
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
			
			
		val resRoot = "resource/systemwidedt"
		
		processor.deepCopy('''«resRoot»/.mvn''', root, false)
		processor.deepCopy('''«resRoot»/.gitignore''', root, false)
		processor.deepCopy('''«resRoot»/mvnw''', root, false)
		processor.deepCopy('''«resRoot»/mvnw.cmd''', root, false)
		processor.deepCopy('''«resRoot»/Dockerfile''', root, false)
		processor.deepCopy('''«resRoot»/pom.xml''', root, false)
		FileUtils.mkdir('''«root»/src/test/java''')
				
		val pkgFile = new File(root + "/src/main/java/" + pkg.replace(".", "/") + "/systemwidedt")
		pkgFile.mkdirs
		processor.deepCopy('''«resRoot»/src/main/resources''', '''«root»/src/main''', false)
		
		processor.deepCopy('''«resRoot»/src/main/java/systemwidedt''', pkgFile.absolutePath, true)
		
		return pkgFile.absolutePath
	}
	
}