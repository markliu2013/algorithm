package com.zfwhub.algorithm.codility.challenge;

/**
 * https://app.codility.com/programmers/lessons/90-tasks_from_indeed_prime_2015_challenge/flood_depth/
 */
public class FloodDepth {
    
    /**
     * base on every point A[i], search the max left and right side.
     * the depth is min(right-A[i], left-A[i])
     */
    public static int solution(int[] A) {
        if (A == null || A.length == 0) {
            throw new RuntimeException("Array A can't be null or empty.");
        }
        int maximumDepth = 0;
        for (int i = 1; i < A.length - 1; i++) {
            // seach left max
            int leftMax = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                leftMax = Math.max(leftMax, A[j]);
            }
            // search right max
            int rightMax = Integer.MIN_VALUE;
            for (int j = i+1; j < A.length; j++) {
                rightMax = Math.max(rightMax, A[j]);
            }
            int depth = 0;
            if (leftMax > A[i] && rightMax > A[i]) {
                depth = Math.min(leftMax - A[i], rightMax - A[i]);
            }
            maximumDepth = Math.max(maximumDepth, depth);
        }
        return maximumDepth;
    }
    
    // TODO FloodDepth
    public static int solution2(int[] A) {
        return 0;
    }
    
    public static void main(String[] args) {
        int[] A = new int[] {1,5};
        System.out.println(FloodDepth.solution(A));
    }

}
