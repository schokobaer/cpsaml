package at.ac.tuwien.big.cps.transform

import at.ac.tuwien.big.cps.CpsamlStandaloneSetup
import at.ac.tuwien.big.cps.transform.generator.SysML2ProjectGenerator
import at.ac.tuwien.big.cps.transform.util.EcoreUtils
import cpsaml.CpsSystem
import cpsaml.impl.CpsamlPackageImpl
import cpsaml.util.CpsamlValidator
import java.util.ArrayList
import java.util.HashMap
import org.eclipse.emf.common.util.BasicDiagnostic
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl

class SysML2Transformer {
	def static void main(String[] arguments) {
				
		val args = new ArrayList<String>(arguments)
		
		//val inputFilePath = "C:\\Users\\Andreas\\Dropbox\\Uni\\MA\\Fend.CPSIoT\\prototype-playground\\itsb.csaml\\its_beta.xmi"
		val ecoreSource = "C:\\Users\\Andreas\\Dropbox\\Uni\\MA\\Fend.CPSIoT\\projects\\cpsaml\\model\\cpsaml.ecore" // path to the cpssysactml.ecore file
		//val outputDir = "C:\\Users\\Andreas\\Desktop\\tmp\\generatorstuff\\sysi"
		
		// handle flags
		val clean = args.removeIf["--clean" == it]
		
		// handle arguments
		if (args.size !== 2) {
			System.err.println("Usage: transformer [--clean] <inputFile> <outputDir>")
			System.exit(1)
		}
		val inputFilePath = args.get(0)
		val outputDir = args.get(1)
		
		// register CpsSysActML.ecore file
		EcoreUtils.register(ecoreSource, CpsamlPackageImpl.init);
		
		// register XMI ResourceLoader
		Resource.Factory.Registry.INSTANCE.extensionToFactoryMap.put("xmi", new XMIResourceFactoryImpl())
				
		CpsamlStandaloneSetup.doSetup
		
		// Load input model
		println('''Loading source file: «inputFilePath»''')
		val resourceSet = new ResourceSetImpl()
		val resource = resourceSet.getResource(URI.createFileURI(inputFilePath), true)
		val sys = resource.allContents.filter(CpsSystem).next
		
		// validate
		if (!CpsamlValidator.INSTANCE.validateCpsSystem(sys, new BasicDiagnostic(), new HashMap())) {
			System.err.println("Invalid input model")
			System.exit(1)
		}
		
		val generator = new SysML2ProjectGenerator()
		
		// clean outputDir content
		if (clean) {
			println("Cleaning target dir " + outputDir);
			generator.clean(outputDir, sys)
		}
		
		// generate sysml2 project
		println("Starting SysML v2 generation ...")
		generator.generate(outputDir, sys)
		println("Finished")
	}
}