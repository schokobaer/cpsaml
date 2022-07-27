package com.example.its.its.cockpit.model.cps.its;

import com.example.its.its.cockpit.annotation.CpsDevice;
import com.example.its.its.cockpit.model.monitor.Cps;
import at.ac.tuwien.big.cps.annotationprocessor.annotation.Id;
import com.example.its.its.cockpit.model.cps.roomba.Roomba;
import java.util.List;

@CpsDevice(Cps.ITS)
public class ITS {
	
	@Id
	private String id = "ITS";
	private List<Roomba> roombas;
	
	public ITS() {
	}
	
	public ITS(List<Roomba> roombas) {
		this.roombas = roombas;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public List<Roomba> getRoombas() {
		return roombas;
	}
	
	public void setRoombas(List<Roomba> roombas) {
		this.roombas = roombas;
	}
	
	
    
    @Override
	public String toString() {
		return "ITS {"
		    + "roombas=" + roombas
			+ "}";
	}
}

