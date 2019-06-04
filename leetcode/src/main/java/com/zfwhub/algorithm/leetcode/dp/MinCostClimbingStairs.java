package com.zfwhub.algorithm.leetcode.dp;

import java.util.Arrays;

// https://leetcode.com/problems/min-cost-climbing-stairs/
public class MinCostClimbingStairs {
    
    public static int solution1(int[] cost) {
        if (cost.length == 1) {
            return 0;
        }
        if (cost.length == 2) {
            return Math.min(cost[0], cost[1]);
        }
        int lastItem = cost[cost.length-1];
        int[] subCost = Arrays.copyOfRange(cost, 0, cost.length-1);
        int result1 = solution1(subCost)+lastItem;
        int lastItem2 = cost[cost.length-2];
        int[] subCost2 = Arrays.copyOfRange(cost, 0, cost.length-2);
        int result2 = solution1(subCost2)+lastItem2;
        return Math.min(result1, result2);
    }
    
    public static void main(String[] args) {
        System.out.println(solution1(new int[] {10, 15, 20}));
        System.out.println(solution1(new int[] {1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }

}
