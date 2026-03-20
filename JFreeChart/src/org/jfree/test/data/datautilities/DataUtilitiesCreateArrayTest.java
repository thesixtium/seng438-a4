package org.jfree.test.data.datautilities;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.jfree.data.DataUtilities;

public class DataUtilitiesCreateArrayTest {

    @Test
    void testCreateNumberArrayValid() {
        double[] data = {1.5, 2.5, 3.5};
        Number[] result = DataUtilities.createNumberArray(data);
        assertEquals(3, result.length);
        assertEquals(1.5, result[0].doubleValue(), 1e-9);
    }

    @Test
    void testCreateNumberArrayNull() {
        assertThrows(IllegalArgumentException.class, () ->
            DataUtilities.createNumberArray(null));
    }

    @Test
    void createNumberArray_LoopBoundary_AllElementsConverted() {
        double[] data = {1.0, 2.0, 3.0};
        Number[] result = DataUtilities.createNumberArray(data);
        assertEquals(3, result.length);
        assertEquals(1.0, result[0].doubleValue(), 1e-9);
        assertEquals(2.0, result[1].doubleValue(), 1e-9);
        assertEquals(3.0, result[2].doubleValue(), 1e-9);
    }

    @Test
    void createNumberArray_ArrayFieldPostIncrementDetected() {
        double[] data = {5.0, 10.0, 15.0};
        Number[] result = DataUtilities.createNumberArray(data);
        assertEquals(5.0,  result[0].doubleValue(), 1e-9);
        assertEquals(10.0, result[1].doubleValue(), 1e-9);
        assertEquals(15.0, result[2].doubleValue(), 1e-9);
    }

    @Test
    void createNumberArray_RepeatedCallGivesSameResult() {
        double[] data = {3.0, 7.0, 11.0};
        Number[] r1 = DataUtilities.createNumberArray(data);
        Number[] r2 = DataUtilities.createNumberArray(data);
        for (int i = 0; i < data.length; i++) {
            assertEquals(r1[i].doubleValue(), r2[i].doubleValue(), 1e-9);
        }
    }

    @Test
    void createNumberArray_AsymmetricValues_ExactConversion() {
        double[] data = {100.0, 0.5, 0.25};
        Number[] result = DataUtilities.createNumberArray(data);
        assertEquals(100.0, result[0].doubleValue(), 1e-9);
        assertEquals(0.5,   result[1].doubleValue(), 1e-9);
        assertEquals(0.25,  result[2].doubleValue(), 1e-9);
    }
}