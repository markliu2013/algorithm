package com.zfwhub.algorithm.leetcode.array;

import java.util.Arrays;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * https://leetcode.com/problems/maximum-subarray/description/
 * https://blog.csdn.net/liu2012huan/article/details/51296635
 */
public class MaximumSubarray {

    /**
     * straightforward, brute force
     */
    public static int maxSubArray(int[] nums) {
        int maxSubArray = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                maxSubArray = Math.max(maxSubArray, sum);
            }
        }
        return maxSubArray;
    }

    /**
     * prefixSum
     */
    public static int maxSubArray1(int[] nums) {
        int[] prefixSums = new int[nums.length];
        // get prefixSum
        prefixSums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixSums[i] = prefixSums[i - 1] + nums[i];
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < prefixSums.length; i++) {
            for (int j = i; j < prefixSums.length; j++) {
                max = Math.max(prefixSums[j] - (i >= 1 ? prefixSums[i - 1] : 0), max);
            }
        }
        return max;
    }

    /**
     * prefixSum's concept
     */
    public static int maxSubArray2(int[] nums) {
        int maxSubArray = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                maxSubArray = Math.max(sum, maxSubArray);
            }
        }
        return maxSubArray;
    }

    /**
     * dynamic programming, low efficiency, deduce from start again
     */
    public static int maxSubArray3(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int lastNum = nums[nums.length - 1];
        //don't choose the last number
        int[] subArray1 = Arrays.copyOfRange(nums, 0, nums.length - 1);
        int max1 = maxSubArray3(subArray1);
        //choose the last number, deduce from start again
        int max2 = Integer.MIN_VALUE;
        for (int i = 0; i <= subArray1.length; i++) {
            int sum = 0;
            for (int j = i; j < subArray1.length; j++) {
                sum += nums[j];
            }
            sum += lastNum;
            max2 = Math.max(sum, max2);
        }
        return Math.max(max1, max2);
    }

    /**
     * divide and conquer
     */
    public static int maxSubArray4(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int mid = nums.length / 2;
        int[] leftNums = Arrays.copyOfRange(nums, 0, mid);
        int[] rightNums = Arrays.copyOfRange(nums, mid, nums.length);
        int maxLeft = maxSubArray4(leftNums);
        int maxRight = maxSubArray4(rightNums);
        //accross mid
        int midMaxLeft = Integer.MIN_VALUE;
        int midMaxRight = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid - 1; i >= 0; i--) {
            sum += nums[i];
            midMaxLeft = Math.max(midMaxLeft, sum);
        }
        sum = 0;
        for (int i = mid; i < nums.length; i++) {
            sum += nums[i];
            midMaxRight = Math.max(midMaxRight, sum);
        }
        int maxMid = midMaxLeft + midMaxRight;
        return Math.max(Math.max(maxLeft, maxRight), maxMid);
    }

    /**
     * dynamic programming - down to top
     */
    public static int maxSubArray5(int[] nums) {
        int[] max = new int[nums.length];
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        max[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = Math.max(sum[i - 1] + nums[i], nums[i]);
            max[i] = Math.max(sum[i], max[i - 1]);
        }
        return max[nums.length - 1];
    }

    /**
     * dynamic programming - down to top - optimize space
     */
    public static int maxSubArray6(int[] nums) {
        int curSum = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            /* end by nums[i]'s maximum */
            if (curSum < 0) {
                curSum = nums[i];
            } else {
                curSum += nums[i];
            }
            /* to i's maximum */
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }

}
