package org.jfree.test.data.datautilities;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.DataUtilities;
import org.junit.jupiter.api.Test;

public class DataUtilitiesNumberArray {

	@Test
	void createNumberArray_NormalValues_positive_ShouldConvertToNumberArray() {
        double[] input_1 = {1.0, 2.5, 3.0};
        Number[] result_1 = DataUtilities.createNumberArray(input_1);

        assertEquals(3, result_1.length);
        assertEquals(1.0, result_1[0].doubleValue(), 1e-9);
        assertEquals(2.5, result_1[1].doubleValue(), 1e-9);
        assertEquals(3.0, result_1[2].doubleValue(), 1e-9);
    }
	@Test
	void createNumberArray_NormalValues_negative_ShouldConvertToNumberArray() {
        double[] input_2 = {-1.0, -2.5, -3.0};
        Number[] result_2 = DataUtilities.createNumberArray(input_2);

        assertEquals(3, result_2.length);
        assertEquals(-1.0, result_2[0].doubleValue(), 1e-9);
        assertEquals(-2.5, result_2[1].doubleValue(), 1e-9);
        assertEquals(-3.0, result_2[2].doubleValue(), 1e-9);
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

}
