package com.kata.pencildurability; 

public class Paper {
	private StringBuilder text = new StringBuilder ();

	public StringBuilder getText() {
		return text;
	}

	public void setText(StringBuilder text) {
		this.text = text;
	}
	
}
