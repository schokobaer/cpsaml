package at.ac.tuwien.cps.codegen.util;

import java.io.File;
import java.util.function.Consumer;

import org.eclipse.emf.ecore.EObject;
import org.omg.kerml.xtext.KerMLStandaloneSetup;
import org.omg.sysml.lang.sysml.Namespace;
import org.omg.sysml.util.SysMLUtil;
import org.omg.sysml.xtext.SysMLStandaloneSetup;

public class SourceLoader extends SysMLUtil {

	
	public SourceLoader(String sysmlLibPath) {
		KerMLStandaloneSetup.doSetup();
		SysMLStandaloneSetup.doSetup();
		
		addExtension("kerml");
		addExtension("sysml");
		
		readAll(sysmlLibPath, true);
	}
	
	
	public void load(String path, Consumer<Namespace> callback) {
		File dir = new File(path);
		readAll(path, true);
		
		inputResources.stream().forEach(r -> {
			if (r.getURI().toString().startsWith(dir.getAbsoluteFile().toURI().toString())) {
				//System.out.println(r.getURI().segment(r.getURI().segmentCount() - 1) + " is a source file!");
				r.getContents().stream().forEach(c -> {
					if (!(c instanceof Namespace)) {
						return;
					}
					Namespace ns = (Namespace) c;
					callback.accept(ns);
				});
			}			
		});
	}
	
}
