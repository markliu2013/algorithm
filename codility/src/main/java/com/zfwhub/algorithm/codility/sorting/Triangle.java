package com.zfwhub.algorithm.codility.sorting;

import java.util.Arrays;

/**
 * Array can combine triangle?
 * https://app.codility.com/programmers/lessons/6-sorting/triangle/
 */
public class Triangle {

    /**
     * brute force, get all combinations, then check.
     * int maybe overflow
     */
    public static int solution(int[] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                for (int k = j + 1; k < A.length; k++) {
                    if (A[i] + A[j] > A[k] && A[i] + A[k] > A[j] && A[j] + A[k] > A[i]) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    /**
     * fix overflow, use subtract instead of addition
     */
    public static int solution2(int[] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                for (int k = j + 1; k < A.length; k++) {
                    if (A[i] > A[k] - A[j] && A[i] > A[j] - A[k] && A[j] > A[i] - A[k]) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    /**
     * check the most possible condition, then no need check others.
     * sorted then check every 3 adjacents
     */
    public static int solution3(int[] A) {
        Arrays.sort(A);
        for (int i = 0; i < A.length - 2; i++) {
            if (A[i] > A[i + 2] - A[i + 1]) {
                return 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(Triangle.solution2(new int[] { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE }));
    }

}
