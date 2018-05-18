package com.zfwhub.algorithm.codility.leader;

import java.util.Arrays;
import java.util.HashSet;

/**
 * How to find leader?
 * 1. Count the occurrences of every element
 * 2. Sorted, then check from middle
 * 3. Stack
 * https://codility.com/media/train/6-Leader.pdf
 */
public class Leader {
    
    /**
     * count the occurrences of every element:
     */
    public static int slowLeader(int[] A) {
        for (int i = 0; i < A.length; i++) {
            int candidate = A[i];
            int count = 0;
            for (int j = 0; j < A.length; j++) {
                if (A[j] == candidate) {
                    count++;
                }
            }
            if (count > A.length / 2) {
                return candidate;
            }
        }
        return -1;
    }
    /**
     * sort
     */
    public static int fastLeader(int[] A) {
        if (A.length == 0) return -1;
        A = A.clone();
        Arrays.sort(A);
        int candidate = A[A.length / 2];
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == candidate) {
                count++;
            }
        }
        if (count > A.length / 2) {
            return candidate;
        }
        return -1;
    }
    
    /**
     *  after removing a pair of elements of dierent values,
     *  the remaining sequence still has the same leader
     */
    public static int goldenLeader(int[] A) {
        if (A.length == 0) return -1;
        // find the possible candidate
        int size = 0;
        int value = 0;
        for (int i = 0; i < A.length; i++) {
            if (size == 0) {
                size++;
                value = A[i];
            } else {
                if (value != A[i]) {
                    size--;
                } else {
                    size++;
                }
            }
        }
        int candidate = -1;
        if (size > 0) {
            candidate = value;
        }
        // check
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == candidate) {
                count++;
            }
        }
        if (count > A.length / 2) {
            return candidate;
        }
        return -1;
    }
    
    /**
     * just help test my solution
     */
    public static int solution(int[] A) {
        int leader = goldenLeader(A);
        if (leader == -1) {
            return -1;
        }
        for (int i = 0; i < A.length; i++) {
            if (A[i] == leader) {
                return i;
            }
        }
        return -1;
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
        System.out.println(Leader.solution(new int[] {2,1,2,1,0}));
    }

}
