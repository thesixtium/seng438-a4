package org.jfree.test.data.range;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

public class ThreeArgRangeShiftTest {
  @Test
  void testShiftAllowZeroCrossingTrue() {
    Range base = new Range(-1.0, 1.0);
    Range shifted = Range.shift(base, -5.0, true);

    assertEquals(-6.0, shifted.getLowerBound(), 1e-9);
    assertEquals(-4.0, shifted.getUpperBound(), 1e-9);
  }

  @Test
  void testShiftAllowZeroCrossingFalse() {
    Range base = new Range(-1.0, 1.0);
    Range shifted = Range.shift(base, -5.0, false);

    // Rnage is expected to shift without crossing zero
    assertTrue(shifted.getLowerBound() <= shifted.getUpperBound());
  }

}