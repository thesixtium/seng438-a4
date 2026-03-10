package org.jfree.test.data.range;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

public class GetLengthTest{
	
	@Test
	public void sameNumberTest() {
		Range tempRange= new Range(1, 1);
        assertEquals( 0, tempRange.getLength(), 
        		"Length should be 0" );
    }
	
	@Test
	public void stressLengthTest() {
	    Range tempRange = new Range(0, Double.MAX_VALUE);
	    assertEquals(Double.MAX_VALUE, tempRange.getLength(),
	            "Length should be Double.MAX_VALUE");
	}
	
	@Test
	public void posRangeTest() {
		Range tempRange= new Range(1, 9);
        assertEquals( 8, tempRange.getLength(), 
        		"Length should be 8" );
    }
	
	@Test
	public void negRangeTest() {
		Range tempRange= new Range(-12, -9);
        assertEquals( 3, tempRange.getLength(), 
        		"Length should be 3" );
    }
	
	@Test
	public void crossOverRangeTest() {
		Range tempRange= new Range(-12, 9);
        assertEquals( 21, tempRange.getLength(), 
        		"Length should be 21" );
    }
}

