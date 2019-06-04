package com.zfwhub.algorithm.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/perfect-squares/
public class PerfectSquares {
    
    // 找硬币问题
    public static int solution1(int n) {
        List<Integer> coins = new ArrayList<>();
        int i = 1;
        while (i*i <= n) {
            coins.add(i*i);
            i = i+1;
        }
        return coinChange(coins, n);
    }
    
    public static int coinChange(List<Integer> coins, int amount) {
        int[] dp = new int[amount+1];
        for (int i = 1; i < dp.length; i++) {
            int min = Integer.MAX_VALUE-5;
            for (int j = 0; j < coins.size(); j++) {
                if (i >= coins.get(j)) {
                    min = Math.min(min, dp[i-coins.get(j)]+1);
                } else {
                    break;
                }
            }
            dp[i] = min;
        }
        return dp[dp.length-1] >= Integer.MAX_VALUE-5 ? -1 : dp[dp.length-1];
    }
    
    public static void main(String[] args) {
        System.out.println(solution1(12));
        System.out.println(solution1(13));
        System.out.println(solution1(1));
    }

}
