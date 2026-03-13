/**
 * 
 */
package org.jfree.test.data.range;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

/**
 * 
 */
class GetLowerBoundTest{

	@Test
	public void sameNumberLower() {
		Range tempRange= new Range(1, 1);
        assertEquals( 1, tempRange.getLowerBound(), 
        		"Lower Bound  should be 1" );
    }
	
	@Test
	public void stressLowerTest() {
	    Range tempRange = new Range(-Double.MAX_VALUE, 0);
	    assertEquals(-Double.MAX_VALUE, tempRange.getLowerBound(),
	            "Lower Bound should be -Double.MAX_VALUE");
	}

	@Test
	public void posLowerTest() {
	    Range tempRange = new Range(3, 7);
	    assertEquals(3, tempRange.getLowerBound(),
	            "Lower Bound should be 3");
	}

	@Test
	public void negLowerTest() {
	    Range tempRange = new Range(-2, 9);
	    assertEquals(-2, tempRange.getLowerBound(),
	            "Lower Bound should be -2");
	}

	@Test
	public void zeroLowerTest() {
	    Range tempRange = new Range(0, 56);
	    assertEquals(0, tempRange.getLowerBound(),
	            "Lower Bound should be 0");
	}
	
	@Test
	public void decLowerTest() {
	    Range tempRange = new Range(-3.46, 0.7);
	    assertEquals(-3.46, tempRange.getLowerBound(),
	            "Lower Bound should be -3.46");
	}
}

