package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

// https://leetcode.com/problems/sudoku-solver/
public class SudokuSolver {
    
    public static final char EMPTY_CHAR = '.'; 
    public static final int RANK = 9;
    
    public static void solveSudoku(char[][] board) {
        List<Character> solution = new ArrayList<>();//每一个格子都是数字几
        dfs(board, solution);
    }
    public static boolean dfs(char[][] board, List<Character> solution) {
        if (isASolution(solution)) {
            processSolution(board, solution);
            return true;
        } else {
            // 每一个格子都尝试1-9
            for (int i = 1; i <= RANK; i++) {
                if (isValid2(board, solution, i)) {
                    makeMove(solution, i);
                    if (dfs(board, solution)) {
                        return true;
                    }
                    unMakeMove(solution);
                }
            }
            return false;
        }
        
    }
    public static boolean isASolution(List<Character> solution) {
        return solution.size() == RANK * RANK;
    }
    public static void processSolution(char[][] board, List<Character> solution) {
        for (int i = 0; i < solution.size(); i++) {
            board[i/9][i%9] = solution.get(i);
        }
    }
    public static boolean isValid(char[][] oriBoard, List<Character> solution, int k) {
        // init the board to check
        char[][] board = new char[9][9];
        for (int i = 0; i < oriBoard.length; i++) {
            for (int j = 0; j < oriBoard[i].length; j++) {
                board[i][j] = oriBoard[i][j];
            }
        }
        for (int i = 0; i < solution.size(); i++) {
            board[i/9][i%9] = solution.get(i);
        }
        if (board[solution.size()/9][solution.size()%9] != EMPTY_CHAR && board[solution.size()/9][solution.size()%9] != Character.forDigit(k, 10)) {
            return false;
        }
        board[solution.size()/9][solution.size()%9] = Character.forDigit(k, 10);
        
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
    
    // 代码还可以优化，不需要每次都全部检查一遍，只需要检查一下当前准备在哪个格子放的数字k
    public static boolean isValid2(char[][] board, List<Character> solution, int k) {
        int nextRow = solution.size()/9;
        int nextCol = solution.size()%9;
        // 如果下一个格子不为空，则k必须与格子的数字一样
        if (board[nextRow][nextCol] != EMPTY_CHAR && board[nextRow][nextCol] != Character.forDigit(k, 10)) {
            return false;
        }
        // 检查行
        if (solution.size() % RANK == 0) {
            return true;
        }
        for (int i = closestNumber(solution.size()-1, RANK); i < solution.size(); i++) {
            if (solution.get(i) == Character.forDigit(k, 10)) {
                return false;
            }
        }
        // 检查列
        for (int i = 0; i < (solution.size()-1)/9; i++) {
            if (solution.size() == 20) {
                if (solution.get(i*9+(solution.size()-1)%9+1) == Character.forDigit(k, 10)) {
                    return false;
                }
            } else {
                if (solution.get(i*9+(solution.size()-1)%9) == Character.forDigit(k, 10)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static void unMakeMove(List<Character> solution) {
        solution.remove(solution.size()-1);
    }

    public static void makeMove(List<Character> solution, int i) {
        solution.add(Character.forDigit(i, 10));
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
    }
    
}
