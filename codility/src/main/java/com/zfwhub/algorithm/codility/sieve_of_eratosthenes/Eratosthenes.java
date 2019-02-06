package com.zfwhub.algorithm.codility.sieve_of_eratosthenes;

import java.util.*;

// https://codility.com/media/train/9-Sieve.pdf
public class Eratosthenes {
    
    // 找1到n的所有质数
    public static HashSet<Integer> sieve1(int n) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 2; i <= n; i++) {
            set.add(i);
        }
        int i = 2;
        while (i * i <= n) {
            int multiplicator = 2;
            while (i * multiplicator <= n) {
                set.remove(i * multiplicator);
                multiplicator++;
            }
            i++;
        }
        return set;
    }

    public static HashSet<Integer> sieve2(int n) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 2; i <= n; i++) {
            set.add(i);
        }
        int i = 2;
        while (i * i <= n) {
            int k = i * i;
            while (k <= n) {
                set.remove(k);
                k += i;
            }
            i++;
        }
        return set;
    }

    // 分解质因数
    public static ArrayList<Integer> factorization(int n) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int result = n;
        while (result > 1) {
            int i = 2;
            boolean isPrime = true;
            while (i * i <= result) {
                if (result % i == 0) {
                    result = result / i;
                    list.add(i);
                    isPrime = false;
                } else {
                    i++;
                }
            }
            if (isPrime) {
                list.add(result);
                result = 1;
            }
        }
        return list;
    }


    public static ArrayList<Integer> factorization2(int x) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int[] F = arraryF(x);
        while (F[x] > 0) {
            list.add(F[x]);
            x = x / F[x];
        }
        list.add(x);
        return list;
    }

    private static int[] arraryF(int n) {
        int[] F = new int[n + 1];
        int i = 2;
        while (i * i <= n) {
            if (F[i] == 0) {
                int k = i * i;
                while (k <= n) {
                    if (F[k] == 0) {
                        F[k] = i;
                    }
                    k += i;
                }
            }
            i += 1;
        }
        return F;
    }
    
    public static void main(String[] args) {
        // System.out.println(Eratosthenes.sieve(100));
        // System.out.println(Eratosthenes.sieve2(Integer.MAX_VALUE-100));
        System.out.println(Eratosthenes.factorization(75));
        System.out.println(Eratosthenes.factorization2(15));
        System.out.println(sieve1(20));
    }

}
