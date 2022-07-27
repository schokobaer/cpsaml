package at.ac.tuwien.cps.codegen.handler;

import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.Namespace;

public class NamespaceHandler implements ElementHandler {

	private BaseHandler base;
	
	public NamespaceHandler(BaseHandler base) {
		this.base = base;
	}
	
	@Override
	public boolean responsible(Object o) {
		return o instanceof Namespace;
	}
	
	@Override
	public void handle(Object o) {
		Namespace n = (Namespace) o;
		n.getMember().forEach(base::handle);
		for (Element e: n.getMember()) {
			//System.out.println(e);
		}
	}
	
	
	
}
