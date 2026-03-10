package org.jfree.test.data.range;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

public class toStringTest {

	@Test
	void testToString() {
	    Range range = new Range(5, 10);
	    String expected = "Range[5.0,10.0]";
	    assertEquals(expected, range.toString());
	}

}
