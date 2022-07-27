package at.ac.tuwien.cps.codegen.model;

import java.util.List;

public class CpsSystem {
	
	private Part systemwideDt;
	private List<Part> cps;
	
	
	public String getName() {
		return systemwideDt.getName();
	}
	public Part getSystemwideDt() {
		return systemwideDt;
	}
	public void setSystemwideDt(Part systemwideDt) {
		this.systemwideDt = systemwideDt;
	}
	public List<Part> getCps() {
		return cps;
	}
	public void setCps(List<Part> cps) {
		this.cps = cps;
	}
		
}
