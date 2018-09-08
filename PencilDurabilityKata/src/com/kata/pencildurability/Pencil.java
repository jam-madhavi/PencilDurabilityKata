package com.kata.pencildurability;

public class Pencil {

	private DurabilityCounter durability;
	
	public Pencil (DurabilityCounter durability) {
		this.durability = durability;
	}
	public DurabilityCounter getDurability() {
		return durability;
	}


	public void setDurability(DurabilityCounter durability) {
		this.durability = durability;
	}


	public void write (Paper paper, String text) {
		int pencilDurability = durability.getDurability();
		int textCharLength = text.length();
		StringBuilder existingText = paper.getText();
		
		if (existingText.length() != 0) {
			existingText.append(" ");
			pencilDurability--;
		}
		if (textCharLength <= pencilDurability) {
			existingText.append(text);
			pencilDurability -= textCharLength;
			durability.setDurability(pencilDurability);
			
		} else {
			int diff = textCharLength - pencilDurability;
			existingText.append(text.substring(0, pencilDurability));
			while (diff >= 0) {
				existingText.append(" ");
				diff--;
			}
		}
		paper.setText(existingText);
	}
}
