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
        int[] nums = ArrayUtil.mergeTwoSortedArray(nums1, nums2);
        if (nums.length % 2 == 0) {
            return (nums[nums.length / 2 - 1] + (double) nums[nums.length / 2]) / 2;
        } else {
            return nums[(nums.length - 1) / 2];
        }
    }
    
    private static double getMedian(int[] nums) {
        if (nums.length % 2 == 0) {
            return (nums[nums.length / 2 - 1] + (double) nums[nums.length / 2]) / 2;
        } else {
            return nums[(nums.length - 1) / 2];
        }
    }
    
    private static int[] mergeTwoSortedArray(int[] nums1, int[] nums2) {
        int[] nums = new int[nums1.length + nums2.length]; // merged
        // 1. nums1 or nums2 empty, just copy the other one.
        // 2. the last int in nums1 is bigger than nums2's first, just combine nums1 and nums2. and vice verse the same
        if (nums1.length == 0) {
            nums = nums2.clone();
        } else if (nums2.length == 0) {
            nums = nums1.clone();
        } else if (nums1[nums1.length-1] <= nums2[0]) {
            System.arraycopy(nums1, 0, nums, 0, nums1.length);
            System.arraycopy(nums2, 0, nums, nums1.length, nums2.length);
        } else if (nums2[nums2.length-1] <= nums1[0]) {
            System.arraycopy(nums2, 0, nums, 0, nums2.length);
            System.arraycopy(nums1, 0, nums, nums2.length, nums1.length);
        } else {
            int numsIndex = 0;
            int nums1Index = 0;
            int nums2Index = 0;
            while (numsIndex < nums.length) {
                if (nums1Index == nums1.length) {
                    System.arraycopy(nums2, nums2Index, nums, numsIndex, nums.length-numsIndex);
                    break;
                }
                if (nums2Index == nums2.length) {
                    System.arraycopy(nums1, nums1Index, nums, numsIndex, nums.length-numsIndex);
                    break;
                }
                if (nums1[nums1Index] < nums2[nums2Index]) {
                    nums[numsIndex] = nums1[nums1Index];
                    nums1Index++;
                } else {
                    nums[numsIndex] = nums2[nums2Index];
                    nums2Index++;
                }
                numsIndex++;
            }
        }
        return nums;
    }
    
    public static double solution3(int[] nums1, int[] nums2) {
        int[] nums = mergeTwoSortedArray(nums1, nums2);
        return getMedian(nums);
    }
    
    public static double solution4(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int m = l1 + l2;
        if (l1 == 0) {
            return getMedian(nums2);
        }
        if (l2 == 0) {
            return getMedian(nums1);
        }
        int count = 0; // count merging index
        int i1 = 0; // scanning nums1 index
        int i2 = 0; // scanning nums2 index
        if (m % 2 == 0) { // have 2 mid number2
            Integer mid1 = null;
            Integer mid2 = null;
            while (count <= m / 2) {
                mid1 = mid2;
                if (i1 == nums1.length) {
                    mid2 = nums2[i2];
                    i2++;
                    count++;
                    continue;
                }
                if (i2 == nums2.length) {
                    mid2 = nums1[i1];
                    i1++;
                    count++;
                    continue;
                }
                if (nums1[i1] < nums2[i2]) {
                    mid2 = nums1[i1];
                    i1++;
                    count++;
                } else {
                    mid2 = nums2[i2];
                    i2++;
                    count++;
                }
            }
            return (mid1 + (double) mid2) / 2;
        } else { // get the only mid number
            Integer mid = null;
            while (count <= m / 2) {
                if (i1 == nums1.length) {
                    mid = nums2[i2];
                    i2++;
                    count++;
                    continue;
                }
                if (i2 == nums2.length) {
                    mid = nums1[i1];
                    i1++;
                    count++;
                    continue;
                }
                if (nums1[i1] < nums2[i2]) {
                    mid = nums1[i1];
                    i1++;
                    count++;
                } else {
                    mid = nums2[i2];
                    i2++;
                    count++;
                }
            }
            return mid;
        }
    }
    
    public static double solution5(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int m = l1 + l2;
        int i1 = 0; // scanning nums1 index
        int i2 = 0; // scanning nums2 index
        Integer mid1 = null;
        Integer mid2 = null;
        for (int i = 0; i <= m / 2 ; i++) {
            mid1 = mid2;
            if (i1 == l1) {
                mid2 = nums2[i2];
                i2++;
                continue;
            }
            if (i2 == l2) {
                mid2 = nums1[i1];
                i1++;
                continue;
            }
            if (nums1[i1] < nums2[i2]) {
                mid2 = nums1[i1];
                i1++;
            } else {
                mid2 = nums2[i2];
                i2++;
            }
        }
        if (m % 2 == 0) {
            return (mid1 + (double) mid2) / 2;
        } else {
            return mid2;
        }
    }
    
public static double solution6(int[] nums1, int[] nums2) {
    int l1 = nums1.length;
    int l2 = nums2.length;
    int m = l1 + l2;
    int i1 = 0; // scanning nums1 index
    int i2 = 0; // scanning nums2 index
    Integer mid1 = null;
    Integer mid2 = null;
    for (int i = 0; i <= m / 2 ; i++) {
        mid1 = mid2;
        // move nums1 or nums2,
        if (i1 < l1 && (i2 >= l2 || nums1[i1] < nums2[i2])) {
            mid2 = nums1[i1];
            i1++;
        } else {
            mid2 = nums2[i2];
            i2++;
        }
    }
    if (m % 2 == 0) {
        return (mid1 + (double) mid2) / 2;
    } else {
        return mid2;
    }
}
    
    
    public static void main(String[] args) {
//        int[] nums1 = new int[] { 7, 8, 9, 10 };
//        int[] nums2 = new int[] { 1, 3, 8, 9 };
//        System.out.println(solution1(nums1, nums2));
//        System.out.println(solution2(nums1, nums2));
        
        int[] nums1 = new int[] { 2,3,4 };
        int[] nums2 = new int[] { 1 };
        System.out.println(solution5(nums1, nums2));
        
    }

}
