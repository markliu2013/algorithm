package com.zfwhub.algorithm.leetcode.dp;

import java.util.*;

// https://leetcode.com/problems/coin-change/
// https://www.cnblogs.com/grandyang/p/5138186.html
// https://blog.csdn.net/kiwi_coder/article/details/45091773
public class CoinChange {
    
    public static int solution1(int[] coins, int amount) {
        Arrays.sort(coins);
        int count = dp(coins, amount);
        return count == Integer.MAX_VALUE ? -1 : count;
    }
    
    public static int solution2(int[] coins, int amount) {
        Arrays.sort(coins);
        HashMap<List<Integer>, Integer> map = new HashMap<>();
        int count = dp2(coins, amount, map);
        return count == Integer.MAX_VALUE ? -1 : count;
    }
    
    public static int dp(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (coins.length == 0) {
            return Integer.MAX_VALUE;
        }
        if (coins.length == 1) {
            if (amount % coins[0] == 0) {
                return amount / coins[0];
            } else {
                return Integer.MAX_VALUE;
            }
        }
        if (coins[0] > amount) {
            return Integer.MAX_VALUE;
        }
        int lastCoin = coins[coins.length-1];
        int[] subCoins = Arrays.copyOfRange(coins, 0, coins.length-1);
        // if the amount to be paid is smaller than the highest denomination, this denomination can be discarded.
        if (amount < lastCoin) {
            return dp(subCoins, amount);
        }
        int minCount = Integer.MAX_VALUE;
        int maxHighestCount = amount / lastCoin;//最多用多少次
        for (int i = maxHighestCount; i >= 0; i--) {
            int count1 = dp(subCoins, ((amount % lastCoin) + (lastCoin*(maxHighestCount-i))));
            if (count1 != Integer.MAX_VALUE) {
                count1 += i;
            }
            int count2 = dp(subCoins, amount-(lastCoin*(maxHighestCount-i)));
            if (count2 != Integer.MAX_VALUE) {
                count2 += maxHighestCount-i;
            }
            minCount = Math.min(minCount, Math.min(count1, count2));
        }
        return minCount;
    }
    
    // dynamic programming memoization
    public static int dp2(int[] coins, int amount, HashMap<List<Integer>, Integer> map) {
        if (amount == 0) {
            return 0;
        }
        if (coins.length == 0) {
            return Integer.MAX_VALUE;
        }
        if (coins.length == 1) {
            if (amount % coins[0] == 0) {
                return amount / coins[0];
            } else {
                return Integer.MAX_VALUE;
            }
        }
        if (coins[0] > amount) {
            return Integer.MAX_VALUE;
        }
        int lastCoin = coins[coins.length-1];
        int[] subCoins = Arrays.copyOfRange(coins, 0, coins.length-1);
        // if the amount to be paid is smaller than the highest denomination, this denomination can be discarded.
        if (amount < lastCoin) {
            List<Integer> memoKey = new ArrayList<>();
            memoKey.add(subCoins.length);
            memoKey.add(amount);
            if (map.containsKey(memoKey)) {
                return map.get(memoKey);
            } else {
                int val = dp2(subCoins, amount, map);
                map.put(memoKey, val);
                return val;
            }
        }
        int minCount = Integer.MAX_VALUE;
        int maxHighestCount = amount / lastCoin;//最多用多少次
        for (int i = maxHighestCount; i >= 0; i--) {
            int count1 = 0;
            List<Integer> memoKey1 = new ArrayList<>();
            memoKey1.add(subCoins.length);
            memoKey1.add((amount % lastCoin) + (lastCoin*(maxHighestCount-i)));
            if (map.containsKey(memoKey1)) {
                count1 = map.get(memoKey1);
            } else {
                int val = dp2(subCoins, ((amount % lastCoin) + (lastCoin*(maxHighestCount-i))), map);
                map.put(memoKey1, val);
                count1 = val;
            }
            if (count1 != Integer.MAX_VALUE) {
                count1 += i;
            }
            
            int count2 = 0;
            List<Integer> memoKey2 = new ArrayList<>();
            memoKey2.add(subCoins.length);
            memoKey2.add(amount-(lastCoin*(maxHighestCount-i)));
            if (map.containsKey(memoKey2)) {
                count2 = map.get(memoKey2);
            } else {
                int val = dp2(subCoins, amount-(lastCoin*(maxHighestCount-i)), map);
                map.put(memoKey2, val);
                count2 = val;
            }
            if (count2 != Integer.MAX_VALUE) {
                count2 += maxHighestCount-i;
            }
            
            minCount = Math.min(minCount, Math.min(count1, count2));
        }
        return minCount;
    }
    
    public static int solution3(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] dp = new int[amount+1];
        for (int i = 1; i < dp.length; i++) {
            int min = Integer.MAX_VALUE-5;
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) {
                    min = Math.min(min, dp[i-coins[j]]+1);
                } else {
                    break;
                }
            }
            dp[i] = min;
        }
        return dp[dp.length-1] >= Integer.MAX_VALUE-5 ? -1 : dp[dp.length-1];
    }
    
    
    
    public static void main(String[] args) {
//        int[] coins1 = new int[] { 1, 2, 5 };
//        int amount1 = 11;
//        System.out.println(solution1(coins1, amount1));
//        System.out.println(solution3(coins1, amount1));
//        int[] coins2 = new int[] { 2 };
//        int amount2 = 3;
//        System.out.println(solution1(coins2, amount2));
//        System.out.println(solution3(coins2, amount2));
//        int[] coins3 = new int[] {  2, 5, 10 , 1 };
//        int amount3 = 27;
//        System.out.println(solution1(coins3, amount3));
//        int[] coins4 = new int[] { 186,419,83,408 };
//        int amount4 = 6249;
//        System.out.println(solution1(coins4, amount4));
//        int[] coins5 = new int[] { 58,92,387,421,194,208,231 };
//        int amount5 = 7798;
//        System.out.println(solution1(coins5, amount5));
//        System.out.println(solution3(coins5, amount5));
        int[] coins6 = new int[] { 474,83,404,3 };
        int amount6 = 264;
//        System.out.println(solution1(coins6, amount6));
        System.out.println(solution3(coins6, amount6));
    }
    
}
