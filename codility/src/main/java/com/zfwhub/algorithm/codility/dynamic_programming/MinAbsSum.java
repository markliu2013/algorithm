package com.zfwhub.algorithm.codility.dynamic_programming;

import java.util.Arrays;

/**
 * https://app.codility.com/programmers/lessons/17-dynamic_programming/min_abs_sum/
 */
public class MinAbsSum {
    // TODO MinAbsSum
    public static int solution(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        if (A.length == 1) {
            return A[0];
        }
        if (A.length == 2) {
            return Math.abs(A[1] - A[0]);
        }
        for (int i = 0; i < A.length; i++) {
            A[i] = Math.abs(A[i]);
        }
        Arrays.sort(A);
        int startIndex = 0;
        int endIndex = A.length - 1;
        int sum = 0;
        boolean sumFlag = false;
        while (startIndex + 1 < endIndex) {
            sumFlag = false;
            for (;startIndex < endIndex; startIndex++) {
                sum += A[startIndex];
                if (sum >= A[endIndex]) {
                    sum = sum - A[endIndex];
                    sumFlag = true;
                    endIndex--;
                    break;
                }
            }
        }
        return sumFlag ? sum : A[endIndex] - sum;
    }
    
    public static void main(String[] args) {
        int[] A = new int[] {10, 11, 12, 13, 14};
        System.out.println(MinAbsSum.solution(A));
    }

}
