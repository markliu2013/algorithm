package com.zfwhub.algorithm.templates.dp;

import static org.junit.Assert.*;

import org.junit.Test;

public class LongestIncreasingSubsequenceTest {

    @Test
    public void testSolution() {
        int[] nums1 = new int[] {1,2,1,3};
        int expected1 = 3;
        int[] nums2 = new int[] {10,9,2,5,3,7,101,18};
        int expected2 = 4;
        int[] nums3 = new int[] {};
        int expected3 = 0;
        int[] nums4 = new int[] {4,10,4,3,8,9};
        int expected4 = 3;
        assertEquals(expected1, LongestIncreasingSubsequence.solution1(nums1));
        assertEquals(expected2, LongestIncreasingSubsequence.solution1(nums2));
        assertEquals(expected3, LongestIncreasingSubsequence.solution1(nums3));
        assertEquals(expected4, LongestIncreasingSubsequence.solution1(nums4));
        assertEquals(expected1, LongestIncreasingSubsequence.solution2(nums1));
        assertEquals(expected2, LongestIncreasingSubsequence.solution2(nums2));
        assertEquals(expected3, LongestIncreasingSubsequence.solution2(nums3));
        assertEquals(expected4, LongestIncreasingSubsequence.solution2(nums4));
    }

}