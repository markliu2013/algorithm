package com.zfwhub.algorithm.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class MathUtilTest {

    @Test
    public void testCombine() {
        assertEquals(1, MathUtil.combine(3, 0));
        assertEquals(3, MathUtil.combine(3, 1));
        assertEquals(3, MathUtil.combine(3, 2));
        assertEquals(1, MathUtil.combine(3, 3));
        assertEquals(1000, MathUtil.combine(1000, 1));
        assertEquals(1000, MathUtil.combine(1000, 999));
    }

}
