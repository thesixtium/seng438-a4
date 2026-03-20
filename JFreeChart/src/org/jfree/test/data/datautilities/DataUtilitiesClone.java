package org.jfree.test.data.datautilities;
import static org.junit.jupiter.api.Assertions.*;
import org.jfree.data.DataUtilities;
import org.junit.jupiter.api.Test;

public class DataUtilitiesClone {

    @Test
    void testNotNull() {
        double[][] source = {{1.0}};
        double[][] result = DataUtilities.clone(source);
        assertEquals(source.length, result.length);
        for (int i = 0; i < source.length; i++) {
            assertArrayEquals(source[i], result[i], 1e-9);
        }
    }

    @Test
    void clone_NullSource_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            DataUtilities.clone(null));
    }

    @Test
    void clone_MultipleRows_AllRowsCopied() {
        double[][] source = {{1.0, 2.0}, {3.0, 4.0}, {5.0, 6.0}};
        double[][] result = DataUtilities.clone(source);
        assertEquals(3, result.length);
        for (int i = 0; i < source.length; i++) {
            assertArrayEquals(source[i], result[i], 1e-9);
        }
    }

    @Test
    void clone_SourceWithNullRow_NullPreserved() {
        double[][] source = {{1.0, 2.0}, null, {3.0, 4.0}};
        double[][] result = DataUtilities.clone(source);
        assertEquals(3, result.length);
        assertNull(result[1]);
        assertArrayEquals(source[0], result[0], 1e-9);
        assertArrayEquals(source[2], result[2], 1e-9);
    }

    @Test
    void clone_MultipleRows_NegatedIndexDetected() {
        double[][] source = {{1.0}, {2.0}, {3.0}};
        assertDoesNotThrow(() -> {
            double[][] result = DataUtilities.clone(source);
            assertEquals(3, result.length);
            assertArrayEquals(new double[]{1.0}, result[0], 1e-9);
            assertArrayEquals(new double[]{2.0}, result[1], 1e-9);
            assertArrayEquals(new double[]{3.0}, result[2], 1e-9);
        });
    }

    @Test
    void clone_RowLengthsPreserved() {
        double[][] source = {{1.0, 2.0, 3.0}, {4.0, 5.0}};
        double[][] result = DataUtilities.clone(source);
        assertEquals(3, result[0].length);
        assertEquals(2, result[1].length);
    }

    @Test
    void clone_SecondRowContentIsCorrect() {
        double[][] source = {{10.0}, {20.0, 30.0}};
        double[][] result = DataUtilities.clone(source);
        assertArrayEquals(new double[]{20.0, 30.0}, result[1], 1e-9);
    }

    @Test
    void clone_EachRowStoredInCorrectSlot() {
        double[][] source = {{1.0}, {2.0}, {3.0}};
        double[][] result = DataUtilities.clone(source);
        assertArrayEquals(new double[]{1.0}, result[0], 1e-9);
        assertArrayEquals(new double[]{2.0}, result[1], 1e-9);
        assertArrayEquals(new double[]{3.0}, result[2], 1e-9);
    }

    @Test
    void clone_IsDeepCopy_MutatingSourceDoesNotAffectClone() {
        double[][] source = {{1.0, 2.0}, {3.0, 4.0}};
        double[][] result = DataUtilities.clone(source);
        source[0][0] = 99.0;
        assertEquals(1.0, result[0][0], 1e-9);
    }

    @Test
    void clone_EmptySource_ReturnsEmptyClone() {
        double[][] source = new double[0][];
        double[][] result = DataUtilities.clone(source);
        assertNotNull(result);
        assertEquals(0, result.length);
    }

    @Test
    void clone_LoopBoundary_ExtraRowAccessDetected() {
        double[][] source = {{1.0}, {2.0}};
        double[][] result = DataUtilities.clone(source);
        assertEquals(2, result.length);
        assertArrayEquals(new double[]{1.0}, result[0], 1e-9);
        assertArrayEquals(new double[]{2.0}, result[1], 1e-9);
    }

    @Test
    void clone_RepeatedCallGivesSameResult() {
        double[][] source = {{5.0, 6.0}, {7.0, 8.0}};
        double[][] r1 = DataUtilities.clone(source);
        double[][] r2 = DataUtilities.clone(source);
        assertArrayEquals(r1[0], r2[0], 1e-9);
        assertArrayEquals(r1[1], r2[1], 1e-9);
    }
}