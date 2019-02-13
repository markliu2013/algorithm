package com.zfwhub.algorithm.utils;

import java.math.BigInteger;

public class MathUtil {
    
    private MathUtil() { }
    
    /**
     * 求 C(n, k)
     * @param n
     * @param k
     * @return
     */
    // TODO 改为使用递推
    public static BigInteger combine(long n, long k) {
        if (k < 0) {
            throw new IllegalArgumentException("k < 0");
        }
        if (n < 0) {
            throw new IllegalArgumentException("n < 0");
        }
        if (k > n) {
            throw new IllegalArgumentException("k > n");
        }
        if (k == 0 || n == k) {
            return BigInteger.valueOf(1);
        }
        //  C(n, k) = C(n, n-k) 优化
        if (k > n / 2 ) {
            k = n - k;
        }
        BigInteger[] dp = new BigInteger[k+1];
        BigInteger[] preDp = new BigInteger[k+1];
        for (int j = 0; j <= k; j++) {
            preDp[j] = BigInteger.valueOf(0);
        }
        for (int i = 0; i <= n; i++) {
            dp[0] = BigInteger.valueOf(1);
            for (int j = 1; j <= k; j++) {
                dp[j] = preDp[j].add(preDp[j-1]);
            }
            preDp = dp.clone();
        }
        return dp[dp.length-1];
//        return combine(n-1, k) + combine(n-1, k-1);
    }
    
    /**
     * n的阶乘。
     * @param n
     * @return
     */
    public static int factorial(int n) {
        if (n == 1 || n == 0) {
            return 1;
        } else {
            int value = 1;
            for (int i = 2; i <= n; i++) {
                value *= i;
            }
            return value;
        }
    }
    
    /**
     * 斐波纳契数
     * @param n
     * @return
     */
    public static int fibonacci(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int a = 1;
        int b = 2;
        int result = 0;
        for (int i = 3; i <= n; i++) {
            result = a + b;
            a = b;
            b = result;
        }
        return result;
    }
   
}
