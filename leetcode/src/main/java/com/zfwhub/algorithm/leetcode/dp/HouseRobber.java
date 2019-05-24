package com.zfwhub.algorithm.leetcode.dp;

import java.util.Arrays;

// https://leetcode.com/problems/house-robber/
public class HouseRobber {
    
    // 动态规划 递归
    public static int solution1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int lastItem = nums[nums.length-1];
        int[] subNums = Arrays.copyOfRange(nums, 0, nums.length-1);
        int[] subNums2 = Arrays.copyOfRange(nums, 0, nums.length-2);
        return Math.max(solution1(subNums), solution1(subNums2)+lastItem);
    }
    
    // 动态规划 递推
    public static int solution2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i]);
        }
        return dp[dp.length-1];
    }
    
    
    public static void main(String[] args) {
        System.out.println(solution1(new int[] {1,2,3,1}));
        System.out.println(solution1(new int[] {2,7,9,3,1}));
        System.out.println(solution2(new int[] {1,2,3,1}));
        System.out.println(solution2(new int[] {2,7,9,3,1}));
    }

}
