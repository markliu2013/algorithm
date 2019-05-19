package com.zfwhub.algorithm.leetcode.contest137;

import java.util.Arrays;

// https://leetcode.com/contest/weekly-contest-137/problems/last-stone-weight-ii/
public class LastStoneWeightII {
    
    private static int MAX = 5;
    
    public static int solution1(int[] stones) {
        if (stones.length == 1) {
            return stones[0];
        }
        int[][] dp = new int[stones.length][MAX];
        for (int j = 0; j < MAX; j++) {
            dp[0][j] = stones[0];
        }
        for (int j = 0; j < MAX; j++) {
            dp[1][j] = Math.abs(stones[0] - stones[1]);
        }
        for (int i = 2; i < stones.length-1; i++) {
            int[] preDp = dp[i-1];
            for (int j = 0; j < MAX; j++) {
                dp[i][j] = Math.abs((j+1)-preDp[j]);
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return stones[stones.length-1] - dp[dp.length-2][stones[stones.length-1]];
    }
    
    public static int solution2(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        int lastItem = A[A.length-1];
        int[] subArray = Arrays.copyOfRange(A, 0, A.length-1);
        return Math.abs(solution2DP(subArray, lastItem) - lastItem);
    }
    
    // 数组A分成两拨，与target的差值最小化。
    private static int solution2DP(int[] A, int target) {
        if (A.length == 0) {
            return 0;
        }
        int lastItem = A[A.length-1];
        int[] subArray = Arrays.copyOfRange(A, 0, A.length-1);
        // lastItem和target一拨
        int result01 = solution2DP(subArray, target+lastItem);
        // lastItem和target不是一拨
        int result02 = solution2DP(subArray, target-lastItem);
        if (Math.abs(result01-lastItem-target) > Math.abs(result02+lastItem-target)) {
            return result02 + lastItem;
        } else {
            return result01 - lastItem;
        }
    }
    
    
    public static void main(String[] args) {
        System.out.println(solution2(new int[] {2,7,4,1,8,1})); // 5
    }
    
}
