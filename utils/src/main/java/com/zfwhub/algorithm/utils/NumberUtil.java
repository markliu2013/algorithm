package com.zfwhub.algorithm.utils;

public class NumberUtil {
    
    /**
     * 寻找一个b的倍数，满足最接近a且不大于a。
     * @param a
     * @param b
     * @return
     */
    public static int closestMultiple(int a, int b) {
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
        return b * (a / b);
    }
    
    /**
     * a到b之间整数的求和，
     * @param a inclusive
     * @param b inclusive
     * @return
     */
    public static int sum(int a, int b) {
        if (a > b) throw new IllegalArgumentException("a > b");
        return ( (a+b) * (b-a+1) ) / 2;
    }
    
    /**
     * 求a和b的最大公约数。
     * @param a
     * @param b
     * @return
     */
    // BigInteger 也可以计算gcd
    public static int gcd(int a, int b) {
        if (a == 0) {
            throw new IllegalArgumentException("a = 0");
        }
        if (b == 0) {
            throw new IllegalArgumentException("b = 0");
        }
        // TODO 负数 gcd
        // 碾转相除法
        if (a % b == 0) {
            return b;
        } else {
            return gcd(b, a % b);
        }
    }

}
