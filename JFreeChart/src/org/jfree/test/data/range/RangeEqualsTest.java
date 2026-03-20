package org.jfree.test.data.range;
import static org.junit.jupiter.api.Assertions.*;
import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

public class RangeEqualsTest {

    @Test
    void equals_SameObject_ShouldReturnTrue() {
        Range r = new Range(0.0, 1.0);
        assertTrue(r.equals(r));
    }

    @Test
    void equals_EqualLowerAndUpper_ShouldReturnTrue() {
        Range r1 = new Range(-2.0, 5.0);
        Range r2 = new Range(-2.0, 5.0);
        assertTrue(r1.equals(r2));
    }

    @Test
    void equals_DifferentUpperSameLower_ShouldReturnFalse() {
        Range r1 = new Range(0.0, 1.0);
        Range r2 = new Range(0.0, 999.0);
        assertFalse(r1.equals(r2));
    }

    @Test
    void equals_DifferentLowerSameUpper_ShouldReturnFalse() {
        Range r1 = new Range(0.0, 1.0);
        Range r2 = new Range(-1.0, 1.0);
        assertFalse(r1.equals(r2));
    }

    @Test
    void equals_Null_ShouldReturnFalse() {
        Range r = new Range(0.0, 1.0);
        assertFalse(r.equals(null));
    }

    @Test
    void equals_DifferentType_ShouldReturnFalse() {
        Range r = new Range(0.0, 1.0);
        assertFalse(r.equals("not a range"));
    }

    @Test
    void equals_ObjectSupertype_ShouldReturnFalse() {
        Range r = new Range(0.0, 1.0);
        Object o = new Object();
        assertFalse(r.equals(o));
    }

    @Test
    void equals_IntegerObject_ShouldReturnFalse() {
        Range r = new Range(0.0, 1.0);
        assertFalse(r.equals(42));
    }

    @Test
    void equals_LowerSlightlyLess_ShouldReturnFalse() {
        Range r1 = new Range(1.0, 5.0);
        Range r2 = new Range(1.1, 5.0);
        assertFalse(r1.equals(r2));
    }

    @Test
    void equals_repeatedCallWithSameLower_StableResult() {
        Range r1 = new Range(3.0, 7.0);
        Range r2 = new Range(3.0, 7.0);
        assertTrue(r1.equals(r2));
        assertTrue(r1.equals(r2));
    }

    @Test
    void equals_repeatedCallDifferentLower_StableResult() {
        Range r1 = new Range(3.0, 7.0);
        Range r2 = new Range(4.0, 7.0);
        assertFalse(r1.equals(r2));
        assertFalse(r1.equals(r2));
    }

    @Test
    void equals_UpperSlightlyGreater_ShouldReturnFalse() {
        Range r1 = new Range(1.0, 5.1);
        Range r2 = new Range(1.0, 5.0);
        assertFalse(r1.equals(r2));
    }

    @Test
    void equals_repeatedCallWithSameUpper_StableResult() {
        Range r1 = new Range(1.0, 9.0);
        Range r2 = new Range(1.0, 9.0);
        assertTrue(r1.equals(r2));
        assertTrue(r1.equals(r2));
    }

    @Test
    void equals_repeatedCallDifferentUpper_StableResult() {
        Range r1 = new Range(1.0, 9.0);
        Range r2 = new Range(1.0, 8.0);
        assertFalse(r1.equals(r2));
        assertFalse(r1.equals(r2));
    }

    @Test
    void equals_ReturnTrue_VerifiedWithAssertTrue() {
        Range r1 = new Range(0.0, 1.0);
        Range r2 = new Range(0.0, 1.0);
        boolean result = r1.equals(r2);
        assertTrue(result);
        assertEquals(true, result);
    }

    @Test
    void equals_Symmetric_BothDirectionsMustBeTrue() {
        Range r1 = new Range(2.0, 8.0);
        Range r2 = new Range(2.0, 8.0);
        assertTrue(r1.equals(r2));
        assertTrue(r2.equals(r1));
    }

    @Test
    void equals_Symmetric_BothDirectionsMustBeFalse() {
        Range r1 = new Range(2.0, 8.0);
        Range r2 = new Range(2.0, 9.0);
        assertFalse(r1.equals(r2));
        assertFalse(r2.equals(r1));
    }

    @Test
    void equals_BothLowerAndUpperDiffer_ShouldReturnFalse() {
        Range r1 = new Range(1.0, 5.0);
        Range r2 = new Range(2.0, 6.0);
        assertFalse(r1.equals(r2));
    }

    @Test
    void equals_ZeroBounds_ShouldReturnTrue() {
        Range r1 = new Range(0.0, 0.0);
        Range r2 = new Range(0.0, 0.0);
        assertTrue(r1.equals(r2));
    }

    @Test
    void equals_NegativeBounds_ShouldReturnTrue() {
        Range r1 = new Range(-10.0, -1.0);
        Range r2 = new Range(-10.0, -1.0);
        assertTrue(r1.equals(r2));
    }
}