package com.zfwhub.algorithm.leetcode.array;

import org.junit.Test;

import static org.junit.Assert.*;

public class MaximumSubarrayTest {
    @Test
    public void testMaxSubArray() throws Exception {

    }

    @Test
    public void testMaxSubArray1() throws Exception {
        int[] nums = new int[] {1};
        int result = MaximumSubarray.maxSubArray1(nums);
        System.out.println(result);
    }

}