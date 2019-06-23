package com.zfwhub.algorithm.leetcode.other;

import java.util.Arrays;

// https://leetcode.com/problems/longest-consecutive-sequence/
public class LongestConsecutiveSequence {
    
    public static int solution1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int result = 1;
        int current = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i-1] == 1) {
                current++;
            } else if (nums[i] - nums[i-1] == 0) {
                
            } else {
                result = Math.max(result, current);
                current = 1;
            }
        }
        return Math.max(result, current);
    }
    
    public static void main(String[] args) {
        System.out.println(solution1(new int[] {0})); //0
        System.out.println(solution1(new int[] {1,2,0,1})); //3
        System.out.println(solution1(new int[] {-1,0})); //2
        System.out.println(solution1(new int[] {100, 4, 200, 1, 3, 2})); //4
        System.out.println(solution1(new int[] {9,1,4,7,3,-1,0,5,8,-1,6})); //7
    }

}
