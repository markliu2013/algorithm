package com.zfwhub.algorithm.codility.leader;

import java.util.Arrays;
import java.util.HashSet;

/**
 * How to find leader?
 * 1. Count the occurrences of every element
 * 2. Sorted, then check from middle
 * 3. 
 * https://codility.com/media/train/6-Leader.pdf
 */
public class Leader {
    
    // TODO find leader
    public static int solution(int[] A) {
        return 0;
    }
    
    /**
     * if hasLeader
     */
    public static boolean hasLeader(int[] A) {
        if (A.length == 0) {
            return false;
        }
        if (A.length == 1) {
            return true;
        }
        //base on set size
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < A.length; i++) {
            set.add(A[i]);
        }
        if (set.size() > (A.length % 2 == 0 ? A.length / 2 : A.length / 2 + 1)) {
            return false;
        }
        // sorted, then check from middle
        A = A.clone();// Don't change original A
        Arrays.sort(A);
        int midNumber =  A[A.length / 2];
        int count = 1;
        for (int i = A.length / 2 - 1; i >= 0; i--) {
            if (A[i] == midNumber) count++;
            else break;
        }
        for (int i = A.length / 2 + 1; i < A.length; i++) {
            if (A[i] == midNumber) count++;
            else break;
        }
        if (count > A.length / 2) return true;
        else return false;
    }
    
    public static void main(String[] args) {
        
    }

}
