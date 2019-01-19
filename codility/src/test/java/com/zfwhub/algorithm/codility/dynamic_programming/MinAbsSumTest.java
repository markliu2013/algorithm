package com.zfwhub.algorithm.codility.dynamic_programming;

import static org.junit.Assert.*;

import org.junit.Test;

public class MinAbsSumTest {
    
    @Test
    public void testSolution() {
        int[] A1 = new int[] {10, 11, 12, 13, 14};
        int result1 = 6;
        int[] A2 = new int[] {3, 3, 3, 4, 5};
        int result2 = 0;
        int[] A3 = new int[] {};
        int result3 = 0;
        int[] A4 = new int[] {3, -4};
        int result4 = 1;
        int[] A5 = new int[] {2, 4, 1};
        int result5 = 1;
        int[] A6 = new int[] {1, 5, -2, 5, 2, 3};
        int result6 = 0;
        int[] A7 = new int[] {1, 5, 2, 5, 2, 3};
        int result7 = 0;
        int[] A8 = new int[] {1, 2, 2, 3, 5, 5};
        int result8 = 2;
        assertEquals(MinAbsSum.solution2(A1), result1);
        assertEquals(MinAbsSum.solution2(A2), result2);
        assertEquals(MinAbsSum.solution2(A3), result3);
        assertEquals(MinAbsSum.solution2(A4), result4);
        assertEquals(MinAbsSum.solution2(A5), result5);
        assertEquals(MinAbsSum.solution2(A6), result6);
        assertEquals(MinAbsSum.solution2(A7), result7);
        assertEquals(MinAbsSum.solution2(A8), result8);
    }

}
