package at.ac.tuwien.big.cps.transform.util

import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EPackage.Registry
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl

class EcoreUtils {
	
	static def register(String path, EPackage pkg) {
		Resource.Factory.Registry.INSTANCE.extensionToFactoryMap.put("ecore", new EcoreResourceFactoryImpl())
		val rs = new ResourceSetImpl();
		val res = rs.getResource(URI.createFileURI(path), true);
		
		res.allContents.filter(EPackage).forEach[
			val nsUri = it.nsURI
			if (!Registry.INSTANCE.containsKey(nsUri)) {
				Registry.INSTANCE.put(nsUri, pkg)
				println('''Registered «nsUri»''')
			}
		]
	}
}