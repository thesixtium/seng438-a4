package org.jfree.test.data.datautilities;
import static org.junit.jupiter.api.Assertions.*;
import org.jfree.data.DataUtilities;
import org.junit.jupiter.api.Test;

public class DataUtilitiesNumberArray {

    @Test
    void createNumberArray_NormalValues_positive_ShouldConvertToNumberArray() {
        double[] input = {1.0, 2.5, 3.0};
        Number[] result = DataUtilities.createNumberArray(input);
        assertEquals(3, result.length);
        assertEquals(1.0, result[0].doubleValue(), 1e-9);
        assertEquals(2.5, result[1].doubleValue(), 1e-9);
        assertEquals(3.0, result[2].doubleValue(), 1e-9);
    }

    @Test
    void createNumberArray_NormalValues_negative_ShouldConvertToNumberArray() {
        double[] input = {-1.0, -2.5, -3.0};
        Number[] result = DataUtilities.createNumberArray(input);
        assertEquals(3, result.length);
        assertEquals(-1.0, result[0].doubleValue(), 1e-9);
        assertEquals(-2.5, result[1].doubleValue(), 1e-9);
        assertEquals(-3.0, result[2].doubleValue(), 1e-9);
    }

    @Test
    void createNumberArray_ZeroValue_ShouldConvertToNumberArray() {
        double[] input = {0.0};
        Number[] result = DataUtilities.createNumberArray(input);
        assertEquals(1, result.length);
        assertEquals(0.0, result[0].doubleValue(), 1e-9);
    }

    @Test
    void createNumberArray_EmptyArray_ShouldReturnEmptyNumberArray() {
        double[] input = {};
        Number[] result = DataUtilities.createNumberArray(input);
        assertEquals(0, result.length);
    }

    @Test
    void createNumberArray_FourElements_AllIncluded() {
        double[] input = {1.0, 2.0, 3.0, 4.0};
        Number[] result = DataUtilities.createNumberArray(input);
        assertEquals(4, result.length);
        assertEquals(4.0, result[3].doubleValue(), 1e-9);
    }

    @Test
    void createNumberArray_OriginalArrayUnmodified() {
        double[] input = {7.0, 8.0, 9.0};
        double[] copy = input.clone();
        DataUtilities.createNumberArray(input);
        assertArrayEquals(copy, input, 1e-9);
    }

    @Test
    void createNumberArray_ExactValuesPreserved_LargeAndSmall() {
        double[] input = {Double.MAX_VALUE, Double.MIN_VALUE, 0.0};
        Number[] result = DataUtilities.createNumberArray(input);
        assertEquals(Double.MAX_VALUE, result[0].doubleValue(), 1e-9);
        assertEquals(Double.MIN_VALUE, result[1].doubleValue(), 1e-9);
        assertEquals(0.0,             result[2].doubleValue(), 1e-9);
    }
}