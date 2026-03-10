package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.jfree.data.Range;

class RangeIntersectsRangeTest {

    @Test
    void testIntersectsRangeObject() {
        Range base = new Range(10.0, 20.0);
        Range other = new Range(15.0, 25.0);
        assertTrue(base.intersects(other), "Range objects should overlap");
    }

    @Test
    void testDoesNotIntersectRangeObject() {
        Range base = new Range(10.0, 20.0);
        Range other = new Range(21.0, 30.0);
        assertFalse(base.intersects(other), "Range objects should not overlap");
    }
}
