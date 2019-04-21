package com.zfwhub.algorithm.leetcode.binary_search;

import java.util.*;

import com.zfwhub.algorithm.utils.ArrayUtil;

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
        int[] nums = mergeTwoSortedArray(nums1, nums2);
        if (nums.length % 2 == 0) {
            return (nums[nums.length / 2 - 1] + (double) nums[nums.length / 2]) / 2;
        } else {
            return nums[(nums.length - 1) / 2];
        }
    }
    
    /**
     * 合并两个有序数组
     * @param nums1
     * @param nums2
     * @return
     */
    private static int[] mergeTwoSortedArray(int[] nums1, int[] nums2) {
        // 如果某个数组是空，则直接返回另一个数组
        if (nums1.length == 0) {
            return nums2.clone();
        }
        if (nums2.length == 0) {
            return nums1.clone();
        }
        int[] result = new int[nums1.length + nums2.length];
        // 逐个循环nums2，使用二分查找往nums1中插入。
        int nums1CurrentIndex = 0; //控制nums1中的插入点。
        int resultCurrentIndex = 0; //逐步填充result，填充到哪个位置了。
        for (int i = 0; i < nums2.length; i++) {
            // 在nums1中寻找插入点。要找一个位置，满足 x <= value 的最大x
            int index = ArrayUtil.upperBound(nums1, nums1CurrentIndex, nums1.length,  nums2[i]);
            // 从nums1CurrentIndex到index区间都复制到result
            System.arraycopy(nums1, nums1CurrentIndex, result, resultCurrentIndex, index-nums1CurrentIndex);
            // 维护nums1CurrentIndex 和 resultCurrentIndex
            resultCurrentIndex += (index-nums1CurrentIndex);
            nums1CurrentIndex += (index-nums1CurrentIndex);
            // nums2当前值，放到result。
            result[resultCurrentIndex] = nums2[i];
            resultCurrentIndex++;
        }
        // nums1有可能还没跑完，则复制nums1剩下的到result。
        if (nums1CurrentIndex < nums1.length) {
            System.arraycopy(nums1, nums1CurrentIndex, result, resultCurrentIndex, nums1.length-nums1CurrentIndex);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[] { 7, 8, 9, 10 };
        int[] nums2 = new int[] { 1, 3, 8, 9 };
        System.out.println(MedianOfTwoSortedArrays.solution1(nums1, nums2));
        System.out.println(MedianOfTwoSortedArrays.solution2(nums1, nums2));
    }

}
