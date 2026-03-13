package org.jfree.test.data.range;
import static org.junit.Assert.*;
import org.junit.*;
import org.jfree.data.Range;
import org.junit.Test;
public class CombineTests {
	private Range range1;
	private Range range2;
	private Range range3;
	private Range range4;
	private Range range5;
	private Range range6;
	
	@Before
	public void before() {
		range1 = new Range( 0, 1 );
		range2 = new Range( -1, 0 );
		range3 = new Range( -1, 1 );
		range4 = new Range( 0, 2 );
		range5 = new Range( -1, 2 );
		range6 = new Range ( 1, 2 );
	}
	
	@Test
	public void combine_BothRangesNull() {
		try {
			Range expected = null;
			Range actual = Range.combine( null, null );
			assertEquals(
				"Range null combine with Range null to make Range null",
				expected,
				actual
			);
		} catch ( Exception e ) {
			fail( "Failed to combine null with null" );
		}
	}
	
	@Test
	public void combine_Range1Null() {
		try {
			Range expected = range2;
			Range actual = Range.combine( null, range2 );
			assertEquals(
				"Range null combine with Range range2 to make Range range2",
				expected,
				actual
			);
		} catch ( Exception e ) {
			fail( "Failed to combine null with range2" );
		}
	}
	
	@Test
	public void combine_Range2Null() {
		try {
			Range expected = range1;
			Range actual = Range.combine( range1, null );
			assertEquals(
				"Range range1 combine with Range null to make Range range1",
				expected,
				actual
			);
		} catch ( Exception e ) {
			fail( "Failed to combine range1 with null" );
		}
	}
	
	@Test
	public void combine_SameRange() {
		try {
			Range expected = range1;
			Range actual = Range.combine( range1, range1 );
			assertEquals(
				"Range range1 combine with Range range1 to make Range range1",
				expected,
				actual
			);
		} catch ( Exception e ) {
			fail( "Failed to combine range1 with range1" );
		}
	}
	
	@Test
	public void combine_TouchingRangesInOrder() {
		try {
			Range expected = range3;
			Range actual = Range.combine( range1, range2 );
			assertEquals(
				"Range range1 combine with Range range2 to make Range range3",
				expected,
				actual
			);
		} catch ( Exception e ) {
			fail( "Failed to combine range1 with range2" );
		}
	}
	
	@Test
	public void combine_TouchingRangesNotInOrder() {
		try {
			Range expected = range3;
			Range actual = Range.combine( range2, range1 );
			assertEquals(
				"Range range2 combine with Range range1 to make Range range3",
				expected,
				actual
			);
		} catch ( Exception e ) {
			fail( "Failed to combine range2 with range1" );
		}
	}
	
	@Test
	public void combine_IntersectingRangesInOrder() {
		try {
			Range expected = range5;
			Range actual = Range.combine( range3, range4 );
			assertEquals(
				"Range range3 combine with Range range4 to make Range range5",
				expected,
				actual
			);
		} catch ( Exception e ) {
			fail( "Failed to combine range3 with range4" );
		}
	}
	
	@Test
	public void combine_IntersectingRangesNotInOrder() {
		try {
			Range expected = range5;
			Range actual = Range.combine( range4, range3 );
			assertEquals(
				"Range range4 combine with Range range3 to make Range range5",
				expected,
				actual
			);
		} catch ( Exception e ) {
			fail( "Failed to combine range4 with range3" );
		}
	}
	
	@Test
	public void combine_NonIntersectingRangesInOrder() {
		try {
			Range expected = range5;
			Range actual = Range.combine( range2, range6 );
			assertEquals(
				"Range range2 combine with Range range6 to make Range range5",
				expected,
				actual
			);
		} catch ( Exception e ) {
			fail( "Failed to combine range2 with range6" );
		}
	}
	
	@Test
	public void combine_NonIntersectingRangesNotInOrder() {
		try {
			Range expected = range5;
			Range actual = Range.combine( range6, range2 );
			assertEquals(
				"Range range6 combine with Range range2 to make Range range5",
				expected,
				actual
			);
		} catch ( Exception e ) {
			fail( "Failed to combine range6 with range2" );
		}
	}
	
	@Test
	public void combine_RangeInsideRangeInOrder() {
		try {
			Range expected = range5;
			Range actual = Range.combine( range5, range3 );
			assertEquals(
				"Range range5 combine with Range range3 to make Range range5",
				expected,
				actual
			);
		} catch ( Exception e ) {
			fail( "Failed to combine range5 with range3" );
		}
	}
	
	@Test
	public void combine_RangeInsideRangeNotInOrder() {
		try {
			Range expected = range5;
			Range actual = Range.combine( range3, range5 );
			assertEquals(
				"Range range3 combine with Range range5 to make Range range5",
				expected,
				actual
			);
		} catch ( Exception e ) {
			fail( "Failed to combine range3 with range5" );
		}
	}
	
	
}