package com.zfwhub.algorithm.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class NumberUtilTest {

    @Test
    public void testClosestNumber() {
        assertEquals(3, NumberUtil.closestNumber(5, 3));
        assertEquals(6, NumberUtil.closestNumber(7, 3));
        assertEquals(9, NumberUtil.closestNumber(9, 3));
    }

}
