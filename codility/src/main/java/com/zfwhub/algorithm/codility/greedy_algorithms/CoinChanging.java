package com.zfwhub.algorithm.codility.greedy_algorithms;

import java.util.*;

// https://codility.com/media/train/14-GreedyAlgorithms.pdf
public class CoinChanging {

    /**
     * greedy, wrong. M = [1,3,4] k = 6
     */
    public static LinkedHashMap<Integer, Integer> solution(int[] M, int k) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
        for (int i = M.length - 1; i >= 0; i--) {
            map.put(M[i], k / M[i]);
            k = k % M[i];
        }
        return map;
    }

    // TODO CoinChanging
    // https://codility.com/media/train/15-DynamicProgramming.pdf
    public static LinkedHashMap<Integer, Integer> solution2(int[] M, int k) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        System.out.println(dp(M, k, map));
        return map;
    }
    
    public static int dp(int[] M, int k, LinkedHashMap<Integer, Integer> map) {
        if (k == 0) {
            return 0;
        }
        if (M.length == 1) {
            if (k % M[0] == 0) {
                return k / M[0];
            } else {
                return -1;
            }
        }
        int[] subM = Arrays.copyOfRange(M, 0, M.length-1);
        // if the amount to be paid is smaller than the highest denomination, this denomination can be discarded.
        if (k < M[M.length-1]) {
            return dp(subM, k, map);
        }
        // use highest denomination
        int count1 = dp(subM, k % M[M.length-1], map) + k / M[M.length-1];
        // dont use highest denomination
        int count2 = dp(subM, k, map);
        return Math.min(count1, count2);
    }
    
    // 计算用了多少coins
    public static int count(LinkedHashMap<Integer, Integer> map) {
        int count =  0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            count += entry.getValue();
        }
        return count;
    }

    public static void main(String[] args) {
        int[] M = new int[] { 1, 3, 4 };
        int k = 6;
        System.out.println(CoinChanging.solution(M, k));
        System.out.println(CoinChanging.solution2(M, k));
    }
}
