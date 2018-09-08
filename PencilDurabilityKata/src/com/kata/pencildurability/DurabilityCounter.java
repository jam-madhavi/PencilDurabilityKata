package com.kata.pencildurability;

public class DurabilityCounter {
	private int durability;
	
	DurabilityCounter (int durability) {
		this.durability = durability;
	}
	
	public void decrementDurability () {
		durability--;
	}
	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}
	
	public String toString(){
		return Integer.toString(durability);
	}
	
}
