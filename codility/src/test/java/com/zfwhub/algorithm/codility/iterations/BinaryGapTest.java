package com.zfwhub.algorithm.codility.iterations;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryGapTest {

    private static BinaryGap binaryGap;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        binaryGap = new BinaryGap();
    }

    @Test
    public void testSolution() throws Exception {
        int result = binaryGap.solution(51712);
        assertEquals(2, result);
    }

}