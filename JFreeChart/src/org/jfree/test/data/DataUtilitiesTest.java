package org.jfree.test.data;

import org.jfree.test.data.datautilities.*;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
	DataUtilitiesClone.class,
	DataUtilitiesCreateArray2DTest.class,
	DataUtCreateNumArr2D.class,
	CalculateColumnTotalTest.class,
	CalcRowTotalWColTest.class,
	DataUtilitiesCreateArrayTest.class,
	DataUtilitiesEqual.class,
	DataUtilitiesNumberArray.class,
	DataUtilitiesRowTotalTest.class,
	GetCumulativePercentagesTests.class
})
public class DataUtilitiesTest {}
