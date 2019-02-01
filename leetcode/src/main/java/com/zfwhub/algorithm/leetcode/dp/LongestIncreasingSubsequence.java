package com.zfwhub.algorithm.leetcode.dp;

import java.util.Arrays;

// https://leetcode.com/problems/longest-increasing-subsequence/
public class LongestIncreasingSubsequence {
    
    public static int solution1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        DPStatus dpStatus = new DPStatus();
        solution1DP(nums, dpStatus);
        System.out.println(dpStatus);
        return Math.max(dpStatus.increasingCountNotLast, dpStatus.increasingCountWithLast);
    }
    
    private static void solution1DP(int[] nums, DPStatus dpStatus) {
        if (nums.length == 1) {
            dpStatus.increasingCountNotLast = 0;
            dpStatus.increasingCountNotLastEndNumber = null;
            dpStatus.increasingCountWithLast = 1;
            return;
        }
        if (nums.length == 2) {
            dpStatus.increasingCountNotLast = 1;
            dpStatus.increasingCountNotLastEndNumber = nums[0];
            if (nums[0] < nums[1]) {
                dpStatus.increasingCountWithLast = 2;
            } else {
                dpStatus.increasingCountWithLast = 1;
            }
            return;
        }
        int lastNum = nums[nums.length-1];
        int[] subNums = Arrays.copyOfRange(nums, 0, nums.length-1);
        solution1DP(subNums, dpStatus);
        int subIncreasingCountNotLast = dpStatus.increasingCountNotLast;
        int subIncreasingCountNotLastEndNumber = dpStatus.increasingCountNotLastEndNumber;
        int subIncreasingCountWithLast = dpStatus.increasingCountWithLast;
        // 算increasingCountNotLast
        dpStatus.increasingCountNotLast = Math.max(subIncreasingCountNotLast, subIncreasingCountWithLast);
        // 算increasingCountNotLastEndNumber
        if (subIncreasingCountNotLast < subIncreasingCountWithLast) {
            dpStatus.increasingCountNotLastEndNumber = subNums[subNums.length-1];
        } else {
            dpStatus.increasingCountNotLastEndNumber = subIncreasingCountNotLastEndNumber;
        }
        // 算increasingCountWithLast，去数组找所有小于lastNum，选increasingCountWithLast最大的那个数。
        int maxIncreasingCountWithLast = 0;
        for (int i = 0; i < subNums.length; i++) {
            if (subNums[i] < lastNum) {
                DPStatus dpStatus2 = new DPStatus();
                solution1DP(Arrays.copyOfRange(subNums, 0, i+1), dpStatus2);
                maxIncreasingCountWithLast = Math.max(maxIncreasingCountWithLast, dpStatus2.increasingCountWithLast);
            }
        }
        
        int a = subIncreasingCountNotLast;
        if (subIncreasingCountNotLastEndNumber < lastNum) {
            a = a+1;
        }
        int b = subIncreasingCountWithLast;
        if (subNums[subNums.length-1] < lastNum) {
            b = b+1;
        }
        dpStatus.increasingCountWithLast = Math.max(a, b);
    }

    static class DPStatus {
        public Integer increasingCountNotLast; //最后一个不选的情况
        public Integer increasingCountNotLastEndNumber; //最后一个不选，以哪个数字结尾的。
        public Integer increasingCountWithLast; //最后一个必须选的情况
        @Override
        public String toString() {
            return "DPStatus [increasingCountNotLast=" + increasingCountNotLast + ", increasingCountNotLastEndNumber=" + increasingCountNotLastEndNumber + ", increasingCountWithLast=" + increasingCountWithLast + "]";
        }
    }
    
    public static void main(String[] args) {
        int[] nums = new int[] {4,10,4};
        System.out.println(LongestIncreasingSubsequence.solution1(nums));
    }
    
}
