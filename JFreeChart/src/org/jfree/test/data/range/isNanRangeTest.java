package org.jfree.test.data.range;
import org.jfree.data.Range;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class isNanRangeTest {

    @Test
    void testIsNaNRangeBothNaN() {
        Range r = new Range(Double.NaN, Double.NaN);
        assertTrue(r.isNaNRange());
    }

    @Test
    void testIsNaNRangeOneNaN() {
        Range r = new Range(Double.NaN, 5.0);
        assertFalse(r.isNaNRange());
    }

    @Test
    void testIsNaNRangeNoNaN() {
        Range r = new Range(1.0, 5.0);
        assertFalse(r.isNaNRange());
    }

    @Test
    void testIsNaNRange_NormalLowerNaNUpper_ShouldReturnFalse() {
        Range r = new Range(1.0, Double.NaN);
        assertFalse(r.isNaNRange());
    }

    @Test
    void testIsNaNRange_OnlyUpperNaN_ShouldReturnFalse() {
        Range r = new Range(3.0, Double.NaN);
        assertFalse(r.isNaNRange());
    }

    @Test
    void testIsNaNRange_OnlyLowerNaN_ShouldReturnFalse() {
        Range r = new Range(Double.NaN, 3.0);
        assertFalse(r.isNaNRange());
    }

    @Test
    void testIsNaNRange_ReturnValueIsExactlyTrue() {
        Range r = new Range(Double.NaN, Double.NaN);
        boolean result = r.isNaNRange();
        assertTrue(result);
    }

    @Test
    void testIsNaNRange_ReturnValueIsExactlyFalse() {
        Range r = new Range(1.0, 2.0);
        boolean result = r.isNaNRange();
        assertFalse(result);
    }

    @Test
    void testIsNaNRange_BothNaN_RepeatedCallStable() {
        Range r = new Range(Double.NaN, Double.NaN);
        assertTrue(r.isNaNRange());
        assertTrue(r.isNaNRange());
    }

    @Test
    void testIsNaNRange_NoNaN_RepeatedCallStable() {
        Range r = new Range(2.0, 8.0);
        assertFalse(r.isNaNRange());
        assertFalse(r.isNaNRange());
    }

    @Test
    void testIsNaNRange_NegatedFieldsStillNaN() {
        Range r = new Range(Double.NaN, Double.NaN);
        assertTrue(r.isNaNRange());
    }
}