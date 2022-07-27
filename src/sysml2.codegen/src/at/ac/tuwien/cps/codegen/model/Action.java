package at.ac.tuwien.cps.codegen.model;

import java.util.List;

import org.omg.sysml.lang.sysml.PerformActionUsage;
import org.omg.sysml.lang.sysml.ReferenceUsage;

public class Action {

	private String name;
	private PerformActionUsage usage;
	private List<ReferenceUsage> params;
	
	public Action(String name, PerformActionUsage usage, List<ReferenceUsage> params) {
		super();
		this.name = name;
		this.usage = usage;
		this.params = params;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public PerformActionUsage getUsage() {
		return usage;
	}
	public void setUsage(PerformActionUsage usage) {
		this.usage = usage;
	}
	public List<ReferenceUsage> getParams() {
		return params;
	}
	public void setParams(List<ReferenceUsage> params) {
		this.params = params;
	}
	
	
	
}
