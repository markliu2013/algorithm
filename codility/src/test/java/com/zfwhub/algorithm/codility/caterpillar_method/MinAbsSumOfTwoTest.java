package com.zfwhub.algorithm.codility.caterpillar_method;

import static org.junit.Assert.*;

import org.junit.Test;

public class MinAbsSumOfTwoTest {

    @Test
    public void testSolution() {
        int[] A1 = new int[] {1,4,-3};
        int expected1 = 1;
        int[] A2 = new int[] {-8,4,5,-10,3};
        int expected2 = 3;
        int[] A3 = new int[] {0};
        int expected3 = 0;
        assertEquals(expected1, MinAbsSumOfTwo.solution1(A1));
        assertEquals(expected2, MinAbsSumOfTwo.solution1(A2));
        assertEquals(expected3, MinAbsSumOfTwo.solution1(A3));
        assertEquals(expected1, MinAbsSumOfTwo.solution2(A1));
        assertEquals(expected2, MinAbsSumOfTwo.solution2(A2));
        assertEquals(expected3, MinAbsSumOfTwo.solution2(A3));
        assertEquals(expected1, MinAbsSumOfTwo.solution3(A1));
        assertEquals(expected2, MinAbsSumOfTwo.solution3(A2));
        assertEquals(expected3, MinAbsSumOfTwo.solution3(A3));
    }

}
