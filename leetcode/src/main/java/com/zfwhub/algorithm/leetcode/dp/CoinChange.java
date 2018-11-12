package com.zfwhub.algorithm.leetcode.dp;

import java.util.*;

// https://leetcode.com/problems/coin-change/
// https://www.cnblogs.com/grandyang/p/5138186.html
public class CoinChange {
    
    public static int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        return dp(coins, amount);
    }
    
    public static int dp(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (coins.length == 0) {
            return -1;
        }
        if (coins.length == 1) {
            if (amount % coins[0] == 0) {
                return amount / coins[0];
            } else {
                return -1;
            }
        }
        int lastCoin = coins[coins.length-1];
        int[] subCoins = Arrays.copyOfRange(coins, 0, coins.length-1);
        // if the amount to be paid is smaller than the highest denomination, this denomination can be discarded.
        if (amount < lastCoin) {
            return dp(subCoins, amount);
        }
        
        int maxHighestCount = amount / lastCoin;//最多用多少次
        int minCount = Integer.MAX_VALUE;
        for (int i = maxHighestCount; i >= 1; i--) {
            int count1 = dp(subCoins, ((amount % lastCoin) + (lastCoin*(maxHighestCount-i)))) + i;
            int count2 = dp(subCoins, amount-(lastCoin*(maxHighestCount-i)))+(maxHighestCount-i);
            int count = -1;
            if (Math.min(count1, count2) == -1) {
                if (count1 == count2 && count1 == -1) {
                    count = -1;
                } else {
                    count = Math.max(count1, count2);
                }
            } else {
                count = Math.min(count1, count2);
            }
            minCount = Math.min(minCount, count);
        }
        return minCount;
    }
    public static void main(String[] args) {
//        int[] coins1 = new int[] { 1, 2, 5 };
//        int amount1 = 11;
//        System.out.println(coinChange(coins1, amount1));
//        int[] coins2 = new int[] { 2 };
//        int amount2 = 3;
//        System.out.println(coinChange(coins2, amount2));
//        int[] coins3 = new int[] {  2, 5, 10 , 1 };
//        int amount3 = 27;
//        System.out.println(coinChange(coins3, amount3));
        int[] coins4 = new int[] { 83, 186, 408, 419 };
        int amount4 = 6249;
        System.out.println(coinChange(coins4, amount4));
    }
}
