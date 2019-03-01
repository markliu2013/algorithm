package com.zfwhub.algorithm.codility.prime_and_composite_numbers;

// https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/min_perimeter_rectangle/
public class MinPerimeterRectangle {

    // brute force
    public static int solution1(int N) {
        int minPerimeter = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            if (N % i == 0) {
                int a = i;
                int b = N / i;
                int perimeter = 2 * (a + b);
                minPerimeter = Math.min(minPerimeter, perimeter);
            }
        }
        return minPerimeter;
    }

    // just need from 1 to n's square root
    public static int solution2(int N) {
        int minPerimeter = Integer.MAX_VALUE;
        int i = 1;
        while (i < Math.sqrt(N)) {
            if (N % i == 0) {
                int a = i;
                int b = N / i;
                int perimeter = 2 * (a + b);
                minPerimeter = Math.min(minPerimeter, perimeter);
            }
            i++;
        }
        if (i == Math.sqrt(N)) {
            int perimeter = 2 * (i + i);
            minPerimeter = Math.min(minPerimeter, perimeter);
        }
        return minPerimeter;
    }

    // the most close a and b, is the answer.
    public static int solution3(int N) {
        int i = (int) Math.ceil(Math.sqrt(N));
        while (i > 1) {
            if (N % i == 0) {
                return 2 * (i + N / i);
            }
            i--;
        }
        return 2 * (1 + N);
    }

    public static void main(String[] args) {
        System.out.println(solution1(5));
        System.out.println(solution2(5));
        System.out.println(solution3(5));
    }

}
