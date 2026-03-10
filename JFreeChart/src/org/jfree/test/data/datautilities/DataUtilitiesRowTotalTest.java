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
        // Arrange
        when(values.getColumnCount()).thenReturn(3);
        when(values.getValue(0, 0)).thenReturn(1.0);
        when(values.getValue(0, 1)).thenReturn(2.0);
        when(values.getValue(0, 2)).thenReturn(3.0);  // last column

        // Act
        double total = DataUtilities.calculateRowTotal(values, 0);

        // Assert
        assertEquals(6.0, total, 1e-9);
    }

    @Test
    void calculateRowTotal_NullCell_ShouldIgnoreNull() {
        // Arrange
        when(values.getColumnCount()).thenReturn(3);
        when(values.getValue(0, 0)).thenReturn(1.0);
        when(values.getValue(0, 1)).thenReturn(null); // null cell
        when(values.getValue(0, 2)).thenReturn(3.0);

        // Act
        double total = DataUtilities.calculateRowTotal(values, 0);

        // Assert
        assertEquals(4.0, total, 1e-9);
    }

    @Test
    void calculateRowTotal_AllNulls_ShouldReturnZero() {
        // Arrange
        when(values.getColumnCount()).thenReturn(3);
        when(values.getValue(0, 0)).thenReturn(null);
        when(values.getValue(0, 1)).thenReturn(null);
        when(values.getValue(0, 2)).thenReturn(null);

        // Act
        double total = DataUtilities.calculateRowTotal(values, 0);

        // Assert
        assertEquals(0.0, total, 1e-9);
    }

    @Test
    void calculateRowTotal_ZeroColumns_ShouldReturnZero() {
        // Arrange
        when(values.getColumnCount()).thenReturn(0);

        // Act
        double total = DataUtilities.calculateRowTotal(values, 0);

        // Assert
        assertEquals(0.0, total, 1e-9);
        verify(values, never()).getValue(anyInt(), anyInt());
    }
    
}
