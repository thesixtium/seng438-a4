package org.jfree.test.data.range;

import static org.junit.jupiter.api.Assertions.*;
import org.jfree.data.Range;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
/**
 * Upper and Lower bound denoted by (x,y)
 * fail("Not yet implemented");
 */
public class RangeConstrainTest{
	static private Range exampleRange;
	
	@BeforeAll
	static void setUp() {
        exampleRange = new Range(5, 13);
    }
	
	@Test
	void constrainAtLowerBound() {
		assertEquals(5.0, exampleRange.constrain(5), 1e-9,
                "The return of constrain should be 5");
	}
	
	@Test
	void constrainAtUpperBound() {
		assertEquals(13.0, exampleRange.constrain(13), 1e-9,
                "The return of constrain should be 13");
	}
	
	@Test
	void constrainInsideBound() {
		assertEquals(7, exampleRange.constrain(7), 1e-9,
                "The return of constrain should be 7 as it is within Range");
	}
	
	@Test
	void constrainNegativeValueonPositiveBound() {
		assertEquals(5, exampleRange.constrain(-109), 1e-9,
                "The return of constrain should be 5 as that is the closest bound");
	}
	
	@Test
	void constrainPositiveValueonNegativeBound() {
		Range tempRange= new Range(-12,-5);
		assertEquals(-5, tempRange.constrain(56), 1e-9,
                "The return of constrain should be -5 as that is the closest bound");
	}
	
	@Test
	void constrainStressTest(){
		Range tempRange= new Range(-12,-5);
		assertEquals(-12, tempRange.constrain(-Double.MAX_VALUE), 1e-9,
                "The return of constrain should be -12 as that is the closest bound");
	}
	
}
