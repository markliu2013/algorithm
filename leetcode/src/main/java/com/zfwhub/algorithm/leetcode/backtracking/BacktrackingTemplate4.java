package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

// 回溯模板 - 判断是否有解
// Combinations, 数组中选出指定数目的组合是否存在
public class BacktrackingTemplate4 {
    
    public static boolean existsCombinations(int[] nums, int n) {
        List<Integer> solution = new ArrayList<>();
        return dfs(solution, nums, n);
    }
    
    public static boolean dfs(List<Integer> solution, int[] arr, int n) {
        if (isASolution(solution, n)) {
            return true;
        } else {
            for (int i = 0; i < arr.length; i++) {
                if (isValid(solution, arr[i])) {
                    makeMove(solution, arr[i]);
                    if (dfs(solution, arr, n)) {
                        return true;
                    }
                    unMakeMove(solution);
                }
            }
            return false;
        }
    }
    
    public static boolean isASolution(List<Integer> solution, int n) {
        return solution.size() == n;
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
        System.out.println(BacktrackingTemplate4.existsCombinations(arr, 6));
    }

}
