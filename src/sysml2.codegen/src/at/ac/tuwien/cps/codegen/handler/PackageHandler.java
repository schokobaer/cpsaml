package at.ac.tuwien.cps.codegen.handler;

import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.Package;

public class PackageHandler implements ElementHandler {

	private BaseHandler base;
	
	public PackageHandler(BaseHandler base) {
		this.base = base;
	}
	
	@Override
	public boolean responsible(Object o) {
		return o instanceof Package;
	}
	
	@Override
	public void handle(Object o) {
		Package p = (Package) o;
		//System.out.println("Traversing package " + p.getName());
		p.getOwnedElement().forEach(base::handle);
	}
	
	
	
}
