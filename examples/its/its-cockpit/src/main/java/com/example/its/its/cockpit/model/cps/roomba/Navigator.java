package com.example.its.its.cockpit.model.cps.roomba;


public class Navigator {
	
	private boolean orientating;
	private String position;
	private String target;
	
	public Navigator() {
	}
	
	public Navigator(boolean orientating, String position, String target) {
		this.orientating = orientating;
		this.position = position;
		this.target = target;
	}

	public boolean getOrientating() {
		return orientating;
	}
	
	public void setOrientating(boolean orientating) {
		this.orientating = orientating;
	}
	
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getTarget() {
		return target;
	}
	
	public void setTarget(String target) {
		this.target = target;
	}
	
	
    
    @Override
	public String toString() {
		return "Navigator {"
		    + "orientating=" + orientating + ", "
		    + "position=" + position + ", "
		    + "target=" + target
			+ "}";
	}
}

