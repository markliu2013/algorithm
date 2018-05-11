package com.zfwhub.algorithm.math.sequence;

/**
 * 阶乘
 */
public class Factorial {

    public int fac(int n) {
        if (n == 1 || n == 0) {
            return 1;
        } else {
            return n * fac(n-1);
        }
    }

    public int fac2(int n) {
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

    public static void main(String[] args) {
        Factorial f = new Factorial();
        System.out.println(f.fac2(5));
    }
}
