package com.zfwhub.algorithm.codility.caterpillar_method;

/**
 * https://app.codility.com/programmers/lessons/15-caterpillar_method/min_abs_sum_of_two/
 */
public class MinAbsSumOfTwo {
    
    /**
     * brute force
     */
    public static int solution(int[] A) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            for (int j = i; j < A.length; j++) {
                min = Math.min(Math.abs(A[i] + A[j]), min);
            }
        }
        return min;
    }
    
    // TODO MinAbsSumOfTwo
    public static int solution2(int[] A) {
        return 0;
    }
    
    public static void main(String[] args) {
        int[] A = new int[] {1,4,-3};
        System.out.println(MinAbsSumOfTwo.solution(A));
        A = new int[] {-8,4,5,-10,3};
        System.out.println(MinAbsSumOfTwo.solution(A));
    }

}
