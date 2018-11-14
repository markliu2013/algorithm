package com.zfwhub.algorithm.leetcode.array;

import java.util.*;

/**
 * https://leetcode.com/problems/4sum/description/
 * https://www.cnblogs.com/shytong/p/5138629.html
 */
public class FourSum {
    
    // 暴力解法
    public static List<List<Integer>> solution(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4) {
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
    
    // slower than the above solution?
    public static List<List<Integer>> solution2(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4) {
            return lists;
        }
        HashSet<List<Integer>> sets = new HashSet<List<Integer>>();
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
                            sets.add(list1);
                        }
                    }
                }
            }
        }
        lists = new ArrayList<List<Integer>>(sets);
        return lists;
    }
    
    public static List<List<Integer>> solution3(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4) {
            return lists;
        }
        Map<Integer, List<Integer[]>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                Integer sum = nums[i] + nums[j];
                if (map.containsKey(sum)) {
                    map.get(sum).add(new Integer[] {i, j});
                } else {
                    List<Integer[]> list = new ArrayList<>();
                    list.add(new Integer[] {i, j});
                    map.put(sum, list);
                }
            }
        }
        Integer[] sumArray = map.keySet().toArray(new Integer[0]);
        for (int i = 0; i < sumArray.length; i++) {
            for (int j = 0; j < sumArray.length; j++) {
                if (sumArray[i] + sumArray[j] == target) {
                    List<Integer[]> list1 = map.get(sumArray[i]);
                    List<Integer[]> list2 = map.get(sumArray[j]);
                    for (int k = 0; k < list1.size(); k++) {
                        for (int l = 0; l < list2.size(); l++) {
                            Integer[] intArr1 = list1.get(k);
                            Integer[] intArr2 = list2.get(l);
                            Set<Integer> set = new HashSet<>();
                            set.add(intArr1[0]);
                            set.add(intArr1[1]);
                            set.add(intArr2[0]);
                            set.add(intArr2[1]);
                            if (set.size() > 3) {
                                List<Integer> list3 = new ArrayList<Integer>();
                                list3.add(nums[intArr1[0]]);
                                list3.add(nums[intArr1[1]]);
                                list3.add(nums[intArr2[0]]);
                                list3.add(nums[intArr2[1]]);
                                Collections.sort(list3);
                                if (!lists.contains(list3)) {
                                    lists.add(list3);
                                }
                            }
                        }
                    }
                }
            }
        }
        return lists;
    }
    
    public static List<List<Integer>> solution4(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4) {
            return lists;
        }
        Map<Integer, List<Integer[]>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                Integer sum = nums[i] + nums[j];
                if (map.containsKey(sum)) {
                    map.get(sum).add(new Integer[] {i, j});
                } else {
                    List<Integer[]> list = new ArrayList<>();
                    list.add(new Integer[] {i, j});
                    map.put(sum, list);
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                int target2 = target - (nums[i] + nums[j]);
                if (map.keySet().contains(target2)) {
                    List<Integer[]> list4 = map.get(target2);
                    for (int k = 0; k < list4.size(); k++) {
                        Integer[] intArr1 = list4.get(k);
                        Set<Integer> set = new HashSet<>();
                        set.add(intArr1[0]);
                        set.add(intArr1[1]);
                        set.add(i);
                        set.add(j);
                        if (set.size() > 3) {
                            List<Integer> list3 = new ArrayList<Integer>();
                            list3.add(nums[list4.get(k)[0]]);
                            list3.add(nums[list4.get(k)[1]]);
                            list3.add(nums[i]);
                            list3.add(nums[j]);
                            Collections.sort(list3);
                            if (!lists.contains(list3)) {
                                lists.add(list3);
                            }
                        }
                    }
                }
            }
        }
        return lists;
    }
    
    public static void main(String[] args) {
        int[] nums = new int[] {1, 0, -1, 0, -2, 2};
        int target = 0;
        System.out.println(FourSum.solution(nums, target));
        System.out.println(FourSum.solution4(nums, target));
    }
}
