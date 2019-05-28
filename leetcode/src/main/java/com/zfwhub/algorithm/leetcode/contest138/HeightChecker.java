package com.zfwhub.algorithm.leetcode.contest138;

import java.util.Arrays;

// https://leetcode.com/contest/weekly-contest-138/problems/height-checker/
public class HeightChecker {
    
    public static int solution1(int[] heights) {
        int[] oldHeights = heights.clone();
        Arrays.sort(heights);
        int count = 0;
        for (int i = 0; i < oldHeights.length; i++) {
            if (oldHeights[i] != heights[i]) {
                count++;
            }
        }
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println(solution1(new int[] {1,1,4,2,1,3}));
    }

}
