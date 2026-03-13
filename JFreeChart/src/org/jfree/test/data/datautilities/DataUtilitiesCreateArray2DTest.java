package org.jfree.data.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.jfree.data.DataUtilities;

class DataUtilitiesCreateArray2DTest {

    @Test
    void testCreateNumberArray2DValid() {
        double[][] data = {{1.0, 2.0}, {3.0, 4.0}};
        Number[][] result = DataUtilities.createNumberArray2D(data);
        assertAll("2D Array Verification",
            () -> assertEquals(2, result.length),
            () -> assertEquals(1.0, result[0][0].doubleValue(), 0.00001),
            () -> assertEquals(4.0, result[1][1].doubleValue(), 0.00001)
        );
    }

    @Test
    void testCreateNumberArray2DNull() {
        assertThrows(IllegalArgumentException.class, () -> DataUtilities.createNumberArray2D(null));
    }
}
