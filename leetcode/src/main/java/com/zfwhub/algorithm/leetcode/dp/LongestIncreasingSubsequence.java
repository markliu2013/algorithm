package com.zfwhub.algorithm.leetcode.dp;

import java.util.Arrays;

// https://leetcode.com/problems/longest-increasing-subsequence/
public class LongestIncreasingSubsequence {
    
    public static int solution1(int[] nums) {
        DPStatus dpStatus = new DPStatus();
        solution1DP(nums, dpStatus);
        return Math.max(dpStatus.increasingCountNotLast, dpStatus.increasingCountWithLast);
    }
    
    private static void solution1DP(int[] nums, DPStatus dpStatus) {
        if (nums.length == 1) {
            dpStatus.increasingCountNotLast = 0;
            dpStatus.increasingCountNotLastEndNumber = null;
            dpStatus.increasingCountWithLast = 1;
            return;
        }
        int lastNum = nums[nums.length-1];
        int[] subNums = Arrays.copyOfRange(nums, 0, nums.length-1);
        solution1DP(subNums, dpStatus);
        int subIncreasingCount = dpStatus.increasingCountNotLast;
        int subIncreasingCountWithLast = dpStatus.increasingCountWithLast;
        if (lastNum > subNums[subNums.length-1]) {
        }
        
    }

    static class DPStatus {
        public Integer increasingCountNotLast;
        public Integer increasingCountNotLastEndNumber;
        public Integer increasingCountWithLast;
    }
    
    public static void main(String[] args) {
        int[] nums = new int[] {10,9,2,5,3,7};
        System.out.println(LongestIncreasingSubsequence.solution1(nums));
    }
    
}
