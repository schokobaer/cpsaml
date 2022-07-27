package at.ac.tuwien.cps.codegen.handler;

import java.util.ArrayList;
import java.util.List;

import at.ac.tuwien.cps.codegen.model.CpsSystem;

public class BaseHandler implements ElementHandler {

	private List<ElementHandler> specifichandlers = new ArrayList<>();
	private CpsSystem system = new CpsSystem();
	
	public BaseHandler() {
		specifichandlers.add(new ActionUsageHandler(this));
		specifichandlers.add(new PartDefinitionHandler(this));
		specifichandlers.add(new PackageHandler(this));
		//specifichandlers.add(new NamespaceHandler(this));
	}
	
	@Override
	public void handle(Object o) {
		specifichandlers.stream()
			.filter(h -> h.responsible(o))
			.findFirst().ifPresent(h -> h.handle(o));
	}

	@Override
	public boolean responsible(Object o) {
		return false;
	}
	
	public CpsSystem getSystem() {
		return system;
	}
}
