package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.jfree.data.Range;

class RangeScaleTest {

    @Test
    void testScalePositive() {
        Range base = new Range(2.0, 10.0);
        Range scaled = Range.scale(base, 2.0);
        assertAll("Scaling Results",
            () -> assertEquals(4.0, scaled.getLowerBound(), 0.00001),
            () -> assertEquals(20.0, scaled.getUpperBound(), 0.00001)
        );
    }

    @Test
    void testScaleNegativeFactorThrowsException() {
        Range base = new Range(2.0, 10.0);
        assertThrows(IllegalArgumentException.class, () -> Range.scale(base, -1.0));
    }
}
