package com.zfwhub.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/4sum/description/
 */
public class FourSum {
    
    public static List<List<Integer>> solution(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 3) {
            return lists;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    for (int l = k+1; l < nums.length; l++) {
                        if (nums[i] + nums[j] + nums[k] + nums[l] == target) {
                            List<Integer> list1 = new ArrayList<Integer>();
                            list1.add(nums[i]);
                            list1.add(nums[j]);
                            list1.add(nums[k]);
                            list1.add(nums[l]);
                            Collections.sort(list1);
                            if (!lists.contains(list1)) {
                                lists.add(list1);
                            }
                        }
                    }
                }
            }
        }
        return lists;
    }
    
}
