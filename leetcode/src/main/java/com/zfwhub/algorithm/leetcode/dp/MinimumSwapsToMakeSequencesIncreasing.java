package com.zfwhub.algorithm.leetcode.dp;
import java.util.*;
import com.zfwhub.algorithm.utils.ArrayUtil;

// https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/
public class MinimumSwapsToMakeSequencesIncreasing {
    
    // 回溯法暴力破解
    public static int minSwap(int[] A, int[] B) {
        List<List<Boolean>> solutionList = new ArrayList<>();
        List<Boolean> solution = new ArrayList<>();
        dfs(solutionList, solution, A, B);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < solutionList.size(); i++) {
            min = Math.min(min, countSolution(solutionList.get(i)));
        }
        return min;
    }
    
    // 动态规划
    public static int minSwap2(int[] A, int[] B) {
        DPResult dpResult = new DPResult();
        minSwapDP(A, B, dpResult);
        return dpResult.minSwap;
    }
    public static void minSwapDP(int[] A, int[] B, DPResult dpResult) {
        if (A.length == 1) {
            dpResult.lastSwapped = false;
            dpResult.minSwap = 0;
            return;
        }
        int a1 = A[A.length-1]; // A的最后一个
        int a2 = A[A.length-2]; // A的倒数第二
        int b1 = B[B.length-1]; // B的最后一个
        int b2 = B[B.length-2]; // B的倒数第二
        int[] subA = Arrays.copyOfRange(A, 0, A.length-1);
        int[] subB = Arrays.copyOfRange(B, 0, B.length-1);
        minSwapDP(subA, subB, dpResult);
        int a = dpResult.minSwap;
        if (dpResult.lastSwapped) {
            if (a1 <= b2 || b1 <= a2) {
                if (a+1 > A.length/2) {
                    dpResult.lastSwapped = false;
                    dpResult.minSwap = A.length-(a+1);
                } else {
                    dpResult.minSwap = a+1;
                }
            } else {
                dpResult.lastSwapped = false;
            }
        } else {
            if (a1 <= a2 || b1 <= b2) {
                if (a+1 > A.length/2) {
                    dpResult.minSwap = A.length/2-(a+1);
                } else {
                    dpResult.lastSwapped = true;
                    dpResult.minSwap = a+1;
                }
            } else {
                dpResult.minSwap = a;
            }
        }
    }
    
    private static class DPResult {
        public boolean lastSwapped = false;
        public int minSwap = Integer.MAX_VALUE;
    }

    private static void dfs(List<List<Boolean>> solutionList, List<Boolean> solution, int[] A, int[] B) {
        if (isASolution(solution, A, B)) {
            processSolution(solutionList, solution);
        } else {
            // 对应每个位置是否交换
            for (int i = 0; i < 2; i++) {
                if (isValid(solution, i, A, B)) {
                    makeMove(solution, i);
                    dfs(solutionList, solution, A, B);
                    unMakeMove(solution);
                }
            }
        }
    }
    
    private static boolean isASolution(List<Boolean> solution, int[] A, int[] B) {
       if (solution.size() == A.length) {
            int[] newA = A.clone();
            int[] newB = B.clone();
            swap(newA, newB, solution);
            if (ArrayUtil.isIncreasing(newA) && ArrayUtil.isIncreasing(newB)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static void processSolution(List<List<Boolean>> solutionList, List<Boolean> solution) {
        solutionList.add(new ArrayList<>(solution));
    }
    
    // 0 false, 1 true 交换不可能超过数组的一半
    private static boolean isValid(List<Boolean> solution, int i, int[] A, int[] B) {
        // 为什么要加这个if，不然退出不了递归，请对比RestoreIpAddresses分析
        if (solution.size() > A.length) {
            return false;
        }
        if (i == 1 && countSolution(solution) + 1 > A.length / 2) {
            return false;
        } else {
            return true;
        }
    }
    
    private static void makeMove(List<Boolean> solution, int n) {
        solution.add(n == 0 ? false : true);
    }
    
    private static void unMakeMove(List<Boolean> solution) {
        solution.remove(solution.size()-1);
    }
    
    private static int countSolution(List<Boolean> solution) {
        int count = 0;
        for (int i = 0; i < solution.size(); i++) {
            if (solution.get(i)) {
                count++;
            }
        }
        return count;
    }
    
    private static void swap(int[] A, int[] B, List<Boolean> solution) {
        for (int i = 0; i < solution.size(); i++) {
            if (solution.get(i)) {
                int temp = A[i];
                A[i] = B[i];
                B[i] = temp;
            }
        }
    }
    
    public static void main(String[] args) {
        int[] A = new int[] {0,7,8,10,10,11,12,13,19,18};
        int[] B = new int[] {4,4,5, 7,11,14,15,16,17,20};
        System.out.println(MinimumSwapsToMakeSequencesIncreasing.minSwap(A, B));
        System.out.println(MinimumSwapsToMakeSequencesIncreasing.minSwap2(A, B));
        //System.out.println(Arrays.toString(newA));
        //System.out.println(Arrays.toString(newB));
    }

}