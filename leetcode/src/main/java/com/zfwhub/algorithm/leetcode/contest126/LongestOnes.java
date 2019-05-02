package com.zfwhub.algorithm.leetcode.contest126;

import java.util.*;

import com.zfwhub.algorithm.utils.CollectionUtil;
// https://leetcode.com/problems/max-consecutive-ones-iii/
public class LongestOnes {
    
    // 强攻, Time Limit Exceeded
    public static int solution1(int[] A, int K) {
        int maxCount = 0;
        List<Integer> zeroIndexes = getZeroIndexes(A);
        if (K >= zeroIndexes.size()) {
            return A.length;
        }
        List<List<Integer>> combinations = CollectionUtil.combine(zeroIndexes, K);
        for (int i = 0; i < combinations.size(); i++) {
            int[] newA = flip(A, combinations.get(i));
            maxCount = Math.max(maxCount, count(newA));
        }
        return maxCount;
    }
    
    private static List<Integer> getZeroIndexes(int[] A) {
        List<Integer> zeroIndexes = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                zeroIndexes.add(i);
            }
        }
        return zeroIndexes;
    }
    
    private static int[] flip(int[] A, List<Integer> list) {
        int[] newA = A.clone();
        for (int i = 0; i < list.size(); i++) {
            newA[list.get(i)] = 1;
        }
        return newA;
    }
    
    // https://leetcode.com/problems/max-consecutive-ones/
    private static int count(int[] A) {
        int maxCount = 0;
        int currentCount = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 1) {
                currentCount++;
            } else {
                maxCount = Math.max(maxCount, currentCount);
                currentCount = 0;
            }
        }
        return Math.max(maxCount, currentCount);
    }
    
    // 当只能翻转一次的时候。
    public static int findMaxConsecutiveOnes(int[] A) {
        
        return 0;
    }
    
    // 动态规划，递归
    public static int solution2(int[] A, int K) {
        if (A.length == 1) {
            if (A[0] == 1) {
                return 1;
            } else {
                if (K > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        int lastItem = A[A.length-1];
        int[] subA = Arrays.copyOfRange(A, 0, A.length-1);
        if (lastItem == 0) {
            if (K <= 0) {
                return solution2(subA, K);
            }
            return Math.max(solution2(subA, K-1)+1, solution2(subA, K));
        } else {
            int result = solution2(subA, K);
            for (int i = subA.length-1; i >= 0; i--) {
                if (subA[i] == 0) {
                    int countZero = 1;
                    subA[i] = 1;
                    for (int j = i-1; j >=0; j--) {
                        if (subA[j] == 0) {
                            countZero++;
                            subA[j] = 1;
                        } else {
                            break;
                        }
                    }
                    if (K >= countZero) {
                        result = Math.max(result, solution2(subA, K-countZero)+1);
                    }
                    break;
                }
            }
            return result;
        }
    }
    
    public static void main(String[] args) {
        int[] A = new int[] {1,1,1,0,0,0,1,1,1,1,0};
        System.out.println(solution2(A, 2));
    }
    
}
