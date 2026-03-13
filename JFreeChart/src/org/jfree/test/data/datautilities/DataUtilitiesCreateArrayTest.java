package org.jfree.test.data.datautilities;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.jfree.data.DataUtilities;

class DataUtilitiesCreateArrayTest {

    @Test
    void testCreateNumberArrayValid() {
        double[] data = {1.5, 2.5, 3.5};
        Number[] result = DataUtilities.createNumberArray(data);
        assertEquals(3, result.length);
        assertEquals(1.5, result[0].doubleValue(), 0.00001);
    }

    @Test
    void testCreateNumberArrayNull() {
        assertThrows(IllegalArgumentException.class, () -> DataUtilities.createNumberArray(null));
    }
}
