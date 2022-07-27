package at.ac.tuwien.cps.codegen.model;

import org.omg.sysml.lang.sysml.DataType;
import org.omg.sysml.lang.sysml.Usage;

public interface Member {
	String getName();
	boolean isList();
	Usage getUsage();
}
