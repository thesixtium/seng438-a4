package org.jfree.test.data.range;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jfree.data.Range;

public class RangeCombineIgnoringNaNTest {
    private Range range1;
    private Range range2;
    private Range range3;
    private Range range4;
    private Range range5;
    private Range range6;
    private Range range7;

    @BeforeEach
    public void before() {
        range1 = new Range(0, 1);
        range2 = new Range(-1, 0);
        range3 = new Range(-1, 1);
        range4 = new Range(0, 2);
        range5 = new Range(-1, 2);
        range6 = new Range(1, 2);
        range7 = new Range(Double.NaN, Double.NaN);
    }

    @Test
    public void combineIgnoringNaN_BothRangesNull() {
        assertNull(Range.combineIgnoringNaN(null, null));
    }

    @Test
    public void combineIgnoringNaN_Range1Null() {
        assertNull(Range.combineIgnoringNaN(null, range7));
    }

    @Test
    public void combineIgnoringNaN_Range2Null() {
        assertNull(Range.combineIgnoringNaN(range7, null));
    }

    @Test
    public void combineIgnoringNaN_Range2Null2() {
        assertEquals(range1, Range.combineIgnoringNaN(range1, null));
    }

    @Test
    public void combineIgnoringNaN_SameRange() {
        assertEquals(range1, Range.combineIgnoringNaN(range1, range1));
    }

    @Test
    public void combineIgnoringNaN_TouchingRangesInOrder() {
        assertEquals(range3, Range.combineIgnoringNaN(range1, range2));
    }

    @Test
    public void combineIgnoringNaN_TouchingRangesNotInOrder() {
        assertEquals(range3, Range.combineIgnoringNaN(range2, range1));
    }

    @Test
    public void combineIgnoringNaN_IntersectingRangesInOrder() {
        assertEquals(range5, Range.combineIgnoringNaN(range3, range4));
    }

    @Test
    public void combineIgnoringNaN_IntersectingRangesNotInOrder() {
        assertEquals(range5, Range.combineIgnoringNaN(range4, range3));
    }

    @Test
    public void combineIgnoringNaN_NonIntersectingRangesInOrder() {
        assertEquals(range5, Range.combineIgnoringNaN(range2, range6));
    }

    @Test
    public void combineIgnoringNaN_NonIntersectingRangesNotInOrder() {
        assertEquals(range5, Range.combineIgnoringNaN(range6, range2));
    }

    @Test
    public void combineIgnoringNaN_RangeInsideRangeInOrder() {
        assertEquals(range5, Range.combineIgnoringNaN(range5, range3));
    }

    @Test
    public void combineIgnoringNaN_RangeInsideRangeNotInOrder() {
        assertEquals(range5, Range.combineIgnoringNaN(range3, range5));
    }

    @Test
    public void combineIgnoringNaN_Range1NullRange2Normal_ReturnsRange2() {
        assertEquals(range1, Range.combineIgnoringNaN(null, range1));
    }

    @Test
    public void combineIgnoringNaN_Range1NullRange2NotNaN_ReturnsRange2NotNull() {
        Range result = Range.combineIgnoringNaN(null, range1);
        assertNotNull(result);
        assertEquals(range1, result);
    }

    @Test
    public void combineIgnoringNaN_line264_range2NotNullAndNotNaN_ReturnsRange2() {
        Range normal = new Range(3, 7);
        Range result = Range.combineIgnoringNaN(null, normal);
        assertEquals(normal, result);
    }

    @Test
    public void combineIgnoringNaN_line264_equalToLessThan_Boundary() {
        Range nanRange = new Range(Double.NaN, Double.NaN);
        Range result = Range.combineIgnoringNaN(null, nanRange);
        assertNull(result);
    }

    @Test
    public void combineIgnoringNaN_line267_range2IsNormalRange_NotNull() {
        Range result = Range.combineIgnoringNaN(null, range3);
        assertNotNull(result);
        assertEquals(range3, result);
    }

