package com.zfwhub.algorithm.leetcode.contest137;

import com.zfwhub.algorithm.utils.ArrayUtil;

// https://leetcode.com/contest/weekly-contest-137/problems/last-stone-weight/
public class LastStoneWeight {
    
    public static int solution1(int[] stones) {
        if (stones.length == 1) {
            return stones[0];
        }
        for (int i = 0; i < stones.length; i++) {
            solution1Helper(stones);
        }
        return ArrayUtil.sum(stones);
    }
    
    private static void solution1Helper(int[] stones) {
        int maxIndex1 = 0;
        int maxIndex2 = 1;
        if (stones[0] < stones[1]) {
            maxIndex1 = 1;
            maxIndex2 = 0;
        }
        for (int i = 2; i < stones.length; i++) {
            if (stones[i] >= stones[maxIndex1]) {
                maxIndex2 = maxIndex1;
                maxIndex1 = i;
            } else if (stones[i] > stones[maxIndex2]) {
                maxIndex2 = i;
            }
        }
        int gap = stones[maxIndex1] - stones[maxIndex2];
        stones[maxIndex1] = gap;
        stones[maxIndex2] = 0;
    }

    public static void main(String[] args) {
        int[] stones = new int[] {2,7,4,1,8,1};
        System.out.println(solution1(stones));
    }
    
}
