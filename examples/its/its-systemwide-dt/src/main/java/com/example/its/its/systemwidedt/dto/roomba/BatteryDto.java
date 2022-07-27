package com.example.its.its.systemwidedt.dto.roomba;


public class BatteryDto {
	
	private boolean docked;
	private double energy;
	
	public BatteryDto() {
	}
	
	public BatteryDto(boolean docked, double energy) {
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
		return "BatteryDto {"
		    + "docked=" + docked + ", "
		    + "energy=" + energy
			+ "}";
	}
}
