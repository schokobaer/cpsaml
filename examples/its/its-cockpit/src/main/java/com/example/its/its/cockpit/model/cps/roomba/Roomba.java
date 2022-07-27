package com.example.its.its.cockpit.model.cps.roomba;

import com.example.its.its.cockpit.annotation.CpsDevice;
import com.example.its.its.cockpit.model.monitor.Cps;
import at.ac.tuwien.big.cps.annotationprocessor.annotation.Id;
import java.util.List;

@CpsDevice(Cps.ROOMBA)
public class Roomba {
	
	@Id
	private String id;
	private List<Task> tasks;
	private String state;
	private Wheels wheels;
	private Speaker speaker;
	private Navigator navigator;
	private Battery battery;
	private Bumper bumper;
	
	public Roomba() {
	}
	
	public Roomba(List<Task> tasks, String state, Wheels wheels, Speaker speaker, Navigator navigator, Battery battery, Bumper bumper) {
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
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public Wheels getWheels() {
		return wheels;
	}
	
	public void setWheels(Wheels wheels) {
		this.wheels = wheels;
	}
	
	public Speaker getSpeaker() {
		return speaker;
	}
	
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	
	public Navigator getNavigator() {
		return navigator;
	}
	
	public void setNavigator(Navigator navigator) {
		this.navigator = navigator;
	}
	
	public Battery getBattery() {
		return battery;
	}
	
	public void setBattery(Battery battery) {
		this.battery = battery;
	}
	
	public Bumper getBumper() {
		return bumper;
	}
	
	public void setBumper(Bumper bumper) {
		this.bumper = bumper;
	}
	
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Roomba that = (Roomba) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
    
    @Override
	public String toString() {
		return "Roomba {"
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

