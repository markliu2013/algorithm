package com.zfwhub.algorithm.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/unique-paths/
public class UniquePaths {
    
    // https://leetcode.com/submissions/detail/229516206/
    public static int solution1(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }
        return solution1(m-1, n) + solution1(m, n-1);
    }
    
    // https://leetcode.com/submissions/detail/229528331/
    public static int solution2(int m, int n) {
        return solution2DP(m, n, new HashMap<>());
    }
    
    public static int solution2DP(int m, int n, Map<DPMapKey, Integer> map) {
        if (m == 1 || n == 1) {
            return 1;
        }
        int result1 = 0;
        DPMapKey key1 = new DPMapKey(m-1, n);
        if (map.containsKey(key1)) {
            result1 = map.get(key1);
        } else {
            result1 = solution2DP(m-1, n, map);
            map.put(key1, result1);
        }
        int result2 = 0;
        DPMapKey key2 = new DPMapKey(m, n-1);
        if (map.containsKey(key2)) {
            result2 = map.get(key2);
        } else {
            result2 = solution2DP(m, n-1, map);
            map.put(key2, result2);
        } 
        return result1 + result2;
    }
    
    // 
    public static int solution3(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
    
    private static class DPMapKey {
        public int m;
        public int n;
        
        public DPMapKey(int m, int n) {
            this.m = m;
            this.n = n;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + m;
            result = prime * result + n;
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
            if (m != other.m)
                return false;
            if (n != other.n)
                return false;
            return true;
        }
        
    }
    
    public static void main(String[] args) {
        System.out.println(solution2(51, 9));
//        System.out.println(solution1(51, 9));
        System.out.println(solution3(51, 9));
    }
    
}
