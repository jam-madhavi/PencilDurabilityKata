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
		if (count <= getPointDurability().getPointCounter()) {
			getPointDurability().setPointCounter(getPointDurability().getPointCounter()-count);
			return 1;
		}	else
				getPointDurability().setPointCounter(0);
		return 0;
	}
	
	public void erase (Paper paper, String removeString) {
		int lastIndexRemoveString = paper.getText().lastIndexOf(removeString);
		StringBuilder text = paper.getText();
		StringBuilder spaces = new StringBuilder ("");
		int removeStringLen = removeString.length();
		int i=0;
		int intialEraserDurability = getPointDurability().getPointCounter();
		int endIndex = lastIndexRemoveString+removeStringLen;
		int startIndex = 0;
		
		if (decrementEraserDurability(removeStringLen ) == 1 ) {
			startIndex =  lastIndexRemoveString;
		}
		else {
			startIndex = lastIndexRemoveString + (removeStringLen-intialEraserDurability);
			removeStringLen = intialEraserDurability;
		}
		
		while (i < removeStringLen) {
			spaces.append(" ");
			i++;
		}
		
		
		text.replace(startIndex, endIndex, spaces.toString());
		paper.setText(text);
	}
	
}
