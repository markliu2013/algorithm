package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

// https://leetcode.com/problems/sudoku-solver/
public class SudokuSolver {
    
    public static void solveSudoku(char[][] board) {
        dfs(board);
    }
    public static void dfs(char[][] board) {
        if (isASolution()) {
            processSolution();
            return;
        }
        
    }
    public static boolean isASolution() {
        return false;
    }
    public static void processSolution() {
        
    }
    public static boolean isValid() {
        return true;
    }
    public static void unmakeMove() {
       
    }

    public static void makeMove() {
        
    }
    public static void main(String[] args) {
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'},
        };
        solveSudoku(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
    
}
