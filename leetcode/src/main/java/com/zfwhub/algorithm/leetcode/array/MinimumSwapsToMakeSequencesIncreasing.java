package com.zfwhub.algorithm.leetcode.array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/description/
 */
public class MinimumSwapsToMakeSequencesIncreasing {
    
    private static int[] newA = null;
    private static int[] newB = null;
    
    // TODO MinimumSwapsToMakeSequencesIncreasing wrong
    public static int solution(int[] A, int[] B) {
        if (A.length == 1) {
            return 0;
        }
        if (A.length == 2) {
            if (A[1] <= A[0] || B[1] <= B[0]) {
                return 1;
            } else {
                return 0;
            }
        }
        int[] subA = Arrays.copyOfRange(A, 0, A.length-1);
        int[] subB = Arrays.copyOfRange(B, 0, B.length-1);
        if (A[A.length-1] >= A[A.length-2]) {
            
        }
        
        
        
        return 0;
    }
    
    public static void main(String[] args) {
        int[] A2 = new int[] {3,3,8,9,10};
        int[] B2 = new int[] {1,7,4,6,8};
        newA = Arrays.copyOfRange(A2, 0, A2.length);
        newB = Arrays.copyOfRange(B2, 0, B2.length);
//        System.out.println(MinimumSwapsToMakeSequencesIncreasing.solution(A2, B2));
        
        System.out.println(1-Math.pow(0.997, 100));
        
        
    }

}
