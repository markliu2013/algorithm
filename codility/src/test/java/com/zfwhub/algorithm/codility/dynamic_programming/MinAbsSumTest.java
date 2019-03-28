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
        int[] A9 = new int[] {91, 92, 93, 94, 95, 96, 97};
        int expected9 = 82;
        assertEquals(expected1, MinAbsSum.solution1(A1));
        assertEquals(expected2, MinAbsSum.solution1(A2));
        assertEquals(expected3, MinAbsSum.solution1(A3));
        assertEquals(expected4, MinAbsSum.solution1(A4));
        assertEquals(expected5, MinAbsSum.solution1(A5));
        assertEquals(expected6, MinAbsSum.solution1(A6));
        assertEquals(expected7, MinAbsSum.solution1(A7));
        assertEquals(expected8, MinAbsSum.solution1(A8));
        assertEquals(expected9, MinAbsSum.solution1(A9));
        assertEquals(expected1, MinAbsSum.solution2(A1));
        assertEquals(expected2, MinAbsSum.solution2(A2));
        assertEquals(expected3, MinAbsSum.solution2(A3));
        assertEquals(expected4, MinAbsSum.solution2(A4));
        assertEquals(expected5, MinAbsSum.solution2(A5));
        assertEquals(expected6, MinAbsSum.solution2(A6));
        assertEquals(expected7, MinAbsSum.solution2(A7));
        assertEquals(expected8, MinAbsSum.solution2(A8));
        assertEquals(expected9, MinAbsSum.solution2(A9));
        assertEquals(expected1, MinAbsSum.solution3(A1));
        assertEquals(expected2, MinAbsSum.solution3(A2));
        assertEquals(expected3, MinAbsSum.solution3(A3));
        assertEquals(expected4, MinAbsSum.solution3(A4));
        assertEquals(expected5, MinAbsSum.solution3(A5));
        assertEquals(expected6, MinAbsSum.solution3(A6));
        assertEquals(expected7, MinAbsSum.solution3(A7));
        assertEquals(expected8, MinAbsSum.solution3(A8));
//        assertEquals(expected9, MinAbsSum.solution3(A9));
        assertEquals(expected1, MinAbsSum.solution4(A1));
        assertEquals(expected2, MinAbsSum.solution4(A2));
        assertEquals(expected3, MinAbsSum.solution4(A3));
        assertEquals(expected4, MinAbsSum.solution4(A4));
        assertEquals(expected5, MinAbsSum.solution4(A5));
        assertEquals(expected6, MinAbsSum.solution4(A6));
        assertEquals(expected7, MinAbsSum.solution4(A7));
        assertEquals(expected8, MinAbsSum.solution4(A8));
        assertEquals(expected9, MinAbsSum.solution3(A9));
    }

}
