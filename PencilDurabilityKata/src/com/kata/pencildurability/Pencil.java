package com.kata.pencildurability;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class Pencil {

	private PointCounter pointDurability;
	
	private PointCounter pencilLength;
	
	private static final String REGEX ="\\s{2,}";
	
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
	
	public void writeText (Paper paper, String text) {
		StringBuilder existingText = paper.getText();
		
		// Adding space if appending text to existing text
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
	
	
	public void editText (Paper paper, String replaceStr) {
		StringBuilder existingText = paper.getText();
		
		int startIndex = 0;
		int endIndex = 0;
		int diff = 0;
		int counter = 0;
		
		Pattern pattern = Pattern.compile(REGEX);
		Matcher matcher = pattern.matcher(existingText);
		StringBuilder existingTextSecondPart = new StringBuilder ("");
		
		// Finding the first 2 or more consecutive whitespaces to fill
		if (matcher.find()) {
			startIndex = matcher.start();
			endIndex = matcher.end();
		}
		
		// # of blank spaces available to write
		diff = endIndex - (startIndex +1); 
		
		// If replace string length is smaller than space available, replace it directly
		if (replaceStr.length() <= diff) {
			existingText.replace(startIndex+1, startIndex+1+replaceStr.length(), replaceStr);
		}
		// If replace string length is greater than blank space available
		else {
			// First part of replace string that can be replaced without shift
			String firstPart = replaceStr.substring(0, endIndex - (startIndex+1));
			
			// Part of string that requires shift
			String secondPart = replaceStr.substring(endIndex-(startIndex+1));
			String replaceStrRemainingPart = existingText.substring(endIndex, 
												endIndex + secondPart.length());
			// Replace the first part
			existingText.replace(startIndex+1, endIndex, firstPart);
			
			// colliding character string
			for (char ch : replaceStrRemainingPart.toCharArray()) {
				if (ch != ' ')
					existingTextSecondPart.append("@");
				else
					existingTextSecondPart.append(secondPart.charAt(counter));
				counter++; 
			}
			
			// Replace the colliding string
			existingText.replace(endIndex, 
									endIndex + secondPart.length(), 
									existingTextSecondPart.toString());
		}
		paper.setText(existingText);
	}
}
