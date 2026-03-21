package org.jfree.test.data.range;

import static org.junit.jupiter.api.Assertions.*;
import org.jfree.data.Range;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
/**
 * Upper and Lower bound denoted by (x,y)
 * fail("Not yet implemented");
 */
public class RangeConstrainTest{
	static private Range exampleRange;
	
	@BeforeAll
	static void setUp() {
        exampleRange = new Range(5, 13);
    }
	@Test
void constructorInvalidRangeThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> {
        new Range(13, 5);
    });
}
	@Test
	void constrainAtLowerBound() {
		assertEquals(5.0, exampleRange.constrain(5), 1e-9,
                "The return of constrain should be 5");
	}
	
	@Test
	void constrainAtUpperBound() {
		assertEquals(13.0, exampleRange.constrain(13), 1e-9,
                "The return of constrain should be 13");
	}
	
	@Test
	void constrainInsideBound() {
		assertEquals(7, exampleRange.constrain(7), 1e-9,
                "The return of constrain should be 7 as it is within Range");
	}
	
	@Test
	void constrainNegativeValueonPositiveBound() {
		assertEquals(5, exampleRange.constrain(-109), 1e-9,
                "The return of constrain should be 5 as that is the closest bound");
	}
	
	@Test
	void constrainPositiveValueonNegativeBound() {
		Range tempRange= new Range(-12,-5);
		assertEquals(-5, tempRange.constrain(56), 1e-9,
                "The return of constrain should be -5 as that is the closest bound");
	}
	
	@Test
	void constrainStressTest(){
		Range tempRange= new Range(-12,-5);
		assertEquals(-12, tempRange.constrain(-Double.MAX_VALUE), 1e-9,
                "The return of constrain should be -12 as that is the closest bound");
	}
	
	@Test
    void constrain_line210_repeatedCallInsideRangeIsSame() {
        double first  = exampleRange.constrain(9);
        double second = exampleRange.constrain(9);
        assertEquals(first, second, 1e-9);
    }

    @Test
    void constrain_line210_exactResultForValueInsideRange() {
        assertEquals(9.0, exampleRange.constrain(9), 1e-9);
    }

    @Test
    void constrain_line211_valueInsideRange_containsCallMatters() {
        assertEquals(8.0, exampleRange.constrain(8), 1e-9);
    }

    @Test
    void constrain_line211_valueAtLowerBound_NotConstrainedFurther() {
        assertEquals(5.0, exampleRange.constrain(5), 1e-9);
    }

    @Test
    void constrain_line211_valueAtUpperBound_NotConstrainedFurther() {
        assertEquals(13.0, exampleRange.constrain(13), 1e-9);
    }

    @Test
    void constrain_line211_postIncrementOnValueDetected() {
        double r1 = exampleRange.constrain(5);
        double r2 = exampleRange.constrain(5);
        assertEquals(5.0, r1, 1e-9);
        assertEquals(r1, r2, 1e-9);
    }

    @Test
    void constrain_line212_valueExactlyAtUpper_NotCappedByBranch() {
        assertEquals(13.0, exampleRange.constrain(13), 1e-9);
    }

    @Test
    void constrain_line212_valueJustAboveUpper_ReturnUpper() {
        assertEquals(13.0, exampleRange.constrain(13.001), 1e-9);
    }

    @Test
    void constrain_line212_repeatedCallAboveUpperIsSame() {
        double r1 = exampleRange.constrain(20);
        double r2 = exampleRange.constrain(20);
        assertEquals(13.0, r1, 1e-9);
        assertEquals(r1, r2, 1e-9);
    }

    @Test
    void constrain_line213_repeatedCallAboveUpperGivesSameResult() {
        Range r = new Range(5, 13);
        assertEquals(13.0, r.constrain(20), 1e-9);
        assertEquals(13.0, r.constrain(20), 1e-9);
    }

    @Test
    void constrain_line213_exactUpperBoundReturned() {
        Range r = new Range(5, 100);
        assertEquals(100.0, r.constrain(200), 1e-9);
    }

    @Test
    void constrain_line215_valueExactlyAtLower_NotConstrainedToLower() {
        assertEquals(5.0, exampleRange.constrain(5), 1e-9);
    }

    @Test
    void constrain_line215_valueJustBelowLower_ReturnLower() {
        assertEquals(5.0, exampleRange.constrain(4.999), 1e-9);
    }

    @Test
    void constrain_line215_valueAboveUpper_ReturnsUpperNotLower() {
        assertEquals(13.0, exampleRange.constrain(50), 1e-9);
    }

    @Test
    void constrain_line215_valueWellBelowLower_ReturnsLower() {
        assertEquals(5.0, exampleRange.constrain(0), 1e-9);
    }

    @Test
    void constrain_line215_negatedLowerDetected() {
        assertEquals(5.0, exampleRange.constrain(3), 1e-9);
    }

    @Test
    void constrain_line215_repeatedCallBelowLowerIsSame() {
        double r1 = exampleRange.constrain(-10);
        double r2 = exampleRange.constrain(-10);
        assertEquals(5.0, r1, 1e-9);
        assertEquals(r1, r2, 1e-9);
    }

    @Test
    void constrain_line216_repeatedCallBelowLowerGivesSameLower() {
        Range r = new Range(5, 13);
        assertEquals(5.0, r.constrain(-10), 1e-9);
        assertEquals(5.0, r.constrain(-10), 1e-9);
    }

    @Test
    void constrain_line216_exactLowerBoundReturned() {
        Range r = new Range(7, 20);
        assertEquals(7.0, r.constrain(-100), 1e-9);
    }

    @Test
    void constrain_line219_exactReturnForBelowRange() {
        assertEquals(5.0, exampleRange.constrain(-1), 1e-9);
    }

    @Test
    void constrain_line219_exactReturnForAboveRange() {
        assertEquals(13.0, exampleRange.constrain(99), 1e-9);
    }

    @Test
    void constrain_line219_exactReturnForInsideRange() {
        assertEquals(11.0, exampleRange.constrain(11), 1e-9);
    }

    @Test
    void constrain_line219_repeatedReturnIsStable() {
        assertEquals(5.0,  exampleRange.constrain(0),  1e-9);
        assertEquals(13.0, exampleRange.constrain(20), 1e-9);
        assertEquals(9.0,  exampleRange.constrain(9),  1e-9);
    }
	
}
