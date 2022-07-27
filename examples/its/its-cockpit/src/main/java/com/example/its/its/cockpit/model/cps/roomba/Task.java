package com.example.its.its.cockpit.model.cps.roomba;

import at.ac.tuwien.big.cps.annotationprocessor.annotation.Bounds;

public class Task {
	
	@Bounds(min = -360.0, max = 360.0) 
	private double angle;
	@Bounds(min = 0.0, max = 255.0) 
	private double time;
	@Bounds(min = -200.0, max = 200.0) 
	private int speed;
	
	public Task() {
	}
	
	public Task(double angle, double time, int speed) {
		this.angle = angle;
		this.time = time;
		this.speed = speed;
	}

	public double getAngle() {
		return angle;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public double getTime() {
		return time;
	}
	
	public void setTime(double time) {
		this.time = time;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	
    
    @Override
	public String toString() {
		return "Task {"
		    + "angle=" + angle + ", "
		    + "time=" + time + ", "
		    + "speed=" + speed
			+ "}";
	}
}

