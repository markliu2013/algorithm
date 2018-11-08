package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

/**
 * https://leetcode.com/problems/word-search/ 
 */
public class WordSearch {
    
    public static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {//入口
                    List<List<Integer>> visited = new ArrayList<>();
                    List<Integer> list1 = new ArrayList<>();
                    list1.add(i);
                    list1.add(j);
                    visited.add(list1);
                    if (dfs(board, word, i, j, 1, visited)) {
                        return true;
                    } else {
                        continue;
                    }
                }
            }
        }
        return false;
    }
    
    // i,j 二维数组搜索入口，k是word的索引。
    public static boolean dfs(char[][] board, String word, int i, int j, int k, List<List<Integer>> visited) {
        if (k == word.length()) {
            return true;
        } else {
            if (i > 0) {
                List<Integer> list1 = new ArrayList<>();
                list1.add(i-1);
                list1.add(j);
                if (visited.contains(list1)) {
                    
                } else {
                    if (board[i-1][j] == word.charAt(k)) {
                        visited.add(list1);
                        return dfs(board, word, i-1, j, k+1, visited);
                    }
                }
            }
            if (i < board.length - 1) {
                List<Integer> list1 = new ArrayList<>();
                list1.add(i+1);
                list1.add(j);
                if (visited.contains(list1)) {
                    
                } else {
                    if (board[i+1][j] == word.charAt(k)) {
                        visited.add(list1);
                        return dfs(board, word, i+1, j, k+1, visited);
                    }
                }
            }
            if (j > 0) {
                List<Integer> list1 = new ArrayList<>();
                list1.add(i);
                list1.add(j-1);
                if (visited.contains(list1)) {
                    
                } else {
                    if (board[i][j-1] == word.charAt(k)) {
                        visited.add(list1);
                        return dfs(board, word, i, j-1, k+1, visited);
                    }
                }
            }
            if (j < board[i].length - 1) {
                List<Integer> list1 = new ArrayList<>();
                list1.add(i);
                list1.add(j+1);
                if (visited.contains(list1)) {
                    
                } else {
                    if (board[i][j+1] == word.charAt(k)) {
                        visited.add(list1);
                        return dfs(board, word, i, j+1, k+1, visited);
                    }
                }
            }
        }
        return false;
    }
    
    // 上下左右检查
    public static void dfs_go(char[][] board, String word, int i, int j, int k) {
        
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
//        System.out.println(exist(board, word1));
        System.out.println(exist(board, word2));
//        System.out.println(exist(board, word3));
        
    }

}
