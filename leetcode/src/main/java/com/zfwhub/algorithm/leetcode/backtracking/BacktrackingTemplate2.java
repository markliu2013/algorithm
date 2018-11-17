package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

// 回溯模板 - 找到所有解的数量
// https://leetcode.com/problems/n-queens-ii/
public class BacktrackingTemplate2 {
    
    static int count = 0;
    
    public static int combine(int[] arr, int n) {
        List<Integer> solution = new ArrayList<>();
        dfs(solution, arr, n);
        return count;
    }
    public static void dfs(List<Integer> solution, int[] arr, int n) {
        if (isASolution(solution, n)) {
            count++;
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (isValid(solution, arr[i])) {
                makeMove(solution, arr[i]);
                dfs(solution, arr, n);
                unMakeMove(solution);
            }
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
        System.out.println(BacktrackingTemplate2.combine(arr, 1));
    }
    
}
