package com.kata.pencildurability;

public class PointCounter {
	private int point;
	
	PointCounter (int point) {
		this.point = point;
	}
	
	public void decrementPointCounter () {
		point--;
	}
	public int getPointCounter() {
		return point;
	}

	public void setPointCounter(int point) {
		this.point = point;
	}
	
	public String toString(){
		return Integer.toString(point);
	}
	
}
