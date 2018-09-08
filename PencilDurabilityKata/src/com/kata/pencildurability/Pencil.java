package com.kata.pencildurability;

import java.util.stream.Stream;


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

	public static int degradeDurability (int pencilDurability, char c) {
		// If the character is uppercase degrade the pencil durability by 2
		if (Character.isUpperCase(c) && pencilDurability >= 2) {
			pencilDurability = pencilDurability-2;
		}
		// If it is not an Uppercase character or space degrade it by 1
		else if (!Character.isUpperCase(c) && c != ' ' && pencilDurability > 0){
			pencilDurability--;
		} 
		return pencilDurability;
	}
	
	public void write (Paper paper, String text) {
		StringBuilder existingText = paper.getText();
		
		if (existingText.length() != 0) {
			existingText.append(" ");
		}
		
		Stream<Character> characterStream = text.chars().mapToObj( c -> (char) c);
		
		characterStream.forEach(c -> {
			int pencilDurability = durability.getDurability();
			int initialDurability = durability.getDurability();
			if (pencilDurability != 0) {
				existingText.append(c);
			}
			pencilDurability = degradeDurability(pencilDurability, c);
			
			if (initialDurability == 0 && pencilDurability == 0) {
				existingText.append(" ");
			}
			durability.setDurability(pencilDurability);
		});
		

		
		paper.setText(existingText);
	}
}
