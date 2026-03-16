package org.jfree.test.data.range;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExpandToIncludeTest {	
	private Range oldRange;
	
	@BeforeEach
	void setUp() {
		oldRange = new Range(5, 10);
	}

	@Test
	void RangeIsNull_ShouldReturnValueNewRange() {
		double value = 3;
		Range newRange = Range.expandToInclude(null, value);
		
		assertEquals(new Range(value, value), newRange);
		assertEquals(value, newRange.getUpperBound());
		assertEquals(value, newRange.getLowerBound());
		
	}
	
	@Test
	void valueIsNotBiggerOrSmaller() {
		double value = 7;
		assertEquals(new Range(5, 10), Range.expandToInclude(oldRange, value));
		assertEquals(5, oldRange.getLowerBound());
		assertEquals(10, oldRange.getUpperBound());
	}

	@Test
	void valueLessThanLowerBound() {
		double value = 2;
		Range newRange = Range.expandToInclude(oldRange, value);
		assertEquals(new Range(2, 10), newRange);
		assertEquals(value, newRange.getLowerBound());
		assertEquals(10, newRange.getUpperBound());
	}
	
	@Test
	void valueLargerThanUpperBound() {
		double value = 15;
		Range newRange = Range.expandToInclude(oldRange, value);
		assertEquals(new Range(5, 15), newRange);
		assertEquals(5, newRange.getLowerBound());
		assertEquals(value, newRange.getUpperBound());
	}
	
	@Test
	void valueRightAboveUpperBound() {
		double value = 11;
		Range newRange = Range.expandToInclude(oldRange, value);
		assertEquals(new Range(5, 11), newRange);
		assertEquals(5, newRange.getLowerBound());
		assertEquals(value, newRange.getUpperBound());
	}
	
	@Test
	void valueRightUnderLowerBound() {
		double value = 4;
		Range newRange = Range.expandToInclude(oldRange, value);
		assertEquals(new Range(4, 10), newRange);
		assertEquals(value, newRange.getLowerBound());
		assertEquals(10, newRange.getUpperBound());
	}

	
	@Test
	void valueEqualsLowerBound() {
	    double value = 5;
	    Range newRange = Range.expandToInclude(oldRange, value);
	    assertEquals(new Range(5, 10), newRange);
	    assertEquals(5, newRange.getLowerBound());
		assertEquals(10, newRange.getUpperBound());
	}

	@Test
	void valueEqualsUpperBound() {
	    double value = 10;
	    Range newRange = Range.expandToInclude(oldRange, value);
	    assertEquals(new Range(5, 10), newRange);
	    assertEquals(5, oldRange.getLowerBound());
		assertEquals(10, oldRange.getUpperBound());
	}
	
	@Test
	void valueIsNegative() {
		double value = -5;
		Range newRange = Range.expandToInclude(oldRange, value);
		assertEquals(new Range(-5, 10), newRange);
		assertEquals(value, newRange.getLowerBound());
		assertEquals(10, newRange.getUpperBound());	
	}

}
