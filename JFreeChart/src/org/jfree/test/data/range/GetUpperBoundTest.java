package org.jfree.test.data.range;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

class GetUpperBoundTest{

	@Test
	public void sameNumberUpper() {
		Range tempRange= new Range(1, 1);
        assertEquals( 1, tempRange.getLowerBound(), 
        		"Upper Bound  should be 1" );
    }
	
	@Test
	public void stressUpperTest() {
	    Range tempRange = new Range(0, Double.MAX_VALUE);
	    assertEquals(Double.MAX_VALUE, tempRange.getUpperBound(),
	            "Upper Bound should be Double.MAX_VALUE");
	}

	@Test
	public void posUpperTest() {
	    Range tempRange = new Range(3, 7);
	    assertEquals(7, tempRange.getUpperBound(),
	            "Upper Bound should be 7");
	}

	@Test
	public void negUpperTest() {
	    Range tempRange = new Range(-9, -3);
	    assertEquals(-3, tempRange.getUpperBound(),
	            "Upper Bound should be -3");
	}

	@Test
	public void zeroUpperTest() {
	    Range tempRange = new Range(-23, 0);
	    assertEquals(0, tempRange.getUpperBound(),
	            "Upper Bound should be 0");
	}
	
	@Test
	public void decUpperTest() {
	    Range tempRange = new Range(-3.46, 0.7);
	    assertEquals(0.7, tempRange.getUpperBound(),
	            "Upper Bound should be 0.7");
	}
}

