package com.zfwhub.algorithm.codility.dynamic_programming;

/**
 * https://app.codility.com/programmers/lessons/17-dynamic_programming/number_solitaire/
 */
public class NumberSolitaire {
    
    public static int solution(int[] A) {
        int[] dp = new int[A.length];
        dp[0] = A[0];
        dp[1] = A[0] + A[1];
        for (int i = 2; i < A.length; i++) {
            if (i <= 6) {
                int max = dp[0];
                for (int j = 0; j <= i-1; j++) {
                    max = Math.max(max, dp[j]);
                }
                dp[i] = max + A[i];
            } else {
                int max = dp[i-6];
                for (int j = (i-6)+1; j <= i-1; j++) {
                    max = Math.max(max, dp[j]);
                }
                dp[i] = max + A[i];
            }
        }
        return dp[dp.length - 1];
    }
    
    public static int solution2(int[] A) {
        int[] dp = new int[A.length];
        dp[0] = A[0];
        dp[1] = A[0] + A[1];
        for (int i = 2; i < A.length; i++) {
            int start = i <= 6 ? 0 : i-6;
            int max = dp[start];
            for (int j = start; j < i; j++) {
                max = Math.max(max, dp[j]);
            }
            dp[i] = max + A[i];
        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
//        int[] A = new int[] {1,-2,0,9,-1,-2};
//        int[] A = new int[] {0,-3,-2,-5,-7,-10,-10,-10,-10,-10};
        int[] A = new int[] {-4,-10,-7};
        System.out.println(NumberSolitaire.solution2(A));
    }
    
}
