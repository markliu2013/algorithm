package com.zfwhub.algorithm.leetcode.backtracking;

/**
 * https://leetcode.com/problems/word-search/ 
 */
public class WordSearch {
    
    public static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {//入口
                    if (dfs(board, word, i, j, 1)) {
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
    public static boolean dfs(char[][] board, String word, int i, int j, int k) {
        if (k == word.length()) {
            return true;
        }
        // 四个方向
        for (int l = 0; l < 3; l++) {
            if (dfs(board, word, i, j, k)) {
                
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        char[][] board = {
                    {'A','B','C','E'},
                    {'S','F','C','S'},
                    {'A','D','E','E'}
                };
        String word1 = "";
        String word2 = "";
        String word3 = "";
        System.out.println(exist(board, word1));
//        System.out.println(exist(board, word2));
//        System.out.println(exist(board, word3));
        
    }

}
