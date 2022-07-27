package com.example.its.its.cockpit.model.cps.roomba;

import at.ac.tuwien.big.cps.annotationprocessor.annotation.Bounds;

public class Battery {
	
	private boolean docked;
	@Bounds(min = 0.0, max = 1.0) 
	private double energy;
	
	public Battery() {
	}
	
	public Battery(boolean docked, double energy) {
		this.docked = docked;
		this.energy = energy;
	}

	public boolean getDocked() {
		return docked;
	}
	
	public void setDocked(boolean docked) {
		this.docked = docked;
	}
	
	public double getEnergy() {
		return energy;
	}
	
	public void setEnergy(double energy) {
		this.energy = energy;
	}
	
	
    
    @Override
	public String toString() {
		return "Battery {"
		    + "docked=" + docked + ", "
		    + "energy=" + energy
			+ "}";
	}
}

