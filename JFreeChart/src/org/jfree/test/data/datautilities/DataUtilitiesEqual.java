package org.jfree.test.data.datautilities;
import static org.junit.jupiter.api.Assertions.*;
import org.jfree.data.DataUtilities;
import org.junit.jupiter.api.Test;

public class DataUtilitiesEqual {

    @Test
    void testAandBisNull() {
        double[][] a = null;
        double[][] b = null;
        assertTrue(DataUtilities.equal(a, b));
    }

    @Test
    void testAisNull() {
        double[][] a = null;
        double[][] b = {{1.0}};
        assertFalse(DataUtilities.equal(a, b));
    }

    @Test
    void testBisNull() {
        double[][] a = {{1.0}};
        double[][] b = null;
        assertFalse(DataUtilities.equal(a, b));
    }

    @Test
    void testDifferentLengths() {
        double[][] a = {{1.0}};
        double[][] b = {{1.0}, {2.0}};
        assertFalse(DataUtilities.equal(a, b));
    }

    @Test
    void testDifferentInnerLengths() {
        double[][] a = {{1.0, 5.0, 7.0}};
        double[][] b = {{1.0}};
        assertFalse(DataUtilities.equal(a, b));
    }

    @Test
    void testSameArray() {
        double[][] a = {{1.0}, {2.0}};
        double[][] b = {{1.0}, {2.0}};
        assertTrue(DataUtilities.equal(a, b));
    }

    @Test
    void testSameInnerArray() {
        double[][] a = {{1.0, 2.0}, {3.0, 6.0}};
        double[][] b = {{1.0, 2.0}, {3.0, 6.0}};
        assertTrue(DataUtilities.equal(a, b));
    }

    @Test
    void testDifferentArray() {
        double[][] a = {{1.0}, {1.0}};
        double[][] b = {{1.0}, {2.0}};
        assertFalse(DataUtilities.equal(a, b));
    }

    @Test
    void testDifferentInnerArray() {
        double[][] a = {{1.0, 4.0}};
        double[][] b = {{1.0, 5.0}};
        assertFalse(DataUtilities.equal(a, b));
    }

    @Test
    void testANullBNull_ReturnIsExactlyTrue() {
        boolean result = DataUtilities.equal(null, null);
        assertTrue(result);
        assertEquals(true, result);
    }

    @Test
    void testANullBNotNull_ReturnIsExactlyFalse() {
        boolean result = DataUtilities.equal(null, new double[][]{{1.0}});
        assertFalse(result);
        assertEquals(false, result);
    }

    @Test
    void testALongerThanB_ShouldReturnFalse() {
        double[][] a = {{1.0}, {2.0}, {3.0}};
        double[][] b = {{1.0}, {2.0}};
        assertFalse(DataUtilities.equal(a, b));
    }

    @Test
    void testBLongerThanA_ShouldReturnFalse() {
        double[][] a = {{1.0}};
        double[][] b = {{1.0}, {2.0}, {3.0}};
        assertFalse(DataUtilities.equal(a, b));
    }

    @Test
    void testLoopBoundary_AllRowsCompared() {
        double[][] a = {{1.0}, {2.0}, {3.0}};
        double[][] b = {{1.0}, {2.0}, {3.0}};
        assertTrue(DataUtilities.equal(a, b));
    }

    @Test
    void testLoopBoundary_LastRowDiffers() {
        double[][] a = {{1.0}, {2.0}, {3.0}};
        double[][] b = {{1.0}, {2.0}, {4.0}};
        assertFalse(DataUtilities.equal(a, b));
    }

    @Test
    void testLoopBoundary_FirstRowDiffers() {
        double[][] a = {{99.0}, {2.0}, {3.0}};
        double[][] b = {{1.0},  {2.0}, {3.0}};
        assertFalse(DataUtilities.equal(a, b));
    }

    @Test
    void testInnerArrayALessThanB_ShouldReturnFalse() {
        double[][] a = {{1.0, 2.0}};
        double[][] b = {{1.0, 3.0}};
        assertFalse(DataUtilities.equal(a, b));
    }

    @Test
    void testInnerArrayBLessThanA_ShouldReturnFalse() {
        double[][] a = {{5.0, 9.0}};
        double[][] b = {{5.0, 8.0}};
        assertFalse(DataUtilities.equal(a, b));
    }

    @Test
    void testInnerArraysEqual_ShouldReturnTrue() {
        double[][] a = {{3.0, 7.0}, {1.0, 2.0}};
        double[][] b = {{3.0, 7.0}, {1.0, 2.0}};
        assertTrue(DataUtilities.equal(a, b));
    }

    @Test
    void testEqualArrays_ReturnIsExactlyTrue() {
        double[][] a = {{2.0, 4.0}, {6.0, 8.0}};
        double[][] b = {{2.0, 4.0}, {6.0, 8.0}};
        boolean result = DataUtilities.equal(a, b);
        assertTrue(result);
        assertEquals(true, result);
    }

    @Test
    void testEqual_Symmetric_BothDirections() {
        double[][] a = {{1.0, 2.0}};
        double[][] b = {{1.0, 2.0}};
        assertTrue(DataUtilities.equal(a, b));
        assertTrue(DataUtilities.equal(b, a));
    }

    @Test
    void testNotEqual_Symmetric_BothDirections() {
        double[][] a = {{1.0, 2.0}};
        double[][] b = {{1.0, 3.0}};
        assertFalse(DataUtilities.equal(a, b));
        assertFalse(DataUtilities.equal(b, a));
    }

    @Test
    void testEmptyArrays_ShouldReturnTrue() {
        double[][] a = {};
        double[][] b = {};
        assertTrue(DataUtilities.equal(a, b));
    }

    @Test
    void testOneEmptyOneNonEmpty_ShouldReturnFalse() {
        double[][] a = {};
        double[][] b = {{1.0}};
        assertFalse(DataUtilities.equal(a, b));
    }
}