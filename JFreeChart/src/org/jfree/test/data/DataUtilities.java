package org.jfree.test.data;

import org.jfree.test.data.datautilities.*;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    GetCumulativePercentagesTests.class,
    DataUtCreateNumArr2D.class,
    DataUtilitiesCalculateColumnTotalTest.class,
    DataUtilitiesRowTotalTest.class,
    DataUtilitiesNumberArray.class
})
public class DataUtilitiesTest {}