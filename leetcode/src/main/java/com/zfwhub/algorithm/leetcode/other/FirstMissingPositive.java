package com.zfwhub.algorithm.leetcode.other;

import java.util.Arrays;
import java.util.Set;

import com.zfwhub.algorithm.utils.ArrayUtil;

// https://leetcode.com/problems/first-missing-positive/
public class FirstMissingPositive {

    public static int solution1(int[] nums) {
        if (nums.length == 0) {
            return 1;
        }
        Set<Integer> set = ArrayUtil.toSet(nums);
        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return nums.length + 1;
    }

    public static int solution2(int[] nums) {
        if (nums.length == 0)
            return 1;
        //第i位存放i+1的数值
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {//nums[i]为正数，放在i+1位置
                //假设交换的数据还是大于0且<i+1，则放在合适的位置,且数据不相等，避免死循环
                //这个while是关键，其它都是没有难度的
                while (nums[i] > 0 && nums[i] < i + 1 && nums[i] != nums[nums[i] - 1]) {
                    int temp = nums[nums[i] - 1];//交换数据
                    nums[nums[i] - 1] = nums[i];
                    nums[i] = temp;
                }
            }
        }
        //循环寻找不符合要求的数据，返回
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        //假设都符合要求，则返回长度+1的值
        return nums.length + 1;
    }

    public static void main(String[] args) {
        //        int[] nums = new int[] {-10,2,1,3,100,-1,-2,4,-3,6,5};
        //        int[] nums = new int[] {1,1};
        int[] nums = new int[] { 3, 4, -1, 1 };
        System.out.println(solution2(nums));
        System.out.println(Arrays.toString(nums));
    }

}
