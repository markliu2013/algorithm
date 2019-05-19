package com.zfwhub.algorithm.leetcode.dp;

import com.zfwhub.algorithm.utils.ArrayUtil;

// https://leetcode.com/problems/minimum-path-sum/
public class MinimumPathSum {
    
    // https://leetcode.com/submissions/detail/229694785/
    public static int solution1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (m == 1) {
            return ArrayUtil.sum(grid[0]);
        }
        if (n == 1) {
            int sum = 0;
            for (int i = 0; i < m; i++) {
                sum += grid[i][0];
            }
            return sum;
        }
        int[][] newM = new int[m][n-1];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length-1; j++) {
                newM[i][j] = grid[i][j];
            }
        }
        int[][] newN = new int[m-1][n];
        for (int i = 0; i < grid.length-1; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                newN[i][j] = grid[i][j];
            }
        }
        int result1 = solution1(newM) + grid[grid.length-1][grid[0].length-1];
        int result2 = solution1(newN) + grid[grid.length-1][grid[0].length-1];
        return Math.min(result1, result2);
    }
    
    // https://leetcode.com/submissions/detail/229884095/
    public static int solution2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j] + grid[i][j], dp[i][j-1] + grid[i][j]);
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
    
    public static void main(String[] args) {
        System.out.println(solution1(new int[][] {{1,3,1},{1,5,1},{4,2,1}}));
        System.out.println(solution2(new int[][] {{1,3,1},{1,5,1},{4,2,1}}));
        System.out.println(solution1(new int[][] {{0,1},{1,0}}));
        System.out.println(solution2(new int[][] {{0,1},{1,0}}));
    }

}
