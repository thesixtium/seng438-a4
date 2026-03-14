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
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(15, result);
    }

    @Test
    void OneValueIsInvalid_ReturnZero() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(2);
        when(values.getColumnCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(3);
        when(values.getValue(0, 1)).thenReturn(4);
        when(values.getValue(1, 0)).thenReturn(null);
        when(values.getValue(1, 1)).thenReturn(8);
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(3.0, result);
    }

    @Test
    void CalculateOnlySelectedRowsTotal() {
        int[] rows = {0, 3};
        double result = DataUtilities.calculateColumnTotal(values, 1, rows);
        assertEquals(4, result);
    }

    @Test
    void CalculateOnlySelectedRows_ContainNull() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(2);
        when(values.getColumnCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(3);
        when(values.getValue(0, 1)).thenReturn(4);
        when(values.getValue(1, 0)).thenReturn(null);
        when(values.getValue(1, 1)).thenReturn(8);
        int[] rows = {0, 1};
        double result = DataUtilities.calculateColumnTotal(values, 0, rows);
        assertEquals(3, result);
    }

    @Test
    void NullData_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
            DataUtilities.calculateColumnTotal(null, 0));
    }

    @Test
    void SingleRow_CorrectValue() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(1);
        when(values.getValue(0, 0)).thenReturn(42.0);
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(42.0, result, 1e-9);
    }

    @Test
    void ZeroRows_ReturnsZero() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(0);
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(0.0, result, 1e-9);
    }

    @Test
    void AllNullValues_ReturnsZero() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(3);
        when(values.getValue(0, 0)).thenReturn(null);
        when(values.getValue(1, 0)).thenReturn(null);
        when(values.getValue(2, 0)).thenReturn(null);
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(0.0, result, 1e-9);
    }

    @Test
    void ExactSumValue_KillsPostIncrementOnTotal() {
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(15.0, result, 1e-9);
        double result2 = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(15.0, result2, 1e-9);
    }

    @Test
    void TwoRows_ExactSum_KillsPostIncrementOnTotal() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(10.0);
        when(values.getValue(1, 0)).thenReturn(20.0);
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(30.0, result, 1e-9);
    }

    @Test
    void LoopBoundary_RowCountOne_IteratesExactlyOnce() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(1);
        when(values.getValue(0, 0)).thenReturn(7.0);
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(7.0, result, 1e-9);
    }

    @Test
    void LoopBoundary_RowCountTwo_BothRowsSummed() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(1.0);
        when(values.getValue(1, 0)).thenReturn(1.0);
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(2.0, result, 1e-9);
    }

    @Test
    void NegatedRowIndex_ThrowsOrProducesWrongResult_KillsMutation() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(3);
        when(values.getValue(0, 0)).thenReturn(1.0);
        when(values.getValue(1, 0)).thenReturn(2.0);
        when(values.getValue(2, 0)).thenReturn(3.0);
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(6.0, result, 1e-9);
    }

    @Test
    void ColumnOne_CorrectSum_KillsColumnIndexMutations() {
        double result = DataUtilities.calculateColumnTotal(values, 1);
        assertEquals(13.0, result, 1e-9);
    }

    @Test
    void AsymmetricValues_ExactTotal_KillsSubstitutionMutations() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(3);
        when(values.getValue(0, 0)).thenReturn(100.0);
        when(values.getValue(1, 0)).thenReturn(0.5);
        when(values.getValue(2, 0)).thenReturn(0.25);
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(100.75, result, 1e-9);
    }

    @Test
    void NegativeValues_SumCorrectly() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(-4.0);
        when(values.getValue(1, 0)).thenReturn(-6.0);
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(-10.0, result, 1e-9);
    }

    @Test
    void MixedSignValues_SumCorrectly() {
        values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(10.0);
        when(values.getValue(1, 0)).thenReturn(-3.0);
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(7.0, result, 1e-9);
    }
}