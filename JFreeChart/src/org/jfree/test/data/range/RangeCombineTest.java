package org.jfree.test.data.range;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jfree.data.Range;

public class RangeCombineTest {
    private Range range1;
    private Range range2;
    private Range range3;
    private Range range4;
    private Range range5;
    private Range range6;

    @BeforeEach
    public void before() {
        range1 = new Range( 0, 1 );
        range2 = new Range( -1, 0 );
        range3 = new Range( -1, 1 );
        range4 = new Range( 0, 2 );
        range5 = new Range( -1, 2 );
        range6 = new Range( 1, 2 );
    }

    @Test
    public void combine_BothRangesNull() {
        Range expected = null;
        Range actual = Range.combine( null, null );
        assertEquals( expected, actual, "Range null combine with Range null to make Range null" );
    }

    @Test
    public void combine_Range1Null() {
        Range expected = range2;
        Range actual = Range.combine( null, range2 );
        assertEquals( expected, actual, "Range null combine with Range range2 to make Range range2" );
    }

    @Test
    public void combine_Range2Null() {
        Range expected = range1;
        Range actual = Range.combine( range1, null );
        assertEquals( expected, actual, "Range range1 combine with Range null to make Range range1" );
    }

    @Test
    public void combine_SameRange() {
        Range expected = range1;
        Range actual = Range.combine( range1, range1 );
        assertEquals( expected, actual, "Range range1 combine with Range range1 to make Range range1" );
    }

    @Test
    public void combine_TouchingRangesInOrder() {
        Range expected = range3;
        Range actual = Range.combine( range1, range2 );
        assertEquals( expected, actual, "Range range1 combine with Range range2 to make Range range3" );
    }

    @Test
    public void combine_TouchingRangesNotInOrder() {
        Range expected = range3;
        Range actual = Range.combine( range2, range1 );
        assertEquals( expected, actual, "Range range2 combine with Range range1 to make Range range3" );
    }

    @Test
    public void combine_IntersectingRangesInOrder() {
        Range expected = range5;
        Range actual = Range.combine( range3, range4 );
        assertEquals( expected, actual, "Range range3 combine with Range range4 to make Range range5" );
    }

    @Test
    public void combine_IntersectingRangesNotInOrder() {
        Range expected = range5;
        Range actual = Range.combine( range4, range3 );
        assertEquals( expected, actual, "Range range4 combine with Range range3 to make Range range5" );
    }

    @Test
    public void combine_NonIntersectingRangesInOrder() {
        Range expected = range5;
        Range actual = Range.combine( range2, range6 );
        assertEquals( expected, actual, "Range range2 combine with Range range6 to make Range range5" );
    }

    @Test
    public void combine_NonIntersectingRangesNotInOrder() {
        Range expected = range5;
        Range actual = Range.combine( range6, range2 );
        assertEquals( expected, actual, "Range range6 combine with Range range2 to make Range range5" );
    }

    @Test
    public void combine_RangeInsideRangeInOrder() {
        Range expected = range5;
        Range actual = Range.combine( range5, range3 );
        assertEquals( expected, actual, "Range range5 combine with Range range3 to make Range range5" );
    }

    @Test
    public void combine_RangeInsideRangeNotInOrder() {
        Range expected = range5;
        Range actual = Range.combine( range3, range5 );
        assertEquals( expected, actual, "Range range3 combine with Range range5 to make Range range5" );
    }
}