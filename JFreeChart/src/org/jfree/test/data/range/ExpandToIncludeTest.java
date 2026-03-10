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
		Range oldRange = null;
		double value = 2.0;
		Range newRange = new Range(2.0, 2.0);
		assertEquals(newRange, Range.expandToInclude(oldRange, value));
	}

	@Test
	void valueLessThanLowerBound() {
		double value = 3.0;
		Range newRange = new Range(3.0, 10);
		assertEquals(newRange, Range.expandToInclude(oldRange, value));
	}
	
	@Test
	void valueLargerThanUpperBound() {
		double value = 11;
		Range newRange = new Range(5, 11);
		assertEquals(newRange, Range.expandToInclude(oldRange, value));
	}
	
	@Test
	void valueIsNotBiggerOrSmaller() {
		double value = 7;
		Range newRange = new Range(5, 10);
		assertEquals(newRange, Range.expandToInclude(oldRange, value));
	}

}
