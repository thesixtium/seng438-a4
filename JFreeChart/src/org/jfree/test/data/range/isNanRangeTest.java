package org.jfree.test.data.range;
import org.jfree.data.Range;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class isNanRangeTest {

  	@Test 
	void testIsNaNRangeBothNaN() {
	    Range r = new Range(Double.NaN, Double.NaN);
	    assertTrue(r.isNaNRange());
	}
	@Test
	void testIsNaNRangeOneNaN() {
	    Range r = new Range(Double.NaN, 5.0);
	    assertFalse(r.isNaNRange());
	}
	@Test
	void testIsNaNRangeNoNaN() {
	    Range r = new Range(1.0, 5.0);
	    assertFalse(r.isNaNRange());
	}
  
}
