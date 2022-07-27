package com.example.its.its.systemwidedt.dto.roomba;


public class SpeedDto {
	
	private int left;
	private int right;
	
	public SpeedDto() {
	}
	
	public SpeedDto(int left, int right) {
		this.left = left;
		this.right = right;
	}

	public int getLeft() {
		return left;
	}
	
	public void setLeft(int left) {
		this.left = left;
	}
	
	public int getRight() {
		return right;
	}
	
	public void setRight(int right) {
		this.right = right;
	}
	
	
	@Override
	public String toString() {
		return "SpeedDto {"
		    + "left=" + left + ", "
		    + "right=" + right
			+ "}";
	}
}
