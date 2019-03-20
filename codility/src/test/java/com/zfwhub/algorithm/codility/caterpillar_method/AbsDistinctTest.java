package com.zfwhub.algorithm.codility.caterpillar_method;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbsDistinctTest {

    @Test
    public void test() {
        int[] A1 = new int[] {-5,-3,-1,0,3,6};
        int expected1 = 5;
        int[] A2 = new int[] {-10};
        int expected2 = 1;
        int[] A3 = new int[] {1,3};
        int expected3 = 2;
        int[] A4 = new int[] {1,1};
        int expected4 = 1;
        int[] A5 = new int[] {-2147483648, -1, 0, 1};
        int expected5 = 3;
        int[] A6 = new int[] {1,1,1,1};
        int expected6 = 1;
        int[] A7 = new int[] {0};
        int expected7 = 1;
        int[] A8 = new int[] {7,8,8};
        int expected8 = 2;
        assertEquals(expected1, AbsDistinct.solution1(A1));
        assertEquals(expected2, AbsDistinct.solution1(A2));
        assertEquals(expected3, AbsDistinct.solution1(A3));
        assertEquals(expected4, AbsDistinct.solution1(A4));
        assertEquals(expected5, AbsDistinct.solution1(A5));
        assertEquals(expected6, AbsDistinct.solution1(A6));
        assertEquals(expected7, AbsDistinct.solution1(A7));
        assertEquals(expected8, AbsDistinct.solution1(A8));
        assertEquals(expected1, AbsDistinct.solution2(A1));
        assertEquals(expected2, AbsDistinct.solution2(A2));
        assertEquals(expected3, AbsDistinct.solution2(A3));
        assertEquals(expected4, AbsDistinct.solution2(A4));
        assertEquals(expected5, AbsDistinct.solution2(A5));
        assertEquals(expected6, AbsDistinct.solution2(A6));
        assertEquals(expected7, AbsDistinct.solution2(A7));
        assertEquals(expected8, AbsDistinct.solution2(A8));
    }

}
