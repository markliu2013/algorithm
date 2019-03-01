package com.zfwhub.algorithm.codility.prime_and_composite_numbers;

import static org.junit.Assert.*;

import org.junit.Test;

public class PeaksTest {

    @Test
    public void testSolution() {
        int[] A1 = new int[] {1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2};
        int expected1 = 3;
        int[] A2 = new int[] {0, 1, 0, 0, 1, 0, 0, 1, 0};
        int expected2 = 3;
        int[] A3 = new int[] {1, 5, 6, 2, 7, 8, 1, 7, 8};
        int expected3 = 1;
        int[] A4 = new int[] {1,3,2,4,2,5,1,2,1,1,1,1,1,1};
        int expected4 = 2;
        assertEquals(expected1, Peaks.solution1(A1));
        assertEquals(expected2, Peaks.solution1(A2));
        assertEquals(expected3, Peaks.solution1(A3));
        assertEquals(expected4, Peaks.solution1(A4));
        assertEquals(expected1, Peaks.solution2(A1));
        assertEquals(expected2, Peaks.solution2(A2));
        assertEquals(expected3, Peaks.solution2(A3));
        assertEquals(expected4, Peaks.solution2(A4));
        assertEquals(expected1, Peaks.solution3(A1));
        assertEquals(expected2, Peaks.solution3(A2));
        assertEquals(expected3, Peaks.solution3(A3));
        assertEquals(expected4, Peaks.solution3(A4));
    }

}
