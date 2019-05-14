package com.zfwhub.algorithm.templates.backtracking;
import java.util.*;

/**
 * 回溯模板 - 找到所有解
 * Combinations, 从数组中选出指定数目的所有组合, C(n, k)
 * https://blog.csdn.net/u010500263/article/details/18435495
 */
public class BacktrackingTemplate1 {
    
    public static List<List<Integer>> combineAll(int[] nums, int k) {
        // 存放所有解的集合
        List<List<Integer>> solutionList = new ArrayList<>();
        // 存放单个解的集合，存储nums对应的索引
        List<Integer> solution = new ArrayList<>();
        
       /* if (n == 0) { // 0! = 1
            solutionList.add(solution);
            return solutionList;
        }*/
        
        dfs(solutionList, solution, nums, k);
        return solutionList;
    }
    
    private static void dfs(List<List<Integer>> solutionList, List<Integer> solution, int[] arr, int k) {
        if (isASolution(solution, k)) {
            processSolution(solutionList, solution, arr);
            return;
        }
        // 每个solution有n个位置放不同的数，在每个位置都尝试arr中的每一个数。
        for (int i = 0; i < arr.length; i++) {
            // 1. isValid返回true 且 isASolution返回false，则会陷入死循环。因为unMakeMove执行不到。
            // 2. isValid返回false 或 isASolution返回true肩负着循环退出。
            if (isValid(solution, i)) {
                makeMove(solution, i);
                dfs(solutionList, solution, arr, k);
                // unMakeMove在下面两种情况执行：
                // 1. isASolution 成功找到一个解
                // 2. for循环结束
                // 也就是找到解或者整个for结束都没找到解
                unMakeMove(solution);
            }
        }
    }
    
    private static boolean isASolution(List<Integer> solution, int k) {
        return solution.size() == k;
    }

    private static void processSolution(List<List<Integer>> solutionList, List<Integer> solution, int[] nums) {
        List<Integer> realSolution = new ArrayList<>(); // 转化后的解。
        for (int i : solution) {
            realSolution.add(nums[i]);
        }
        solutionList.add(new ArrayList<>(realSolution));
    }
    
    // isValid 肩负着递归的退出条件，否则会陷入死循环。
    private static boolean isValid(List<Integer> solution, int i) {
        // 组合问题不能重复
        if (solution.contains(i)) {
            return false;
        }
        // 去重
        if (solution.size() > 0 && i < solution.get(solution.size()-1)) {
            return false;
        }
        return true;
    }
    
    private static void makeMove(List<Integer> solution, int i) {
        solution.add(i);
    }
    
    private static void unMakeMove(List<Integer> solution) {
        solution.remove(solution.size()-1);
    }
    
    public static void main(String[] args) {
        int[] arr = new int[] {3,2,1,0};
        System.out.println(BacktrackingTemplate1.combineAll(arr, 2));
    }
}
