package com.zfwhub.algorithm.leetcode.todo;

/**
 * https://leetcode.com/problems/max-increase-to-keep-city-skyline/description/
 */
public class MaxIncreaseToKeepCitySkyline {

    public static int solution(int[][] grid) {
        int[] skyline1 = new int[grid.length];
        int[] skyline2 = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            int max1 = 0;
            int max2 = 0;
            for (int j = 0; j < grid[i].length; j++) {
                max1 = Math.max(max1, grid[i][j]);
                max2 = Math.max(max2, grid[j][i]);
            }
            skyline1[i] = max1;
            skyline2[i] = max2;
        }
        int amount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int min = Math.min(skyline1[i], skyline2[j]);
                int num1 = grid[i][j];
                amount += min - num1;
            }
        }
        return amount;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][] { { 3, 0, 8, 4 }, { 2, 4, 5, 7 }, { 9, 2, 6, 3 }, { 0, 3, 1, 0 } };
        System.out.println(MaxIncreaseToKeepCitySkyline.solution(grid));
    }

}
