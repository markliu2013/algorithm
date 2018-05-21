package com.zfwhub.algorithm.codility.maximum_slice_problem;


/**
 * https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_slice_sum/
 * exactly the same with MaximumSubarray LeetCode 
 */
public class MaxSliceSum {
    
    /**
     * dynamic programming, down to top 
     */
    public static int solution(int[] A) {
        int[] max1 = new int[A.length];//end with i's MaxSliceSum
        int[] max2 = new int[A.length];//from 0 to i's MaxSliceSum
        max1[0] = A[0];
        max2[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            if (max1[i-1] + A[i] > A[i]) {
                max1[i] = max1[i-1] + A[i];
            } else {
                max1[i] = A[i];
            }
            if (max1[i] > max2[i-1]) {
                max2[i] = max1[i];
            } else {
                max2[i] = max2[i-1];
            }
        }
        return max2[A.length-1];
    }
    
    // TODO sliding window, maybe.
    public static int solution2(int[] A) {
        return 0;
    }
    
    public static void main(String[] args) {
        System.out.println(MaxSliceSum.solution(new int[] {3,2,-6,4,0}));
    }

}
