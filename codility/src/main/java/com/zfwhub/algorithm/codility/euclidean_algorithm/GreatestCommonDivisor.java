package com.zfwhub.algorithm.codility.euclidean_algorithm;

public class GreatestCommonDivisor {
    
    public static int solution(int a, int b) {
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
            return solution(b, a);
        }
    }
    
    public static void main(String[] args) {
        System.out.println(GreatestCommonDivisor.solution(319, 377));
    }

}
