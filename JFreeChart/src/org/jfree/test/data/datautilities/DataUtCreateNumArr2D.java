package org.jfree.test.data.datautilities;
import static org.junit.jupiter.api.Assertions.*;
import org.jfree.data.DataUtilities;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DataUtCreateNumArr2D {
    static private double[][] arr;

    @BeforeAll
    static void setUp() {
        arr = new double[5][9];
    }

    @Test
    void testSameNoOfRows() {
        Number[][] createdNumbers = DataUtilities.createNumberArray2D(arr);
        assertEquals(arr.length, createdNumbers.length);
    }

    @Test
    void testSameNoOfColumns() {
        Number[][] createdNumbers = DataUtilities.createNumberArray2D(arr);
        assertEquals(arr[0].length, createdNumbers[0].length);
    }

    @Test
    void testValueValidAtBeginning() {
        arr[0][0] = 1.3;
        Number[][] createdNumbers = DataUtilities.createNumberArray2D(arr);
        assertEquals(1.3, createdNumbers[0][0].doubleValue(), 1e-9);
    }

    @Test
    void testValueValidAtEnd() {
        arr[4][8] = Double.MAX_VALUE;
        Number[][] createdNumbers = DataUtilities.createNumberArray2D(arr);
        assertEquals(Double.MAX_VALUE, createdNumbers[4][8].doubleValue(), 1e-9);
    }

    @Test
    void createNumberArray2D_LoopBoundary_AllRowsConverted() {
        double[][] data = {{1.0}, {2.0}, {3.0}};
        Number[][] result = DataUtilities.createNumberArray2D(data);
        assertEquals(3, result.length);
        assertEquals(1.0, result[0][0].doubleValue(), 1e-9);
        assertEquals(2.0, result[1][0].doubleValue(), 1e-9);
        assertEquals(3.0, result[2][0].doubleValue(), 1e-9);
    }

    @Test
    void createNumberArray2D_LastRowCorrect() {
        double[][] data = {{10.0}, {20.0}, {99.0}};
        Number[][] result = DataUtilities.createNumberArray2D(data);
        assertEquals(99.0, result[2][0].doubleValue(), 1e-9);
    }

    @Test
    void createNumberArray2D_EachRowInCorrectSlot() {
        double[][] data = {{1.0, 2.0}, {3.0, 4.0}, {5.0, 6.0}};
        Number[][] result = DataUtilities.createNumberArray2D(data);
        assertEquals(1.0, result[0][0].doubleValue(), 1e-9);
        assertEquals(3.0, result[1][0].doubleValue(), 1e-9);
        assertEquals(5.0, result[2][0].doubleValue(), 1e-9);
    }

    @Test
    void createNumberArray2D_NullInput_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
            DataUtilities.createNumberArray2D(null));
    }

    @Test
    void createNumberArray2D_AsymmetricRows_CorrectValues() {
        double[][] data = {{100.0, 200.0, 300.0}, {400.0, 500.0}};
        Number[][] result = DataUtilities.createNumberArray2D(data);
        assertEquals(3, result[0].length);
        assertEquals(2, result[1].length);
        assertEquals(300.0, result[0][2].doubleValue(), 1e-9);
        assertEquals(500.0, result[1][1].doubleValue(), 1e-9);
    }
}