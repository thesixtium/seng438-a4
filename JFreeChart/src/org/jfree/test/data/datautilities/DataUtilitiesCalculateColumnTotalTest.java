package org.jfree.test.data.datautilities;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Testing the calculateColumnTotal method in DataUtilities class
// In order of the tests:
// - All valid values: should pass
// - Testing out of bound columns to see if error is thrown
// - Testing null values
public class DataUtilitiesCalculateColumnTotalTest {
	private Values2D values;
	
	@BeforeEach
	void setUp() {
		values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(2);
        when(values.getColumnCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(3);
        when(values.getValue(0, 1)).thenReturn(4);
        when(values.getValue(1, 0)).thenReturn(7);
        when(values.getValue(1, 1)).thenReturn(8);
	}
	
    @Test
    void ValidColumnValues_SumValues() {
        double result = DataUtilities.calculateColumnTotal(values, 0);
        double expected = 10;

        assertEquals(expected, result);
    }
    
    @Test
    void UpperOutOfBoundIndex_ThrowIllegalArgumentError() {
    	assertThrows(ArrayIndexOutOfBoundsException.class, ()-> {
    		DataUtilities.calculateColumnTotal(values, -1);
    	});
    }
    
    @Test
    void LowerOutOfBoundIndex_ThrowIllegalArgumentError() {
    	assertThrows(ArrayIndexOutOfBoundsException.class, ()-> {
    		DataUtilities.calculateColumnTotal(values, 2);
    	});
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
        assertEquals(0, result);
        
        assertThrows(IllegalArgumentException.class, ()-> {
        	DataUtilities.calculateColumnTotal(values, 0);
        });
    }
    
    @Test
    void OneValueIsInvalid_ThrowIllegalArgumentError() {
    	values = mock(Values2D.class);
        when(values.getRowCount()).thenReturn(2);
        when(values.getColumnCount()).thenReturn(2);
        when(values.getValue(0, 0)).thenReturn(3);
        when(values.getValue(0, 1)).thenReturn(4);
        when(values.getValue(1, 0)).thenReturn(null);
        when(values.getValue(1, 1)).thenReturn(8);
        
        assertThrows(IllegalArgumentException.class, ()-> {
        	DataUtilities.calculateColumnTotal(values, 0);
        });
    }
    
    
}
