package com.zfwhub.algorithm.codility.caterpillar_method;

import java.util.Arrays;

import com.zfwhub.algorithm.utils.ArrayUtil;

// https://app.codility.com/programmers/lessons/15-caterpillar_method/min_abs_sum_of_two/
public class MinAbsSumOfTwo {
    
    // brute force
    public static int solution1(int[] A) {
        int minAbsSum = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            for (int j = i; j < A.length; j++) {
                minAbsSum = Math.min(Math.abs(A[i] + A[j]), minAbsSum);
            }
        }
        return minAbsSum;
    }
    
    // binary search Performance 100
    public static int solution2(int[] A) {
        Arrays.sort(A);
        int minAbsSum = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            int val = 0;
            if (A[i] < 0) {
                // 寻找一个最接近target的数
                int target = A[i] * -1;
                int closeIndex = ArrayUtil.closeBound(A, i, A.length, target);
                val = Math.abs(A[i] + A[closeIndex]);
                minAbsSum = Math.min(val, minAbsSum);
            } else {
                val = A[i] + A[i];
                minAbsSum = Math.min(val, minAbsSum);
                break;
            }
        }
        return minAbsSum;
    }
    
    // caterpillar method
    public static int solution3(int[] A) {
        Arrays.sort(A);
        if (A[0] >= 0) {
            return A[0] + A[0];
        }
        if (A[A.length-1] <= 0) {
            return (A[A.length-1] + A[A.length-1])*(-1);
        }
        int leftIndex = 0;
        int rightIndex = A.length - 1;
        int minAbsSum = Math.abs(A[leftIndex] + A[rightIndex]);
        while (leftIndex <= rightIndex) {
            int currentSum = A[leftIndex] + A[rightIndex];
            minAbsSum = Math.min(minAbsSum, Math.abs(currentSum));
            if (currentSum > 0) {
                rightIndex--;
            } else if (currentSum < 0) {
                leftIndex++;
            } else {
                return 0;
            }
        }
        return minAbsSum;
    }
    
    public static void main(String[] args) {
        int[] A = new int[] {1,4,-3};
        System.out.println(solution3(A));
    }

}
