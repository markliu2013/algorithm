package com.zfwhub.algorithm.leetcode.binary_search;

/**
 * https://leetcode.com/problems/binary-search/
 */
public class BinarySearch {
    
    public static int solution(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (target == nums[i]) {
                return i;
            }
        }
        return -1;
    }
    
    public static int solution2(int[] nums, int target) {
        int mid = nums.length / 2;
        while (mid > 0 && mid < nums.length) {
            if (nums[mid] > target) {
                mid = mid / 2;
            } else if (nums[mid] < target) {
                mid = (mid + nums.length) / 2;
            } else {
                return mid;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        int[] nums = new int[] {-1,0,3,5,9,12};
        int target = 9;
        int[] nums1 = new int[] {-1,0,3,5,9,12};
        int target1 = 2;
        System.out.println(solution(nums, target));
       // System.out.println(solution2(nums, target));
        System.out.println(solution(nums1, target1));
        System.out.println(solution2(nums1, target1));
    }

}
