package com.zfwhub.algorithm.codility.maximum_slice_problem;
/**
 * https://app.codility.com/programmers/lessons/9-maximum_slice_problem/max_profit/
 */
public class MaxProfit {
    
    /**
     * straightforward, brute force
     */
    public static int solution(int[] A) {
        int maxProfit = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i+1; j < A.length; j++) {
                maxProfit = Math.max(maxProfit, A[j] - A[i]);
            }
        }
        return maxProfit;
    }
    
    // TODO MaxProfit
    public static int solution2(int[] A) {
        return 0;
    }
    
    public static void main(String[] args) {
        System.out.println(MaxProfit.solution(new int[] {23171,21011,21123,21366,21013,21367}));
    }
    
}
