package at.ac.tuwien.cps.codegen.util

import org.omg.sysml.util.SysMLUtil
import java.util.function.Consumer
import org.omg.sysml.lang.sysml.Namespace
import java.io.File
import org.omg.kerml.xtext.KerMLStandaloneSetup
import org.omg.sysml.xtext.SysMLStandaloneSetup

class DynamicSourceLoader extends SysMLUtil {
	
	def init() {
		KerMLStandaloneSetup.doSetup()
		SysMLStandaloneSetup.doSetup()
		
		addExtension("kerml")
		addExtension("sysml")
	}
	
	def load(String path, Consumer<Namespace> callback) {
		val dir = new File(path)
		readAll(path, true)
		
		inputResources.forEach[
			it.contents.filter(Namespace).forEach[crawl(it)]
		]
				
		inputResources.forEach[
			if (it.getURI().toString().startsWith(dir.getAbsoluteFile().toURI().toString())) {		
				it.getContents().filter(Namespace).forEach[
					callback.accept(it)
				]
			}			
		]
		
	}
	
	def crawl(Namespace ns) {
		ns.member
			.filter(org.omg.sysml.lang.sysml.Package)
			//.forEach[p | p.im]
			/*
			.forEach[p | p.member.forEach[ m |
				println("Member " + m.class.simpleName + " of package " + p.name)
			]]
			* 
			*/
	}
}