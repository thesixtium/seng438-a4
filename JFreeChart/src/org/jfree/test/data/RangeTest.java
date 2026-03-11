package org.jfree.test.data;

import org.jfree.test.data.range.*;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
	RangeIntersectsDoubleTest.class,
	RangeIntersectsRangeTest.class,
	RangeHashCodeTest.class,
	RangeEqualsTest.class,
	RangeExpandTest.class,
	RangeConstrainTest.class,
	RangeCombineTest.class,
	RangeCombineIgnoringNaNTest.class,
	Range_contains_test.class,
	OneDoubleArgRangeContstraintTest.class,
	isNanRangeTest.class,
	GetUpperBoundTest.class,
	GetLengthTest.class,
	getCentralValueTest.class,
	ExpandToIncludeTest.class,
	GetLowerBoundTest.class,
	RangeScaleTest.class,
	ThreeArgRangeShiftTest.class,
	toStringTest.class,
	TwoArgRangeShiftTest.class
})
public class RangeTest {}
