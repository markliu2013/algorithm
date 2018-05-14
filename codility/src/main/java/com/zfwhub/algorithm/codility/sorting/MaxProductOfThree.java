package com.zfwhub.algorithm.codility.sorting;

import java.util.Arrays;

/**
 * 从数组中找出三个乘积最大的数
 * https://leetcode.com/problems/maximum-product-of-three-numbers/description/ 
 */
public class MaxProductOfThree {
    
    /**
     * brute force, 选出所有的组合。 
     */
    public static int solution(int[] A) {
        int maxProduct = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            for (int j = i+1; j < A.length; j++) {
                for (int k = j+1; k < A.length; k++) {
                    maxProduct = Math.max(maxProduct, A[i]*A[j]*A[k]);
                }
            }
        }
        return maxProduct;
    }
    
    /**
     * 排序之后, 分情况讨论。 
     */
    public static int solution2(int[] A) {
        if (A.length == 3) {
            return A[0] * A[1] * A[2];
        }
        Arrays.sort(A);
        if (A[1] >= 0 || A[A.length - 1] <= 0) {//少于两个负数或没有正数, 则肯定是最后面三个数。
           return A[A.length - 1] * A[A.length - 2] * A[A.length - 3];
        }
        if (A[A.length - 3] <= 0) {//少于两个正数
            return A[0] * A[1] * A[A.length - 1];
        }
        return Math.max(A[0] * A[1] * A[A.length - 1], A[A.length - 1] * A[A.length - 2] * A[A.length - 3]);
    }
    /**
     * 情况讨论可以简化 
     */
    public static int solution3(int[] A) {
        Arrays.sort(A);
        return Math.max(A[0] * A[1] * A[A.length - 1], A[A.length - 1] * A[A.length - 2] * A[A.length - 3]);
    }
    
    public static void main(String[] args) {
        System.out.println(MaxProductOfThree.solution3(new int[] {-2,-3,-4}));
    }
    
}
