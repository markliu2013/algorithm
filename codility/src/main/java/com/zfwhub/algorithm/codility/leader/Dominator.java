package com.zfwhub.algorithm.codility.leader;

import java.util.AbstractMap;
import java.util.HashMap;
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
    
    public static void main(String[] args) {
        System.out.println(Dominator.solution(new int[] { }));
    }

}
