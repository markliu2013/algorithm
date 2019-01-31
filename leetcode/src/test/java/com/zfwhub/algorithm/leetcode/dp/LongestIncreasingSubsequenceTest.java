package com.zfwhub.algorithm.leetcode.dp;

import static org.junit.Assert.*;

import org.junit.Test;

public class LongestIncreasingSubsequenceTest {

    @Test
    public void testSolution() {
        int[] nums1 = new int[] {1,2,1,3};
        int expected1 = 3;
        int[] nums2 = new int[] {10,9,2,5,3,7,101,18};
        int expected2 = 4;
        assertEquals(expected1, LongestIncreasingSubsequence.solution1(nums1));
        assertEquals(expected2, LongestIncreasingSubsequence.solution1(nums2));
    }

}
