package com.example.its.its.systemwidedt.dto.roomba;


public class TaskDto {
	
	private double angle;
	private double time;
	private int speed;
	
	public TaskDto() {
	}
	
	public TaskDto(double angle, double time, int speed) {
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
		return "TaskDto {"
		    + "angle=" + angle + ", "
		    + "time=" + time + ", "
		    + "speed=" + speed
			+ "}";
	}
}
