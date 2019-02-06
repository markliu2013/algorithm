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
     * 求a和b的最大公约数。
     * @param a
     * @param b
     * @return
     */
    // TODO gcd
    public static int gcd(int a, int b) {
        return 0;
    }

}
