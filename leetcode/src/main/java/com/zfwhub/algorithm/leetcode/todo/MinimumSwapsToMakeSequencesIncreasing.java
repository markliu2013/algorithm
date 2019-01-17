package com.zfwhub.algorithm.leetcode.todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/description/
 * https://blog.csdn.net/zjucor/article/details/79599287
 */
public class MinimumSwapsToMakeSequencesIncreasing {

    // 交换不可能超过数组的一半
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
        dfs(solutionList, solution, int[] A, int[] B);
        return 0;
    }

    private static void dfs(List<List<Boolean>> solutionList, List<Boolean> solution, int[] A, int[] B) {
        if (isASolution(solution, A, B)) {
            processSolution(solutionList, solution);
            return;// TODO 需不需要return，得看情况。SubSets为什么不需要？
        }
        // 每个solution有n个位置放不同的数，在每个位置都尝试arr中的每一个数。
        for (int i = 0; i < arr.length; i++) {
            // TODO isValid 肩负着递归的退出条件，ClimbingWays
            if (isValid(solution, arr[i])) {
                makeMove(solution, arr[i]);
                dfs(solutionList, solution, arr, n);
                // unMakeMove在下面两种情况执行：
                // 1. isASolution 成功找到一个解
                // 2. for循环结束
                // 也就是找到解或者整个for结束都没找到解
                unMakeMove(solution);
            }
        }
    }
    
    private static boolean isASolution(List<Integer> solution, int[] A, int[] B) {
        return solution.size() == n;
    }

    private static void processSolution(List<List<Integer>> solutionList, List<Integer> solution) {
        solutionList.add(new ArrayList<>(solution));
    }
    
    private static boolean isValid(List<Integer> solution, int n) {
        if (solution.size() == 0) {
            return true;
        }
//      if (solution.contains(n)) return false;
        return solution.get(solution.size()-1) < n;
    }
    
    private static void makeMove(List<Integer> solution, int n) {
        solution.add(n);
    }
    
    private static void unMakeMove(List<Integer> solution) {
        solution.remove(solution.size()-1);
    }
    
    public static void main(String[] args) {
        int[] A5 = new int[] {0,7,8,10,10,11,12,13,19,18};
        int[] B5 = new int[] {4,4,5,7,11,14,15,16,17,20};
        
        System.out.println(MinimumSwapsToMakeSequencesIncreasing.minSwap(A5, B5));
        //System.out.println(Arrays.toString(newA));
        //System.out.println(Arrays.toString(newB));
    }

}
