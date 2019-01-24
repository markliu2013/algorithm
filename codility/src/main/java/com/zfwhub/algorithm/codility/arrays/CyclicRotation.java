package com.zfwhub.algorithm.codility.arrays;

import java.util.Arrays;

/**
 * shift array K indexes
 * https://app.codility.com/programmers/lessons/2-arrays/cyclic_rotation/start/
 * https://mark.zfwhub.com/java-solution-to-cyclicrotation.html
 */
public class CyclicRotation {

    /*
     * Shift Array Step by Step
     */
    public int[] solution(int[] A, int K) {
        int times = A.length == 0 ? 0 : K % A.length;
        for (int i = 0; i < times; i++) {
            int temp = A[0];
            int last = A[A.length - 1];
            for (int j = 1; j < A.length; j++) {
                int swap = A[j];
                A[j] = temp;
                temp = swap;
            }
            A[0] = last;
        }
        return A;
    }

    /**
     * Shift Array all in One
     */
    public int[] solution2(int[] A, int K) {
        int times = A.length == 0 ? 0 : K % A.length;
        int[] B = new int[A.length];
        for (int i = times; i < A.length; i++) {
            B[i] = A[i - times];
        }
        for (int i = 0; i < times; i++) {
            B[i] = A[A.length - times + i];
        }
        return B;
    }

    /**
     * Simplify The Code 
     */
    public int[] solution3(int[] A, int K) {
        int times = A.length == 0 ? 0 : K % A.length;
        // int[] B = Arrays.copyOfRange(A, A.length-times, A.length);
        // int[] C = Arrays.copyOfRange(A, 0, A.length-times);
        int[] D = new int[A.length];
        System.arraycopy(A, 0, D, times, A.length - times);
        System.arraycopy(A, A.length - times, D, 0, times);
        return D;
    }

    public static void main(String[] args) {
        CyclicRotation cyclicRotation = new CyclicRotation();
        int[] B = cyclicRotation.solution3(new int[] { 3, 8, 9, 7, 6 }, 1);
        System.out.println(Arrays.toString(B));
    }
}
