package com.example.its.its.systemwidedt.dto.its;

import com.example.its.its.systemwidedt.dto.roomba.RoombaDto;
import java.util.List;

public class ITSDto {
	
	private List<RoombaDto> roombas;
	
	public ITSDto() {
	}
	
	public ITSDto(List<RoombaDto> roombas) {
		this.roombas = roombas;
	}

	public List<RoombaDto> getRoombas() {
		return roombas;
	}
	
	public void setRoombas(List<RoombaDto> roombas) {
		this.roombas = roombas;
	}
	
	
	@Override
	public String toString() {
		return "ITSDto {"
		    + "roombas=" + roombas
			+ "}";
	}
}
