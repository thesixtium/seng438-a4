package org.jfree.test.data.datautilities;
import static org.junit.jupiter.api.Assertions.*;
import org.jfree.data.DataUtilities;
import org.junit.jupiter.api.Test;

public class DataUtilitiesClone {

    @Test
    void testNotNull() {
        double[][] source = { { 1.0 } };
        double[][] result = DataUtilities.clone(source);
        assertEquals(source.length, result.length);
        for (int i = 0; i < source.length; i++) {
            assertArrayEquals(source[i], result[i], 1e-9);
        }
    }

    @Test
    void clone_NullSource_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            DataUtilities.clone(null);
        });
    }

    @Test
    void clone_MultipleRows_AllRowsCopied() {
        double[][] source = { { 1.0, 2.0 }, { 3.0, 4.0 }, { 5.0, 6.0 } };
        double[][] result = DataUtilities.clone(source);
        assertEquals(3, result.length);
        for (int i = 0; i < source.length; i++) {
            assertArrayEquals(source[i], result[i], 1e-9);
        }
    }

    @Test
    void clone_SourceWithNullRow_NullPreserved() {
        double[][] source = { { 1.0, 2.0 }, null, { 3.0, 4.0 } };
        double[][] result = DataUtilities.clone(source);
        assertEquals(3, result.length);
        assertNull(result[1], "null row must remain null in the clone");
        assertArrayEquals(source[0], result[0], 1e-9);
        assertArrayEquals(source[2], result[2], 1e-9);
    }

    @Test
    void clone_MultipleRows_NegatedIndexDetected() {
        double[][] source = { { 1.0 }, { 2.0 }, { 3.0 } };
        // If i is negated on row 1 or 2, an exception would be thrown
        assertDoesNotThrow(() -> {
            double[][] result = DataUtilities.clone(source);
            assertEquals(3, result.length);
            assertArrayEquals(new double[]{ 1.0 }, result[0], 1e-9);
            assertArrayEquals(new double[]{ 2.0 }, result[1], 1e-9);
            assertArrayEquals(new double[]{ 3.0 }, result[2], 1e-9);
        });
    }

    @Test
    void clone_RowLengthsPreserved() {
        double[][] source = { { 1.0, 2.0, 3.0 }, { 4.0, 5.0 } };
        double[][] result = DataUtilities.clone(source);
        assertEquals(3, result[0].length, "first row length must be 3");
        assertEquals(2, result[1].length, "second row length must be 2");
    }

    @Test
    void clone_SecondRowContentIsCorrect() {
        double[][] source = { { 10.0 }, { 20.0, 30.0 } };
        double[][] result = DataUtilities.clone(source);
        // If i was negated on row 1, source[-1] would throw or copy wrong data
        assertArrayEquals(new double[]{ 20.0, 30.0 }, result[1], 1e-9,
                "second row content must match source");
    }

    @Test
    void clone_EachRowStoredInCorrectSlot() {
        double[][] source = { { 1.0 }, { 2.0 }, { 3.0 } };
        double[][] result = DataUtilities.clone(source);
        // If clone[i++] was used, result[0]={1}, result[2]={2}, result[?]={3}
        assertArrayEquals(new double[]{ 1.0 }, result[0], 1e-9, "slot 0 must hold row 0");
        assertArrayEquals(new double[]{ 2.0 }, result[1], 1e-9, "slot 1 must hold row 1");
        assertArrayEquals(new double[]{ 3.0 }, result[2], 1e-9, "slot 2 must hold row 2");
    }

    @Test
    void clone_IsDeepCopy_MutatingSourceDoesNotAffectClone() {
        double[][] source = { { 1.0, 2.0 }, { 3.0, 4.0 } };
        double[][] result = DataUtilities.clone(source);
        source[0][0] = 99.0; // mutate original
        assertEquals(1.0, result[0][0], 1e-9,
                "clone must be independent of source after creation");
    }

    @Test
    void clone_EmptySource_ReturnsEmptyClone() {
        double[][] source = new double[0][];
        double[][] result = DataUtilities.clone(source);
        assertNotNull(result);
        assertEquals(0, result.length);
    }
}