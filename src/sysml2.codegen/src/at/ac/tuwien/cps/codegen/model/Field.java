package at.ac.tuwien.cps.codegen.model;

import org.omg.sysml.lang.sysml.AttributeDefinition;
import org.omg.sysml.lang.sysml.AttributeUsage;
import org.omg.sysml.lang.sysml.DataType;
import org.omg.sysml.lang.sysml.Type;
import org.omg.sysml.lang.sysml.Usage;

public class Field implements Member {

	private String name;
	private boolean list;
	private DataType type;
	private AttributeUsage usage;
	
	public Field(String name, boolean list, DataType type, AttributeUsage usage) {
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

	public DataType getType() {
		return type;
	}

	public void setType(DataType type) {
		this.type = type;
	}

	public AttributeUsage getUsage() {
		return usage;
	}

	public void setUsage(AttributeUsage usage) {
		this.usage = usage;
	}
	
	
	
}
