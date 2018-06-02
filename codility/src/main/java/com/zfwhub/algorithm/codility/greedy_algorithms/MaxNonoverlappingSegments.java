package com.zfwhub.algorithm.codility.greedy_algorithms;
/**
 * https://app.codility.com/programmers/lessons/16-greedy_algorithms/max_nonoverlapping_segments/
 */
public class MaxNonoverlappingSegments {
    
    public static int solution(int[] A, int[] B) {
        if (A == null || B == null) {
            throw new IllegalArgumentException("array A and B can't be null");
        }
        if (A.length == 0 || B.length == 0) {
            return 0;
        }
        int count = 1;
        int[] preSegment = new int[2];
        preSegment[0] = A[0];
        preSegment[1] = B[0];
        for (int i = 1; i < A.length; i++) {
            if (preSegment[1] < A[i]) {
                count++;
                preSegment[0] = A[i];
                preSegment[1] = B[i];
            }
        }
        return count;
    }
    
    public static void main(String[] args) {
        int[] A = new int[] {1,3,7,9,9};
        int[] B = new int[] {5,6,8,9,10};
        System.out.println(MaxNonoverlappingSegments.solution(A, B));
    }

}
