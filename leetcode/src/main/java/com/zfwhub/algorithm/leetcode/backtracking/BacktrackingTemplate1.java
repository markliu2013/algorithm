package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

// 回溯模板 - 找到所有解
// Combinations, 从数组中选出指定数目的所有组合
public class BacktrackingTemplate1 {
    public static List<List<Integer>> combine(int[] arr, int n) {
        // TODO BacktrackingTemplate1 检查输入参数
        List<List<Integer>> solutionList = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        dfs(solutionList, solution, arr, n);
        return solutionList;
    }
    
    public static void dfs(List<List<Integer>> solutionList, List<Integer> solution, int[] arr, int n) {
        if (isASolution(solution, n)) {
            processSolution(solutionList, solution);
        } else {
            for (int i = 0; i < arr.length; i++) {
                if (isValid(solution, arr[i])) {
                    makeMove(solution, arr[i]);
                    dfs(solutionList, solution, arr, n);
                    unMakeMove(solution);
                }
            }
        }
    }
    
    public static boolean isASolution(List<Integer> solution, int n) {
        return solution.size() == n;
    }

    public static void processSolution(List<List<Integer>> solutionList, List<Integer> solution) {
        solutionList.add(new ArrayList<>(solution));
    }
    
    public static boolean isValid(List<Integer> solution, int n) {
        if (solution.size() == 0) {
            return true;
        }
        return solution.get(solution.size()-1) < n;
    }
    
    public static void makeMove(List<Integer> solution, int n) {
        solution.add(n);
    }
    
    public static void unMakeMove(List<Integer> solution) {
        solution.remove(solution.size()-1);
    }
    
    public static void main(String[] args) {
        int[] arr = new int[] {1,2,3};
        System.out.println(BacktrackingTemplate1.combine(arr, 3));
    }
}
