
package org.jfree.test.data;

import org.jfree.test.data.range.*;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    RangeCombineTest.class,
    RangeConstrainTest.class,
    RangeExpandTest.class,
    Range_contains_test.class,
    RangeEqualsTest.class
})
public class RangeTest {}
