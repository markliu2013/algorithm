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
        int startIndex = 0;
        int endIndex = nums.length-1;
        int mid = (startIndex + endIndex) / 2;
        while (startIndex <= endIndex) {
            mid = (startIndex + endIndex) / 2;
            if (nums[mid] > target) {
                endIndex = mid-1;
            } else if (nums[mid] < target) {
                startIndex = mid+1;
            } else {
                return mid;
            }
        }
        return -1;
    }
    
    // https://www.zhihu.com/question/36132386/answer/155438728
    public int solution3(int[] nums, int target) {
        int low = 0, high = nums.length-1;
        while (low <= high) { 
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) { low = mid + 1; }
            if (nums[mid] > target) { high = mid - 1; }
            if (nums[mid] == target) { return mid; }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        int[] nums = new int[] {-1,0,3,5,9,12};
        int target = 9;
        int[] nums1 = new int[] {-1,0,3,5,9,12};
        int target1 = 2;
        int[] nums2 = new int[] {5};
        int target2 = 5;
        System.out.println(solution(nums, target));
        System.out.println(solution2(nums, target));
        System.out.println(solution(nums1, target1));
        System.out.println(solution2(nums1, target1));
        System.out.println(solution(nums2, target2));
        System.out.println(solution2(nums2, target2));
    }

}
