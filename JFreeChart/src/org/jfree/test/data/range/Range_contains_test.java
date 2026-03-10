package org.jfree.test.data.range;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Range_contains_test {

    private Range range;

    @BeforeEach
    void setUp() {
        range = new Range(0.0, 10.0);
    }

    @Test
    void contains_ValueInsideRange_ShouldReturnTrue() {
        assertTrue(range.contains(5.0));
    }

    @Test
    void contains_ValueAtLowerBound_ShouldReturnTrue() {
        assertTrue(range.contains(0.0));
    }

    @Test
    void contains_ValueAtUpperBound_ShouldReturnTrue() {
        assertTrue(range.contains(10.0));
    }

    @Test
    void contains_ValueBelowLowerBound_ShouldReturnFalse() {
        assertFalse(range.contains(-0.1));
    }

    @Test
    void contains_ValueAboveUpperBound_ShouldReturnFalse() {
        assertFalse(range.contains(10.1));
    }
}
