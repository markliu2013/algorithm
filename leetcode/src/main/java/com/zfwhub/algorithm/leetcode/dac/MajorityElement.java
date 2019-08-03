package com.zfwhub.algorithm.leetcode.dac;

import java.util.*;

// https://leetcode.com/problems/majority-element/
public class MajorityElement {
    
    // Divide And Conquer
    public static int majorityElement(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int mid = nums.length / 2;
        int[] leftNums = Arrays.copyOfRange(nums, 0, mid);
        int[] rightNums = Arrays.copyOfRange(nums, mid, nums.length);
        int majorityLeft = majorityElement(leftNums);
        int majorityRight = majorityElement(rightNums);
        // 整体的majority肯定是左右两个
        if (majorityLeft == majorityRight) {
            return majorityLeft;
        }
        int leftCount1 = 0;//左边不等于majorityLeft的个数
        int leftCount2 = 0;//左边等于majorityRight的个数
        int rightCount1 = 0;//右边不等于majorityRight的个数
        int rightCount2 = 0;//右边等于majorityLeft的个数
        for (int i = 0; i < leftNums.length; i++) {
            if (leftNums[i] != majorityLeft) {
                leftCount1++;
            }
            if (leftNums[i] == majorityRight) {
                leftCount2++;
            }
        }
        for (int i = 0; i < rightNums.length; i++) {
            if (rightNums[i] != majorityRight) {
                rightCount1++;
            }
            if (rightNums[i] == majorityLeft) {
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
