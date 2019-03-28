package com.zfwhub.algorithm.codility.sorting;

import static org.junit.Assert.*;

import org.junit.Test;

public class NumberOfDiscIntersectionsTest {

    @Test
    public void testSolution() {
        int[] A1 = new int[] {1, 5, 2, 1, 4, 0};
        int expected1 = 11;
        int[] A2 = new int[] {1, 10, 100, 1};
        int expected2 = 5;
        assertEquals(expected1, NumberOfDiscIntersections.solution1(A1));
        assertEquals(expected2, NumberOfDiscIntersections.solution1(A2));
    }

}
