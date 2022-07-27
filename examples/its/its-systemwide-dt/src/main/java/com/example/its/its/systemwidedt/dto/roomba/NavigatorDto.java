package com.example.its.its.systemwidedt.dto.roomba;


public class NavigatorDto {
	
	private boolean orientating;
	private String position;
	private String target;
	
	public NavigatorDto() {
	}
	
	public NavigatorDto(boolean orientating, String position, String target) {
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
		return "NavigatorDto {"
		    + "orientating=" + orientating + ", "
		    + "position=" + position + ", "
		    + "target=" + target
			+ "}";
	}
}
