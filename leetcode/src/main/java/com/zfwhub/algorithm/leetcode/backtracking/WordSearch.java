package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

// https://leetcode.com/problems/word-search/
// https://leetcode.com/problems/word-search/discuss/27658/Accepted-very-short-Java-solution.-No-additional-space.
// https://leetcode.com/submissions/detail/188489526/
public class WordSearch {

    public static int k = 0; // k 代表当前word的index

    public static boolean exist(char[][] board, String word) {
        List<List<List<Integer>>> result = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {//入口
                    k = 0;
                    List<List<Integer>> solution = new ArrayList<>();
                    makeMove(solution, i, j);
                    dfs(result, solution, board, word);
                }
            }
        }
        return result.size() > 0;
    }

    public static boolean dfs(List<List<List<Integer>>> result, List<List<Integer>> solution, char[][] board, String word) {
        if (isASolution(solution, word)) {
            processSolution(result, solution);
            return true;
        }
        // i,j 二维数组搜索入口，k是word的索引。
        // 每一个位置，都上下左右尝试。
        int i = solution.get(solution.size() - 1).get(0);
        int j = solution.get(solution.size() - 1).get(1);
        //上
        if (i > 0) {
            i = i - 1;
            if (isValid(board, word, i, j, solution)) {
                makeMove(solution, i, j);
                if (dfs(result, solution, board, word)) {
                    return true;
                }
                unmakeMove(solution);
            } else {
                i = i + 1;
            }

        }
        i = solution.get(solution.size() - 1).get(0);
        j = solution.get(solution.size() - 1).get(1);
        //下
        if (i < board.length - 1) {
            i = i + 1;
            if (isValid(board, word, i, j, solution)) {
                makeMove(solution, i, j);
                if (dfs(result, solution, board, word)) {
                    return true;
                }
                unmakeMove(solution);
            } else {
                i = i - 1;
            }
        }
        //左
        i = solution.get(solution.size() - 1).get(0);
        j = solution.get(solution.size() - 1).get(1);
        if (j > 0) {
            j = j - 1;
            if (isValid(board, word, i, j, solution)) {
                makeMove(solution, i, j);
                if (dfs(result, solution, board, word)) {
                    return true;
                }
                unmakeMove(solution);
            } else {
                j = j + 1;
            }
        }
        //右
        i = solution.get(solution.size() - 1).get(0);
        j = solution.get(solution.size() - 1).get(1);
        if (j < board[i].length - 1) {
            j = j + 1;
            if (isValid(board, word, i, j, solution)) {
                makeMove(solution, i, j);
                if (dfs(result, solution, board, word)) {
                    return true;
                }
                unmakeMove(solution);
            } else {
                j = j - 1;
            }
        }
        return false;
    }

    public static boolean isASolution(List<List<Integer>> solution, String word) {
        return solution.size() == word.length();
    }

    public static void processSolution(List<List<List<Integer>>> result, List<List<Integer>> solution) {
        result.add(new ArrayList<>(solution));
    }

    // 检查是否相等，且不能重复走
    public static boolean isValid(char[][] board, String word, int i, int j, List<List<Integer>> solution) {
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
        k = k + 1;
    }

    public static void unmakeMove(List<List<Integer>> solution) {
        solution.remove(solution.size() - 1);
        k = k - 1;
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
