package org.jfree.test.data.datautilities;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GetCumulativePercentagesTests {
    private KeyedValues values;
    private final double delta = 0.00000001;

    @BeforeEach
    public void before() {
        values = mock(KeyedValues.class);
        when( values.getItemCount() ).thenReturn( 3 );
        when( values.getKey( 0 ) ).thenReturn( 0 );
        when( values.getKey( 1 ) ).thenReturn( 1 );
        when( values.getKey( 2 ) ).thenReturn( 2 );
        when( values.getValue( 0 ) ).thenReturn( 5.0 );
        when( values.getValue( 1 ) ).thenReturn( 9.0 );
        when( values.getValue( 2 ) ).thenReturn( 2.0 );
    }

    @Test
    public void getCumulativePercentages_ErrorWithNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            DataUtilities.getCumulativePercentages( null );
        });
    }

    @Test
    public void getCumulativePercentages_numericKeyValue0() {
        KeyedValues result = DataUtilities.getCumulativePercentages( values );
        assertEquals( 0.3125, result.getValue( 0 ).doubleValue(), delta );
    }

    @Test
    public void getCumulativePercentages_numericKeyValue1() {
        KeyedValues result = DataUtilities.getCumulativePercentages( values );
        assertEquals( 0.8750, result.getValue( 1 ).doubleValue(), delta );
    }

    @Test
    public void getCumulativePercentages_numericKeyValue2() {
        KeyedValues result = DataUtilities.getCumulativePercentages( values );
        assertEquals( 1.0000, result.getValue( 2 ).doubleValue(), delta );
    }
}