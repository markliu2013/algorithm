package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

import javax.swing.border.Border;

// https://leetcode.com/problems/sudoku-solver/
public class SudokuSolver {
    
    public static final char EMPTY_CHAR = '.'; 
    public static final int RANK = 9;
    
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
    public static boolean isValid(char[][] board) {
        // Each of the digits 1-9 must occur exactly once in each row.
        for (int i = 0; i < RANK; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < RANK; j++) {
                if (board[i][j] != EMPTY_CHAR && set.contains(board[i][j])) {
                    return false;
                } else {
                    set.add(board[i][j]);
                }
            }
        }
        // Each of the digits 1-9 must occur exactly once in each column.
        for (int i = 0; i < RANK; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < RANK; j++) {
                if (board[j][i] != EMPTY_CHAR && set.contains(board[j][i])) {
                    return false;
                } else {
                    set.add(board[j][i]);
                }
            }
        }
        // Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
        for (int i = 0; i < RANK; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < RANK; j++) {
                int row = closestNumber(i,3)+j/3;
                int col = j%3+(i%3)*3;
                if (board[row][col] != EMPTY_CHAR && set.contains(board[row][col])) {
                    return false;
                } else {
                    set.add(board[row][col]);
                }
            }
        }
        return true;
    }
    public static void unmakeMove() {
       
    }

    public static void makeMove() {
        
    }
    
    // find the number closest to n and divisible by m
    // https://www.geeksforgeeks.org/find-number-closest-n-divisible-m/
    public static int closestNumber(int n, int m) {
        int q = n / m; 
        int n1 = m * q; 
        return n1; 
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
        for (int i = 0; i < RANK; i++) {
            for (int j = 0; j < RANK; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
       // System.out.println(closestNumber(7,3));
    }
    
}
