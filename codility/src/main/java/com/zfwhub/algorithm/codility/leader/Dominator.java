package com.zfwhub.algorithm.codility.leader;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

/**
 * An array A consisting of N integers is given. The dominator of array A is the
 * value that occurs in more than half of the elements of A.
 * https://app.codility.com/programmers/lessons/8-leader/dominator/
 */
public class Dominator {

    /**
     * HashMap
     */
    public static int solution(int[] A) {
        if (!hasLeader(A.clone())) {
            return -1;
        }
        HashMap<Integer, Entry<Integer, Integer>> map = new HashMap<Integer, Map.Entry<Integer, Integer>>();
        for (int i = 0; i < A.length; i++) {
            if (map.containsKey(A[i])) {
                Entry<Integer, Integer> entry = map.get(A[i]);
                entry.setValue(entry.getValue() + 1);
                map.put(A[i], entry);
            } else {
                map.put(A[i], new AbstractMap.SimpleEntry<Integer, Integer>(i, 1));
            }
        }
        for (Map.Entry<Integer, Entry<Integer, Integer>> entry : map.entrySet()) {
            if (entry.getValue().getValue() > A.length / 2) {
                return entry.getValue().getKey();
            }
        }
        return -1;
    }
    
    // TODO array hasLeader
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
        System.out.println(Dominator.solution(new int[] { }));
        System.out.println(Dominator.hasLeader(new int[] { 1,2,2,2,3}));
    }

}
