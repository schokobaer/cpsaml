package at.ac.tuwien.cps.codegen.generator.catkinws

import at.ac.tuwien.cps.codegen.model.CpsSystem
import at.ac.tuwien.cps.codegen.model.Part
import at.ac.tuwien.cps.codegen.processor.TemplateFileProcessor
import at.ac.tuwien.cps.codegen.util.FileUtils
import java.io.File

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*
import at.ac.tuwien.cps.codegen.generator.catkinws.ros.CMakeListsGenerator
import at.ac.tuwien.cps.codegen.generator.catkinws.ros.LaunchFileGenerator
import at.ac.tuwien.cps.codegen.generator.catkinws.ros.RosUtil
import at.ac.tuwien.cps.codegen.generator.catkinws.ros.MsgGenerator
import at.ac.tuwien.cps.codegen.generator.catkinws.ros.PythonInitGenerator
import at.ac.tuwien.cps.codegen.generator.catkinws.ros.ComponentClientGenerator
import at.ac.tuwien.cps.codegen.generator.catkinws.ros.SrvGenerator
import at.ac.tuwien.cps.codegen.generator.catkinws.ros.ComponentBaseGenerator
import at.ac.tuwien.cps.codegen.generator.catkinws.ros.ComponentNodeGenerator
import at.ac.tuwien.cps.codegen.generator.catkinws.ros.DtBaseGenerator
import at.ac.tuwien.cps.codegen.util.CpsUtil

class CatkinWsGenerator {
	
	def generate(String path, CpsSystem system) {
		
		// create project root
		val root = new File(getTarget(path))
		root.mkdirs()
		
		// clone static project from resources; generate pom
		copyWorkspace(root.absolutePath, system)
		
		val msgGenerator = new MsgGenerator()
		val srvGenerator = new SrvGenerator()
		val componentNodeGenerator = new ComponentNodeGenerator()
		val componentBaseGenerator = new ComponentBaseGenerator()
		val componentClientGenerator = new ComponentClientGenerator()
		
		system.cps.forEach[
			val pkgRoot = '''«root.absolutePath»/src/«it.name.toLow»'''
			var singleton = CpsUtil.isSingletonCps(system, it)
			
			// create package
			createPackageStructure(pkgRoot, it)
			new CMakeListsGenerator().generate(pkgRoot, it)
			new LaunchFileGenerator().generate(pkgRoot, it, singleton)
			
			RosUtil.getMsgs(it).forEach[ attr |
				msgGenerator.generate(pkgRoot, attr)
			]
			
			RosUtil.getSrvs(it).forEach[action |
				srvGenerator.generate(pkgRoot, action)
			]
			
			RosUtil.getNodes(it).forEach[ node |
				componentBaseGenerator.generate(pkgRoot, it.name.toLow, node)
				componentClientGenerator.generate(pkgRoot, it.name.toLow, node)
				componentNodeGenerator.generate(pkgRoot, node, false)
			]
			new DtBaseGenerator().generate(pkgRoot, it, singleton)
			componentNodeGenerator.generate(pkgRoot, it, true)
			new PythonInitGenerator().generate(pkgRoot, it)
			new DockerfileGenerator().generate(getTarget(path), it, singleton)
			new StartupScriptGenerator().generate(getTarget(path), it, singleton)
		]
		
	}
	
	def clean(String path) {
		FileUtils.deleteDirectory(getTarget(path))
	}
	
	private def getTarget(String path) {
		return new File(path).absolutePath + '''/catkin_ws'''
	}
	
	private def copyWorkspace(String root, CpsSystem system) {
		val resRoot = "resource/catkin_ws"
		FileUtils.deepCopy('''«resRoot»/build''', root, false)
		FileUtils.deepCopy('''«resRoot»/devel''', root, false)
		FileUtils.deepCopy('''«resRoot»/.catkin_workspace''', root, false)
		FileUtils.mkdir('''«root»/src''')
	}
	
	private def createPackageStructure(String root, Part part) {
		val processor = new TemplateFileProcessor()
			.addFileExtensionSupport("package.xml")
			.setExpression("cps.toLow", part.name.toLow)
			.setExpression("cps", part.name)
			
		val resRoot = "resource/catkin_ws/src/cps"
		FileUtils.mkdir(root)
		processor.deepCopy(resRoot, root, true)
	}
}