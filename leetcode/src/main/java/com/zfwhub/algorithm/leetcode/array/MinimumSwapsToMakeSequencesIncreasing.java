package com.zfwhub.algorithm.leetcode.array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/description/
 */
public class MinimumSwapsToMakeSequencesIncreasing {
    
    private static int[] newA = null;
    private static int[] newB = null;
    
    private static int swapCount = 0;
    private static boolean lastSwaped = false;
    
    public static void solution(int[] A, int[] B) {
        if (A.length == 1) {
            lastSwaped = false;
            return;
        }
        if (A[A.length-1] <= newA[A.length-2] || B[B.length-1] <= newB[B.length-2]) {
            if (lastSwaped == false) {
                int temp = A[A.length-1];
                newA[A.length-1] = B[B.length-1];
                newB[B.length-1] = temp;
                swapCount++;
            } else {
                int temp1 = A[A.length-1];
                newA[A.length-1] = B[B.length-1];
                newB[B.length-1] = temp1;
                // reverse back
                int temp2 = A[A.length-2];
                newA[A.length] = B[B.length];
                newB[B.length] = temp2;
            }
            lastSwaped = true;
        }
        int[] subA = Arrays.copyOfRange(A, 0, A.length-1);
        int[] subB = Arrays.copyOfRange(B, 0, B.length-1);
        solution(subA, subB);
    }
    
    public static int minSwap(int[] A, int[] B) {
        newA = Arrays.copyOfRange(A, 0, A.length);
        newB = Arrays.copyOfRange(B, 0, B.length);
        solution(A, B);
        return swapCount;
    }
    
    public static void main(String[] args) {
        int[] A2 = new int[] {3,3,8,9,10};
        int[] B2 = new int[] {1,7,4,6,8};
        
        System.out.println(MinimumSwapsToMakeSequencesIncreasing.minSwap(A2, B2));
        System.out.println(Arrays.toString(newA));
        System.out.println(Arrays.toString(newB));
    }

}
