package com.zfwhub.algorithm.templates.backtracking;
import java.util.*;

/**
 * 回溯模板 - 找到所有解
 * Combinations, 从数组中选出指定数目的所有组合, C(n, k)
 * https://blog.csdn.net/u010500263/article/details/18435495
 */
public class BacktrackingTemplate1 {
    
    public static List<List<Integer>> combineAll(int[] nums, int n) {
        // 存放所有解的集合
        List<List<Integer>> solutionList = new ArrayList<>();
        // 存放单个解的集合
        List<Integer> solution = new ArrayList<>();
        
        if (n == 0) {// 0! = 1
            solutionList.add(solution);
            return solutionList;
        }
        
        dfs(solutionList, solution, nums, n);
        return solutionList;
    }
    
    private static void dfs(List<List<Integer>> solutionList, List<Integer> solution, int[] arr, int n) {
        if (isASolution(solution, n)) {
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
    
    private static boolean isASolution(List<Integer> solution, int n) {
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
        int[] arr = new int[] {1,2,3};
        System.out.println(BacktrackingTemplate1.combineAll(arr, 2));
    }
}
