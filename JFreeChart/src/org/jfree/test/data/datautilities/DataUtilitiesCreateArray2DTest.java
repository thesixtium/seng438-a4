package org.jfree.test.data.datautilities;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.jfree.data.DataUtilities;

public class DataUtilitiesCreateArray2DTest {

    @Test
    void testCreateNumberArray2DValid() {
        double[][] data = {{1.0, 2.0}, {3.0, 4.0}};
        Number[][] result = DataUtilities.createNumberArray2D(data);
        assertAll(
            () -> assertEquals(2, result.length),
            () -> assertEquals(1.0, result[0][0].doubleValue(), 1e-9),
            () -> assertEquals(4.0, result[1][1].doubleValue(), 1e-9)
        );
    }

    @Test
    void testCreateNumberArray2DNull() {
        assertThrows(IllegalArgumentException.class, () ->
            DataUtilities.createNumberArray2D(null));
    }

    @Test
    void createNumberArray2D_ThreeRows_AllConverted() {
        double[][] data = {{1.0}, {2.0}, {3.0}};
        Number[][] result = DataUtilities.createNumberArray2D(data);
        assertEquals(3, result.length);
        assertEquals(1.0, result[0][0].doubleValue(), 1e-9);
        assertEquals(2.0, result[1][0].doubleValue(), 1e-9);
        assertEquals(3.0, result[2][0].doubleValue(), 1e-9);
    }

    @Test
    void createNumberArray2D_CorrectSlotAssignment() {
        double[][] data = {{10.0}, {20.0}, {30.0}};
        Number[][] result = DataUtilities.createNumberArray2D(data);
        assertEquals(10.0, result[0][0].doubleValue(), 1e-9);
        assertEquals(20.0, result[1][0].doubleValue(), 1e-9);
        assertEquals(30.0, result[2][0].doubleValue(), 1e-9);
    }
}