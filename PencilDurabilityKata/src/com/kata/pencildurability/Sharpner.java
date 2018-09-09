package com.kata.pencildurability;

public class Sharpner {

	public void sharpen (Pencil pencil, int pointDurability) {
		// sharpen pencil and decrement pencillength
		if (pencil.getPencilLength().getPointCounter() >= 1) {
			pencil.getPencilLength().decrementPointCounter();
			pencil.getDurability().setPointCounter(pointDurability);
		} else {
			System.out.println("Pencil length is zero, cannot restore its point durability.");
		}
	}
}
