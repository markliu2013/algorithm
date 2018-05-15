package com.zfwhub.algorithm.codility.sorting;

import java.util.Arrays;

/**
 * 数组是否能组成三角形
 * https://app.codility.com/programmers/lessons/6-sorting/triangle/
 */
public class Triangle {
    
    /**
     * brute force, 选出所有的组合, 判断。
     * 当int过大时会越界。
     */
    public static int solution(int[] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = i+1; j < A.length; j++) {
                for (int k = j+1; k < A.length; k++) {
                    if (A[i] + A[j] > A[k] && A[i] + A[k] > A[j] && A[j] + A[k] > A[i]) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }
    
    /**
     * 解决越界, 灵活变换不等式, 灵活运用知识, 思路要开阔。
     */
    public static int solution2(int[] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = i+1; j < A.length; j++) {
                for (int k = j+1; k < A.length; k++) {
                    if (A[i] > A[k] - A[j]  && A[i] > A[j] - A[k] && A[j] > A[i] - A[k]) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }
    
    /**
     * 如果最有可能的条件下不成立，则其他条件不用判断。
     * 先排序，则只需要判断相邻的三个数。 
     */
    public static int solution3(int[] A) {
        Arrays.sort(A);
        for (int i = 0; i < A.length-2; i++) {
            if (A[i] > A[i+2] - A[i+1]) {
                return 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(Triangle.solution2(new int[] {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE}));
    }
    
}
