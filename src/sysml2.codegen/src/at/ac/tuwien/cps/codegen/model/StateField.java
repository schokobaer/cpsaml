package at.ac.tuwien.cps.codegen.model;

import org.omg.sysml.lang.sysml.AttributeUsage;
import org.omg.sysml.lang.sysml.Behavior;
import org.omg.sysml.lang.sysml.DataType;
import org.omg.sysml.lang.sysml.StateUsage;

public class StateField implements Member {

	private String name;
	private boolean list;
	private Behavior type;
	private StateUsage usage;
	
	public StateField(String name, boolean list, Behavior type, StateUsage usage) {
		super();
		this.name = name;
		this.list = list;
		this.type = type;
		this.usage = usage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isList() {
		return list;
	}

	public void setList(boolean list) {
		this.list = list;
	}

	public Behavior getType() {
		return type;
	}

	public void setType(Behavior type) {
		this.type = type;
	}

	public StateUsage getUsage() {
		return usage;
	}

	public void setUsage(StateUsage usage) {
		this.usage = usage;
	}
	
	
	
}
