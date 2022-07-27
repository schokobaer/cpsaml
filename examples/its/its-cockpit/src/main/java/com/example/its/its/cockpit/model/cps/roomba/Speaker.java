package com.example.its.its.cockpit.model.cps.roomba;


public class Speaker {
	
	private boolean playing;
	
	public Speaker() {
	}
	
	public Speaker(boolean playing) {
		this.playing = playing;
	}

	public boolean getPlaying() {
		return playing;
	}
	
	public void setPlaying(boolean playing) {
		this.playing = playing;
	}
	
	
    
    @Override
	public String toString() {
		return "Speaker {"
		    + "playing=" + playing
			+ "}";
	}
}

