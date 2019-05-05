package com.zfwhub.algorithm.datastructure.sort;

import java.util.*;

public class Sort {
    
    // 冒泡排序
    public static void solution1(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            boolean flag = true;
            for (int j = 0; j < nums.length-i; j++) {
                if (nums[j+1] < nums[j]) {
                    int tmp = nums[j+1];
                    nums[j+1] = nums[j];
                    nums[j] = tmp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }
    
    public static void main(String[] args) {
        int[] nums = new int[] {1,5,2,3};
        solution1(nums);
        System.out.println(Arrays.toString(nums));
    }

}
