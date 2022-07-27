package com.example.its.its.cockpit.model.cps.roomba;

import at.ac.tuwien.big.cps.annotationprocessor.annotation.Bounds;

public class Speed {
	
	@Bounds(min = -200.0, max = 200.0) 
	private int left;
	@Bounds(min = -200.0, max = 200.0) 
	private int right;
	
	public Speed() {
	}
	
	public Speed(int left, int right) {
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
		return "Speed {"
		    + "left=" + left + ", "
		    + "right=" + right
			+ "}";
	}
}

