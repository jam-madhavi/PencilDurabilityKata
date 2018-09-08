package com.kata.pencildurability;

public class Pencil {

	public void write (Paper paper, String text) {
		StringBuilder existingText = paper.getText();
		
		if (existingText.length() != 0)
			existingText.append(" ");
		existingText.append(text);
		
		paper.setText(existingText);
	}
}
