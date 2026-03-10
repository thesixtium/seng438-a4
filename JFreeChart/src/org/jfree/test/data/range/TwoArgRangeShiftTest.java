package org.jfree.test.data.range;

import org.jfree.data.Range;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TwoArgRangeShiftTest {

  @Test
  void testShiftNullBaseThrowsException() {
    assertThrows(IllegalArgumentException.class, () -> {
      Range.shift(null, 5.0);
    });
  }

  @Test
  void testShiftPositiveDelta_NoZeroCrossing() {
    Range base = new Range(1.0, 5.0);
    Range shifted = Range.shift(base, 2.0);

    assertEquals(3.0, shifted.getLowerBound(), 1e-9);
    assertEquals(7.0, shifted.getUpperBound(), 1e-9);
  }

  @Test
  void testShiftNegativeDelta_NoZeroCrossing() {
    Range base = new Range(2.0, 6.0);
    Range shifted = Range.shift(base, -1.0);

    assertEquals(1.0, shifted.getLowerBound(), 1e-9);
    assertEquals(5.0, shifted.getUpperBound(), 1e-9);

  }
}