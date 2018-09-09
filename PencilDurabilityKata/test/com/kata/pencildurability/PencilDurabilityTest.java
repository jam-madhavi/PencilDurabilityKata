package com.kata.pencildurability;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PencilDurabilityTest {

	Pencil pencil;
	Paper paper;
	PointCounter intialPencilDurability;
	PointCounter initialPencilLength;
	PointCounter intialEraserDurability;
	PointCounter lowerIntialDurabilityPencil;
	PointCounter lowerIntialDurabilityEraser;
	Sharpner sharpner;
	Eraser eraser;
	static int HIGHER_DEGRAGE_POINT;
	static int LOWER_DEGRADE_POINT;
	static int HIGHER_LENGTH_VALUE;
	static int LOWER_LENGTH_VALUE;
	static int LOWER_DEGRAGE_POINT_ERASER;
	
	@Before 
	public void setUp () {
		HIGHER_DEGRAGE_POINT = 100;
		LOWER_DEGRADE_POINT = 12;
		intialPencilDurability = new PointCounter (HIGHER_DEGRAGE_POINT);
		
		HIGHER_LENGTH_VALUE = 5;
		LOWER_LENGTH_VALUE = 2;
		initialPencilLength = new PointCounter(LOWER_LENGTH_VALUE);

		lowerIntialDurabilityPencil = new PointCounter (LOWER_DEGRADE_POINT);
		pencil = new Pencil (intialPencilDurability, initialPencilLength);
		
		paper = new Paper ();
		sharpner = new Sharpner ();
		
		LOWER_DEGRAGE_POINT_ERASER = 3;
		intialEraserDurability = new PointCounter (LOWER_DEGRADE_POINT);
		lowerIntialDurabilityEraser = new PointCounter (LOWER_DEGRAGE_POINT_ERASER);
		eraser = new Eraser (intialEraserDurability);
	}
	
	@Test
	public void test1WriteTextOnPaperWithPencil() {
		pencil.writeText(paper, "She sells sea shells");
		assertEquals ("She sells sea shells", paper.getText().toString());
		
	}
	
	@Test
	public void test2AppendTextToExistingTextOnPaperWithPencil () {
		pencil.writeText(paper, "She sells sea shells");
		pencil.writeText(paper, "down by the sea shore");
		assertEquals ("She sells sea shells down by the sea shore", paper.getText().toString());
		
	}

	@Test
	public void test3PencilWritingTextLessThanInitialDurabilityTenCharacters () {
		pencil.setDurability(lowerIntialDurabilityPencil);
		pencil.writeText(paper, "She sells");
		assertEquals ("She sells", paper.getText().toString());
	}
	
	@Test
	public void test4PencilWritingTextMoreThanInitialDurabilityTenCharacters () {
		pencil.setDurability(lowerIntialDurabilityPencil);
		pencil.writeText(paper, "she sells");
		pencil.writeText(paper, "sea shells");
		assertEquals ("she sells sea s     ", paper.getText().toString());
	}
	
	@Test
	public void test5PencilWritingTextUpperCaseLettersDegradePencilByTwo () {
		pencil.setDurability(lowerIntialDurabilityPencil);
		pencil.writeText(paper, "She Sells");
		pencil.writeText(paper, "sea shells");
		assertEquals ("She Sells se        ", paper.getText().toString());
	}
	
	@Test
	public void test6PencilWritingTextSharpenPencil () {
		pencil.setDurability(lowerIntialDurabilityPencil);
		pencil.writeText(paper, "She Sells");
		sharpner.sharpen(pencil, LOWER_DEGRADE_POINT);
		pencil.writeText(paper, "sea shells");
		assertEquals ("She Sells sea shells", paper.getText().toString());
		
	}
	
	@Test
	public void test7CheckPencilInitalLength () {
		assertEquals("2", pencil.getPencilLength().toString());
	}
	
	@Test
	public void test8CheckPencilLengthAfterSharpen () {
		pencil.writeText(paper, "She Sells");
		sharpner.sharpen(pencil, LOWER_DEGRADE_POINT);
		pencil.writeText(paper, "sea shells");
		assertEquals ("She Sells sea shells", paper.getText().toString());
		assertEquals ("1", pencil.getPencilLength().toString());
	}
	
	@Test
	public void test9NoRestorePencilDurabilityWhenPencilLengthIsZero () {
		pencil.writeText(paper, "She Sells");
		sharpner.sharpen(pencil, LOWER_DEGRADE_POINT);
		pencil.writeText(paper, "sea shells");
		sharpner.sharpen(pencil, LOWER_DEGRADE_POINT);
		pencil.writeText(paper, "down by the sea shore");
		assertEquals ("She Sells sea shells down by the sea      ", paper.getText().toString());
		assertEquals ("0", pencil.getPencilLength().toString());
	}
	
	@Test
	public void test10EraseText () {
		String text = "How much wood would a woodchuck chuck if a woodchuck could chuck wood?";
		String text1 = "How much wood would a woodchuck chuck if a woodchuck could       wood?";
		pencil.writeText(paper, text);
		eraser.erase(paper, "chuck");
		assertEquals (text1, paper.getText().toString());
		eraser.erase(paper, "chuck");
		assertEquals ("How much wood would a woodchuck chuck if a wood      could       wood?",
						paper.getText().toString());
	}
	
	@Test
	public void test11EraserPointDurabilityDegrade () {
		pencil.writeText(paper, "Buffalo Bill");
		eraser.getPointDurability().setPointCounter(LOWER_DEGRAGE_POINT_ERASER);
		assertEquals("3", eraser.getPointDurability().toString());
		eraser.erase(paper, "Bill");
		assertEquals("Buffalo B   ", paper.getText().toString());
	}
	
	@Test
	public void test12EditTextExactWordFit ()  {
		pencil.writeText(paper, "An       a day keeps the doctor away");
		pencil.editText(paper, "onion");
		assertEquals("An onion a day keeps the doctor away", paper.getText().toString());
	}
	
	@Test
	public void test13EditTextLessCharacterWordToReplace ()  {
		pencil.writeText(paper, "An       a day keeps the doctor away");
		pencil.editText(paper, "ogen");
		assertEquals("An ogen  a day keeps the doctor away", paper.getText().toString());
	}
	
	@Test
	public void test13EditTextNineCharacterWordToReplace ()  {
		pencil.writeText(paper, "An       a day keeps the doctor away");
		pencil.editText(paper, "artichoke");
		assertEquals("An artich@k@ay keeps the doctor away", paper.getText().toString());
	}
	
	@Test
	public void test14EditTextTenCharacterWordToReplace ()  {
		pencil.writeText(paper, "An       a day keeps the doctor away");
		pencil.editText(paper, "watermelon");
		assertEquals("An waterm@l@@y keeps the doctor away", paper.getText().toString());
	}
}
