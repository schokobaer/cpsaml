package at.ac.tuwien.cps.codegen.handler;

import org.omg.sysml.lang.sysml.ActionUsage;
import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.Package;
import org.omg.sysml.lang.sysml.PartDefinition;
import org.omg.sysml.lang.sysml.ReferenceUsage;

public class ActionUsageHandler implements ElementHandler {

	private BaseHandler base;
	
	public ActionUsageHandler(BaseHandler base) {
		this.base = base;
	}
	
	@Override
	public boolean responsible(Object o) {
		return o instanceof ActionUsage;
	}
	
	@Override
	public void handle(Object o) {
		ActionUsage au = (ActionUsage) o;
		System.out.print("Action " + au.getName());
		au.getParameter().forEach(p -> {
			ReferenceUsage ref = (ReferenceUsage) p;
			System.out.print(" - Param " + p.getName() + ": " + ref.getReferenceType());
		});
		System.out.println();
	}
	
	
	
}
