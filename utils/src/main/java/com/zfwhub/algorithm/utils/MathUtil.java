package com.zfwhub.algorithm.utils;

public class MathUtil {
    
    private MathUtil() { }
    
    /**
     * 求 C(n, k)
     * @param n
     * @param k
     * @return
     */
    public static int combine(int n, int k) {
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
            return 1;
        }
        return combine(n-1, k) + combine(n-1, k-1);
    }
    
    public static void main(String[] args) {
        int re = MathUtil.combine(100, 98);
        System.out.println(re);
    }

}
