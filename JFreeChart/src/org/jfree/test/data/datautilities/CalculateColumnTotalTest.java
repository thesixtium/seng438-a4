package org.jfree.test.data.datautilities;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculateColumnTotalTest {
// Please note that the second for loop in the calculatecolumnTotal(data, column) method is INFEASIBLE. Coverage will never reach 100%
// Please note that the if (total > 0) statement in the calculatecolumnTotal(data, column, validrows) method is INFEASIBLE. Coverage will never reach 100%
	private Values2D values;
	
	// Initializing sample table to use for test cases
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
    // Taken from A2
    void ValidColumnValues_SumValues() {
    	double result = DataUtilities.calculateColumnTotal(values, 0);
        double expected = 15;

        assertEquals(expected, result);
    }
    
    @Test
    // Taken from A2
    void UpperOutOfBoundIndex_ThrowIllegalArgumentError() {
    	// A bug because per the specifications,
    	assertThrows(ArrayIndexOutOfBoundsException.class, ()-> {
    		DataUtilities.calculateColumnTotal(values, -1);
    	});
    }
    
    @Test
    // Taken from A2
    void LowerOutOfBoundIndex_ThrowIllegalArgumentError() {
    	assertThrows(ArrayIndexOutOfBoundsException.class, ()-> {
    		DataUtilities.calculateColumnTotal(values, 2);
    	});
    }
    
    @Test
    // Taken from A2
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
    }
    
    @Test
    // Taken from A2
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
    
    @Test
    void CalculateOnlySelectedRowsTotal() {
    	int [] rows = {0, 3};
    	double result = DataUtilities.calculateColumnTotal(values, 1, rows);
    	// Answer should only include rows 1 and 3 (for a zero-based index thats index [0] and [2])
    	double expected = 5;
    	assertEquals(expected, result);
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
    	int [] rows = {0, 1};
    	double result = DataUtilities.calculateColumnTotal(values, 0, rows);
    	double expected = 3;
    	assertEquals(expected, result);
    }

}

