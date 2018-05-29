package com.zfwhub.algorithm.codility.counting_elements;

import java.util.HashSet;

/**
 * check array is permutation or not.
 * A permutation is a sequence containing each element from 1 to N once, and only once.
 * https://app.codility.com/programmers/lessons/4-counting_elements/perm_check/
 */
public class PermCheck {

    /**
     * use HashSet optimization.
     * convert array into set, then check from 1
     */
    public int solution(int[] A) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < A.length; i++) {
            set.add(A[i]);
        }
        for (int i = 1; i <= A.length; i++) {
            if (!set.contains(i)) {
                return 0;
            }
        }
        return 1;
    }

    /**
     * n is array size
     * if number in array is over 1 to n, then return 0 directly.
     * if array is permutation, so from 1 to N once, and only once.
     * define boolean array index match the number in array
     * if wee meet true twice, return 0. end loop return 1.
     */
    public int solution2(int[] A) {
        boolean[] seen = new boolean[A.length + 1];//+1可以避免seen[A[i]-1]
        for (int i = 0; i < A.length; i++) {
            if (A[i] < 1 || A[i] > A.length)
                return 0;
            if (seen[A[i]] == true)
                return 0;
            else
                seen[A[i]] = true;
        }
        return 1;
    }

    public static void main(String[] args) {
        PermCheck pc = new PermCheck();
        int result = pc.solution2(new int[] { 4, 1, 3, 2 });
        System.out.println(result);
    }
}
