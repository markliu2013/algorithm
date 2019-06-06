package com.zfwhub.algorithm.leetcode.dp;

// https://leetcode.com/problems/integer-break/
public class IntegerBreak {

    public static int solution1(int n) {
        if (n == 1) {
            return 1;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n-1; i++) {
            max = Math.max(max, Math.max(solution1(n-i) * i, (n-i)*i));
        }
        return max;
    }
    
    public static int solution2(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.max(dp[i], Math.max(dp[i-j]*j, (i+1-j)*j));
            }
        }
        return dp[n-1];
    }
    
    public static void main(String[] args) {
        System.out.println(solution1(25));
        System.out.println(solution2(50));
    }

}
