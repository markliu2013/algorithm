package com.zfwhub.algorithm.leetcode.number;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/3sum/description/
 */
public class ThreeSum {
    
    public static List<List<Integer>> solution(int[] nums) {
        if (nums == null || nums.length < 3) {
            return null;
        }
        List<List<Integer>> lists = new ArrayList<List<Integer>>();  
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> list1 = new ArrayList<Integer>();
                        list1.add(nums[i]);
                        list1.add(nums[j]);
                        list1.add(nums[k]);
                        if (!lists.contains(list1)) {
                            lists.add(list1);    
                        }
                    }
                }
            }
        }
        return lists;
    }
    
    public static void main(String[] args) {
        int[] nums = new int[] {-1,0,1,2,-1,-4};
        System.out.println(ThreeSum.solution(nums));
    }

}
