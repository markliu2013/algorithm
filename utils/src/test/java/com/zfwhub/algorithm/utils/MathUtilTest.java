package com.zfwhub.algorithm.utils;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class MathUtilTest {

    @Test
    public void testCombine() {
        assertEquals(BigInteger.valueOf(1), MathUtil.combine(3, 0));
        assertEquals(BigInteger.valueOf(3), MathUtil.combine(3, 1));
        assertEquals(BigInteger.valueOf(3), MathUtil.combine(3, 2));
        assertEquals(BigInteger.valueOf(1), MathUtil.combine(3, 3));
        assertEquals(BigInteger.valueOf(1000), MathUtil.combine(1000, 1));
        assertEquals(BigInteger.valueOf(1000), MathUtil.combine(1000, 999));
    }

}
