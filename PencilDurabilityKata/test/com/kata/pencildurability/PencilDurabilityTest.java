package com.kata.pencildurability;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PencilDurabilityTest {

	Pencil pencil;
	Paper paper;
	
	@Before 
	public void setUp () {
		pencil = new Pencil ();
		paper = new Paper ();
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

}
