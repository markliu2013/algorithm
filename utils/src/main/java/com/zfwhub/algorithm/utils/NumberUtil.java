package com.zfwhub.algorithm.utils;

public class NumberUtil {
    
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
    
}
