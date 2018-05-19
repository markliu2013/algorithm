package com.zfwhub.algorithm.codility.prime_and_composite_numbers;

/**
 * https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/min_perimeter_rectangle/ 
 */
public class MinPerimeterRectangle {
    
    /**
     * brute force 
     */
    public static int solution(int N) {
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
    
    // TODO MinPerimeterRectangle
    public static int solution2(int N) {
        int count = 0;
        
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println(MinPerimeterRectangle.solution(30));
    }
    
}
