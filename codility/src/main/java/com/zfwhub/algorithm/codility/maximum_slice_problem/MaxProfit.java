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
            for (int j = i + 1; j < A.length; j++) {
                maxProfit = Math.max(maxProfit, A[j] - A[i]);
            }
        }
        return maxProfit;
    }

    /**
     * DP 
     */
    public static int solution2(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int[] maxProfitArr = new int[A.length];
        int[] minArr = new int[A.length];
        minArr[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            if (A[i] < minArr[i - 1]) {
                minArr[i] = A[i];
            } else {
                minArr[i] = minArr[i - 1];
            }
            maxProfitArr[i] = A[i] - minArr[i];
        }
        int maxProfit = 0;
        for (int i = 0; i < maxProfitArr.length; i++) {
            maxProfit = Math.max(maxProfit, maxProfitArr[i]);
        }
        return maxProfit;
    }

    // TODO DP simple
    public static int solution3(int[] A) {
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(MaxProfit.solution(new int[] { 23171, 21011, 21123, 21366, 21013, 21367 }));
        System.out.println(MaxProfit.solution2(new int[] { 23171, 21011, 21123, 21366, 21013, 21367 }));
    }

}
