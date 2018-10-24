package com.zfwhub.algorithm.leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/3sum-closest/
 */
public class ThreeSumCloset {

    public static int solution(int[] nums, int target) {
        int result = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int gap = Math.abs(nums[i] + nums[j] + nums[k] - target);
                    if (gap < min) {
                        min = gap;
                        result = nums[i] + nums[j] + nums[k];
                    }
                }
            }
        }
        return result;
    }

    public static int solution2(int[] nums, int target) {
        // 优化数组，删除出现超过三次的元素
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        List<Integer> list2 = new ArrayList<Integer>(); //nums 优化后的list, 删除超过三次的元素。
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            list2.add(entry.getKey());
            if (entry.getValue() > 1) {
                list2.add(entry.getKey());
            }
            if (entry.getValue() > 2) {
                list2.add(entry.getKey());
            }
        }
        Collections.sort(list2);
        
        int result = list2.get(0) + list2.get(1) + list2.get(2);
        int min = Math.abs(result - target);
        for (int i = 3; i < list2.size(); i++) {
            int closeTarget = target - list2.get(i);//从i的左侧找两个数的和最接近closeTarget
            int j = 0;//左端指针
            int k = i - 1;//右端指针
            int temp = list2.get(j) + list2.get(k);
            if (temp < closeTarget) {
                while (j < k) {
                    j++;
                    temp = list2.get(j) + list2.get(k);
                }
            }
            
            while (j < k) {
                if (temp < closeTarget) {
                    j++;
                } else if (temp == closeTarget) {
                    break;
                } else {
                    k--;
                }
            }
            int gap = Math.abs(list2.get(i) + temp - target);
            if (gap < min) {
                min = gap;
                result = list2.get(i) + temp;
            }
            
            /*closeTarget = closeTarget * -1;
            j = 0;//左端指针
            k = i - 1;//右端指针
            temp = list2.get(j) + list2.get(k);
            while (j < k) {
                if (temp < closeTarget) {
                    temp = Math.min(list2.get(j++) + list2.get(k), temp);
                } else if (temp == closeTarget) {
                    break;
                } else {
                    temp = Math.min(list2.get(j) + list2.get(k--), temp);
                }
            }
            int gap1 = Math.abs(list2.get(i) + temp - target);
            if (gap1 < min) {
                min = gap1;
                result = list2.get(i) + temp;
            }*/
            
        }
        return result;
    }
    
    public static void main(String[] args) {
        int[] nums = new int[] {1, 1, 1, 0};
        int target = -100;
        int[] nums1 = new int[] {-1, 2, 1, -4};
        int target1 = 1;
        int[] nums2 = new int[] {0, 2, 1, -3};
        int target2 = 1;
        System.out.println(solution(nums, target));
        System.out.println(solution2(nums, target));
        System.out.println(solution(nums1, target1));
        System.out.println(solution2(nums1, target1));
        System.out.println(solution(nums2, target2));
        System.out.println(solution2(nums2, target2));
    }

}
