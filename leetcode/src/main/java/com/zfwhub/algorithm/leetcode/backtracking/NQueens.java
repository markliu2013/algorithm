package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

// https://leetcode.com/problems/n-queens/
public class NQueens {
    
    public List<List<String>> solution1(int n) {
        List<List<String>> list = new ArrayList<>();
        List<Integer> rows = new ArrayList<>();
        solveNQueens_backtrack(list, rows, n);
        return list;
    }

    // NQueens 按模板改造
    public static List<List<String>> solution2(int n) {
        List<List<String>> solutionList = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();//每一行都是第几个放皇后
        dfs(solutionList, solution, n);
        return solutionList;
    }
    
    private void solveNQueens_backtrack(List<List<String>> list, List<Integer> rows, int n) {
        if (rows.size() == n) {
            List<String> list1 = new ArrayList<>();
            for (int i = 0; i < rows.size(); i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < rows.get(i); j++) {
                    sb.append('.');
                }
                sb.append('Q');
                for (int j = rows.get(i)+1; j < n; j++) {
                    sb.append('.');
                }
                list1.add(sb.toString());
            }
            list.add(list1);
        } else {
            for (int i = 0; i < n; i++) {//每一行都一列一列的尝试
                if (!solveNQueens_backtrack_check(rows, i)) {
                    continue;
                }
                rows.add(i);//make_move
                solveNQueens_backtrack(list, rows, n);
                rows.remove(rows.size()-1);//unmake_move
            }
        }
    }
    
    //i是列数，在第rows.size行，尝试第i列
    private boolean solveNQueens_backtrack_check(List<Integer> rows, int i) {
        if (rows.contains(i)) {//同一列
            return false;
        } else {//不在同一列，检查对角线
            for (int j = 0; j < rows.size(); j++) {
                if (i == rows.get(j)+(rows.size()-j)) {
                    return false;
                }
                if (i == rows.get(j)-(rows.size()-j)) {
                    return false;
                }
            }
            return true;
        }
    }
    
    private static void dfs(List<List<String>> solutionList, List<Integer> solution, int n) {
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
    
    private static boolean isASolution(List<Integer> solution, int n) {
        return solution.size() == n;
    }
    
    private static void processSolution(List<List<String>> solutionList, List<Integer> solution) {
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
    private static boolean isValid(List<Integer> solution, int i) {
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
    
    private static void unMakeMove(List<Integer> solution) {
        solution.remove(solution.size()-1);
    }

    private static void makeMove(List<Integer> solution, int i) {
        solution.add(i);
    }
    
    public static void main(String[] args) {
        System.out.println(new NQueens().solution1(4));
    }
}
