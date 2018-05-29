package com.zfwhub.algorithm.codility.euclidean_algorithm;

import java.util.ArrayList;
import java.util.HashSet;

import com.zfwhub.algorithm.codility.sieve_of_eratosthenes.Eratosthenes;

/**
 * https://app.codility.com/programmers/lessons/12-euclidean_algorithm/common_prime_divisors/
 */
public class CommonPrimeDivisors {

    /**
     * If they have the same numbers in factorization?
     */
    public static int solution(int[] A, int[] B) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            int N = A[i];
            int M = B[i];
            ArrayList<Integer> list1 = Eratosthenes.factorization(N);
            ArrayList<Integer> list2 = Eratosthenes.factorization(M);
            HashSet<Integer> set1 = new HashSet<Integer>();
            set1.addAll(list1);
            HashSet<Integer> set2 = new HashSet<Integer>();
            set2.addAll(list2);
            if (set1.equals(set2)) {
                count++;
            }
        }
        return count;
    }

    // TODO CommonPrimeDivisors
    public static int solution2(int[] A, int[] B) {
        return 0;
    }

    // TODO 
    public static boolean checkNums(int N, int M) {
        if (N < M) {
            return false;
        } else if (N > M) {
            return checkNums(M, N);
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        int[] A = new int[] { 15, 10, 3 };
        int[] B = new int[] { 75, 30, 5 };
        System.out.println(CommonPrimeDivisors.solution(A, B));
    }
}
