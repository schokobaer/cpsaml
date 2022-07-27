package com.example.its.its.systemwidedt.dto.roomba;


public class WheelsDto {
	
	private boolean lifted;
	private SpeedDto speed;
	
	public WheelsDto() {
	}
	
	public WheelsDto(boolean lifted, SpeedDto speed) {
		this.lifted = lifted;
		this.speed = speed;
	}

	public boolean getLifted() {
		return lifted;
	}
	
	public void setLifted(boolean lifted) {
		this.lifted = lifted;
	}
	
	public SpeedDto getSpeed() {
		return speed;
	}
	
	public void setSpeed(SpeedDto speed) {
		this.speed = speed;
	}
	
	
	@Override
	public String toString() {
		return "WheelsDto {"
		    + "lifted=" + lifted + ", "
		    + "speed=" + speed
			+ "}";
	}
}
