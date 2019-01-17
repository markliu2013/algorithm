package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

// https://leetcode.com/problems/n-queens-ii/
// 运用 BacktrackingTemplate2
public class NQueensII {
    
    public static int totalNQueens(int n) {
        List<Integer> solution = new ArrayList<>();//每一行都是第几个放皇后
        return dfs(solution, n, 0);
    }
    
    private static int dfs(List<Integer> solution, int n, int count) {
        if (isASolution(solution, n)) {
            count++;
        } else {
            for (int i = 0; i < n; i++) {//每一行都一列一列的尝试
                if (isValid(solution, i)) {
                    makeMove(solution, i);
                    count = dfs(solution, n, count);
                    unMakeMove(solution);
                }
            }
        }
        return count;
    }
    
    private static boolean isASolution(List<Integer> solution, int n) {
        return solution.size() == n;
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
        System.out.println(totalNQueens(4));
    }
    
}
