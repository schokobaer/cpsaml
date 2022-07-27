package at.ac.tuwien.cps.codegen.model;

import org.omg.sysml.lang.sysml.Behavior;
import org.omg.sysml.lang.sysml.PartDefinition;
import org.omg.sysml.lang.sysml.PartUsage;
import org.omg.sysml.lang.sysml.StateUsage;

public class PartField implements Member {

	private String name;
	private boolean list;
	private PartDefinition type;
	private PartUsage usage;
	
	public PartField(String name, boolean list, PartDefinition type, PartUsage usage) {
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
	public PartDefinition getType() {
		return type;
	}
	public void setType(PartDefinition type) {
		this.type = type;
	}
	public PartUsage getUsage() {
		return usage;
	}
	public void setUsage(PartUsage usage) {
		this.usage = usage;
	}
	
	public boolean isComposite() {
		return usage.isComposite();
	}
	
}
