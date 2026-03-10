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
        range1 = new Range( 0, 1 );
        range2 = new Range( -1, 0 );
        range3 = new Range( -1, 1 );
        range4 = new Range( 0, 2 );
        range5 = new Range( -1, 2 );
        range6 = new Range( 1, 2 );
        range7 = new Range( Double.NaN , Double.NaN );
    }

    @Test
    public void combineIgnoringNaN_BothRangesNull() {
        Range expected = null;
        Range actual = Range.combineIgnoringNaN( null, null );
        assertEquals( expected, actual, "Range null combine with Range null to make Range null" );
    }

    @Test
    public void combineIgnoringNaN_Range1Null() {
        Range expected = null;
        Range actual = Range.combineIgnoringNaN( null, range7 );
        assertEquals( expected, actual, "Range null combine with Range range2 to make Range range2" );
    }

    @Test
    public void combineIgnoringNaN_Range2Null() {
        Range expected = null;
        Range actual = Range.combineIgnoringNaN( range7, null );
        assertEquals( expected, actual, "Range range1 combine with Range null to make Range range1" );
    }

    @Test
    public void combineIgnoringNaN_Range2Null2() {
        Range expected = range1;
        Range actual = Range.combineIgnoringNaN( range1, null );
        assertEquals( expected, actual, "Range range1 combine with Range null to make Range range1" );
    }

    @Test
    public void combineIgnoringNaN_SameRange() {
        Range expected = range1;
        Range actual = Range.combineIgnoringNaN( range1, range1 );
        assertEquals( expected, actual, "Range range1 combine with Range range1 to make Range range1" );
    }

    @Test
    public void combineIgnoringNaN_SameRange2() {
        Range expected = range7;
        Range actual = Range.combineIgnoringNaN( range7, range7 );
        assertEquals( expected, actual, "Range range1 combine with Range range1 to make Range range1" );
    }

    @Test
    public void combineIgnoringNaN_TouchingRangesInOrder() {
        Range expected = range3;
        Range actual = Range.combineIgnoringNaN( range1, range2 );
        assertEquals( expected, actual, "Range range1 combine with Range range2 to make Range range3" );
    }

    @Test
    public void combineIgnoringNaN_TouchingRangesNotInOrder() {
        Range expected = range3;
        Range actual = Range.combineIgnoringNaN( range2, range1 );
        assertEquals( expected, actual, "Range range2 combine with Range range1 to make Range range3" );
    }

    @Test
    public void combineIgnoringNaN_IntersectingRangesInOrder() {
        Range expected = range5;
        Range actual = Range.combineIgnoringNaN( range3, range4 );
        assertEquals( expected, actual, "Range range3 combine with Range range4 to make Range range5" );
    }

    @Test
    public void combineIgnoringNaN_IntersectingRangesNotInOrder() {
        Range expected = range5;
        Range actual = Range.combineIgnoringNaN( range4, range3 );
        assertEquals( expected, actual, "Range range4 combine with Range range3 to make Range range5" );
    }

    @Test
    public void combineIgnoringNaN_NonIntersectingRangesInOrder() {
        Range expected = range5;
        Range actual = Range.combineIgnoringNaN( range2, range6 );
        assertEquals( expected, actual, "Range range2 combine with Range range6 to make Range range5" );
    }

    @Test
    public void combineIgnoringNaN_NonIntersectingRangesNotInOrder() {
        Range expected = range5;
        Range actual = Range.combineIgnoringNaN( range6, range2 );
        assertEquals( expected, actual, "Range range6 combine with Range range2 to make Range range5" );
    }

    @Test
    public void combineIgnoringNaN_RangeInsideRangeInOrder() {
        Range expected = range5;
        Range actual = Range.combineIgnoringNaN( range5, range3 );
        assertEquals( expected, actual, "Range range5 combine with Range range3 to make Range range5" );
    }

    @Test
    public void combineIgnoringNaN_RangeInsideRangeNotInOrder() {
        Range expected = range5;
        Range actual = Range.combineIgnoringNaN( range3, range5 );
        assertEquals( expected, actual, "Range range3 combine with Range range5 to make Range range5" );
    }
}