package org.jfree.test.data.range;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Testing the expand method in Range class
// In order of the tests:
// - All valid values: should pass
// - Testing null values
// - Testing a non numeric number
// - Testing negative margin: should pass
// - Testing zero range: should pass
public class RangeExpandTest {
    private Range oldRange;
    private Range newRange;
    private double lowerMargin;
    private double upperMargin;

    @BeforeEach
    void setUp() {
        oldRange = new Range(5, 10);
        lowerMargin = 0.40;
    	upperMargin = 1.60;
    }
    
    @Test
    // Taken from A2
    void RangeExpands_WithValidNumbers() {
    	newRange = new Range(3, 18);
    	assertEquals(newRange, Range.expand(oldRange, lowerMargin, upperMargin));
    }
    
    @Test
    // Taken from A2
    void RangeIsNull_ThrowIllegalArgumentError() {
    	assertThrows(IllegalArgumentException.class, ()-> {
    		Range.expand(null, lowerMargin, upperMargin);
    	});
    }
    
    @Test
    // Taken from A2
    void LowerMarginNotNumeric_ThrowIllegalArgumentError() {
    	assertThrows(IllegalArgumentException.class, ()-> {
    		Range.expand(oldRange, 'a', upperMargin);
    	});
    }
    
    @Test
    // Taken from A2
    void RangeExpands_WithNegativeLowerMargin() {
    	double lowerMargin = -0.40;
    	newRange = new Range(7, 18);
    	assertEquals(newRange, Range.expand(oldRange, lowerMargin, upperMargin));
    }
    
    @Test
    // Taken from A2
    void ZeroRange_NoChangeWithExpand() {
    	oldRange = new Range(5, 5);
    	newRange = new Range(5, 5);
    	assertEquals(newRange, Range.expand(oldRange, lowerMargin, upperMargin));
    }
    
    // For statement coverage, the if (lower > upper) is never called, therefore a new test is added for better coverage
    @Test
    void lowerGreaterthanUpper() {
    	double upperMargin = -1.60;
    	newRange = new Range(0.7, 0.7);
    	assertEquals(newRange, Range.expand(oldRange, lowerMargin, upperMargin));
    }
    
    @Test
    void lowerEqualsUpper() {
    	double upperMargin = -1.4;
    	newRange = new Range(3, 3);
    	assertEquals(newRange, Range.expand(oldRange, lowerMargin, upperMargin));
    }
}
