package com.zfwhub.algorithm.leetcode.array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/description/
 * https://blog.csdn.net/zjucor/article/details/79599287
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
        for (int i = 1; i < A.length-1; i++) {
            if (A[i] <= newA[i-1] || B[i] <= newB[i-1]) {
                if (newA[i] < A[i+1] && newB[i] < B[i+1]) {
                    newA[i-1] = B[i-1];
                    newB[i-1] = A[i-1];
                    newA[i] = A[i];
                    newB[i] = B[i];
                } else {
                    newA[i] = B[i];
                    newB[i] = A[i];
                }
                count++;
            } else {
                newA[i] = A[i];
                newB[i] = B[i];
            }
        }
        // the last check
        if (A[A.length-1] <= newA[A.length-1-1] || B[B.length-1] <= newB[B.length-1-1]) {
            newA[A.length-1] = B[B.length-1];
            newB[B.length-1] = A[A.length-1];
            count++;
        } else {
            newA[A.length-1] = A[A.length-1];
            newB[B.length-1] = B[B.length-1];
        }
        System.out.println(Arrays.toString(newA));
        System.out.println(Arrays.toString(newB));
        return count;
    }
    
    public static void main(String[] args) {
        int[] A5 = new int[] {0,7,8,10,10,11,12,13,19,18};
        int[] B5 = new int[] {4,4,5,7,11,14,15,16,17,20};
        
        System.out.println(MinimumSwapsToMakeSequencesIncreasing.minSwap(A5, B5));
        //System.out.println(Arrays.toString(newA));
        //System.out.println(Arrays.toString(newB));
    }

}
