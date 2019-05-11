package com.zfwhub.algorithm.datastructure.sort;

import java.util.*;

import com.zfwhub.algorithm.utils.ArrayUtil;

public class Sort {

    // 冒泡排序 In-Place n2
    public static void solution1(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
            boolean flag = true;
            for (int j = 0; j < nums.length - i; j++) {
                if (nums[j + 1] < nums[j]) {
                    int tmp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = tmp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    // 插入排序 In-Place n2
    public static void solution2(int[] A) {
        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < A.length; i++) {
            // 记录要插入的数据
            int tmp = A[i];
            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && A[j-1] > tmp) {
                A[j] = A[j-1];
                j--;
            }
            if (j != i) {
                A[j] = tmp;
            }
        }
    }
    
    // 归并排序
    public static int[] solution3(int[] nums) {
        if (nums.length < 2) {
            return nums;
        }
        int mid = nums.length / 2;
        int[] leftNums = Arrays.copyOfRange(nums, 0, mid);
        int[] rightNums = Arrays.copyOfRange(nums, mid, nums.length);
        return nums = ArrayUtil.mergeTwoSortedArray(solution3(leftNums), solution3(rightNums));
    }
    
    public static void main(String[] args) {
        int[] nums = new int[] { 5, 1, 2, 3 };
        System.out.println(Arrays.toString(solution3(nums)));
    }

}
