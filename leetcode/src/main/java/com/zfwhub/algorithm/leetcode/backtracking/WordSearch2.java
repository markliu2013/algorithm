package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

// 用这个模板思想
// https://medium.com/@andreaiacono/backtracking-explained-7450d6ef9e1a
// https://leetcode.com/problems/word-search/discuss/27658/Accepted-very-short-Java-solution.-No-additional-space.
public class WordSearch2 {
    
    public static boolean exist(char[][] board, String word) {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (board[x][y] == word.charAt(0)) {//入口
                    if (backtrack(board, word, x, y, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // x和y是当前board的索引，i是当前word的索引
    public static boolean backtrack(char[][] board, String word, int x, int y, int i) {
        if (isExit(board, word, x, y, i)) {
            return true;
        }
        // 每一个位置，都上下左右尝试。只要有一个位置合法
        if (isValid(board, word, x, y+1, i+1) ||
            isValid(board, word, x, y-1, i+1) ||
            isValid(board, word, x+1, y, i+1) ||
            isValid(board, word, x-1, y, i+1)) {
            makeMove(board, word, x, y, i);
            if (backtrack(board, word, x, y, i)) {
                return true;
            }
            unMakeMove(board, word, x, y, i);
        }
        
        return false;
    }
    
    public static boolean isExit(char[][] board, String word, int x, int y, int i) {
        return i == word.length();
    }
    
    public static boolean isValid(char[][] board, String word, int x, int y, int i) {
        if (x < 0 || y < 0 || x >= board.length || y >= board[x].length) {
            return false;
        }
        // 只需要判断是否相等，因为用了board[x][y] ^= 256记录是否已访问。
        return board[x][y] == word.charAt(i);
    }
    
    public static void makeMove(char[][] board, String word, int x, int y, int i) {
        board[x][y] ^= 256;
    }
    
    public static void unMakeMove(char[][] board, String word, int x, int y, int i) {
        board[x][y] ^= 256;
    }

    public static void main(String[] args) {
        char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
        String word1 = "ABCCED";
        String word2 = "SEE";
        String word3 = "ABCB";
        System.out.println(exist(board, word1));
        System.out.println(exist(board, word2));
        System.out.println(exist(board, word3));
        char[][] board4 = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'E', 'S' }, { 'A', 'D', 'E', 'E' } };
        String word4 = "ABCESEE";
        System.out.println(exist(board4, word4));
        char[][] board5 = { { 'b', 'b' }, { 'a', 'b' }, { 'b', 'a' } };
        String word5 = "a";
        System.out.println(exist(board5, word5));
    }

}
