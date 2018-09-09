package com.kata.pencildurability;

public class Eraser {
	private PointCounter pointDurability;

	public Eraser (PointCounter durability) {
		this.pointDurability = durability;
	}
	public PointCounter getPointDurability() {
		return pointDurability;
	}

	public void setPointDurability(PointCounter pointDurability) {
		this.pointDurability = pointDurability;
	}
	
	public int decrementEraserDurability (int count) {
		// If erase string length is less than the eraser durability, update the counter
		if (count <= getPointDurability().getPointCounter()) {
			getPointDurability().setPointCounter(getPointDurability().getPointCounter()-count);
			return 1;
		}	else // else set the counter to zero
				getPointDurability().setPointCounter(0);
		return 0;
	}
	
	public void erase (Paper paper, String removeString) {
		int lastIndexRemoveString = paper.getText().lastIndexOf(removeString);
		int removeStringLen = removeString.length();
		int i=0;
		int intialEraserDurability = getPointDurability().getPointCounter();
		int endIndex = lastIndexRemoveString+removeStringLen;
		int startIndex = 0;
		
		StringBuilder text = paper.getText();
		StringBuilder spaces = new StringBuilder ("");
		
		// If erase durability is greater than string to remove, start Index is same as word start index in paper text.
		if (decrementEraserDurability(removeStringLen ) == 1 ) {
			startIndex =  lastIndexRemoveString;
		}
		else { // else move the start index as per available erase durability, so as to delete from the last 
			startIndex = lastIndexRemoveString + (removeStringLen-intialEraserDurability);
			removeStringLen = intialEraserDurability;
		}
		// number of spaces to replace the string to remove
		while (i < removeStringLen) {
			spaces.append(" ");
			i++;
		}
		text.replace(startIndex, endIndex, spaces.toString());
		paper.setText(text);
	}
	
}
