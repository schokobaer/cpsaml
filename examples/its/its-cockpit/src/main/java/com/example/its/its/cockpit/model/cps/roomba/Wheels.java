package com.example.its.its.cockpit.model.cps.roomba;


public class Wheels {
	
	private boolean lifted;
	private Speed speed;
	
	public Wheels() {
	}
	
	public Wheels(boolean lifted, Speed speed) {
		this.lifted = lifted;
		this.speed = speed;
	}

	public boolean getLifted() {
		return lifted;
	}
	
	public void setLifted(boolean lifted) {
		this.lifted = lifted;
	}
	
	public Speed getSpeed() {
		return speed;
	}
	
	public void setSpeed(Speed speed) {
		this.speed = speed;
	}
	
	
    
    @Override
	public String toString() {
		return "Wheels {"
		    + "lifted=" + lifted + ", "
		    + "speed=" + speed
			+ "}";
	}
}

