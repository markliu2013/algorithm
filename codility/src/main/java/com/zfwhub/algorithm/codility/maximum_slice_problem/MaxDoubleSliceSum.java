package com.zfwhub.algorithm.codility.maximum_slice_problem;

/**
 * https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_double_slice_sum/
 */
public class MaxDoubleSliceSum {
    
    /**
     * straightforward, brute force
     */
    public static int solution(int[] A) {
        if (A == null || A.length < 3) {
            throw new RuntimeException("The array can't be null and length greater than 3");
        }
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
    
    /**
     * prefix sums; 
     */
    public static int solution2(int[] A) {
        int[] prefixSums = new int[A.length];
        // get prefixSum
        prefixSums[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            prefixSums[i] = prefixSums[i-1] + A[i];
        }
        int maxDoubleSliceSum = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            for (int j = i+1; j < A.length; j++) {
                for (int k = j+1; k < A.length; k++) {
                    int sum1 = prefixSums[j-1] - prefixSums[(i+1)-1];//sum of i+1 to j-1
                    int sum2 = prefixSums[k-1] - prefixSums[(j+1)-1];//sum of j+1 to k-1
                    int sum = sum1 + sum2;
                    maxDoubleSliceSum = Math.max(maxDoubleSliceSum, sum);
                }
            }
        }
        return maxDoubleSliceSum;
    }
    
    // TODO MaxDoubleSliceSum
    public static int solution3(int[] A) {
        return 0;
    }
    
    public static void main(String[] args) {
        System.out.println(MaxDoubleSliceSum.solution(new int[] {3,2,6,-1,4,5,-1,2}));
        System.out.println(MaxDoubleSliceSum.solution2(new int[] {3,2,6,-1,4,5,-1,2}));
        System.out.println(MaxDoubleSliceSum.solution(new int[] {-1,-2,-3,4,5}));
        System.out.println(MaxDoubleSliceSum.solution2(new int[] {-1,-2,-3,4,5}));
    }
    
}
