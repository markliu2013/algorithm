package com.zfwhub.algorithm.utils;

import java.math.BigInteger;

public class MathUtil {
    
    private MathUtil() { }
    
    // 计算排列组合数，阶乘的最大值。
    private static final int MAX_COMBINE = 10000;
    // 计算阶乘支持的最大数
    private static final int MAX_FACTORIAL = 10000;
    // 计算斐波那契支持的最大数
    private static final int MAX_FIBONACCI = 50000;
    
    /**
     * 求组合数 C(n, k)
     * @param n
     * @param k
     * @return
     */
    public static BigInteger combine(int n, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("k < 0");
        }
        if (n < 0) {
            throw new IllegalArgumentException("n < 0");
        }
        if (k > n) {
            throw new IllegalArgumentException("k > n");
        }
        if (n > MAX_COMBINE) {
            throw new IllegalArgumentException("n > " + MAX_COMBINE);
        }
        if (k == 0 || n == k) {
            return BigInteger.valueOf(1);
        }
        if (k == 1) {
            return BigInteger.valueOf(n);
        }
        return factorial(n).divide(factorial(k).multiply(factorial(n-k)));
        // 递推
        /* // C(n, k) = C(n, n-k) 优化
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
        return dp[dp.length-1];*/
        // 递归
//        return combine(n-1, k).add(combine(n-1, k-1));
    }
    
    /**
     * n的阶乘。
     * @param n
     * @return
     */
    public static BigInteger factorial(int n) {
        // 负数没有阶乘
        if (n < 0) {
            throw new IllegalArgumentException("n < 0");
        }
        if (n > MAX_FACTORIAL) {
            throw new IllegalArgumentException("n > " + MAX_FACTORIAL);
        }
        // 0! = 1
        if (n == 1 || n == 0) {
            return BigInteger.valueOf(1);
        } else {
            BigInteger value = BigInteger.valueOf(1);
            for (int i = 2; i <= n; i++) {
                value = value.multiply(BigInteger.valueOf(i));
            }
            return value;
        }
    }
    
    /**
     * 求斐波那契的第n项，首项从0开始。0 1 1 2 3 5。
     * @param n
     * @return
     */
    // 通向公式算法
    public static BigInteger fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n < 0");
        }
        if (n > MAX_FIBONACCI) {
            throw new IllegalArgumentException("n > " + MAX_FIBONACCI);
        }
        if (n == 0) {
            return BigInteger.valueOf(0);
        }
        if (n == 1) {
            return BigInteger.valueOf(1);
        }
        BigInteger a = BigInteger.valueOf(0);
        BigInteger b = BigInteger.valueOf(1);
        BigInteger temp = BigInteger.valueOf(0);
        for (int i = 2; i <= n; i++) {
            temp = a.add(b);
            a = b;
            b = temp;
        }
        return temp;
    }
    
    public static void main(String[] args) {
        System.out.println(fibonacci(30));
    }
    
}
