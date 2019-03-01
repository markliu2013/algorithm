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
        assertEquals(expected1, Peaks.solution1(A1));
        assertEquals(expected2, Peaks.solution1(A2));
        assertEquals(expected1, Peaks.solution2(A1));
        assertEquals(expected2, Peaks.solution2(A2));
    }

}
