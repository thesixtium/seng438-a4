package org.jfree.test.data.range;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

public class OneDoubleArgRangeContstraintTest {
  @Test
  void testConstrainValueInsideRange() {
    Range r = new Range(1.0, 5.0);
    assertEquals(3.0, r.constrain(3.0), 1e-9);
  }

  @Test
  void testConstrainValueAboveUpper() {
    Range r = new Range(1.0, 5.0);
    assertEquals(5.0, r.constrain(10.0), 1e-9);
  }

  @Test
  void testConstrainValueBelowLower() {
    Range r = new Range(1.0, 5.0);
    assertEquals(1.0, r.constrain(-3.0), 1e-9);
  }

  @Test
  void testConstrainAtLowerBoundary() {
    Range r = new Range(1.0, 5.0);
    assertEquals(1.0, r.constrain(1.0), 1e-9);
  }

  @Test
  void testConstrainAtUpperBoundary() {
    Range r = new Range(1.0, 5.0);
    assertEquals(5.0, r.constrain(5.0), 1e-9);
  }

}
