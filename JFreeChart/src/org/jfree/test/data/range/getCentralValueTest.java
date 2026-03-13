package org.jfree.test.data.range;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

class getCentralValueTest{

	@Test
	public void sameNumberCenter() {
		Range tempRange= new Range(1, 1);
        assertEquals( 1, tempRange.getCentralValue(), 
        		"Central Value should be 1" );
    }
	
	@Test
	public void posRangeCenter() {
		Range tempRange= new Range(1, 7);
        assertEquals(4, tempRange.getCentralValue(), 
        		"Central Value should be 4" );
    }
	
	@Test
	public void negRangeCenter() {
	    Range tempRange = new Range(-7, -3);
	    assertEquals(-5, tempRange.getCentralValue(),
	            "Central Value should be -5");
	}

	@Test
	public void evenRangeCenter() {
	    Range tempRange = new Range(2, 4);
	    assertEquals(3, tempRange.getCentralValue(),
	            "Central Value should be 3");
	}

	@Test
	public void oddRangeCenter() {
	    Range tempRange = new Range(-3, -1);
	    assertEquals(-2, tempRange.getCentralValue(),
	            "Central Value should be -2");
	}

	@Test
	public void stressRangeCenter() {
	    Range tempRange = new Range(0, Double.MAX_VALUE);
	    assertEquals(Double.MAX_VALUE / 2, tempRange.getCentralValue(),
	            "Central Value should be half of Double.MAX_VALUE");
	}
	
	@Test
	public void decimalRangeCenter() {
	    Range tempRange = new Range(0.1, 0.3);
	    assertEquals(0.2, tempRange.getCentralValue(), 1e-9,
	            "Central value should be 0.2");
	}
}


