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
        //  a bug exists because only lower is compared
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
}
