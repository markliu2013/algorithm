package com.zfwhub.algorithm.codility.caterpillar_method;

import java.util.HashSet;
/**
 * https://app.codility.com/programmers/lessons/15-caterpillar_method/abs_distinct/
 */
public class AbsDistinct {
    
    /**
     * HashSet
     */
    public static int solution(int[] A) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < A.length; i++) {
            if (!set.contains(Math.abs(A[i]))) {
                set.add(Math.abs(A[i]));
            }
        }
        return set.size();
    }
    
    // TODO AbsDistinct
    /**
     * in place algorithm
     */
    public static int solution2(int[] A) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < A.length; i++) {
            if (!set.contains(Math.abs(A[i]))) {
                set.add(Math.abs(A[i]));
            }
        }
        return set.size();
    }
    
    public static void main(String[] args) {
        int[] A = new int[] {-5,-3,-1,0,3,6};
        System.out.println(AbsDistinct.solution(A));
    }

}
