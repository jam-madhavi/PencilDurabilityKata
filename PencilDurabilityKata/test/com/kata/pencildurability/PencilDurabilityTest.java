package com.kata.pencildurability;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PencilDurabilityTest {

	Pencil pencil;
	Paper paper;
	DurabilityCounter intialPencilDurability;
	DurabilityCounter eraserDurability;
	DurabilityCounter lowerDurabilityPencil;
	@Before 
	public void setUp () {
		intialPencilDurability = new DurabilityCounter (50);
		pencil = new Pencil (intialPencilDurability);
		paper = new Paper ();
		eraserDurability = new DurabilityCounter (5);
		lowerDurabilityPencil = new DurabilityCounter (12);
	}
	
	@Test
	public void test1WriteTextOnPaperWithPencil() {
		pencil.write(paper, "She sells sea shells");
		assertEquals ("She sells sea shells", paper.getText().toString());
		
	}
	
	@Test
	public void test2AppendTextToExistingTextOnPaperWithPencil () {
		pencil.write(paper, "She sells sea shells");
		pencil.write(paper, "down by the sea shore");
		assertEquals ("She sells sea shells down by the sea shore", paper.getText().toString());
		
	}

	@Test
	public void test3PencilWritingTextLessThanInitialDurabilityTenCharacters () {
		pencil.setDurability(lowerDurabilityPencil);
		pencil.write(paper, "She sells");
		assertEquals ("She sells", paper.getText().toString());
	}
	
	@Test
	public void test4PencilWritingTextMoreThanInitialDurabilityTenCharacters () {
		pencil.setDurability(lowerDurabilityPencil);
		pencil.write(paper, "she sells");
		pencil.write(paper, "sea shells");
		assertEquals ("she sells sea s     ", paper.getText().toString());
	}
	
	@Test
	public void test5PencilWritingTextUpperCaseLettersDegradePencilByTwo () {
		pencil.setDurability(lowerDurabilityPencil);
		pencil.write(paper, "She Sells");
		pencil.write(paper, "sea shells");
		assertEquals ("She Sells se        ", paper.getText().toString());
	}
}
