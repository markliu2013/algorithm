package com.zfwhub.algorithm.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberUtil {
    
    private static final int GET_ALL_PRIME_MAX = 30000000;
    
    public enum ClosestMultipleFlag {
        FLOOR, ROUND, CEIL;
    }
    
    /**
     * 寻找一个b的倍数，满足最接近a。
     * @param a
     * @param b
     * @param flag 控制和a的大小关系，FLOOR，不大于a。ROUND，绝对值最接近a。CEIL，大于或等于a。
     * @return
     */
    public static int closestMultiple(int a, int b, ClosestMultipleFlag flag) {
        if (a < 0) {
            throw new IllegalArgumentException("a < 0");
        }
        if (b < 0) {
            throw new IllegalArgumentException("b < 0");
        }
        // 0没有倍数
        if (b == 0) {
            throw new IllegalArgumentException("b = 0");
        }
        if (flag == ClosestMultipleFlag.FLOOR) {
            return b * (a / b);
        } else if (flag == ClosestMultipleFlag.CEIL) {
            return (int)(b * Math.ceil((double)a / b));
        } else {
            return (int)(b * Math.round((double)a / b));
        }
    }
    
    /**
     * a到b之间整数的求和，
     * @param start inclusive
     * @param stop inclusive
     * @return
     */
    public static long sum(long start, long stop) {
        if (start > stop) throw new IllegalArgumentException("a > b");
        return ( (start+stop) * (stop-start+1) ) / 2;
    }
    
    /**
     * 求a和b的最大公约数。
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a, int b) {
        if (a == 0) throw new IllegalArgumentException("a = 0");
        if (b == 0) throw new IllegalArgumentException("b = 0");
        if (a < 0) throw new IllegalArgumentException("a < 0");
        if (b < 0) throw new IllegalArgumentException("b < 0");
        // 碾转相除法. BigInteger 也可以计算gcd
        if (a % b == 0) {
            return b;
        } else {
            return gcd(b, a % b);
        }
    }
    
    /**
     * 小于或等于n的所有质数。
     * @param n
     * @return
     */
    public static List<Integer> getAllPrime(int n) {
        if (n > GET_ALL_PRIME_MAX) {
            throw new IllegalArgumentException("n > " + GET_ALL_PRIME_MAX);
        }
        boolean prime[] = new boolean[n + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        List<Integer> primeNumbers = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (prime[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }
    
}
