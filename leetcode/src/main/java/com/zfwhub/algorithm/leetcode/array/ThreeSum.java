package com.zfwhub.algorithm.leetcode.array;

import java.util.*;

/**
 * https://leetcode.com/problems/3sum/description/
 */
public class ThreeSum {
    
    // too slow
    public static List<List<Integer>> solution(int[] nums) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 3) {
            return lists;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                for (int k = j+1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> list1 = new ArrayList<Integer>();
                        list1.add(nums[i]);
                        list1.add(nums[j]);
                        list1.add(nums[k]);
                        Collections.sort(list1);
                        if (!lists.contains(list1)) {
                            lists.add(list1);
                        }
                    }
                }
            }
        }
        return lists;
    }
    
    
    
    public static List<List<Integer>> solution2(int[] nums) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 3) {
            return lists;
        }
        // check [0,0,0]
//        int countZero = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] == 0) {
//                countZero++;
//            }
//        }
//        if (countZero >= 3) {
//            List<Integer> list1 = new ArrayList<Integer>();
//            list1.add(0);
//            list1.add(0);
//            list1.add(0);
//            lists.add(list1);
//        }
        HashSet<Integer> set1 = new HashSet<Integer>();
        HashSet<Integer> set2 = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (!set1.contains(nums[i])) {
                set1.add(nums[i]);
            } else {
                set2.add(nums[i]);
            }
        }
        Integer[] nums1 = set1.toArray(new Integer[0]);
        System.out.println(set1);
        System.out.println(set2);
        for (int i = 0; i < nums1.length; i++) {
            for (int j = i+1; j < nums1.length; j++) {
                for (int k = j+1; k < nums1.length; k++) {
                    if (nums1[i] + nums1[j] + nums1[k] == 0) {
                        List<Integer> list1 = new ArrayList<Integer>();
                        list1.add(nums1[i]);
                        list1.add(nums1[j]);
                        list1.add(nums1[k]);
                        lists.add(list1);
                    }
                }
            }
        }
        Iterator<Integer> iter2 = set2.iterator();
        while (iter2.hasNext()) {
            Integer num1 = iter2.next();
            nums1 = set1.toArray(new Integer[0]);
            for (int i = 0; i < nums1.length; i++) {
                if (nums1[i] + num1 + num1 == 0) {
                    List<Integer> list1 = new ArrayList<Integer>();
                    list1.add(num1);
                    list1.add(num1);
                    list1.add(nums1[i]);
                    lists.add(list1);
                }
            }
        }
        return lists;
    }
    
    public static List<List<Integer>> solution3(int[] nums) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 3) {
            return lists;
        }
        HashSet<Integer> set1 = new HashSet<Integer>();
        HashSet<Integer> set2 = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (!set1.contains(nums[i])) {
                set1.add(nums[i]);
            } else {
                set2.add(nums[i]);
            }
        }
        Integer[] nums1 = set1.toArray(new Integer[0]);
        for (int i = 0; i < nums1.length; i++) {
            for (int j = i+1; j < nums1.length; j++) {
                int target = 0 - (nums1[i] + nums1[j]);
                if (set1.contains(target) && target != nums[i] && target != nums[j]) {
                    List<Integer> list1 = new ArrayList<Integer>();
                    list1.add(nums[i]);
                    list1.add(nums[j]);
                    list1.add(target);
                    lists.add(list1);
                }
            }
        }
        /*Iterator<Integer> iter2 = set2.iterator();
        while (iter2.hasNext()) {
            Integer num1 = iter2.next();
            int target = 0 - num1;
            for (int i = 0; i < nums1.length; i++) {
                int complement = target - nums1[i];
                if (set1.contains(complement) && complement != nums1[i]) {
                    List<Integer> list1 = new ArrayList<Integer>();
                    list1.add(num1);
                    list1.add(target);
                    list1.add(complement);
                    lists.add(list1);
                }
            }
        }*/
        return lists;
    }
    
    public static void main(String[] args) {
        int[] nums = new int[] {-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0};
        System.out.println(ThreeSum.solution(nums));
        System.out.println(ThreeSum.solution2(nums));
    }

}
