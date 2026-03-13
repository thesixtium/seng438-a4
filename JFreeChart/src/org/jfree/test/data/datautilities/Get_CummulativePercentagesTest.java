package org.jfree.test.data.datautilities;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;

import org.junit.jupiter.api.Test;

class DataUtilitiesGetCumulativePercentagesTest {

  @Test
  void testNullDataThrowsException_getCumulativePercentages() {
    assertThrows(IllegalArgumentException.class, () -> {
      DataUtilities.getCumulativePercentages(null);
    });
  }

  @Test
  void testTypicalValues_getCumulativePercentages() {
    KeyedValues data = mock(KeyedValues.class);

    when(data.getItemCount()).thenReturn(3);
    when(data.getKey(0)).thenReturn("A");
    when(data.getKey(1)).thenReturn("B");
    when(data.getKey(2)).thenReturn("C");

    when(data.getValue(0)).thenReturn(1.0);
    when(data.getValue(1)).thenReturn(1.0);
    when(data.getValue(2)).thenReturn(2.0);

    KeyedValues result = DataUtilities.getCumulativePercentages(data);

    assertEquals(0.25, result.getValue(0).doubleValue(), 1e-9);
    assertEquals(0.50, result.getValue(1).doubleValue(), 1e-9);
    assertEquals(1.00, result.getValue(2).doubleValue(), 1e-9);
  }

  @Test
  void testNullValueIncluded_getCumulativePercentages() {
    KeyedValues data = mock(KeyedValues.class);

    when(data.getItemCount()).thenReturn(3);
    when(data.getKey(0)).thenReturn("A");
    when(data.getKey(1)).thenReturn("B");
    when(data.getKey(2)).thenReturn("C");

    when(data.getValue(0)).thenReturn(1.0);
    when(data.getValue(1)).thenReturn(null);
    when(data.getValue(2)).thenReturn(3.0);

    KeyedValues result = DataUtilities.getCumulativePercentages(data);

    assertEquals(0.25, result.getValue(0).doubleValue(), 1e-9);
    assertEquals(0.25, result.getValue(1).doubleValue(), 1e-9);
    assertEquals(1.00, result.getValue(2).doubleValue(), 1e-9);
  }
}