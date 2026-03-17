package org.jfree.test.data.datautilities;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.DataUtilities;
import org.junit.jupiter.api.Test;

public class DataUtilitiesEqual {

	@Test
	void testAandBisNull() {
        double [][] a = null;
        double [][] b = null;

        assertTrue(DataUtilities.equal( a, b ) );
    }
	
	@Test
	void testAisNull() {
		double [][] a = null;
		double [][] b = { { 1.0 } };

        assertFalse(DataUtilities.equal(a, b));
	}

	@Test
	void testBisNull() {
        double [][] a = { { 1.0 } };
        double [][] b = null;

        assertFalse(DataUtilities.equal(a, b));
    }

	@Test
	void testDifferentLengths() {
        double [][] a = { { 1.0 } };
        double [][] b = { { 1.0 }, { 2.0 } };

        assertFalse(DataUtilities.equal( a, b ) );
    }
	
	@Test
	void testDifferentInnerLengths() {
		double [][] a = { { 1.0, 5.0, 7.0 } };
        double [][] b = { { 1.0 } };

        assertFalse(DataUtilities.equal(a, b));
	}

	@Test
	void testSameArray() {
        double [][] a = { { 1.0 }, { 2.0 } };
        double [][] b = { { 1.0 }, { 2.0 } };

        assertTrue(DataUtilities.equal( a, b ) );
    }
	
	@Test
	void testSameInnerArray() {
		double [][] a = { { 1.0, 2.0 }, { 3.0, 6.0 } };
        double [][] b = { { 1.0, 2.0 }, { 3.0, 6.0 } };

        assertTrue(DataUtilities.equal( a, b ) );
	}

	@Test
	void testDifferentArray() {
        double [][] a = { { 1.0 }, { 1.0 } };
        double [][] b = { { 1.0 }, { 2.0 } };

        assertFalse(DataUtilities.equal( a, b ) );
    }
	
	@Test
	void testDifferentInnerArray() {
		double [][] a = { { 1.0, 4.0 } };
        double [][] b = { { 1.0, 5.0 } };

        assertFalse(DataUtilities.equal( a, b ) );
	}

	
}
