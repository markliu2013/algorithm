package com.zfwhub.algorithm.leetcode.array;

import java.util.*;

// https://leetcode.com/problems/single-number-iii/
public class SingleNumberIII {
    
    public static int[] solution1(int[] A) {
        int[] result = new int[2];
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < A.length; i++) {
            if (set.contains(A[i])) {
                set.remove(A[i]);
            } else {
                set.add(A[i]);
            }
        }
        Iterator<Integer> it = set.iterator();
        result[0] = it.next();
        result[1] = it.next();
        return result;
    }
    
    public static int[] solution2(int[] nums) {
        Arrays.sort(nums);
        return solution2Helper(nums);
    }
    
    public static int[] solution2Helper(int[] nums) {
        if (nums.length == 2) {
            return nums;
        }
        // 后面有mid+1 所有这里减去1避免越界。
        int mid = nums.length / 2-1;
        while (nums[mid] == nums[mid+1]) {
            mid++;
        }
        int[] leftNums = Arrays.copyOfRange(nums, 0, mid+1);
        int[] rightNums = Arrays.copyOfRange(nums, mid+1, nums.length);
        int leftSingleNumber = singleNumber2(leftNums);
        int rightSingleNumber = singleNumber2(rightNums);
        // 刚好把两个数分在了左右两边，最后的结果可能为0
        if ((leftSingleNumber != 0 || leftNums.length%2 != 0) && rightSingleNumber != 0) {
            return new int[] {leftSingleNumber, rightSingleNumber};
        }
        // 只能找一边继续分
        if (leftSingleNumber != 0) {
            return solution2Helper(leftNums);
        }
        if (rightSingleNumber != 0) {
            return solution2Helper(rightNums);
        }
        // 这种情况题目的数据不会出现
        return null;
    }
    
    private static int singleNumber2(int[] A) {
        int result = 0;
        for (int i = 0; i < A.length; i++) {
            result ^= A[i];
        }
        return result;
    }
    
    // https://blog.csdn.net/xy913741894/article/details/52145043
    
    public static void main(String[] args) {
//        int[] A = new int[] {1,2,1,3,2,5};
        int[] A = new int[] {0,1,2,2};
        System.out.println(Arrays.toString(solution2(A)));
    }

}
