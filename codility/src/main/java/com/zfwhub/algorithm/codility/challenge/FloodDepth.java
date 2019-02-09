package com.zfwhub.algorithm.codility.challenge;

// https://app.codility.com/programmers/lessons/90-tasks_from_indeed_prime_2015_challenge/flood_depth/
public class FloodDepth {
    
    /*
     * base on every point A[i], search the max left and right side.
     * the depth is min(right-A[i], left-A[i])
     */
    public static int solution1(int[] A) {
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
    
    // dynamic programming
    public static int solution2(int[] A) {
        if (A == null || A.length == 0) {
            throw new RuntimeException("Array A can't be null or empty.");
        }
        if (A.length == 1 || A.length == 2) {
            return 0;
        }
        int lowDepthIndex = 0;
        int highDepthIndex = 0;
        int currentDept = 0;
        if (A[0] > A[1]) {
            lowDepthIndex = 1;
            currentDept = 0;
        } else {
            lowDepthIndex = 1;
            highDepthIndex = 1;
            currentDept = 0;
        }
        for (int i = 2; i < A.length; i++) {
            int preHighDepthIndex = highDepthIndex;
            if (A[i] < A[lowDepthIndex]) {
                lowDepthIndex = i;
            }
            if (A[i] > A[highDepthIndex]) {
                highDepthIndex = i;
            }
            if (A[i] > A[i-1]) {
                if (A[i] > A[preHighDepthIndex]) {
                    currentDept = Math.max(A[preHighDepthIndex]-A[lowDepthIndex], currentDept);
                    lowDepthIndex = i;
                    highDepthIndex = i;
                } else {
                    currentDept = Math.max(A[i]-A[lowDepthIndex], currentDept);
                }
            }
        }
        return currentDept;
    }
    
    // TODO FloodDepth simply dynamic programming, top to down?
    public static int solution3(int[] A) {
        return 0;
    }
    
    public static void main(String[] args) {
//        int[] A = new int[] {3,1};
//        int[] A = new int[] {1,3,2,1,2,1,5,3,3,4,2};
        int[] A = new int[] {3,1,2};
        System.out.println(FloodDepth.solution2(A));
    }

}
