package com.zfwhub.algorithm.leetcode.array;

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
    
    // TODO RemoveDuplicatesFromSortedArray
    public static int removeDuplicates2(int[] nums) {
        return 0;
    }
    
    public static void main(String[] args) {
        
        
    }

}
