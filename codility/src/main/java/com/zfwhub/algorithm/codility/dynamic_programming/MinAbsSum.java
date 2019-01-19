package com.zfwhub.algorithm.codility.dynamic_programming;
import java.util.Arrays;

// https://app.codility.com/programmers/lessons/17-dynamic_programming/min_abs_sum/
public class MinAbsSum {
    
    // TODO MinAbsSum
    public static int solution(int[] A) {
        int minSubAbsSum = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            for (int j = i+1; j < A.length; j++) {
                int[] subArr = Arrays.copyOfRange(A, i, j);
                int minAbsSum = minAbsSum(subArr);
                minSubAbsSum = Math.min(minSubAbsSum, minAbsSum);
            }
        }
        return minSubAbsSum;
    }
    
    public static int minAbsSum(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        if (A.length == 1) {
            return A[0];
        }
        for (int i = 0; i < A.length; i++) {
            A[i] = Math.abs(A[i]);
        }
        Arrays.sort(A);
        int startIndex = 0;
        int endIndex = A.length - 1;
        int minAbsSum = A[endIndex] * (-1) + A[startIndex];
        while (startIndex + 1 < endIndex) {
            if (minAbsSum <= 0) {
                startIndex++;
                minAbsSum += A[startIndex];
            } else {
                endIndex--;
                minAbsSum -= A[endIndex];
            }
        }
        return Math.abs(minAbsSum);
    }

    // https://stackoverflow.com/questions/44897316/
    public static int solution2(int[] A) {
        if (A.length == 0)
            return 0;
        if (A.length == 1)
            return A[0];
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += Math.abs(A[i]);
        }
        int[] indices = new int[A.length];
        indices[0] = 0;
        int half = sum / 2;
        int localSum = Math.abs(A[0]);
        int minLocalSum = Integer.MAX_VALUE;
        int placeIndex = 1;
        for (int i = 1; i < A.length; i++) {
            if (localSum < half) {
                if (Math.abs(2 * minLocalSum - sum) > Math.abs(2 * localSum - sum))
                    minLocalSum = localSum;
                localSum += Math.abs(A[i]);
                indices[placeIndex++] = i;
            } else {
                if (localSum == half)
                    return Math.abs(2 * half - sum);

                if (Math.abs(2 * minLocalSum - sum) > Math.abs(2 * localSum - sum))
                    minLocalSum = localSum;
                if (placeIndex > 1) {
                    localSum -= Math.abs(A[indices[placeIndex--]]);
                    i = indices[placeIndex];
                }
            }
        }
        return (Math.abs(2 * minLocalSum - sum));
    }

    public static void main(String[] args) {
        //        int[] A = new int[] {10, 11, 12, 13, 14};
        int[] A = new int[] { 1, 5, 2, 5, 2, 3 };
//        int[] A = new int[] { 1, 2, 2, 3, 5, 5};
        System.out.println(MinAbsSum.solution2(A));
    }

}
