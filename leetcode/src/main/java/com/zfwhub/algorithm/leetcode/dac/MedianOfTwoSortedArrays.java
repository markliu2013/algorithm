package com.zfwhub.algorithm.leetcode.dac;

import java.util.*;

// https://leetcode.com/problems/median-of-two-sorted-arrays/description/
public class MedianOfTwoSortedArrays {

    public static double solution1(int[] nums1, int[] nums2) {
        int[] nums = new int[nums1.length + nums2.length];
        System.arraycopy(nums1, 0, nums, 0, nums1.length);
        System.arraycopy(nums2, 0, nums, nums1.length, nums2.length);
        Arrays.sort(nums);
        if (nums.length % 2 == 0) {
            return (nums[nums.length / 2 - 1] + (double) nums[nums.length / 2]) / 2;
        } else {
            return nums[(nums.length - 1) / 2];
        }
    }

    public static double solution2(int[] nums1, int[] nums2) {
        
        return 0;
    }

    // TODO mergetTwoSortedArray
    public static int[] mergetTwoSortedArray(int[] nums1, int[] nums2) {
        
        return null;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[] { 7, 8, 9, 10 };
        int[] nums2 = new int[] { 1, 3, 8, 9 };
        System.out.println(MedianOfTwoSortedArrays.solution1(nums1, nums2));
        System.out.println(MedianOfTwoSortedArrays.solution2(nums1, nums2));
    }

}
