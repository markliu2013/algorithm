package com.zfwhub.algorithm.utils;

public class NumberUtil {
    
    /**
     * 寻找一个b的倍数，满足最接近a且不大于a。
     * @param a
     * @param b
     * @return
     */
    public static int closestNumber(int a, int b) {
        return b * (a / b);
    }
    
    /**
     * 计算1到n之间自然数的求和。
     * @param n
     * @return
     */
    public static int getSumFromOne(int n) {
        return (n*(n+1)) / 2;
    }
    
    /**
     * a到b之间自然数的求和，
     * @param a inclusive
     * @param b inclusive
     * @return
     */
    public  long sum(int a, int b) {
//        if (k < 0) {
//            throw new IllegalArgumentException("k < 0");
//        }
//        if (n < 0) {
//            throw new IllegalArgumentException("n < 0");
//        }
//        if (k > n) {
//            throw new IllegalArgumentException("k > n");
//        }
        return ( (a+b) * (b-a+1) ) / 2;
    }
    
    /**
     * 求a和b的最大公约数。
     * @param a
     * @param b
     * @return
     */
    // BigInteger 也可以计算gcd
    public static long gcd(long a, long b) {
        // 碾转相除法
        if (a % b == 0) {
            return b;
        } else {
            return gcd(b, a % b);
        }
    }

}
