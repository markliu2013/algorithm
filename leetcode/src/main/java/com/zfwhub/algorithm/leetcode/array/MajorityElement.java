package com.zfwhub.algorithm.leetcode.array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/majority-element/
 */
public class MajorityElement {
    
    public static int majorityElement(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            if (nums[0] == nums[1]) {
                return nums[0];
            } else {
                return Integer.MIN_VALUE;
            }
        }
        int mid = nums.length / 2;
        int[] leftNums = Arrays.copyOfRange(nums, 0, mid);
        int[] rightNums = Arrays.copyOfRange(nums, mid, nums.length);
        int majorityLeft = majorityElement(leftNums);
        int majorityRight = majorityElement(rightNums);
        if (leftNums.length > rightNums.length) {
            if (majorityLeft != Integer.MIN_VALUE) {
                return majorityLeft;
            } else {
                return majorityRight;
            }
        } else {
            if (majorityRight != Integer.MIN_VALUE) {
                return majorityRight;
            } else {
                return majorityLeft;
            }
        }
    }
    
    public static void main(String[] args) {
        int[] nums1 = new int[] {6,6,6,7,7};
        int[] nums2 = new int[] {2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElement(nums1));
        System.out.println(majorityElement(nums2));
    }

}
