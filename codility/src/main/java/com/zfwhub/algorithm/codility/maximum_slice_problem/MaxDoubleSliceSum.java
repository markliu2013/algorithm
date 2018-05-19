package com.zfwhub.algorithm.codility.maximum_slice_problem;

/**
 * https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_double_slice_sum/
 */
public class MaxDoubleSliceSum {
    
    /**
     * straightforward, brute force
     */
    public static int solution(int[] A) {
        int maxDoubleSliceSum = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            for (int j = i+1; j < A.length; j++) {
                for (int k = j+1; k < A.length; k++) {
                    int sum = 0;
                    for (int l = i+1; l < j; l++) {
                        sum += A[l];
                    }
                    for (int l = j+1; l < k; l++) {
                        sum += A[l];
                    }
                    maxDoubleSliceSum = Math.max(maxDoubleSliceSum, sum);
                }
            }
        }
        return maxDoubleSliceSum;
    }
    
    // TODO
    public static int solution2(int[] A) {
        return 0;
    }
    
    public static void main(String[] args) {
        System.out.println(MaxDoubleSliceSum.solution(new int[] {3,2,6,-1,4,5,-1,2}));
    }
    
}
