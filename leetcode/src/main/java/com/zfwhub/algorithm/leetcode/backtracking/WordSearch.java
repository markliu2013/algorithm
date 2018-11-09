package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

/**
 * https://leetcode.com/problems/word-search/ 
 */
public class WordSearch {
    
    public static boolean exist(char[][] board, String word) {
        List<List<List<Integer>>> result = new ArrayList<>();
        List<List<Integer>> solution = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {//入口
                    dfs(result, solution, board, word, i, j, 0);
                }
            }
        }
        System.out.println(result);
        return result.size() > 0;
    }
    
    // i,j 二维数组搜索入口，k是word的索引。
    public static void dfs(List<List<List<Integer>>> result, List<List<Integer>> solution, char[][] board, String word, int i, int j, int k) {
        if (isASolution(solution, word)) {
            processSolution(result, solution);
            return;
        }
        while (k < word.length()-1) {
            k = k + 1;
            //上
            if (i > 0) {
                i = i - 1;
            }
            //下
            if (i < board.length - 1) {
                i = i + 1;
            }
            //左
            if (j > 0) {
                j = j - 1;
            }
            //右
            if (j < board[i].length - 1) {
                j = j + 1;
            }
            if (isValid(board, word, i, j, k, solution)) {
                makeMove(solution, i, j);
                dfs(result, solution, board, word, i, j, k);
                unmakeMove(solution);
            }
        }
    }
    
    public static boolean isASolution(List<List<Integer>> solution, String word) {
        return solution.size() == word.length();
    }
    
    public static void processSolution(List<List<List<Integer>>> result, List<List<Integer>> solution) {
        result.add(new ArrayList<>(solution));
    }
    // 检查是否相等，且不能重复走
    public static boolean isValid(char[][] board, String word, int i, int j, int k, List<List<Integer>> solution) {
        List<Integer> step = new ArrayList<>();
        step.add(i);
        step.add(j);
        if (solution.contains(step)) {
            return false;
        }
        return board[i][j] == word.charAt(k);
    }
    public static void makeMove(List<List<Integer>> solution, int i, int j) {
        List<Integer> step = new ArrayList<>();
        step.add(i);
        step.add(j);
        solution.add(step);
    }
    public static void unmakeMove(List<List<Integer>> solution) {
        solution.remove(solution.size()-1);
    }
    public static void main(String[] args) {
        char[][] board = {
                    {'A','B','C','E'},
                    {'S','F','C','S'},
                    {'A','D','E','E'}
                };
        String word1 = "ABCCED";
        String word2 = "SEE";
        String word3 = "ABCB";
        System.out.println(exist(board, word1));
//        System.out.println(exist(board, word2));
//        System.out.println(exist(board, word3));
        
    }

}
