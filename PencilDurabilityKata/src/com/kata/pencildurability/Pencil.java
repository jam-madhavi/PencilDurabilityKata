package com.kata.pencildurability;

import java.util.stream.Stream;


public class Pencil {

	private PointCounter pointDurability;
	
	private PointCounter pencilLength;
	
	public Pencil (PointCounter pointDurability, PointCounter pencilLength) {
		this.pointDurability = pointDurability;
		this.pencilLength = pencilLength;
	}
		
	public PointCounter getDurability() {
		return pointDurability;
	}

	public void setDurability(PointCounter durability) {
		this.pointDurability = durability;
	}
	
	public PointCounter getPencilLength() {
		return pencilLength;
	}

	public void setPencilLength(PointCounter pencilLength) {
		this.pencilLength = pencilLength;
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
			int pencilDurability = pointDurability.getPointCounter();
			int initialDurability = pointDurability.getPointCounter();
			if (pencilDurability != 0) {
				existingText.append(c);
			}
			pencilDurability = degradeDurability(pencilDurability, c);
			
			if (initialDurability == 0 && pencilDurability == 0) {
				existingText.append(" ");
			}
			pointDurability.setPointCounter(pencilDurability);
		});
		
		paper.setText(existingText);
	}
}
