package org.jfree.test.data.range;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.jfree.data.Range;

class RangeIntersectsDoubleTest {

    @Test
    void testIntersectsLowerBound() {
        Range range = new Range(5.0, 10.0);
        assertTrue(range.intersects(4.0, 6.0));
    }
    @Test
    void testIntersectsUpperBound() {
        Range range = new Range(5.0, 10.0);
        assertTrue(range.intersects(9.0, 11.0));
    }
    @Test
    void testDoesNotIntersectLower() {
        Range range = new Range(5.0, 10.0);
        assertFalse(range.intersects(1.0, 4.0));
    }

    @Test
    void testIntersects_b0ExactlyAtLower_EntersIfBranch() {
        Range range = new Range(5.0, 10.0);
        assertTrue(range.intersects(5.0, 6.0));
    }

    @Test
    void testIntersects_b0JustBelowLower_b1JustAboveLower() {
        Range range = new Range(5.0, 10.0);
        assertTrue(range.intersects(4.9, 5.1));
    }

    @Test
    void testIntersects_repeatedCallDoesNotMutateLower() {
        Range range = new Range(5.0, 10.0);
        range.intersects(5.0, 6.0);
        assertTrue(range.intersects(5.0, 6.0));
    }

    @Test
    void testIntersects_b0AtLowerRepeated_PreDecrementNotVisible() {
        Range range = new Range(5.0, 10.0);
        range.intersects(4.0, 5.5);
        assertFalse(range.intersects(1.0, 4.9)); // would pass if lower silently shifted down
    }

    @Test
    void testIntersects_b0BelowLower_b1ExactlyAtLower_ShouldNotIntersect() {
        Range range = new Range(5.0, 10.0);
        assertFalse(range.intersects(3.0, 5.0));
    }

    @Test
    void testIntersects_b0BelowLower_b1SlightlyAboveLower_ShouldIntersect() {
        Range range = new Range(5.0, 10.0);
        assertTrue(range.intersects(3.0, 5.1));
    }

    @Test
    void testIntersects_b1EqualToLower_ShouldReturnFalse() {
        Range range = new Range(5.0, 10.0);
        assertFalse(range.intersects(2.0, 5.0));
    }

    @Test
    void testIntersects_line180_repeatedCallDetectsFieldMutation() {
        Range range = new Range(5.0, 10.0);
        assertFalse(range.intersects(1.0, 5.0)); // b1 == lower → false
        assertFalse(range.intersects(1.0, 5.0)); // still false if lower unchanged
    }

    @Test
    void testIntersects_line180_preDecrementFieldDetected() {
        Range range = new Range(5.0, 10.0);
        range.intersects(3.0, 5.0);
        assertFalse(range.intersects(3.0, 5.0)); // lower must still be 5.0
    }

    @Test
    void testIntersects_b0ExactlyAtUpper_ShouldNotIntersect() {
        Range range = new Range(5.0, 10.0);
        assertFalse(range.intersects(10.0, 15.0));
    }

    @Test
    void testIntersects_invertedInterval_ShouldNotIntersect() {
        Range range = new Range(5.0, 10.0);
        assertFalse(range.intersects(8.0, 7.0)); // b1 < b0, invalid range
    }

    @Test
    void testIntersects_asymmetricValues_NegationDetected() {
        Range range = new Range(2.0, 8.0);
        assertTrue(range.intersects(3.0, 9.0));  // -3.0 < 2.0 is still true, but
        assertFalse(range.intersects(9.0, 11.0)); // -9.0 < 8.0 would wrongly be true
    }

    @Test
    void testIntersects_b1EqualsB0_InsideRange_ShouldIntersect() {
        Range range = new Range(5.0, 10.0);
        assertTrue(range.intersects(7.0, 7.0)); // point interval inside range
    }

    @Test
    void testIntersects_b0AtUpperBoundary_StrictlyLess() {
        Range range = new Range(5.0, 10.0);
        assertFalse(range.intersects(10.0, 12.0));
    }

    @Test
    void testIntersects_b1ExactlyEqualsB0_BoundaryCheck() {
        Range range = new Range(5.0, 10.0);
        assertTrue(range.intersects(6.0, 6.0)); // b1 == b0, >= allows this, > would not
    }

    @Test
    void testIntersects_b0StrictlyLessThanUpper_NotEqual() {
        Range range = new Range(5.0, 10.0);
        assertTrue(range.intersects(7.0, 11.0)); // b0=7 < 10, != 10
    }

    @Test
    void testIntersects_line183_repeatedCallsDetectUpperMutation() {
        Range range = new Range(5.0, 10.0);
        assertFalse(range.intersects(10.0, 15.0)); // b0 == upper → false
        assertFalse(range.intersects(10.0, 15.0)); // upper must be unchanged
    }

    @Test
    void testIntersects_line183_repeatedCallsDetectLowerMutation() {
        Range range = new Range(5.0, 10.0);
        assertTrue(range.intersects(6.0, 9.0));
        assertTrue(range.intersects(6.0, 9.0)); // result must be stable
    }

    @Test
    void testIntersects_b0JustBelowUpper_ThenAtUpper() {
        Range range = new Range(5.0, 10.0);
        assertTrue(range.intersects(9.9, 12.0));  // b0 < upper → true
        assertFalse(range.intersects(10.0, 12.0)); // b0 == upper → false (catches upper++)
    }
}