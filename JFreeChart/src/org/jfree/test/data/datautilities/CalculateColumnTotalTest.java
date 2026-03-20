package org.jfree.test.data.datautilities;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculateColumnTotalTest {

    private Values2D values;

    @BeforeEach
    void setUp() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(3);
        when(values.getColumnCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(3);
        when(values.getValue(0, 1)).thenReturn(4);
        when(values.getValue(1, 0)).thenReturn(7);
        when(values.getValue(1, 1)).thenReturn(8);
        when(values.getValue(2, 0)).thenReturn(5);
        when(values.getValue(2, 1)).thenReturn(1);
    }

    @Test
    void ValidColumnValues_SumValues() {
        assertEquals(15, DataUtilities.calculateColumnTotal(values, 0));
    }

    @Test
    void OneValueIsInvalid_ReturnZero() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(3);
        when(values.getValue(1, 0)).thenReturn(null);
        assertEquals(3.0, DataUtilities.calculateColumnTotal(values, 0));
    }

    @Test
    void CalculateOnlySelectedRowsTotal() {
        int[] rows = {0, 3};
        assertEquals(4, DataUtilities.calculateColumnTotal(values, 1, rows));
    }

    @Test
    void CalculateOnlySelectedRows_ContainNull() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(3);
        when(values.getValue(1, 0)).thenReturn(null);
        int[] rows = {0, 1};
        assertEquals(3, DataUtilities.calculateColumnTotal(values, 0, rows));
    }

    @Test
    void NullData_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
            DataUtilities.calculateColumnTotal(null, 0));
    }

    @Test
    void ZeroRows_ReturnsZero() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(0);
        assertEquals(0.0, DataUtilities.calculateColumnTotal(values, 0), 1e-9);
    }

    @Test
    void AllNullValues_ReturnsZero() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(3);
        when(values.getValue(0, 0)).thenReturn(null);
        when(values.getValue(1, 0)).thenReturn(null);
        when(values.getValue(2, 0)).thenReturn(null);
        assertEquals(0.0, DataUtilities.calculateColumnTotal(values, 0), 1e-9);
    }

    @Test
    void ColumnOne_CorrectSum() {
        assertEquals(13.0, DataUtilities.calculateColumnTotal(values, 1), 1e-9);
    }

    @Test
    void NegativeValues_SumCorrectly() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(-4.0);
        when(values.getValue(1, 0)).thenReturn(-6.0);
        assertEquals(-10.0, DataUtilities.calculateColumnTotal(values, 0), 1e-9);
    }

    @Test
    void MixedSignValues_SumCorrectly() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(10.0);
        when(values.getValue(1, 0)).thenReturn(-3.0);
        assertEquals(7.0, DataUtilities.calculateColumnTotal(values, 0), 1e-9);
    }

    @Test
    void LoopBoundary_ExtraRowAccessChangesResult() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(1.0);
        when(values.getValue(1, 0)).thenReturn(2.0);
        when(values.getValue(2, 0)).thenReturn(99.0);
        assertEquals(3.0, DataUtilities.calculateColumnTotal(values, 0), 1e-9);
    }

    @Test
    void LoopStart_Row0MustBeIncluded() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(3);
        when(values.getValue(0, 0)).thenReturn(100.0);
        when(values.getValue(1, 0)).thenReturn(1.0);
        when(values.getValue(2, 0)).thenReturn(1.0);
        assertEquals(102.0, DataUtilities.calculateColumnTotal(values, 0), 1e-9);
    }

    @Test
    void LoopBoundary_LessOrEqualWouldAccessExtraRow() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(1);
        when(values.getValue(0, 0)).thenReturn(5.0);
        when(values.getValue(1, 0)).thenReturn(50.0);
        assertEquals(5.0, DataUtilities.calculateColumnTotal(values, 0), 1e-9);
    }

    @Test
    void LoopBoundary_RowCountZero_NoIterations() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(0);
        assertEquals(0.0, DataUtilities.calculateColumnTotal(values, 0), 1e-9);
    }

    @Test
    void Total_PostIncrementDetected_SingleRow() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(1);
        when(values.getValue(0, 0)).thenReturn(5.0);
        assertEquals(5.0, DataUtilities.calculateColumnTotal(values, 0), 1e-9);
    }

    @Test
    void Total_PostIncrementDetected_MultipleRows() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(3);
        when(values.getValue(0, 0)).thenReturn(10.0);
        when(values.getValue(1, 0)).thenReturn(10.0);
        when(values.getValue(2, 0)).thenReturn(10.0);
        assertEquals(30.0, DataUtilities.calculateColumnTotal(values, 0), 1e-9);
    }

    @Test
    void Total_PostDecrementDetected_PreciseSum() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(7.0);
        when(values.getValue(1, 0)).thenReturn(3.0);
        assertEquals(10.0, DataUtilities.calculateColumnTotal(values, 0), 1e-9);
    }

    @Test
    void Return_ExactValue_PostIncrementStructurallyUnkillable() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(4.0);
        when(values.getValue(1, 0)).thenReturn(6.0);
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(10.0, result, 1e-9);
        assertNotEquals(11.0, result, 1e-9);
        assertNotEquals(9.0, result, 1e-9);
    }

    @Test
    void AsymmetricValues_ExactTotal() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(3);
        when(values.getValue(0, 0)).thenReturn(100.0);
        when(values.getValue(1, 0)).thenReturn(0.5);
        when(values.getValue(2, 0)).thenReturn(0.25);
        assertEquals(100.75, DataUtilities.calculateColumnTotal(values, 0), 1e-9);
    }
}