package com.zfwhub.algorithm.leetcode.array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/description/
 */
public class MinimumSwapsToMakeSequencesIncreasing {
    
    public static int minSwap(int[] A, int[] B) {
        int count = 0;
        int[] newA = new int[A.length];
        int[] newB = new int[B.length];
        newA[0] = A[0];
        newB[0] = B[0];
        boolean lastSwaped = false;
        if (A.length == 1) {
            return count;
        }
        for (int i = 1; i < A.length; i++) {
            if (A[i] <= newA[i-1] || B[i] <= newB[i-1]) {
                if (lastSwaped) {
                    
                }
                newA[i] = B[i];
                newB[i] = A[i];
                lastSwaped = true;
            } else {
                newA[i] = A[i];
                newB[i] = B[i];
            }
        }
        System.out.println(Arrays.toString(newA));
        System.out.println(Arrays.toString(newB));
        return count;
    }
    
    public static void main(String[] args) {
        int[] A2 = new int[] {3,3,8,9,10};
        int[] B2 = new int[] {1,7,4,6,8};
        
        System.out.println(MinimumSwapsToMakeSequencesIncreasing.minSwap(A2, B2));
        //System.out.println(Arrays.toString(newA));
        //System.out.println(Arrays.toString(newB));
    }

}
