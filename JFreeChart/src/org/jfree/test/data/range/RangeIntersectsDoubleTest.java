package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.jfree.data.Range;

class RangeIntersectsDoubleTest {

    @Test
    void testIntersectsLowerBound() {
        Range range = new Range(5.0, 10.0);
        assertTrue(range.intersects(4.0, 6.0), "Should intersect at the lower bound");
    }

    @Test
    void testIntersectsUpperBound() {
        Range range = new Range(5.0, 10.0);
        assertTrue(range.intersects(9.0, 11.0), "Should intersect at the upper bound");
    }

    @Test
    void testDoesNotIntersectLower() {
        Range range = new Range(5.0, 10.0);
        assertFalse(range.intersects(1.0, 4.0), "Should not intersect below the range");
    }
}
