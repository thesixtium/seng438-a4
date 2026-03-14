package org.jfree.test.data.range;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RangeExpandTest {

    private Range oldRange;
    private Range newRange;
    private double lowerMargin;
    private double upperMargin;

    @BeforeEach
    void setUp() {
        oldRange = new Range(5, 10);
        lowerMargin = 0.40;
        upperMargin = 1.60;
    }

    @Test
    void RangeExpands_WithValidNumbers() {
        newRange = new Range(3, 18);
        assertEquals(newRange, Range.expand(oldRange, lowerMargin, upperMargin));
    }
    @Test
    void RangeIsNull_ThrowIllegalArgumentError() {
        assertThrows(IllegalArgumentException.class, () -> Range.expand(null, lowerMargin, upperMargin));
    }
    @Test
    void RangeExpands_WithNegativeLowerMargin() {
        double lowerMargin = -0.40;
        newRange = new Range(7, 18);
        assertEquals(newRange, Range.expand(oldRange, lowerMargin, upperMargin));
    }
    @Test
    void ZeroRange_NoChangeWithExpand() {
        oldRange = new Range(5, 5);
        newRange = new Range(5, 5);
        assertEquals(newRange, Range.expand(oldRange, lowerMargin, upperMargin));
    }
    @Test
    void lowerEqualsUpper() {
        double upperMargin = -1.4;
        newRange = new Range(3, 3);
        assertEquals(newRange, Range.expand(oldRange, lowerMargin, upperMargin));
    }

    @Test
    void expand_line353_repeatedCallGivesSameResult() {
        Range first  = Range.expand(oldRange, lowerMargin, upperMargin);
        Range second = Range.expand(oldRange, lowerMargin, upperMargin);
        assertEquals(first, second);
    }

    @Test
    void expand_line353_exactLowerBoundValue() {
        Range result = Range.expand(oldRange, lowerMargin, upperMargin);
        assertEquals(3.0, result.getLowerBound(), 1e-9);
    }

    @Test
    void expand_line354_exactUpperBoundValue() {
        Range result = Range.expand(oldRange, lowerMargin, upperMargin);
        assertEquals(18.0, result.getUpperBound(), 1e-9);
    }

    @Test
    void expand_line354_repeatedCallGivesSameUpperBound() {
        double ub1 = Range.expand(oldRange, lowerMargin, upperMargin).getUpperBound();
        double ub2 = Range.expand(oldRange, lowerMargin, upperMargin).getUpperBound();
        assertEquals(ub1, ub2, 1e-9);
    }

    @Test
    void expand_lowerGreaterThanUpper_ReturnsMidpointRange() {
        double upperMargin = -1.60;
        Range result = Range.expand(oldRange, lowerMargin, upperMargin);
        assertEquals(2.5, result.getLowerBound(), 1e-9,
                "lower bound should be midpoint when lower > upper");
        assertEquals(2.5, result.getUpperBound(), 1e-9,
                "upper bound should be midpoint when lower > upper");
    }

    @Test
    void expand_lowerEqualsUpper_BoundsUnchangedByBranchLogic() {
        double upperMargin = -1.4; // lower=3, upper=3 → equal, branch skipped
        Range result = Range.expand(oldRange, lowerMargin, upperMargin);
        assertEquals(3.0, result.getLowerBound(), 1e-9);
        assertEquals(3.0, result.getUpperBound(), 1e-9);
    }

    @Test
    void expand_lowerGreaterThanUpper_NegationDetected() {
        double upperMargin = -1.60;
        Range result = Range.expand(oldRange, lowerMargin, upperMargin);
        // If lower was negated the branch would not fire and we'd get an invalid range
        assertTrue(result.getLowerBound() <= result.getUpperBound(),
                "result must be a valid (non-inverted) range");
    }

    @Test
    void expand_lowerGreaterThanUpper_MidpointIsAverage_NotSum() {
        double upperMargin = -1.60; // lower=3, upper=2
        Range result = Range.expand(oldRange, lowerMargin, upperMargin);
        assertNotEquals(5.0, result.getLowerBound(), 1e-9,
                "midpoint must be average (2.5), not sum (5.0)");
        assertEquals(2.5, result.getLowerBound(), 1e-9);
    }

    @Test
    void expand_lowerGreaterThanUpper_MidpointUsesAddition_NotSubtraction() {
        double upperMargin = -1.60;
        Range result = Range.expand(oldRange, lowerMargin, upperMargin);
        assertNotEquals(0.5, result.getLowerBound(), 1e-9);
        assertEquals(2.5, result.getLowerBound(), 1e-9);
    }

    @Test
    void expand_lowerGreaterThanUpper_BothBoundsEqualMidpoint() {
        double upperMargin = -1.60;
        Range result = Range.expand(oldRange, lowerMargin, upperMargin);
        assertEquals(result.getLowerBound(), result.getUpperBound(), 1e-9,
                "both bounds must equal the midpoint");
    }

    @Test
    void expand_line359_exactReturnedBounds_NormalCase() {
        // length=5, lower=5-2=3, upper=10+8=18 — precise end-to-end check
        Range result = Range.expand(oldRange, lowerMargin, upperMargin);
        assertAll(
            () -> assertEquals(3.0,  result.getLowerBound(), 1e-9),
            () -> assertEquals(18.0, result.getUpperBound(), 1e-9)
        );
    }

    @Test
    void expand_line359_postIncrementOnReturnDetected() {
        Range r1 = Range.expand(oldRange, lowerMargin, upperMargin);
        Range r2 = Range.expand(oldRange, lowerMargin, upperMargin);
        assertEquals(r1.getLowerBound(), r2.getLowerBound(), 1e-9);
        assertEquals(r1.getUpperBound(), r2.getUpperBound(), 1e-9);
    }

    @Test
    void expand_asymmetricMargins_LowerAndUpperAreDistinct() {
        Range result = Range.expand(new Range(0.0, 4.0), 0.25, 0.75);
        // length=4, lower=0-1=−1, upper=4+3=7
        assertEquals(-1.0, result.getLowerBound(), 1e-9);
        assertEquals(7.0,  result.getUpperBound(), 1e-9);
    }
}