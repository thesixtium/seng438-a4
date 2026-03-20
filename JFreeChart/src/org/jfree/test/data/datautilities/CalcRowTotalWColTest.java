package org.jfree.test.data.datautilities;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalcRowTotalWColTest {
    private Values2D values;

    @BeforeEach
    void setUp() {
        values = mock(Values2D.class);
    }

    @Test
    void calculateRowTotal_IncludesLastColumn_ShouldSumAllValues() {
        when(values.getColumnCount()).thenReturn(3);
        when(values.getValue(0, 0)).thenReturn(1.0);
        when(values.getValue(0, 1)).thenReturn(2.0);
        when(values.getValue(0, 2)).thenReturn(3.0);
        int[] columns = {0, 2};
        assertEquals(4.0, DataUtilities.calculateRowTotal(values, 0, columns), 1e-9);
    }

    @Test
    void calculateRowTotal_NullCell_ShouldIgnoreNull() {
        when(values.getColumnCount()).thenReturn(3);
        when(values.getValue(0, 0)).thenReturn(1.0);
        when(values.getValue(0, 1)).thenReturn(null);
        when(values.getValue(0, 2)).thenReturn(3.0);
        int[] columns = {1, 2};
        assertEquals(3.0, DataUtilities.calculateRowTotal(values, 0, columns), 1e-9);
    }

    @Test
    void zeroColumnCountbutColumnZero() {
        when(values.getColumnCount()).thenReturn(0);
        int[] columns = {0};
        assertEquals(0.0, DataUtilities.calculateRowTotal(values, 0, columns), 1e-9);
        verify(values, never()).getValue(anyInt(), anyInt());
    }

    @Test
    void colCountLessthanZero() {
        when(values.getColumnCount()).thenReturn(-2);
        int[] columns = {0};
        assertEquals(0.0, DataUtilities.calculateRowTotal(values, 0, columns), 1e-9);
        verify(values, never()).getValue(anyInt(), anyInt());
    }

    @Test
    void colGreaterthcolCount() {
        when(values.getColumnCount()).thenReturn(3);
        when(values.getValue(0, 0)).thenReturn(1.0);
        when(values.getValue(0, 1)).thenReturn(2.0);
        when(values.getValue(0, 2)).thenReturn(3.0);
        int[] columns = {0, 1, 3};
        assertEquals(3.0, DataUtilities.calculateRowTotal(values, 0, columns), 1e-9);
    }

    @Test
    void calculateRowTotal_NullData_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
            DataUtilities.calculateRowTotal(null, 0));
    }

    @Test
    void calculateRowTotal_LoopBoundary_ExtraColumnAccessDetected() {
        when(values.getColumnCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(1.0);
        when(values.getValue(0, 1)).thenReturn(2.0);
        when(values.getValue(0, 2)).thenReturn(99.0);
        assertEquals(3.0, DataUtilities.calculateRowTotal(values, 0), 1e-9);
    }

    @Test
    void calculateRowTotal_RowIndex_NegationDetected() {
        when(values.getColumnCount()).thenReturn(2);
        when(values.getValue(1, 0)).thenReturn(3.0);
        when(values.getValue(1, 1)).thenReturn(4.0);
        when(values.getValue(-1, 0)).thenReturn(null);
        when(values.getValue(-1, 1)).thenReturn(null);
        assertEquals(7.0, DataUtilities.calculateRowTotal(values, 1), 1e-9);
    }

    @Test
    void calculateRowTotal_TotalPostIncrementDetected() {
        when(values.getColumnCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(7.0);
        when(values.getValue(0, 1)).thenReturn(3.0);
        assertEquals(10.0, DataUtilities.calculateRowTotal(values, 0), 1e-9);
    }

    @Test
    void calculateRowTotal_ReturnExactValue() {
        when(values.getColumnCount()).thenReturn(3);
        when(values.getValue(0, 0)).thenReturn(1.0);
        when(values.getValue(0, 1)).thenReturn(2.0);
        when(values.getValue(0, 2)).thenReturn(3.0);
        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals(6.0, result, 1e-9);
        assertNotEquals(7.0, result, 1e-9);
        assertNotEquals(5.0, result, 1e-9);
    }

    @Test
    void calculateRowTotal_ValidCols_ExactIterationCount() {
        when(values.getColumnCount()).thenReturn(3);
        when(values.getValue(0, 0)).thenReturn(10.0);
        when(values.getValue(0, 1)).thenReturn(20.0);
        when(values.getValue(0, 2)).thenReturn(30.0);
        int[] validCols = {0, 1, 2};
        assertEquals(60.0, DataUtilities.calculateRowTotal(values, 0, validCols), 1e-9);
    }

    @Test
    void calculateRowTotal_ValidCols_CorrectColumnAccessed() {
        when(values.getColumnCount()).thenReturn(3);
        when(values.getValue(0, 0)).thenReturn(1.0);
        when(values.getValue(0, 1)).thenReturn(1000.0);
        when(values.getValue(0, 2)).thenReturn(1.0);
        int[] validCols = {0, 2};
        assertEquals(2.0, DataUtilities.calculateRowTotal(values, 0, validCols), 1e-9);
    }

    @Test
    void calculateRowTotal_ValidCols_SecondElementAccessedCorrectly() {
        when(values.getColumnCount()).thenReturn(3);
        when(values.getValue(0, 0)).thenReturn(5.0);
        when(values.getValue(0, 1)).thenReturn(500.0);
        when(values.getValue(0, 2)).thenReturn(7.0);
        int[] validCols = {0, 2};
        assertEquals(12.0, DataUtilities.calculateRowTotal(values, 0, validCols), 1e-9);
    }

    @Test
    void calculateRowTotal_ColIndexEqualToColCount_Excluded() {
        when(values.getColumnCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(5.0);
        when(values.getValue(0, 1)).thenReturn(5.0);
        when(values.getValue(0, 2)).thenReturn(99.0);
        int[] validCols = {0, 1, 2};
        assertEquals(10.0, DataUtilities.calculateRowTotal(values, 0, validCols), 1e-9);
    }

    @Test
    void calculateRowTotal_ColJustBelowColCount_Included() {
        when(values.getColumnCount()).thenReturn(3);
        when(values.getValue(0, 2)).thenReturn(8.0);
        int[] validCols = {2};
        assertEquals(8.0, DataUtilities.calculateRowTotal(values, 0, validCols), 1e-9);
    }

    @Test
    void calculateRowTotal_ReplacedCondWithTrue_AllColsProcessed() {
        when(values.getColumnCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(3.0);
        when(values.getValue(0, 1)).thenReturn(4.0);
        int[] validCols = {0, 1};
        assertEquals(7.0, DataUtilities.calculateRowTotal(values, 0, validCols), 1e-9);
    }

    @Test
    void calculateRowTotal_ColPostIncrementDetected() {
        when(values.getColumnCount()).thenReturn(3);
        when(values.getValue(0, 0)).thenReturn(100.0);
        when(values.getValue(0, 1)).thenReturn(200.0);
        when(values.getValue(0, 2)).thenReturn(300.0);
        int[] validCols = {0, 1, 2};
        assertEquals(600.0, DataUtilities.calculateRowTotal(values, 0, validCols), 1e-9);
    }

    @Test
    void calculateRowTotal_TotalPostIncrementDetected_ValidCols() {
        when(values.getColumnCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(4.0);
        when(values.getValue(0, 1)).thenReturn(6.0);
        int[] validCols = {0, 1};
        double r1 = DataUtilities.calculateRowTotal(values, 0, validCols);
        double r2 = DataUtilities.calculateRowTotal(values, 0, validCols);
        assertEquals(10.0, r1, 1e-9);
        assertEquals(r1, r2, 1e-9);
    }

    @Test
    void calculateRowTotal_ValidCols_ReturnExactAndStable() {
        when(values.getColumnCount()).thenReturn(3);
        when(values.getValue(0, 0)).thenReturn(1.0);
        when(values.getValue(0, 1)).thenReturn(2.0);
        when(values.getValue(0, 2)).thenReturn(3.0);
        int[] validCols = {0, 1, 2};
        double result = DataUtilities.calculateRowTotal(values, 0, validCols);
        assertEquals(6.0, result, 1e-9);
        assertNotEquals(7.0, result, 1e-9);
        assertNotEquals(5.0, result, 1e-9);
    }
}