package com.zfwhub.algorithm.leetcode.dac;

import java.util.*;

/**
 * Find the median of the two sorted arrays.
 * https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 */
public class MedianOfTwoSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
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

    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        if (nums1[0] >= nums2[nums2.length - 1]) {
            int length = nums1.length + nums2.length;
            if (length % 2 == 0) {
                if (length / 2 - 1 < nums1.length - 1) {
                    return (nums1[(length / 2 - 1) - nums2.length] + (double) nums1[length / 2 - nums2.length]) / 2;
                } else if (length / 2 - 1 > nums1.length - 1) {
                    return (nums2[length / 2 - 1] + (double) nums2[length / 2]) / 2;
                } else {
                    return (nums1[length / 2 - 1] + (double) nums2[length / 2 - nums1.length]) / 2;
                }
            } else {
                if ((length - 1) / 2 <= nums1.length - 1) {
                    return (nums1[(length - 1) / 2 - nums2.length]);
                } else {
                    return (nums2[(length - 1) / 2]);
                }
            }
        }
        if (nums2[0] >= nums1[nums1.length - 1]) {
            return findMedianSortedArrays(nums2, nums1);
        }
        // TODO findMedianSortedArrays2
        return 0;
    }

    // TODO mergetTwoSortedArray
    public static int[] mergetTwoSortedArray(int[] nums1, int[] nums2) {

        return null;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[] { 7, 8, 9, 10 };
        int[] nums2 = new int[] { 1, 3, 8, 9 };
        System.out.println(MedianOfTwoSortedArrays.findMedianSortedArrays(nums1, nums2));
        System.out.println(MedianOfTwoSortedArrays.findMedianSortedArrays2(nums1, nums2));
    }

}
