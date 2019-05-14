package com.zfwhub.algorithm.leetcode.contest136;

import java.util.Arrays;
import java.util.List;

import com.zfwhub.algorithm.utils.ArrayUtil;
import com.zfwhub.algorithm.utils.CollectionUtil;

// https://leetcode.com/contest/weekly-contest-136/problems/partition-array-for-maximum-sum/
public class PartitionArrayMaximumSum {

    // 理解错误
    public static int solution1(int[] A, int K) {
        int maxSum = Integer.MIN_VALUE;
        List<List<Integer>> combinations = CollectionUtil.combine(CollectionUtil.newIntList(1, A.length), K-1);
        for (List<Integer> combination : combinations) {
            int sum = 0;
            int preIndex = 0;
            combination.add(A.length);
            for (int i = 0; i < combination.size(); i++) {
                sum += ArrayUtil.max(A, preIndex, combination.get(i)) * (combination.get(i) - preIndex);
                preIndex = combination.get(i);
            }
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }
    
    // 动态规划，递归，超时。 https://leetcode.com/submissions/detail/228550125/
    public static int solution2(int[] A, int K) {
        if (A.length <= K) {
            return ArrayUtil.max(A) * A.length;
        }
        int maxResult = Integer.MIN_VALUE;
        for (int i = 1; i <= K; i++) {
            if (A.length - i < 0) {
                break;
            }
            int[] subA = Arrays.copyOfRange(A, 0, A.length-i);
            int subMax = ArrayUtil.max(A, A.length-i, A.length);
            maxResult = Math.max(maxResult, solution2(subA, K) + subMax * i);
        }
        return maxResult;
    }
    
    // 动态规划，递推。 https://leetcode.com/submissions/detail/228593245/
    public static int solution3(int[] A, int K) {
        int[] prefixMaxs = new int[A.length];
        prefixMaxs[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            prefixMaxs[i] = Math.max(prefixMaxs[i-1], A[i]);
        }
        int[] dp = new int[A.length];
        for (int i = 0; i < K; i++) {
            dp[i] = prefixMaxs[i]*(i+1);
        }
        for (int i = K; i < A.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 1; j <= K; j++) {
                int a = ArrayUtil.max(A, i+1-j, i+1) * j + dp[i-j];
                max = Math.max(max, a) ;
            }
            dp[i] = max;
        }
        return ArrayUtil.max(dp, dp.length-K, dp.length);
    }
    
    public static void main(String[] args) {
//        int[] A = new int[] {1,15,7,9,2,5,10};
        int[] A = new int[] {1,4,1,5,7,3,6,1,9,9,3};
        int K = 4;
        System.out.println(solution2(A, K));
        System.out.println(solution3(A, K));
    }
    
}
