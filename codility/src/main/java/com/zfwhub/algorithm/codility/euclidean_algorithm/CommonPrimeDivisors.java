package com.zfwhub.algorithm.codility.euclidean_algorithm;

import java.util.ArrayList;
import java.util.HashSet;

import com.zfwhub.algorithm.codility.sieve_of_eratosthenes.Eratosthenes;
import com.zfwhub.algorithm.utils.NumberUtil;

// https://app.codility.com/programmers/lessons/12-euclidean_algorithm/common_prime_divisors/
public class CommonPrimeDivisors {

    public static int solution1(int[] A, int[] B) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            int N = A[i];
            int M = B[i];
            ArrayList<Integer> list1 = Eratosthenes.factorization2(N);
            ArrayList<Integer> list2 = Eratosthenes.factorization2(M);
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

    // https://stackoverflow.com/questions/34251682/finding-common-prime-divisors-in-two-sets-of-numbers-quickly
    public static int solution2(int[] A, int[] B) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            int a = A[i];
            int b = B[i];
            if (checkNums(a, b)) {
                count++;
            }
        }
        return count;
    }

    public static boolean checkNums(int a, int b) {
        if (a == b) {
            return true;
        }
        if (a == 1 || b == 1) {
            return false;
        }
        int gcd = NumberUtil.gcd(a, b);
        if (gcd == 1) {
            return false;
        }
        int newa = a / gcd;
        int newb = b / gcd;
        while (true) {
            if (newa != 1) {
                newa = newa / NumberUtil.gcd(newa, gcd);
            }
            if (newb != 1) {
                newb = newb / NumberUtil.gcd(newb, gcd);
            }
            if (newa == 1 && newb == 1) {
                return true;
            }
            if ((newa != 1 && NumberUtil.gcd(newa, gcd) == 1) || (newb != 1 && NumberUtil.gcd(newb, gcd) == 1)) {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        int[] A = new int[] { 15, 10, 3 };
        int[] B = new int[] { 75, 30, 5 };
        System.out.println(CommonPrimeDivisors.solution1(A, B));
        System.out.println(CommonPrimeDivisors.solution2(A, B));
    }
}
