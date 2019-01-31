package com.zfwhub.algorithm.leetcode.dp;

import java.util.Arrays;

// https://leetcode.com/problems/longest-increasing-subsequence/
public class LongestIncreasingSubsequence {
    
    public static int solution1(int[] nums) {
        DPStatus dpStatus = new DPStatus();
        solution1DP(nums, dpStatus);
        return dpStatus.increasingCount;
    }
    
    private static void solution1DP(int[] nums, DPStatus dpStatus) {
        if (nums.length == 1) {
            dpStatus.endNumber = nums[0];
            dpStatus.increasingCount = 1;
            return;
        }
        int lastNum = nums[nums.length-1];
        int[] subNums = Arrays.copyOfRange(nums, 0, nums.length-1);
        solution1DP(subNums, dpStatus);
        int subEndNumber = dpStatus.endNumber;
        int subIncreasingCount = dpStatus.increasingCount;
        if (lastNum > subEndNumber) {
            dpStatus.endNumber = lastNum;
            dpStatus.increasingCount = subIncreasingCount+1;
        }
    }

    static class DPStatus {
        public int endNumber; //结尾的那个数
        public int increasingCount; //
    }
    
    public static void main(String[] args) {
        int[] nums = new int[] {1,2,1,3};
        System.out.println(LongestIncreasingSubsequence.solution1(nums));
    }
    
}
