package com.zfwhub.algorithm.codility.prime_and_composite_numbers;
/**
 * https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/count_factors/ 
 */
public class CountFactors {
    
    /**
     * count 1 by 1 
     */
    public static int solution(int N) {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (N % i == 0) {
                count++;
            }
        }
        return count;
    }
    
    // TODO CountFactors
    public static int solution2(int N) {
        int count = 0;
        
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println(CountFactors.solution(24));
    }

}
