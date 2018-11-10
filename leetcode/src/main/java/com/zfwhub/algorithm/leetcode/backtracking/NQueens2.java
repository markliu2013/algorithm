package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

// NQueens 按模板改造
public class NQueens2 {
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> solutionList = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();//每一行都是第几个放皇后
        dfs(solutionList, solution, n);
        return solutionList;
    }
    public static void dfs(List<List<String>> solutionList, List<Integer> solution, int n) {
        if (isASolution(solution, n)) {
            processSolution(solutionList, solution);
        } else {
            for (int i = 0; i < n; i++) {//每一行都一列一列的尝试
                if (isValid(solution, i)) {
                    makeMove(solution, i);
                    dfs(solutionList, solution, n);
                    unMakeMove(solution);
                }
            }
        }
        
    }
    public static boolean isASolution(List<Integer> solution, int n) {
        return solution.size() == n;
    }
    public static void processSolution(List<List<String>> solutionList, List<Integer> solution) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < solution.size(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < solution.size(); j++) {
                sb.append('.');
            }
            sb.setCharAt(solution.get(i), 'Q');
            list.add(sb.toString());
        }
        solutionList.add(list);
    }
    // i是列数，在第rows.size行，尝试第i列
    public static boolean isValid(List<Integer> solution, int i) {
        if (solution.contains(i)) {//同一列
            return false;
        } else {//不在同一列，检查对角线
            for (int j = 0; j < solution.size(); j++) {
                if (i == solution.get(j)+(solution.size()-j)) {
                    return false;
                }
                if (i == solution.get(j)-(solution.size()-j)) {
                    return false;
                }
            }
            return true;
        }
    }
    public static void unMakeMove(List<Integer> solution) {
        solution.remove(solution.size()-1);
    }

    public static void makeMove(List<Integer> solution, int i) {
        solution.add(i);
    }
    
    public static void main(String[] args) {
        System.out.println(solveNQueens(8).size());
    }
}
