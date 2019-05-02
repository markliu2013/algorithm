package com.zfwhub.algorithm.leetcode.dp;

import java.util.*;

// https://leetcode.com/problems/uncrossed-lines/
public class UncrossedLines {
    
    // 动态规划，递归，超时。https://leetcode.com/submissions/detail/225416713/
    public static int solution1(int[] A, int[] B) {
        if (A.length == 0) {
            return 0;
        }
        if (B.length == 0) {
            return 0;
        }
        int lastItem = A[A.length-1];
        int[] subA = Arrays.copyOfRange(A, 0, A.length-1);
        int max = solution1(subA, B);
        for (int i = 0; i < B.length; i++) {
            if (B[i] == lastItem) {
                int[] newB = Arrays.copyOfRange(B, 0, i);
                max = Math.max(max, solution1(subA, newB)+1);
            }
        }
        return max;
    }
    
    private static class DpMapKey {
        
        public int[] A;
        public int[] B;
        
        public DpMapKey(int[] a, int[] b) {
            A = a;
            B = b;
        }
        
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + Arrays.hashCode(A);
            result = prime * result + Arrays.hashCode(B);
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
            DpMapKey other = (DpMapKey) obj;
            if (!Arrays.equals(A, other.A))
                return false;
            if (!Arrays.equals(B, other.B))
                return false;
            return true;
        }
    }
    
    // 动态规划，递归+map https://leetcode.com/submissions/detail/226255528/
    public static int solution2(int[] A, int[] B) {
        return solution2DP(A, B, new HashMap<>());
    }
    
    public static int solution2DP(int[] A, int[] B, Map<DpMapKey, Integer> map) {
        if (A.length == 0) {
            return 0;
        }
        if (B.length == 0) {
            return 0;
        }
        int lastItem = A[A.length-1];
        int[] subA = Arrays.copyOfRange(A, 0, A.length-1);
        DpMapKey key1 = new DpMapKey(subA, B);
        int max = 0;
        if (map.containsKey(key1)) {
            max = map.get(key1);
        } else {
            max = solution2DP(subA, B, map);
            map.put(key1, max);
        }
        for (int i = 0; i < B.length; i++) {
            if (B[i] == lastItem) {
                int[] newB = Arrays.copyOfRange(B, 0, i);
                DpMapKey key2 = new DpMapKey(subA, newB);
                int currentMax = 0;
                if (map.containsKey(key2)) {
                    currentMax = map.get(key2);
                } else {
                    currentMax = solution2DP(subA, newB, map);
                    map.put(key2, currentMax);
                }
                max = Math.max(max, currentMax+1);
            }
        }
        return max;
    }
    
    // 动态规划，递推。 https://leetcode.com/problems/uncrossed-lines/submissions/
    public static int solution3(int[] A, int[] B) {
        if (A.length == 0) {
            return 0;
        }
        if (B.length == 0) {
            return 0;
        }
        int[][] dp = new int[A.length][B.length];
        for (int i = 0; i < B.length; i++) {
            if (A[0] == B[i]) {
                for (int j = i; j < B.length; j++) {
                    dp[0][j] = 1;
                }
                break;
            }
        }
        for (int i = 1; i < A.length; i++) {
            int[] preDp = dp[i-1];
            if (B[0] == A[i]) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = preDp[0];
            }
            for (int j = 1; j < B.length; j++) {
                dp[i][j] = Math.max(preDp[j], dp[i][j-1]);
                if (B[j] == A[i]) {
                    dp[i][j] = Math.max(dp[i][j], preDp[j-1]+1);
                }
            }
        }
        return dp[A.length-1][B.length-1];
    }
    
    public static void main(String[] args) {
        int[] A = new int[] {2,5,1,2,5};
        int[] B = new int[] {10,5,2,1,5,2};
        System.out.println(solution1(A, B));
        System.out.println(solution3(A, B));
    }

}
