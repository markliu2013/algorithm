package com.zfwhub.algorithm.codility.sieve_of_eratosthenes;

import java.util.Arrays;
import java.util.HashMap;

// https://app.codility.com/programmers/lessons/11-sieve_of_eratosthenes/count_non_divisible/
public class CountNonDivisible {

    // brute force
    public static int[] solution1(int[] A) {
        int[] result = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < result.length; j++) {
                if (A[i] % A[j] != 0) {
                    result[i]++;
                }
            }
        }
        return result;
    }

    // Firstly use map to store every element's count, Then we count every element's divisors.
    // Performance 100%
    public static int[] solution2(int[] A) {
        int[] result = new int[A.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            if (map.containsKey(A[i])) {
                map.put(A[i], map.get(A[i]) + 1);
            } else {
                map.put(A[i], 1);
            }
        }
        // count divisors
        for (int i = 0; i < A.length; i++) {
            int count = map.containsKey(1) ? map.get(1) : 0;
            int j = 2;
            while (j * j < A[i]) {
                if (A[i] % j == 0) {
                    int b = A[i] / j;
                    if (map.containsKey(j)) {
                        count += map.get(j);
                    }
                    if (map.containsKey(b)) {
                        count += map.get(b);
                    }
                }
                j++;
            }
            if (j * j == A[i] && A[i] != 1) {
                if (map.containsKey(j)) {
                    count += map.get(j);
                }
            }
            if (A[i] != 1) {
                count += map.get(A[i]);//itself
            }
            result[i] = A.length - count;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(CountNonDivisible.solution1(new int[] { 3, 1, 2, 3, 6 })));
        System.out.println(Arrays.toString(CountNonDivisible.solution2(new int[] { 3, 1, 2, 3, 6 })));
    }

}
