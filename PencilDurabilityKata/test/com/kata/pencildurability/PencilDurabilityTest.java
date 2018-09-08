package com.kata.pencildurability;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PencilDurabilityTest {

	Pencil pencil;
	Paper paper;
	PointCounter intialPencilDurability;
	PointCounter initialPencilLength;
	PointCounter eraserDurability;
	PointCounter lowerIntialDurabilityPencil;
	Sharpner sharpner;
	Eraser eraser;
	static int HIGHER_DEGRAGE_POINT;
	static int LOWER_DEGRADE_POINT;
	static int HIGHER_LENGTH_VALUE;
	static int LOWER_LENGTH_VALUE;
	
	@Before 
	public void setUp () {
		HIGHER_DEGRAGE_POINT = 100;
		LOWER_DEGRADE_POINT = 12;
		intialPencilDurability = new PointCounter (HIGHER_DEGRAGE_POINT);
		
		HIGHER_LENGTH_VALUE = 5;
		LOWER_LENGTH_VALUE = 2;
		initialPencilLength = new PointCounter(LOWER_LENGTH_VALUE);
		
		pencil = new Pencil (intialPencilDurability, initialPencilLength);
		paper = new Paper ();
		eraserDurability = new PointCounter (LOWER_DEGRADE_POINT);
		lowerIntialDurabilityPencil = new PointCounter (LOWER_DEGRADE_POINT);
		sharpner = new Sharpner ();
		eraser = new Eraser ();
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
		pencil.setDurability(lowerIntialDurabilityPencil);
		pencil.write(paper, "She sells");
		assertEquals ("She sells", paper.getText().toString());
	}
	
	@Test
	public void test4PencilWritingTextMoreThanInitialDurabilityTenCharacters () {
		pencil.setDurability(lowerIntialDurabilityPencil);
		pencil.write(paper, "she sells");
		pencil.write(paper, "sea shells");
		assertEquals ("she sells sea s     ", paper.getText().toString());
	}
	
	@Test
	public void test5PencilWritingTextUpperCaseLettersDegradePencilByTwo () {
		pencil.setDurability(lowerIntialDurabilityPencil);
		pencil.write(paper, "She Sells");
		pencil.write(paper, "sea shells");
		assertEquals ("She Sells se        ", paper.getText().toString());
	}
	
	@Test
	public void test6PencilWritingTextSharpenPencil () {
		pencil.setDurability(lowerIntialDurabilityPencil);
		pencil.write(paper, "She Sells");
		sharpner.sharpen(pencil, LOWER_DEGRADE_POINT);
		pencil.write(paper, "sea shells");
		assertEquals ("She Sells sea shells", paper.getText().toString());
		
	}
	
	@Test
	public void test7CheckPencilInitalLength () {
		assertEquals("2", pencil.getPencilLength().toString());
	}
	
	@Test
	public void test8CheckPencilLengthAfterSharpen () {
		pencil.write(paper, "She Sells");
		sharpner.sharpen(pencil, LOWER_DEGRADE_POINT);
		pencil.write(paper, "sea shells");
		assertEquals ("She Sells sea shells", paper.getText().toString());
		assertEquals ("1", pencil.getPencilLength().toString());
	}
	
	@Test
	public void test9NoRestorePencilDurabilityWhenPencilLengthIsZero () {
		pencil.write(paper, "She Sells");
		sharpner.sharpen(pencil, LOWER_DEGRADE_POINT);
		pencil.write(paper, "sea shells");
		sharpner.sharpen(pencil, LOWER_DEGRADE_POINT);
		pencil.write(paper, "down by the sea shore");
		assertEquals ("She Sells sea shells down by the sea      ", paper.getText().toString());
		assertEquals ("0", pencil.getPencilLength().toString());
	}
	
	@Test
	public void test10EraseText () {
		String text = "How much wood would a woodchuck chuck if a woodchuck could chuck wood?";
		String text1 = "How much wood would a woodchuck chuck if a woodchuck could       wood?";
		pencil.write(paper, text);
		eraser.erase(paper, "chuck");
		assertEquals (text1, paper.getText().toString());
		eraser.erase(paper, "chuck");
		assertEquals ("How much wood would a woodchuck chuck if a wood      could       wood?",
						paper.getText().toString());
	}
}
