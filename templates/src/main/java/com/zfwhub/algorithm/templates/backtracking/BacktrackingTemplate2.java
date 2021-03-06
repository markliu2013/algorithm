package com.zfwhub.algorithm.templates.backtracking;
import java.util.*;

/**
 * 回溯模板 - 找到所有解的数量
 * Combinations, 从数组中选出指定数目的组合的个数
 */
public class BacktrackingTemplate2 {
    
    public static int countCombinations(int[] arr, int n) {
        List<Integer> solution = new ArrayList<>();
        return dfs(solution, arr, n, 0);
    }
    
    private static int dfs(List<Integer> solution, int[] arr, int n, int count) {
        if (isASolution(solution, n)) {
            count++;
        } else {
            for (int i = 0; i < arr.length; i++) {
                if (isValid(solution, arr[i])) {
                    makeMove(solution, arr[i]);
                    count = dfs(solution, arr, n, count);
                    unMakeMove(solution);
                }
            }
        }
        return count;
    }
    
    private static boolean isASolution(List<Integer> solution, int n) {
        return solution.size() == n;
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
        System.out.println(BacktrackingTemplate2.countCombinations(arr, 1));
    }
    
}
