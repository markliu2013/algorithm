package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

// 回溯模板 - 找到一个解
// Combinations, 从数组中选出指定数目的一个组合
public class BacktrackingTemplate3 {
    
    public static List<Integer> combine(int[] nums, int n) {
        List<Integer> result = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        dfs(result, solution, nums, n);
        return result;
    }
    
    public static boolean dfs(List<Integer> result, List<Integer> solution, int[] arr, int n) {
        if (isASolution(solution, n)) {
            processSolution(result, solution);
            return true;
        } else {
            for (int i = 0; i < arr.length; i++) {
                if (isValid(solution, arr[i])) {
                    makeMove(solution, arr[i]);
                    if (dfs(result, solution, arr, n)) {
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

    public static void processSolution(List<Integer> result, List<Integer> solution) {
     // TODO BacktrackingTemplate3 为什么这么写是不行的？
     // result = new ArrayList<>(solution);
        result.addAll(solution);
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
        System.out.println(BacktrackingTemplate3.combine(arr, 2));
    }
}
