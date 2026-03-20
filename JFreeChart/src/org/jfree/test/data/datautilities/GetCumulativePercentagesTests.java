package org.jfree.test.data.datautilities;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GetCumulativePercentagesTests {
    private KeyedValues values;
    private final double delta = 0.00000001;

    @BeforeEach
    public void before() {
        values = mock(KeyedValues.class);
        when(values.getItemCount()).thenReturn(3);
        when(values.getKey(0)).thenReturn(0);
        when(values.getKey(1)).thenReturn(1);
        when(values.getKey(2)).thenReturn(2);
        when(values.getValue(0)).thenReturn(5.0);
        when(values.getValue(1)).thenReturn(9.0);
        when(values.getValue(2)).thenReturn(2.0);
    }

    @Test
    public void getCumulativePercentages_ErrorWithNull() {
        assertThrows(IllegalArgumentException.class, () ->
            DataUtilities.getCumulativePercentages(null));
    }

    @Test
    public void getCumulativePercentages_numericKeyValue0() {
        KeyedValues result = DataUtilities.getCumulativePercentages(values);
        assertEquals(0.3125, result.getValue(0).doubleValue(), delta);
    }

    @Test
    public void getCumulativePercentages_numericKeyValue1() {
        KeyedValues result = DataUtilities.getCumulativePercentages(values);
        assertEquals(0.8750, result.getValue(1).doubleValue(), delta);
    }

    @Test
    public void getCumulativePercentages_numericKeyValue2() {
        KeyedValues result = DataUtilities.getCumulativePercentages(values);
        assertEquals(1.0000, result.getValue(2).doubleValue(), delta);
    }

    @Test
    public void getCumulativePercentages_LoopBoundary_ExtraItemAccessDetected() {
        KeyedValues v = mock(KeyedValues.class);
        when(v.getItemCount()).thenReturn(2);
        when(v.getKey(0)).thenReturn(0);
        when(v.getKey(1)).thenReturn(1);
        when(v.getValue(0)).thenReturn(4.0);
        when(v.getValue(1)).thenReturn(6.0);
        when(v.getValue(2)).thenReturn(99.0);
        KeyedValues result = DataUtilities.getCumulativePercentages(v);
        assertEquals(0.4, result.getValue(0).doubleValue(), delta);
        assertEquals(1.0, result.getValue(1).doubleValue(), delta);
    }

    @Test
    public void getCumulativePercentages_FirstItemIncluded_LoopStartDetected() {
        KeyedValues v = mock(KeyedValues.class);
        when(v.getItemCount()).thenReturn(2);
        when(v.getKey(0)).thenReturn(0);
        when(v.getKey(1)).thenReturn(1);
        when(v.getValue(0)).thenReturn(100.0);
        when(v.getValue(1)).thenReturn(100.0);
        KeyedValues result = DataUtilities.getCumulativePercentages(v);
        assertEquals(0.5, result.getValue(0).doubleValue(), delta);
        assertEquals(1.0, result.getValue(1).doubleValue(), delta);
    }

    @Test
    public void getCumulativePercentages_LessOrEqualBoundary_ExactItemCount() {
        KeyedValues v = mock(KeyedValues.class);
        when(v.getItemCount()).thenReturn(1);
        when(v.getKey(0)).thenReturn(0);
        when(v.getValue(0)).thenReturn(10.0);
        when(v.getValue(1)).thenReturn(50.0);
        KeyedValues result = DataUtilities.getCumulativePercentages(v);
        assertEquals(1.0, result.getValue(0).doubleValue(), delta);
    }

    @Test
    public void getCumulativePercentages_ZeroItems_ReturnsEmptyResult() {
        KeyedValues v = mock(KeyedValues.class);
        when(v.getItemCount()).thenReturn(0);
        KeyedValues result = DataUtilities.getCumulativePercentages(v);
        assertNotNull(result);
        assertEquals(0, result.getItemCount());
    }

    @Test
    public void getCumulativePercentages_NullValue_IsIgnored() {
        KeyedValues v = mock(KeyedValues.class);
        when(v.getItemCount()).thenReturn(3);
        when(v.getKey(0)).thenReturn(0);
        when(v.getKey(1)).thenReturn(1);
        when(v.getKey(2)).thenReturn(2);
        when(v.getValue(0)).thenReturn(5.0);
        when(v.getValue(1)).thenReturn(null);
        when(v.getValue(2)).thenReturn(5.0);
        KeyedValues result = DataUtilities.getCumulativePercentages(v);
        assertEquals(0.5, result.getValue(0).doubleValue(), delta);
        assertEquals(0.5, result.getValue(1).doubleValue(), delta);
        assertEquals(1.0, result.getValue(2).doubleValue(), delta);
    }

    @Test
    public void getCumulativePercentages_TotalPostIncrementDetected() {
        KeyedValues v = mock(KeyedValues.class);
        when(v.getItemCount()).thenReturn(2);
        when(v.getKey(0)).thenReturn(0);
        when(v.getKey(1)).thenReturn(1);
        when(v.getValue(0)).thenReturn(3.0);
        when(v.getValue(1)).thenReturn(7.0);
        KeyedValues result = DataUtilities.getCumulativePercentages(v);
        assertEquals(0.3, result.getValue(0).doubleValue(), delta);
        assertEquals(1.0, result.getValue(1).doubleValue(), delta);
    }

    @Test
    public void getCumulativePercentages_SecondLoopBoundary_AllItemsProcessed() {
        KeyedValues v = mock(KeyedValues.class);
        when(v.getItemCount()).thenReturn(3);
        when(v.getKey(0)).thenReturn(0);
        when(v.getKey(1)).thenReturn(1);
        when(v.getKey(2)).thenReturn(2);
        when(v.getValue(0)).thenReturn(2.0);
        when(v.getValue(1)).thenReturn(2.0);
        when(v.getValue(2)).thenReturn(2.0);
        KeyedValues result = DataUtilities.getCumulativePercentages(v);
        assertEquals(1.0 / 3.0, result.getValue(0).doubleValue(), delta);
        assertEquals(2.0 / 3.0, result.getValue(1).doubleValue(), delta);
        assertEquals(1.0,       result.getValue(2).doubleValue(), delta);
    }

    @Test
    public void getCumulativePercentages_NullInSecondLoop_DoesNotAffectPercentages() {
        KeyedValues v = mock(KeyedValues.class);
        when(v.getItemCount()).thenReturn(2);
        when(v.getKey(0)).thenReturn(0);
        when(v.getKey(1)).thenReturn(1);
        when(v.getValue(0)).thenReturn(null);
        when(v.getValue(1)).thenReturn(10.0);
        KeyedValues result = DataUtilities.getCumulativePercentages(v);
        assertEquals(0.0, result.getValue(0).doubleValue(), delta);
        assertEquals(1.0, result.getValue(1).doubleValue(), delta);
    }

    @Test
    public void getCumulativePercentages_RunningTotalPostIncrementDetected() {
        KeyedValues v = mock(KeyedValues.class);
        when(v.getItemCount()).thenReturn(3);
        when(v.getKey(0)).thenReturn(0);
        when(v.getKey(1)).thenReturn(1);
        when(v.getKey(2)).thenReturn(2);
        when(v.getValue(0)).thenReturn(1.0);
        when(v.getValue(1)).thenReturn(1.0);
        when(v.getValue(2)).thenReturn(1.0);
        KeyedValues result = DataUtilities.getCumulativePercentages(v);
        assertEquals(1.0 / 3.0, result.getValue(0).doubleValue(), delta);
        assertEquals(2.0 / 3.0, result.getValue(1).doubleValue(), delta);
        assertEquals(1.0,       result.getValue(2).doubleValue(), delta);
    }

    @Test
    public void getCumulativePercentages_AsymmetricValues_AllPercentagesExact() {
        KeyedValues v = mock(KeyedValues.class);
        when(v.getItemCount()).thenReturn(4);
        when(v.getKey(0)).thenReturn(0);
        when(v.getKey(1)).thenReturn(1);
        when(v.getKey(2)).thenReturn(2);
        when(v.getKey(3)).thenReturn(3);
        when(v.getValue(0)).thenReturn(10.0);
        when(v.getValue(1)).thenReturn(20.0);
        when(v.getValue(2)).thenReturn(30.0);
        when(v.getValue(3)).thenReturn(40.0);
        KeyedValues result = DataUtilities.getCumulativePercentages(v);
        assertEquals(0.10, result.getValue(0).doubleValue(), delta);
        assertEquals(0.30, result.getValue(1).doubleValue(), delta);
        assertEquals(0.60, result.getValue(2).doubleValue(), delta);
        assertEquals(1.00, result.getValue(3).doubleValue(), delta);
    }

    @Test
    public void getCumulativePercentages_SingleItem_IsOneHundredPercent() {
        KeyedValues v = mock(KeyedValues.class);
        when(v.getItemCount()).thenReturn(1);
        when(v.getKey(0)).thenReturn(0);
        when(v.getValue(0)).thenReturn(42.0);
        KeyedValues result = DataUtilities.getCumulativePercentages(v);
        assertEquals(1.0, result.getValue(0).doubleValue(), delta);
    }

    @Test
    public void getCumulativePercentages_RepeatedCallsGiveSameResult() {
        KeyedValues r1 = DataUtilities.getCumulativePercentages(values);
        KeyedValues r2 = DataUtilities.getCumulativePercentages(values);
        assertEquals(r1.getValue(0).doubleValue(), r2.getValue(0).doubleValue(), delta);
        assertEquals(r1.getValue(1).doubleValue(), r2.getValue(1).doubleValue(), delta);
        assertEquals(r1.getValue(2).doubleValue(), r2.getValue(2).doubleValue(), delta);
    }
}