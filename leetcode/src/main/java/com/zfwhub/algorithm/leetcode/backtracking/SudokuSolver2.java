package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

import com.zfwhub.algorithm.utils.NumberUtil;

// https://leetcode.com/problems/sudoku-solver/
// 第二种思路，可以把solution按照board初始化，在isASolution中判断solution中不存在空点
public class SudokuSolver2 {
    
    public static final char EMPTY_CHAR = '.'; 
    public static final int RANK = 9;
    
    public static void solveSudoku(char[][] board) {
        List<Character> solution = new ArrayList<>();//每一个格子都是数字几
        for (int i = 0; i < RANK; i++) {
            for (int j = 0; j < RANK; j++) {
                solution.add(board[i][j]);
            }
        }
        int index = 0;//尝试到哪一个了。
        dfs(board, solution, index);
    }
    
    private static boolean dfs(char[][] board, List<Character> solution, int index) {
        if (isASolution(solution, index)) {
            processSolution(board, solution);
            return true;
        } else {
            // 每一个格子都尝试1-9
            for (int i = 1; i <= RANK; i++) {
                if (isValid(solution, i, index)) {
                    makeMove(solution, i, index);
                    if (dfs(board, solution, index+1)) {
                        return true;
                    }
                    unMakeMove(solution, index, board);
                }
            }
            return false;
        }
        
    }
    
    private static boolean isASolution(List<Character> solution, int index) {
        /*for (int i = 0; i < solution.size(); i++) {
            if (solution.get(i) == EMPTY_CHAR) {
                return false;
            }
        }
        return true;*/
        return index == RANK * RANK;
    }
    
    private static void processSolution(char[][] board, List<Character> solution) {
        for (int i = 0; i < solution.size(); i++) {
            board[i/9][i%9] = solution.get(i);
        }
    }
    
    private static boolean isValid(List<Character> solution, int k, int index) {
        // 如果下一个格子不为空，则k必须与格子的数字一样
        if (solution.get(index) != EMPTY_CHAR) {
            if (solution.get(index) == Character.forDigit(k, 10)) {
                return true;
            } else {
                return false;
            }
        }
        // 检查行
        int rowStart = NumberUtil.closestNumber(index, RANK);
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
        int subBoxStartIndex = startSubBox(index);
        for (int i = 0; i < RANK; i++) {
            int subBoxIndex = subBoxStartIndex + i % 3 + (i / 3) * 9;
            if (solution.get(subBoxIndex) == Character.forDigit(k, 10)) {
                return false;
            }
        }
        return true;
    }
    
    private static void unMakeMove(List<Character> solution, int index, char[][] board) {
        // 此处有坑，solution之前不一定是 空 的，因为尝试的数字和之前不一致也会不合法。
        // 注意isValid不合法的所有情况都需要考虑
        // 可以和board对照，置换成board之前的字符，
        // TODO SudokuSolver2 另一种思路，通过isValid返回。
        solution.set(index, board[index/9][index%9]);
    }

    private static void makeMove(List<Character> solution, int i, int index) {
        solution.set(index, Character.forDigit(i, 10));
    }
    
    // 根据index，推算出index所在宫的起始索引
    private static int startSubBox(int i) {
        return NumberUtil.closestNumber(i / 9, 3)*9 + NumberUtil.closestNumber(i, 3) % 9;
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
