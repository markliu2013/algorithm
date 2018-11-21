package com.zfwhub.algorithm.leetcode.dp;

import java.util.*;

/**
 * Count ways to reach the n’th stair
 * Fibonacci Concept
 */
public class ClimbingWays {

    /**
     * Dynamic Programming, top to down, Simple Recursive Solution
     */
    public static int getClimbingWays1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return getClimbingWays1(n - 1) + getClimbingWays1(n - 2);
    }

    /**
     * dynamic programming memoization
     */
    public static int getClimbingWays2(int n, HashMap<Integer, Integer> map) {
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
            int value = getClimbingWays2(n - 1, map) + getClimbingWays2(n - 2, map);
            map.put(n, value);
            return value;
        }
    }

    /**
     * dynamic programming, Iterative Bottom-Up Solution
     */
    public static int getClimbingWays3(int n) {
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
    
    public static List<List<Integer>> getClimbingWays4(int n) {
        List<List<Integer>> solutionList = new ArrayList<>();
        // 存储1和2，代表每次走多少步。
        List<Integer> solution = new ArrayList<>();
        dfs(solutionList, solution, n, 0);
        return solutionList;
    }
    
    // c当前在哪一级
    public static void dfs(List<List<Integer>> solutionList, List<Integer> solution, int n, int c) {
        if (isASolution(solution, n, c)) {
            processSolution(solutionList, solution);
            return;
        }
        // 走一步或两步
        for (int i = 1; i <= 2; i++) {
            if (isValid(solution, i)) {
                makeMove(solution, i);
                dfs(solutionList, solution, n, c+i);
                unMakeMove(solution);
            }
        }
    }
    
    public static boolean isASolution(List<Integer> solution, int n, int c) {
        System.out.println(solution);
        return c == n;
    }

    public static void processSolution(List<List<Integer>> solutionList, List<Integer> solution) {
        solutionList.add(new ArrayList<>(solution));
    }
    
    public static boolean isValid(List<Integer> solution, int n) {
        return solution.stream().mapToInt(Integer::intValue).sum() <= n;
    }
    
    public static void makeMove(List<Integer> solution, int n) {
        solution.add(n);
    }
    
    public static void unMakeMove(List<Integer> solution) {
        solution.remove(solution.size()-1);
    }
    

    public static void main(String[] args) {
//        System.out.println(ClimbingWays.getClimbingWays1(10));
        System.out.println(ClimbingWays.getClimbingWays4(5));
    }

}