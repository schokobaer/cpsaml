package at.ac.tuwien.cps.codegen.handler;

import org.omg.sysml.lang.sysml.Element;

public interface ElementHandler {

	boolean responsible(Object o);
	
	void handle(Object o);
}
