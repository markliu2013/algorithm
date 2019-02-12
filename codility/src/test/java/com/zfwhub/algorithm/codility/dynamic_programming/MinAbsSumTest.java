package com.zfwhub.algorithm.codility.dynamic_programming;

import static org.junit.Assert.*;

import org.junit.Test;

public class MinAbsSumTest {
    
    @Test
    public void testSolution() {
        int[] A1 = new int[] {10, 11, 12, 13, 14};
        int expected1 = 6;
        int[] A2 = new int[] {3, 3, 3, 4, 5};
        int expected2 = 0;
        int[] A3 = new int[] {};
        int expected3 = 0;
        int[] A4 = new int[] {3, -4};
        int expected4 = 1;
        int[] A5 = new int[] {2, 4, 1};
        int expected5 = 1;
        int[] A6 = new int[] {1, 5, -2, 5, 2, 3};
        int expected6 = 0;
        int[] A7 = new int[] {1, 5, 2, 5, 2, 3};
        int expected7 = 0;
        int[] A8 = new int[] {1, 2, 2, 3, 5, 5};
        int expected8 = 0;
        assertEquals(expected1, MinAbsSum.solution1(A1));
        assertEquals(expected2, MinAbsSum.solution1(A2));
        assertEquals(expected3, MinAbsSum.solution1(A3));
        assertEquals(expected4, MinAbsSum.solution1(A4));
        assertEquals(expected5, MinAbsSum.solution1(A5));
        assertEquals(expected6, MinAbsSum.solution1(A6));
        assertEquals(expected7, MinAbsSum.solution1(A7));
        assertEquals(expected8, MinAbsSum.solution1(A8));
    }

}
