package com.zfwhub.algorithm.leetcode.array;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
 * https://leetcode.com/articles/two-pointer-technique/
 * https://app.codility.com/programmers/lessons/15-caterpillar_method/
 */
public class RemoveDuplicatesFromSortedArray {

    /**
     * Cheat, Fake
     */
    public static int removeDuplicates(int[] nums) {
        LinkedHashSet<Integer> set = new LinkedHashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int i = 0;
        Iterator<Integer> setIt = set.iterator();
        while (setIt.hasNext()) {
            nums[i] = setIt.next();
            i++;
        }
        return set.size();
    }

    /**
     * two-pointer-technique, in place algorithm
     */
    public static int removeDuplicates2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                index++;
                nums[index] = nums[i];
            }
        }
        return index + 1;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[] { 1, 1, 2 };
        System.out.println(removeDuplicates2(nums1));
        System.out.println(Arrays.toString(nums1));
        int[] nums2 = new int[] { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
        System.out.println(removeDuplicates2(nums2));
        System.out.println(Arrays.toString(nums2));
    }

}
