package com.zfwhub.algorithm.templates.dp;

import java.util.*;

/**
 * https://leetcode.com/problems/climbing-stairs/
 * Fibonacci Concept
 */
public class ClimbingStairs {

    /**
     * Dynamic Programming, top to down, Simple Recursive Solution
     */
    public static int climbStairs(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    /**
     * dynamic programming memoization
     */
    public static int climbStairs2(int n, HashMap<Integer, Integer> map) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        } else {
            int value = climbStairs2(n - 1, map) + climbStairs2(n - 2, map);
            map.put(n, value);
            return value;
        }
    }

    /**
     * dynamic programming, Iterative Bottom-Up Solution
     */
    public static int climbStairs3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int a = 1;
        int b = 2;
        int temp = 0;
        for (int i = 3; i <= n; i++) {
            temp = a + b;
            a = b;
            b = temp;
        }
        return temp;
    }
    
    public static List<List<Integer>> climbStairs4(int n) {
        List<List<Integer>> solutionList = new ArrayList<>();
        // 存储1和2，代表每次走多少步。
        List<Integer> solution = new ArrayList<>();
        dfs(solutionList, solution, n);
        return solutionList;
    }
    
    public static void dfs(List<List<Integer>> solutionList, List<Integer> solution, int n) {
        if (isASolution(solution, n)) {
            processSolution(solutionList, solution);
            return;
        }
        // 走一步或两步
        for (int i = 1; i <= 2; i++) {
            if (isValid(solution, i, n)) {
                makeMove(solution, i);
                dfs(solutionList, solution, n);
                unMakeMove(solution);
            }
        }
    }
    
    // 所有的步数加起来等于楼梯级数。
    public static boolean isASolution(List<Integer> solution, int n) {
        return solution.stream().mapToInt(Integer::intValue).sum() == n;
    }

    public static void processSolution(List<List<Integer>> solutionList, List<Integer> solution) {
        solutionList.add(new ArrayList<>(solution));
    }
    
    // 下一步不能超过楼梯级数。
    public static boolean isValid(List<Integer> solution, int i, int n) {
        return solution.stream().mapToInt(Integer::intValue).sum() + i <= n;
    }
    
    public static void makeMove(List<Integer> solution, int n) {
        solution.add(n);
    }
    
    public static void unMakeMove(List<Integer> solution) {
        solution.remove(solution.size()-1);
    }
    
    public static void main(String[] args) {
        System.out.println(ClimbingStairs.climbStairs(5));
        System.out.println(ClimbingStairs.climbStairs4(5).size());
        System.out.println(ClimbingStairs.climbStairs4(5));
    }

}