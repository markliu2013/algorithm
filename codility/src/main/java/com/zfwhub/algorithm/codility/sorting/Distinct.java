package com.zfwhub.algorithm.codility.sorting;

import java.util.HashSet;

/**
 * given an array A consisting of N integers, returns the number of distinct values in array A.
 * https://app.codility.com/programmers/lessons/6-sorting/distinct/
 */
public class Distinct {
    
    /**
     * HashSet
     */
    public static int solution(int[] A) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < A.length; i++) {
            set.add(A[i]);
        }
        return set.size();
    }
    
    // TODO no built in, need sort?
    public static int solution2(int[] A) {
        return 0;
    }
    
    public static void main(String[] args) {
        System.out.println(Distinct.solution2(new int[] {-2,1,1,2,3,1}));
    }
}
