package com.zfwhub.algorithm.codility.sorting;

import java.util.HashSet;

/**
 * 找出数组中，所有不重复数字的个数。
 * https://app.codility.com/programmers/lessons/6-sorting/distinct/ 
 */
public class Distinct {
    
    public static int solution(int[] A) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < A.length; i++) {
            set.add(A[i]);
        }
        return set.size();
    }
    
    // TODO
    public static int solution2(int[] A) {
        return 0;
    }
    

    public static void main(String[] args) {
        System.out.println(Distinct.solution2(new int[] {-2,1,1,2,3,1}));
    }
}
