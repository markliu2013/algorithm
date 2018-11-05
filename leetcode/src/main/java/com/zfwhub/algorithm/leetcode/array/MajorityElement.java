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
        int mid = nums.length / 2;
        int[] leftNums = Arrays.copyOfRange(nums, 0, mid);
        int[] rightNums = Arrays.copyOfRange(nums, mid, nums.length);
        int majorityLeft = majorityElement(leftNums);
        int majorityRight = majorityElement(rightNums);
        if (majorityLeft == majorityRight) {
            return majorityLeft;
        }
        int leftCount1 = 0;//左边不等于majorityLeft的个数
        int leftCount2 = 0;//左边等于majorityRight的个数
        int rightCount1 = 0;//右边不等于majorityRight的个数
        int rightCount2 = 0;//右边等于majorityLeft的个数
        for (int i = 0; i < mid; i++) {
            if (nums[i] != majorityLeft) {
                leftCount1++;
            }
            if (nums[i] == majorityRight) {
                leftCount2++;
            }
        }
        for (int i = mid; i < nums.length; i++) {
            if (nums[i] != majorityRight) {
                rightCount1++;
            }
            if (nums[i] == majorityLeft) {
                rightCount2++;
            }
        }
        int majorityLeftCount = leftNums.length - leftCount1 + rightCount2;
        int majorityRightCount = rightNums.length - rightCount1 + leftCount2;
        if (majorityLeftCount > majorityRightCount) {
            return majorityLeft;
        } else if (majorityLeftCount < majorityRightCount) {
            return majorityRight;
        } else {
            return -1;
        }
    }
    
    public static void main(String[] args) {
        int[] nums1 = new int[] {2,2};
        int[] nums2 = new int[] {2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElement(nums1));
        System.out.println(majorityElement(nums2));
    }

}
