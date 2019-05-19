package com.zfwhub.algorithm.leetcode.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/unique-paths-ii/
public class UniquePathsII {
    
    // https://leetcode.com/submissions/detail/229690305/
    public static int solution1(int[][] obstacleGrid) {
        if (obstacleGrid[obstacleGrid.length-1][obstacleGrid[obstacleGrid.length-1].length-1] == 1) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (m == 1) {
            for (int i = 0; i < n; i++) {
                if (obstacleGrid[0][i] == 1) {
                    return 0;
                }
            }
            return 1;
        }
        if (n == 1) {
            for (int i = 0; i < m; i++) {
                if (obstacleGrid[i][0] == 1) {
                    return 0;
                }
            }
            return 1;
        }
        int[][] newM = new int[m][n-1];
        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[i].length-1; j++) {
                newM[i][j] = obstacleGrid[i][j];
            }
        }
        int[][] newN = new int[m-1][n];
        for (int i = 0; i < obstacleGrid.length-1; i++) {
            for (int j = 0; j < obstacleGrid[i].length; j++) {
                newN[i][j] = obstacleGrid[i][j];
            }
        }
        return solution1(newM) + solution1(newN);
    }
    
    // https://leetcode.com/submissions/detail/229879454/
    public static int solution2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                for (int j = i; j < n; j++) {
                    dp[0][j] = 0;
                }
                break;
            }
        }
        for (int j = 0; j < m; j++) {
            dp[j][0] = 1;
        }
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                for (int j = i; j < m; j++) {
                    dp[j][0] = 0;
                }
                break;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
    
    // https://leetcode.com/submissions/detail/229877454/
    public static int solution3(int[][] obstacleGrid) {
        return solution3DPMap(obstacleGrid, new HashMap<>());
    }
    
    public static int solution3DPMap(int[][] obstacleGrid, Map<DPMapKey, Integer> map) {
        if (obstacleGrid[obstacleGrid.length-1][obstacleGrid[obstacleGrid.length-1].length-1] == 1) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (m == 1) {
            for (int i = 0; i < n; i++) {
                if (obstacleGrid[0][i] == 1) {
                    return 0;
                }
            }
            return 1;
        }
        if (n == 1) {
            for (int i = 0; i < m; i++) {
                if (obstacleGrid[i][0] == 1) {
                    return 0;
                }
            }
            return 1;
        }
        int[][] newM = new int[m][n-1];
        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[i].length-1; j++) {
                newM[i][j] = obstacleGrid[i][j];
            }
        }
        int[][] newN = new int[m-1][n];
        for (int i = 0; i < obstacleGrid.length-1; i++) {
            for (int j = 0; j < obstacleGrid[i].length; j++) {
                newN[i][j] = obstacleGrid[i][j];
            }
        }
        int result1 = 0;
        DPMapKey key1 = new DPMapKey(newM);
        if (map.containsKey(key1)) {
            result1 = map.get(key1);
        } else {
            result1 = solution3DPMap(newM, map);
            map.put(key1, result1);
        }
        int result2 = 0;
        DPMapKey key2 = new DPMapKey(newN);
        if (map.containsKey(key2)) {
            result2 = map.get(key2);
        } else {
            result2 = solution3DPMap(newN, map);
            map.put(key2, result2);
        }
        return result1 + result2;
    }
    
    private static class DPMapKey {
        public int[][] obstacleGrid;

        public DPMapKey(int[][] obstacleGrid) {
            this.obstacleGrid = obstacleGrid;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + Arrays.deepHashCode(obstacleGrid);
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            DPMapKey other = (DPMapKey) obj;
            if (!Arrays.deepEquals(obstacleGrid, other.obstacleGrid))
                return false;
            return true;
        }
        
    }
    
    public static void main(String[] args) {
//        int[][] obstacleGrid = new int[][] {{0,0,0},{0,1,0},{0,0,0}};
        int[][] obstacleGrid = new int[][] {{0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},{0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0},{1,1,1,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,1,0,0,0,0,0,0,0,0,1,0,0,1},{0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0},{0,0,0,1,0,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,1,0},{1,0,1,1,1,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0},{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,1,0,0,0,1,0,1,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,1,0},{0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1,0,0,0,0,0},{0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},{1,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,1,0,0,0,1,0,1,0,0,0,0,1},{0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,1,1,0,0,0,0,0},{0,1,0,1,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1,0,0,0,0,0},{0,1,0,0,0,0,0,0,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,1,0,1},{1,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,1,0,0,1,0,0,0,0,0,0},{0,0,1,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,1,1,0,1,0,0,0,0,1,1},{0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,0,1,0,1},{1,1,1,0,1,0,0,0,0,1,0,0,0,0,0,0,1,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0,0},{0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,0,1,1},{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,1,0,0,0}};
        System.out.println(solution2(obstacleGrid));
        System.out.println(solution3(obstacleGrid));
    }

}
