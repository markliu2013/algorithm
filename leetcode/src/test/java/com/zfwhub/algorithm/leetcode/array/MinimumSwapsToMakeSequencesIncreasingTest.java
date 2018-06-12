package com.zfwhub.algorithm.leetcode.array;

import static org.junit.Assert.*;

import org.junit.Test;

public class MinimumSwapsToMakeSequencesIncreasingTest {

    @Test
    public void testSolution() {
        int[] A1 = new int[] {1,3,5,4};
        int[] B1 = new int[] {1,2,3,7};
        int result1 = 1;
        int[] A2 = new int[] {3,3,8,9,10};
        int[] B2 = new int[] {1,7,4,6,8};
        int result2 = 1;
        assertEquals(result1, MinimumSwapsToMakeSequencesIncreasing.solution(A1, B1));
        assertEquals(result2, MinimumSwapsToMakeSequencesIncreasing.solution(A2, B2));
    }

}
