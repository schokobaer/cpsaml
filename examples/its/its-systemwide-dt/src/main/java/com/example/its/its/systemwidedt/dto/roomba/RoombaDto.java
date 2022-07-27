package com.example.its.its.systemwidedt.dto.roomba;

import java.util.List;
import com.example.its.its.systemwidedt.dto.roomba.TaskDto;

public class RoombaDto {
	
	private String id;
	private List<TaskDto> tasks;
	private String state;
	private WheelsDto wheels;
	private SpeakerDto speaker;
	private NavigatorDto navigator;
	private BatteryDto battery;
	private BumperDto bumper;
	
	public RoombaDto() {
	}
	
	public RoombaDto(List<TaskDto> tasks, String state, WheelsDto wheels, SpeakerDto speaker, NavigatorDto navigator, BatteryDto battery, BumperDto bumper) {
		this.tasks = tasks;
		this.state = state;
		this.wheels = wheels;
		this.speaker = speaker;
		this.navigator = navigator;
		this.battery = battery;
		this.bumper = bumper;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public List<TaskDto> getTasks() {
		return tasks;
	}
	
	public void setTasks(List<TaskDto> tasks) {
		this.tasks = tasks;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public WheelsDto getWheels() {
		return wheels;
	}
	
	public void setWheels(WheelsDto wheels) {
		this.wheels = wheels;
	}
	
	public SpeakerDto getSpeaker() {
		return speaker;
	}
	
	public void setSpeaker(SpeakerDto speaker) {
		this.speaker = speaker;
	}
	
	public NavigatorDto getNavigator() {
		return navigator;
	}
	
	public void setNavigator(NavigatorDto navigator) {
		this.navigator = navigator;
	}
	
	public BatteryDto getBattery() {
		return battery;
	}
	
	public void setBattery(BatteryDto battery) {
		this.battery = battery;
	}
	
	public BumperDto getBumper() {
		return bumper;
	}
	
	public void setBumper(BumperDto bumper) {
		this.bumper = bumper;
	}
	
	
	@Override
	public String toString() {
		return "RoombaDto {"
		    + "tasks=" + tasks + ", "
		    + "state=" + state + ", "
		    + "wheels=" + wheels + ", "
		    + "speaker=" + speaker + ", "
		    + "navigator=" + navigator + ", "
		    + "battery=" + battery + ", "
		    + "bumper=" + bumper
			+ "}";
	}
}
