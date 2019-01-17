package com.zfwhub.algorithm.leetcode.todo;

import java.util.*;

import com.zfwhub.algorithm.utils.ArrayUtil;

/**
 * https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/
 * https://blog.csdn.net/zjucor/article/details/79599287
 */
public class MinimumSwapsToMakeSequencesIncreasing {

    public static int minSwap(int[] A, int[] B) {
        if (A.length == 1) {
            return 0;
        }
        if (A.length == 2) {
            if (A[0] < A[1] && B[0] < B[1]) {
                return 0;
            } else {
                return 1;
            }
        }
        int a1 = A[A.length-1]; // A的最后一个
        int a2 = A[A.length-2]; // A的倒数第二
        int b1 = B[B.length-1]; // B的最后一个
        int b2 = B[B.length-2]; // B的倒数第二
        int[] subA = Arrays.copyOfRange(A, 0, A.length-1);
        int[] subB = Arrays.copyOfRange(B, 0, B.length-1);
        int a = minSwap(subA, subB);
        
        
       /* int[] subA = Arrays.copyOfRange(A, 0, A.length-1);
        int[] subB = Arrays.copyOfRange(B, 0, B.length-1);
        // 最后一个不交换
        int a = minSwap(subA, subB);
        int[] newA = new int[A.length + 1];
        System.arraycopy(subA, 0, newA, 0, subA.length);
        newA[newA.length-1] = lastB;
        int[] newB = new int[B.length + 1];
        System.arraycopy(newB, 0, newB, 0, subB.length);
        newA[newB.length-1] = lastA;
        int b = minSwap(newA, newB);
        return Math.min(a, b);*/
        return 0;
    }
    
    // 回溯法暴力破解
    public static int minSwap2(int[] A, int[] B) {
        List<List<Boolean>> solutionList = new ArrayList<>();
        List<Boolean> solution = new ArrayList<>();
        dfs(solutionList, solution, A, B);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < solutionList.size(); i++) {
            min = Math.min(min, countSolution(solutionList.get(i)));
        }
        return min;
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
        int[] A5 = new int[] {1,3,5,4};
        int[] B5 = new int[] {1,2,3,7};
        
        System.out.println(MinimumSwapsToMakeSequencesIncreasing.minSwap2(A5, B5));
        //System.out.println(Arrays.toString(newA));
        //System.out.println(Arrays.toString(newB));
    }

}