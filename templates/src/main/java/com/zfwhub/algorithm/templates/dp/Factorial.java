package com.zfwhub.algorithm.templates.dp;

/**
 * 阶乘算法
 */
public class Factorial {

    /**
     * recursion, from top to down
     */
    public int fac(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * fac(n - 1);
        }
    }

    /**
     * for loop, from down to top
     */
    public int fac2(int n) {
        if (n == 0) {
            return 1;
        } else {
            int value = 1;
            for (int i = 2; i <= n; i++) {
                value *= i;
            }
            return value;
        }
    }

    public static void main(String[] args) {
        Factorial f = new Factorial();
        System.out.println(f.fac2(5));
        System.out.println(f.fac(5));
    }
}
