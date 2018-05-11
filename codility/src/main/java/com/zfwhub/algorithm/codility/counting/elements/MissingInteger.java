package com.zfwhub.algorithm.codility.counting.elements;

import java.util.HashSet;
import java.util.Set;

/**
 * 找一个数组中不存在的最小正整数。
 * https://app.codility.com/programmers/lessons/4-counting_elements/missing_integer/
 */
public class MissingInteger {
    
    /**
     * 利用HashSet优化，现将数组转到set，然后从1开始查找。
     */
    public int solution(int[] A) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < A.length; i++) {
            set.add(A[i]);
        }
        for (int i = 1; i <= A.length; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return A.length + 1;
    }
    
    public int solution2(int[] A) {
        int num = 1;
        HashSet<Integer> set=new HashSet<Integer>();
        for (int i=0; i<A.length; i++) {
            set.add(A[i]);
        }
        while(set.contains(num)) {
            num++;
        }
        return num;
    }
    
    public int solution3(int[] A) {
        int counter[] = new int[A.length];

        // Count the items, only the positive numbers
        for (int i = 0; i < A.length; i++)
            if (A[i] > 0 && A[i] <= A.length)
                counter[A[i] - 1]++;

        // Return the first number that has count 0
        for (int i = 0; i < counter.length; i++)
            if (counter[i] == 0)
                return i + 1;

        // If no number has count 0, then that means all number in the sequence
        // appears so the next number not appearing is in next number after the
        // sequence.
        return A.length + 1;
    }
    
    public static void main(String[] args) {
        MissingInteger mi = new MissingInteger();
        System.out.println(mi.solution(new int[] { 1, 3, 6, 4, 1, 2 }));
    }
}
