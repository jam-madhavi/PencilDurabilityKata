package com.kata.pencildurability;

public class Eraser {
	private PointCounter pointDurability;

	public PointCounter getPointDurability() {
		return pointDurability;
	}

	public void setPointDurability(PointCounter pointDurability) {
		this.pointDurability = pointDurability;
	}
	
	public void erase (Paper paper, String removeString) {
		int lastIndexRemoveString = paper.getText().lastIndexOf(removeString);
		StringBuilder text = paper.getText();
		StringBuilder spaces = new StringBuilder ("");
		
		int i=0;
		while (i < removeString.length()) {
			spaces.append(" ");
			i++;
		}
		text.replace(lastIndexRemoveString, lastIndexRemoveString+removeString.length(), spaces.toString());
		paper.setText(text);
	}
	
}
