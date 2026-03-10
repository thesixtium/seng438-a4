package org.jfree.test.data.datautilities;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.DataUtilities;
import org.junit.jupiter.api.Test;

public class DataUtilitiesClone {

	@Test
	void testNotNull() {
        double [][] source = { { 1.0 } };
        double [][] result = DataUtilities.clone( source );

        assertEquals( source.length, result.length );
        for ( int i = 0; i < source.length; i++ ) {
            assertArrayEquals( source[i], result[i], 1e-9 );
        }
    }
}
