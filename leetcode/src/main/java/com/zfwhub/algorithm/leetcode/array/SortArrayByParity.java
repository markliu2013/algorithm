package com.zfwhub.algorithm.leetcode.array;

import java.util.Arrays;

// https://leetcode.com/problems/sort-array-by-parity/
public class SortArrayByParity {
    
    public static int[] solution1(int[] A) {
        int[] result = new int[A.length];
        int evenIndex = 0;
        int oddIndex = A.length-1;
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) { // even
                result[evenIndex] = A[i];
                evenIndex++;
            } else { // odd
                result[oddIndex] = A[i];
                oddIndex--;
            }
        }
        return result;
    }
    
    // In place
    public static int[] solution2(int[] A) {
        return null;
    }
    
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution1(new int[] {3,1,2,4})));
    }

}
