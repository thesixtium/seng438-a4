package org.jfree.test.data.range;

import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.Test;

public class RangeHashCodeTest {

    @Test
    void testhashCode() {
        Range r = new Range(0.0, 1.0);
        assertNotNull( r.hashCode() );
    }

}
