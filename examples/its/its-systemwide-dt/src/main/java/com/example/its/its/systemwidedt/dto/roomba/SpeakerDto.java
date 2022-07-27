package com.example.its.its.systemwidedt.dto.roomba;


public class SpeakerDto {
	
	private boolean playing;
	
	public SpeakerDto() {
	}
	
	public SpeakerDto(boolean playing) {
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
		return "SpeakerDto {"
		    + "playing=" + playing
			+ "}";
	}
}
