package com.zfwhub.algorithm.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class NumberUtilTest {

    @Test
    public void testClosestMultiple() {
        assertEquals(3, NumberUtil.closestMultiple(5, 3));
        assertEquals(6, NumberUtil.closestMultiple(7, 3));
        assertEquals(9, NumberUtil.closestMultiple(9, 3));
    }
    
    @Test
    public void testGcd() {
        assertEquals(5, NumberUtil.gcd(10, 5));
        assertEquals(1, NumberUtil.gcd(7, 3));
        assertEquals(1, NumberUtil.gcd(-7, 10));
    }

}
