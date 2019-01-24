package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

// https://leetcode.com/problems/n-queens/
public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> list = new ArrayList<>();
        List<Integer> rows = new ArrayList<>();
        solveNQueens_backtrack(list, rows, n);
        return list;
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
    public static void main(String[] args) {
        System.out.println(new NQueens().solveNQueens(4));
    }
}