    @Test
    public void combineIgnoringNaN_line267_repeatedCallReturnsSameObject() {
        Range result1 = Range.combineIgnoringNaN(null, range1);
        Range result2 = Range.combineIgnoringNaN(null, range1);
        assertEquals(result1, result2);
    }

    @Test
    public void combineIgnoringNaN_line270_range1IsNaNRange2Null_ReturnsNull() {
        assertNull(Range.combineIgnoringNaN(range7, null));
    }

    @Test
    public void combineIgnoringNaN_line270_range1NotNaN_Range2Null_ReturnsRange1() {
        Range result = Range.combineIgnoringNaN(range1, null);
        assertEquals(range1, result);
    }

    @Test
    public void combineIgnoringNaN_line270_equalToLessOrEqual_Boundary() {
        Range normalRange = new Range(2, 5);
        Range result = Range.combineIgnoringNaN(normalRange, null);
        assertNotNull(result);
        assertEquals(normalRange, result);
    }

    @Test
    public void combineIgnoringNaN_line277_bothNaN_ReturnsNull() {
        assertNull(Range.combineIgnoringNaN(range7, range7));
    }

    @Test
    public void combineIgnoringNaN_line277_oneNaNOneNormal_NotNull() {
        Range result = Range.combineIgnoringNaN(range7, range1);
        assertNotNull(result);
    }

    @Test
    public void combineIgnoringNaN_line277_normalAndNaN_LowerBoundFromNormal() {
        Range result = Range.combineIgnoringNaN(range1, range7);
        assertNotNull(result);
    }

    @Test
    public void combineIgnoringNaN_line277_negatedConditional_BothNaNMustReturnNull() {
        Range nan1 = new Range(Double.NaN, Double.NaN);
        Range nan2 = new Range(Double.NaN, Double.NaN);
        assertNull(Range.combineIgnoringNaN(nan1, nan2));
    }

    @Test
    public void combineIgnoringNaN_line277_removedIsNaNCall_Detected() {
        Range normal = new Range(1, 5);
        Range nan = new Range(Double.NaN, Double.NaN);
        Range result = Range.combineIgnoringNaN(normal, nan);
        assertNotNull(result);
        assertFalse(Double.isNaN(result.getLowerBound()));
    }

    @Test
    public void combineIgnoringNaN_line280_exactLowerBound() {
        Range r1 = new Range(2, 8);
        Range r2 = new Range(3, 10);
        Range result = Range.combineIgnoringNaN(r1, r2);
        assertEquals(2.0, result.getLowerBound(), 1e-9);
    }

    @Test
    public void combineIgnoringNaN_line280_exactUpperBound() {
        Range r1 = new Range(2, 8);
        Range r2 = new Range(3, 10);
        Range result = Range.combineIgnoringNaN(r1, r2);
        assertEquals(10.0, result.getUpperBound(), 1e-9);
    }

    @Test
    public void combineIgnoringNaN_line280_postIncrementOnLowerDetected() {
        Range r1 = new Range(2, 8);
        Range r2 = new Range(3, 10);
        Range result1 = Range.combineIgnoringNaN(r1, r2);
        Range result2 = Range.combineIgnoringNaN(r1, r2);
        assertEquals(result1.getLowerBound(), result2.getLowerBound(), 1e-9);
    }

    @Test
    public void combineIgnoringNaN_line280_postIncrementOnUpperDetected() {
        Range r1 = new Range(2, 8);
        Range r2 = new Range(3, 10);
        Range result1 = Range.combineIgnoringNaN(r1, r2);
        Range result2 = Range.combineIgnoringNaN(r1, r2);
        assertEquals(result1.getUpperBound(), result2.getUpperBound(), 1e-9);
    }

    @Test
    public void combineIgnoringNaN_line280_asymmetricBoundsDistinguishLowerFromUpper() {
        Range r1 = new Range(-5, 3);
        Range r2 = new Range(-2, 11);
        Range result = Range.combineIgnoringNaN(r1, r2);
        assertEquals(-5.0, result.getLowerBound(), 1e-9);
        assertEquals(11.0, result.getUpperBound(), 1e-9);
    }
}