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
    
    @Test
    void lowerEqualsUpper() {
    	double upperMargin = -1.4;
    	newRange = new Range(3, 3);
    	assertEquals(newRange, Range.expand(oldRange, lowerMargin, upperMargin));
    }
}
