package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

import com.zfwhub.algorithm.utils.NumberUtil;

// https://leetcode.com/problems/sudoku-solver/
public class SudokuSolver {
    
    public static final char EMPTY_CHAR = '.'; 
    public static final int RANK = 9;
    
    public static void solution1(char[][] board) {
        List<Character> solution = new ArrayList<>();//每一个格子都是数字几
        dfs(board, solution);
    }
    
    public static void solution2(char[][] board) {
        List<Character> solution = new ArrayList<>();//每一个格子都是数字几
        for (int i = 0; i < RANK; i++) {
            for (int j = 0; j < RANK; j++) {
                solution.add(board[i][j]);
            }
        }
        int index = 0;//尝试到哪一个了。
        dfs2(board, solution, index);
    }
    
    private static boolean dfs(char[][] board, List<Character> solution) {
        if (isASolution(solution)) {
            processSolution(board, solution);
            return true;
        } else {
            // 每一个格子都尝试1-9
            for (int i = 1; i <= RANK; i++) {
                if (isValid(board, solution, i)) {
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
    
    private static boolean isASolution(List<Character> solution) {
        return solution.size() == RANK * RANK;
    }
    
    private static void processSolution(char[][] board, List<Character> solution) {
        for (int i = 0; i < solution.size(); i++) {
            board[i/9][i%9] = solution.get(i);
        }
    }
    
    private static boolean isValid(char[][] oriBoard, List<Character> solution, int k) {
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
        if (board[solution.size()/9][solution.size()%9] != EMPTY_CHAR) {
            if (board[solution.size()/9][solution.size()%9] == Character.forDigit(k, 10)) {
                return true;
            } else {
                return false;
            }
            
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
        for (int i = 0; i < RANK; i++) { //循环9个宫
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < RANK; j++) {//具体的宫，需要推断出宫的索引
                int row = NumberUtil.closestMultiple(i,3)+j/3;
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
    @SuppressWarnings("unused")
    private static boolean isValid2(char[][] board, List<Character> solution, int k) {
        int nextRow = solution.size()/9;
        int nextCol = solution.size()%9;
        // 如果下一个格子不为空，则k必须与格子的数字一样
        if (board[nextRow][nextCol] != EMPTY_CHAR) {
            if (board[nextRow][nextCol] == Character.forDigit(k, 10)) {
                return true;
            } else {
                return false;
            }
        }
        
        // 有坑，不能只检查solution前面的数，必须向后检查board里面非空的数。否则会陷入死循环。
        // 第二种思路，可以把solution按照board初始化，在isASolution中判断solution中不存在空点
        
        // 检查行
        int row = solution.size() / RANK;
        if (solution.size() % RANK == 0) {//一行的第一个
            for (int i = 1; i < RANK; i++) {
                if (board[row][i] == Character.forDigit(k, 10)) {
                    return false;
                }
            }
            return true;
        }
        for (int i = NumberUtil.closestMultiple(solution.size()-1, RANK); i < solution.size(); i++) {
            if (solution.get(i) == Character.forDigit(k, 10)) {
                return false;
            }
        }
        for (int i = (solution.size())%9+1; i < RANK; i++) {
            if (board[row][i] == Character.forDigit(k, 10)) {
                return false;
            }
        }
        // 检查列
        int col = solution.size() % RANK;
        for (int i = 0; i < (solution.size()-1)/9; i++) {
            if (solution.get(i*9+col) == Character.forDigit(k, 10)) {
                return false;
            }
        }
        for (int i = (solution.size()-1)/9+1; i < RANK; i++) {
            if (board[i][col] == Character.forDigit(k, 10)) {
                return false;
            }
        }
        // SudokuSolver 检查宫 要算出当前宫，里面夹杂了solution和board的数据。
        return true;
    }
    
    private static void unMakeMove(List<Character> solution) {
        solution.remove(solution.size()-1);
    }

    private static void makeMove(List<Character> solution, int i) {
        solution.add(Character.forDigit(i, 10));
    }
    
    private static boolean dfs2(char[][] board, List<Character> solution, int index) {
        if (isASolution2(solution, index)) {
            processSolution2(board, solution);
            return true;
        } else {
            // 每一个格子都尝试1-9
            for (int i = 1; i <= RANK; i++) {
                if (isValid2(solution, i, index)) {
                    makeMove2(solution, i, index);
                    if (dfs2(board, solution, index+1)) {
                        return true;
                    }
                    unMakeMove2(solution, index, board);
                }
            }
            return false;
        }
        
    }
    
    private static boolean isASolution2(List<Character> solution, int index) {
        /*for (int i = 0; i < solution.size(); i++) {
            if (solution.get(i) == EMPTY_CHAR) {
                return false;
            }
        }
        return true;*/
        return index == RANK * RANK;
    }
    
    private static void processSolution2(char[][] board, List<Character> solution) {
        for (int i = 0; i < solution.size(); i++) {
            board[i/9][i%9] = solution.get(i);
        }
    }
    
    private static boolean isValid2(List<Character> solution, int k, int index) {
        // 如果下一个格子不为空，则k必须与格子的数字一样
        if (solution.get(index) != EMPTY_CHAR) {
            if (solution.get(index) == Character.forDigit(k, 10)) {
                return true;
            } else {
                return false;
            }
        }
        // 检查行
        int rowStart = NumberUtil.closestMultiple(index, RANK);
        for (int i = rowStart; i <= rowStart+8; i++) {
            if (solution.get(i) == Character.forDigit(k, 10)) {
                return false;
            }
        }
        // 检查列
        for (int i = 0; i < RANK; i++) {
            if (solution.get(index % 9 + i*9) == Character.forDigit(k, 10)) {
                return false;
            }
        }
        // 检查宫
        int subBoxStartIndex = startSubBox2(index);
        for (int i = 0; i < RANK; i++) {
            int subBoxIndex = subBoxStartIndex + i % 3 + (i / 3) * 9;
            if (solution.get(subBoxIndex) == Character.forDigit(k, 10)) {
                return false;
            }
        }
        return true;
    }
    
    private static void unMakeMove2(List<Character> solution, int index, char[][] board) {
        // 此处有坑，solution之前不一定是 空 的，因为尝试的数字和之前不一致也会不合法。
        // 注意isValid不合法的所有情况都需要考虑
        // 可以和board对照，置换成board之前的字符，
        solution.set(index, board[index/9][index%9]);
    }

    private static void makeMove2(List<Character> solution, int i, int index) {
        solution.set(index, Character.forDigit(i, 10));
    }
    
    // 根据index，推算出index所在宫的起始索引
    private static int startSubBox2(int i) {
        return NumberUtil.closestMultiple(i / 9, 3)*9 + NumberUtil.closestMultiple(i, 3) % 9;
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
        solution2(board);
        for (int i = 0; i < RANK; i++) {
            for (int j = 0; j < RANK; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
    
}
