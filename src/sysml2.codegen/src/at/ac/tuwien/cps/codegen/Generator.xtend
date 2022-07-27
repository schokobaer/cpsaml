package at.ac.tuwien.cps.codegen

import at.ac.tuwien.cps.codegen.generator.catkinws.CatkinWsGenerator
import at.ac.tuwien.cps.codegen.generator.cockpit.CockpitProjectGenerator
import at.ac.tuwien.cps.codegen.generator.systemwidedt.SystemwideDtProjectGenerator
import at.ac.tuwien.cps.codegen.handler.BaseHandler
import at.ac.tuwien.cps.codegen.util.FileUtils
import at.ac.tuwien.cps.codegen.util.SourceLoader

import static extension at.ac.tuwien.cps.codegen.util.NamingUtil.*
import java.util.ArrayList

class Generator {
	def static void main(String[] arguments) {
		
		//var outputDir = "C:\\Users\\Andreas\\Desktop\\tmp\\generatorstuff\\blabla";
		val sysMlLibPath = "C:\\Users\\Andreas\\Dropbox\\Uni\\MA\\SysMLv2\\repo\\SysML-v2-Pilot-Implementation\\sysml.library"; // pah to the sysml.library project folder
		//var inputDir = "C:\\Users\\Andreas\\Dropbox\\Uni\\MA\\Fend.CPSIoT\\prototype-playground\\its.sysml\\src";
		//val pkg = "at.ac.tuwien.big";
		val args = new ArrayList<String>(arguments)
		
		// Flags set
		val clean = args.removeIf["--clean" == it]
		var dt = args.removeIf["--dt" == it]
		var cockpit = args.removeIf["--cockpit" == it]
		var ros = args.removeIf["--catkin" == it]
		
		// If no specific target platform is used, generate them all
		if (!dt && !cockpit && !ros) {
			dt = cockpit = ros = true 
		}
		
		// handle arguments
		if (args.size < 2 || args.size > 3) {
			System.err.println("Usage: generator [--clean] [--dt] [--cockpit] [--catkin] <inputDir> <outputDir> [packageName]")
			System.exit(1)
		}		
		val inputDir = args.get(0)
		val outputDir = args.get(1)
		var pkg = args.size === 3 ? args.get(2) : null
		
		// load and handle input files
		val handler = new BaseHandler();
		val sourceLoader = new SourceLoader(sysMlLibPath);
		sourceLoader.load(inputDir, [ns |
			ns.getMember().forEach[handler.handle(it)];
		]);
		System.out.println("Finished Model crawling");
		
		// set package name, if not explicitly set by argument
		if (pkg === null) {
			pkg = "com.example." + handler.system.name.toLow
			println("Package name implicitly set to " + pkg)
		}
		
		val catkinWsGenerator = new CatkinWsGenerator()
		val systemwideDtGenerator = new SystemwideDtProjectGenerator();
		val cockpitGenerator = new CockpitProjectGenerator();
		val system = handler.system
		
		// clean output dir content
		if (clean) {
			println("Cleaning target dir " + outputDir);
			if (dt) {
				systemwideDtGenerator.clean(outputDir, system)
			}
			if (cockpit) {
				cockpitGenerator.clean(outputDir, system)
			}
			if (ros) {
				catkinWsGenerator.clean(outputDir)
			}
		}
		
		// generate systemwide-DT
		if (dt) {
			println("Starting Systemwide-DT code generation ...");
			systemwideDtGenerator.generate(outputDir, pkg, system);
		}
		
		// generate cockpit
		if (cockpit) {
			println("Starting Cockpit code generation ...");
			cockpitGenerator.generate(outputDir, pkg, system);
		}
		
		// generate catkin_ws
		if (ros) {
			println("Starting catkin_ws code generation ...");
			catkinWsGenerator.generate(outputDir, system);
		}
		
		println("Finished");
		
	}
}