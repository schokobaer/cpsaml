package com.example.its.its.cockpit.model.cps.roomba;


public class Bumper {
	
	private boolean pushed;
	
	public Bumper() {
	}
	
	public Bumper(boolean pushed) {
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
		return "Bumper {"
		    + "pushed=" + pushed
			+ "}";
	}
}

