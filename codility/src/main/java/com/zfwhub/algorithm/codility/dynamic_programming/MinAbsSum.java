package com.zfwhub.algorithm.codility.dynamic_programming;

import java.util.*;

import com.zfwhub.algorithm.utils.ArrayUtil;
import com.zfwhub.algorithm.utils.CollectionUtil;

// https://app.codility.com/programmers/lessons/17-dynamic_programming/min_abs_sum/
// 其实是把数组分成两部分，两部分的和差值最小。
public class MinAbsSum {
    
    // 组合暴力解法. Performance 0%s
    public static int solution1(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        int result = Integer.MAX_VALUE;
        // 所有选择的组合，选的为1，不选的为-1。因为A中有重复元素，必须使用索引判断。
        List<List<Integer>> list = CollectionUtil.subsets(CollectionUtil.newIntList(0, A.length));
        for (List<Integer> list2 : list) {
            // 组合生成S数组
            int[] S = new int[A.length];
            for (int i = 0; i < A.length; i++) {
                if (list2.contains(i)) {
                    S[i] = 1;
                } else {
                    S[i] = -1;
                }
            }
            // 计算 val(A, S) 
            int absSum = 0;
            for (int i = 0; i < A.length; i++) {
                absSum +=  A[i]*S[i];
            }
            absSum = Math.abs(absSum);
            
            result = Math.min(result, absSum);
        }
        return result;
    }
    
    // 动态规划，递归。
    // https://app.codility.com/demo/results/trainingXYB78A-PFT/
    public static int solution2(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        int lastItem = A[A.length-1];
        int[] subArray = Arrays.copyOfRange(A, 0, A.length-1);
        return Math.abs(solution2DP(subArray, lastItem) - lastItem);
    }
    
    private static int solution2DP(int[] A, int target) {
        if (A.length == 0) {
            return 0;
        }
        int lastItem = A[A.length-1];
        int[] subArray = Arrays.copyOfRange(A, 0, A.length-1);
        // lastItem和target一拨
        int result01 = solution2DP(subArray, target+lastItem);
        // lastItem和target不是一拨
        int result02 = solution2DP(subArray, target-lastItem);
        if (Math.abs(result01-lastItem-target) > Math.abs(result02+lastItem-target)) {
            return result02 + lastItem;
        } else {
            return result01 - lastItem;
        }
    }
    
    // TODO MinAbsSum 后面都没搞定。
    // 动态规划，递推。
    public static int solution3(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        // 将数组全部变为正数后再排序处理，不影响结果。
        ArrayUtil.abs(A);
        int lastItem = A[A.length-1];
        int[][] dp = new int[A.length][200];
        for (int i = 0; i < A.length-1; i++) {
            for (int j = 0; j < 100; j++) {
                // 根据 dp[i] 推算 dp[i+1][j]
                if (Math.abs(dp[i][j+A[i]]-A[i]-j) > Math.abs(dp[i][Math.abs(j-A[i])]+A[i]-j)) {
                    dp[i+1][j] = dp[i][Math.abs(j-A[i])] + A[i];
                } else {
                    dp[i+1][j] = Math.abs(dp[i][j+A[i]] - A[i]);
                }
            }
            for (int j = 100; j < 200; j++) {
                dp[i+1][j] = dp[i][j-A[i]]+A[i];
            }
        }
        return Math.abs(lastItem - dp[dp.length-1][lastItem]);
    }
    
    public static int solution4(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        // 将数组全部变为正数后再排序处理，不影响结果。
        ArrayUtil.abs(A);
        
        // dp存的是最后结果。
        int[][] dp = new int[A.length][101];
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i < A.length-1; i++) {
            for (int j = 0; j < 100; j++) {
                // 根据 dp[i] 推算 dp[i+1][j]
                if (j+A[i] <= 100) {
                    /*if (dp[i][j+A[i]] > dp[i][Math.abs(j-A[i])]) {
                        // A[i]和j不是一拨，[1,2,4]
                        dp[i+1][j] = dp[i][Math.abs(j-A[i])];
                    } else {
                        // A[i]和j一拨，[3,1,2]
                        dp[i+1][j] = dp[i][j+A[i]];
                    }*/
                    dp[i+1][j] = Math.min(dp[i][j+A[i]], dp[i][Math.abs(j-A[i])]);
                    
                } else {
                    dp[i+1][j] = dp[i][Math.abs(j-A[i])];
                }
                
            }
        }
        return dp[dp.length-1][A[A.length-1]];
    }
    
    public static int solution5(int[] A) {
        if (A.length == 0) {
            return 0;
        }
        ArrayUtil.abs(A);
        int sumA = ArrayUtil.sum(A);
        int halfSum = sumA / 2;
        System.out.println(halfSum);
                
        return 0;
    }
    
    public static void main(String[] args) {
        int[] A = new int[] { 50,51,100};
//        System.out.println(solution1(A));
        System.out.println(solution2(A));
        System.out.println(solution3(A));
        System.out.println(solution4(A));
        double ii = 5/(double)2;
        System.out.println(ii);
    }

}
