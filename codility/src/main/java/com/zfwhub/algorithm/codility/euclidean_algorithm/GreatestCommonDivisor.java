package com.zfwhub.algorithm.codility.euclidean_algorithm;

public class GreatestCommonDivisor {

    /**
     * My solution, from small number's biggest Divisor, check if it is large number's
     */
    public static int gcd(int a, int b) {
        //a < b
        if (a <= b) {
            if (b % a == 0) {
                return a;
            }
            int i = 2;
            int max = 1;
            while (i * i <= a) {
                if (a % i == 0) {
                    if (b % (a / i) == 0) {
                        return a / i;
                    }
                    if (b % i == 0) {
                        max = i;
                    }
                }
                i++;
            }
            return max;
        } else {
            return gcd(b, a);
        }
    }

    /**
     * euclidean algorithm by subtraction
     */
    public static int gcd2(int a, int b) {
        if (a == b) {
            return a;
        }
        if (a > b) {
            return gcd2(a - b, b);
        } else {
            return gcd2(a, b - a);
        }
    }

    /**
     * euclidean algorithm by dividing
     */
    public static int gcd3(int a, int b) {
        if (a % b == 0) {
            return b;
        } else {
            return gcd3(b, a % b);
        }

    }

    public static void main(String[] args) {
        System.out.println(GreatestCommonDivisor.gcd(123121212, 1231234234));
        System.out.println(GreatestCommonDivisor.gcd3(123121212, 1231234234));
        //System.out.println(GreatestCommonDivisor.gcd2(12312, 1231234234));
    }

}
