package org.jfree.test.data.datautilities;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.DataUtilities;
import org.junit.jupiter.api.Test;

public class DataUtilitiesEqual {

	@Test
	void testAisNull() {
        double [][] a = null;
        double [][] b = null;

        assertEquals( true, DataUtilities.equal( a, b ) );
    }

	@Test
	void testBisNull() {
        double [][] a = { { 1.0 } };
        double [][] b = null;

        assertEquals( false, DataUtilities.equal( a, b ) );
    }

	@Test
	void testDifferentLengths() {
        double [][] a = { { 1.0 } };
        double [][] b = { { 1.0 }, { 2.0 } };

        assertEquals( false, DataUtilities.equal( a, b ) );
    }

	@Test
	void testSameArray() {
        double [][] a = { { 1.0 }, { 2.0 } };
        double [][] b = { { 1.0 }, { 2.0 } };

        assertEquals( true, DataUtilities.equal( a, b ) );
    }

	@Test
	void testDifferentArray() {
        double [][] a = { { 1.0 }, { 1.0 } };
        double [][] b = { { 1.0 }, { 2.0 } };

        assertEquals( false, DataUtilities.equal( a, b ) );
    }

	
}
