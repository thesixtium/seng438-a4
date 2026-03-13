package org.jfree.test.data.datautilities;

import static org.junit.jupiter.api.Assertions.*;
import org.jfree.data.DataUtilities;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Will test  CreateNumberArray2D
 * Interested in if it accurately returns a Number[][]of the primitive
 * double[][]
 */

public class DataUtCreateNumArr2D{
	static private DataUtilities exampleDataUt;
	static private double [][] arr;
	
	@BeforeAll
	static void setUp() {
        arr= new double[5][9];
    }

	@Test
	void testSameNoOfRows() {
		Number[][] createdNumbers=  DataUtilities.createNumberArray2D(arr);
		assertEquals(arr.length, createdNumbers.length, "Expected same number of rows");
	}
	
	@Test
	void testSameNoOfColumns() {
		Number[][] createdNumbers=  DataUtilities.createNumberArray2D(arr);
		assertEquals(arr[0].length, createdNumbers[0].length, "Expected same number of columns");
	}
	
	@Test
	void testValueValidAtBeginning() { //test if value remains valid after conversion
		arr[0][0]= 1.3;
		Number[][] createdNumbers=  DataUtilities.createNumberArray2D(arr);
		assertEquals(1.3, createdNumbers[0][0], "Expected value 1.3 at beginning");
	}
	@Test
	void testValueValidAtEnd() { //test if value remains valid after conversion
		arr[4][8]= Double.MAX_VALUE;
		Number[][] createdNumbers=  DataUtilities.createNumberArray2D(arr);
		assertEquals(Double.MAX_VALUE, createdNumbers[4][8].doubleValue(), 1e-9, "Expected value is max of double at end");
	}
	@Test
	void testIncompleteSpecifications() {//test what happens if you don't specify number of columns in source
		double [][] incmpArr= new double[2][];
		Number[][] createdNumbers=  DataUtilities.createNumberArray2D(incmpArr);
		assertEquals(incmpArr[0].length, createdNumbers[0].length, "Expected same number of columns");
	}
}
