package com.example.its.its.systemwidedt.dto.roomba;


public class BumperDto {
	
	private boolean pushed;
	
	public BumperDto() {
	}
	
	public BumperDto(boolean pushed) {
		this.pushed = pushed;
	}

	public boolean getPushed() {
		return pushed;
	}
	
	public void setPushed(boolean pushed) {
		this.pushed = pushed;
	}
	
	
	@Override
	public String toString() {
		return "BumperDto {"
		    + "pushed=" + pushed
			+ "}";
	}
}
