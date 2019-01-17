package com.zfwhub.algorithm.templates.backtracking;


import java.util.*;

/**
 * 回溯模板 - 找到一个解
 * Combinations, 从数组中选出指定数目的一个组合
 */
public class BacktrackingTemplate3 {
    
    public static List<Integer> combineOne(int[] nums, int n) {
        List<Integer> result = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        dfs(result, solution, nums, n);
        return result;
    }
    
    private static boolean dfs(List<Integer> result, List<Integer> solution, int[] arr, int n) {
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
    
    private static boolean isASolution(List<Integer> solution, int n) {
        return solution.size() == n;
    }

    private static void processSolution(List<Integer> result, List<Integer> solution) {
     // TODO BacktrackingTemplate3 为什么这么写是不行的？
     // result = new ArrayList<>(solution);
        result.addAll(solution);
    }
    
    private static boolean isValid(List<Integer> solution, int n) {
        if (solution.size() == 0) {
            return true;
        }
        return solution.get(solution.size()-1) < n;
    }
    
    private static void makeMove(List<Integer> solution, int n) {
        solution.add(n);
    }
    
    private static void unMakeMove(List<Integer> solution) {
        solution.remove(solution.size()-1);
    }
    
    public static void main(String[] args) {
        int[] arr = new int[] {1,2,3};
        System.out.println(BacktrackingTemplate3.combineOne(arr, 2));
    }
}
