package org.jfree.test.data.datautilities;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataUtilitiesRowTotalTest {
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
        assertEquals(6.0, DataUtilities.calculateRowTotal(values, 0), 1e-9);
    }

    @Test
    void calculateRowTotal_NullCell_ShouldIgnoreNull() {
        when(values.getColumnCount()).thenReturn(3);
        when(values.getValue(0, 0)).thenReturn(1.0);
        when(values.getValue(0, 1)).thenReturn(null);
        when(values.getValue(0, 2)).thenReturn(3.0);
        assertEquals(4.0, DataUtilities.calculateRowTotal(values, 0), 1e-9);
    }

    @Test
    void calculateRowTotal_AllNulls_ShouldReturnZero() {
        when(values.getColumnCount()).thenReturn(3);
        when(values.getValue(0, 0)).thenReturn(null);
        when(values.getValue(0, 1)).thenReturn(null);
        when(values.getValue(0, 2)).thenReturn(null);
        assertEquals(0.0, DataUtilities.calculateRowTotal(values, 0), 1e-9);
    }

    @Test
    void calculateRowTotal_ZeroColumns_ShouldReturnZero() {
        when(values.getColumnCount()).thenReturn(0);
        assertEquals(0.0, DataUtilities.calculateRowTotal(values, 0), 1e-9);
        verify(values, never()).getValue(anyInt(), anyInt());
    }

    @Test
    void calculateRowTotal_NullData_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
            DataUtilities.calculateRowTotal(null, 0, new int[]{0}));
    }

    @Test
    void calculateRowTotal_NegativeColCount_ReturnsZero() {
        when(values.getColumnCount()).thenReturn(-1);
        when(values.getValue(0, 0)).thenReturn(99.0);
        assertEquals(0.0, DataUtilities.calculateRowTotal(null == values ? null : values, 0, new int[]{0}), 1e-9);
    }

    @Test
    void calculateRowTotal_NegativeColCount_TotalSetToZero() {
        Values2D v = mock(Values2D.class);
        when(v.getColumnCount()).thenReturn(-5);
        assertEquals(0.0, DataUtilities.calculateRowTotal(v, 0, new int[]{0}), 1e-9);
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
    void calculateRowTotal_ValidCols_EmptyArray_ReturnsZero() {
        when(values.getColumnCount()).thenReturn(3);
        int[] validCols = {};
        assertEquals(0.0, DataUtilities.calculateRowTotal(values, 0, validCols), 1e-9);
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
    void calculateRowTotal_ValidCols_NegatedColDetected() {
        when(values.getColumnCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(10.0);
        when(values.getValue(0, 1)).thenReturn(20.0);
        int[] validCols = {0, 1};
        assertEquals(30.0, DataUtilities.calculateRowTotal(values, 0, validCols), 1e-9);
    }

    @Test
    void calculateRowTotal_ColIndexEqualToColCount_ShouldBeExcluded() {
        when(values.getColumnCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(5.0);
        when(values.getValue(0, 1)).thenReturn(5.0);
        when(values.getValue(0, 2)).thenReturn(99.0);
        int[] validCols = {0, 1, 2};
        assertEquals(10.0, DataUtilities.calculateRowTotal(values, 0, validCols), 1e-9);
    }

    @Test
    void calculateRowTotal_RowIndex_NegationDetected() {
        when(values.getColumnCount()).thenReturn(2);
        when(values.getValue(1, 0)).thenReturn(3.0);
        when(values.getValue(1, 1)).thenReturn(4.0);
        when(values.getValue(-1, 0)).thenReturn(null);
        when(values.getValue(-1, 1)).thenReturn(null);
        int[] validCols = {0, 1};
        assertEquals(7.0, DataUtilities.calculateRowTotal(values, 1, validCols), 1e-9);
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
    void calculateRowTotal_TotalPostIncrementDetected_PreciseSum() {
        when(values.getColumnCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(7.0);
        when(values.getValue(0, 1)).thenReturn(3.0);
        int[] validCols = {0, 1};
        assertEquals(10.0, DataUtilities.calculateRowTotal(values, 0, validCols), 1e-9);
    }

    @Test
    void calculateRowTotal_TotalPostIncrementDetected_RepeatedCall() {
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
    void calculateRowTotal_ReturnValue_ExactAndStable() {
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

    @Test
    void calculateRowTotal_AsymmetricValues_ExactResult() {
        when(values.getColumnCount()).thenReturn(3);
        when(values.getValue(0, 0)).thenReturn(100.0);
        when(values.getValue(0, 1)).thenReturn(0.5);
        when(values.getValue(0, 2)).thenReturn(0.25);
        int[] validCols = {0, 1, 2};
        assertEquals(100.75, DataUtilities.calculateRowTotal(values, 0, validCols), 1e-9);
    }
}