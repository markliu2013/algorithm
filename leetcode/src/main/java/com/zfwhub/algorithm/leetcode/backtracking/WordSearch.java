package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

// https://leetcode.com/problems/word-search/
public class WordSearch {

    public static int k = 0; // k 代表当前word的index

    public static boolean solution1(char[][] board, String word) {
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
    
    public static boolean solution2(char[][] board, String word) {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (board[x][y] == word.charAt(0)) {//入口
                    // 每个solution包含x，y两个索引
                    List<List<Integer>> solution = new ArrayList<>();
                    List<Integer> coordinate = new ArrayList<>();
                    coordinate.add(x);
                    coordinate.add(y);
                    solution.add(coordinate);
                    if (dfs2(board, word, x, y, solution)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean dfs(List<List<List<Integer>>> result, List<List<Integer>> solution, char[][] board, String word) {
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

    private static boolean isASolution(List<List<Integer>> solution, String word) {
        return solution.size() == word.length();
    }

    private static void processSolution(List<List<List<Integer>>> result, List<List<Integer>> solution) {
        result.add(new ArrayList<>(solution));
    }

    // 检查是否相等，且不能重复走
    private static boolean isValid(char[][] board, String word, int i, int j, List<List<Integer>> solution) {
        List<Integer> step = new ArrayList<>();
        step.add(i);
        step.add(j);
        if (solution.contains(step)) {
            return false;
        }
        return board[i][j] == word.charAt(k);
    }

    private static void makeMove(List<List<Integer>> solution, int i, int j) {
        List<Integer> step = new ArrayList<>();
        step.add(i);
        step.add(j);
        solution.add(step);
        k = k + 1;
    }

    private static void unmakeMove(List<List<Integer>> solution) {
        solution.remove(solution.size() - 1);
        k = k - 1;
    }

    // x和y是当前board的索引
    private static boolean dfs2(char[][] board, String word, int x, int y, List<List<Integer>> solution) {
        if (isASolution2(board, word, x, y, solution)) {
            return true;
        }
        // 每一个位置，都上下左右尝试。只要有一个位置合法
        for (int i = 1; i <= 4; i++) { //1,2,3,4 分别代表上下左右
            if (isValid2(board, word, solution, i)) {
                makeMove2(board, word, solution, i);
                if (dfs2(board, word, x, y, solution)) {
                    return true;
                }
                unMakeMove2(board, word, solution, i);
            }
        }
        return false;
    }

    private static boolean isASolution2(char[][] board, String word, int x, int y, List<List<Integer>> solution) {
        return solution.size() == word.length();
    }

    // direction 1,2,3,4 分别代表上下左右
    private static boolean isValid2(char[][] board, String word, List<List<Integer>> solution, int direction) {
        List<Integer> coordinate = getXYByDirection(solution, direction);
        int x = coordinate.get(0);
        int y = coordinate.get(1);
        if (x < 0 || y < 0 || x >= board.length || y >= board[x].length) {
            return false;
        }
        if (solution.contains(coordinate)) {
            return false;
        }
        return board[x][y] == word.charAt(solution.size());
    }

    private static void makeMove2(char[][] board, String word, List<List<Integer>> solution, int direction) {
        List<Integer> coordinate = getXYByDirection(solution, direction);
        solution.add(coordinate);
    }

    private static void unMakeMove2(char[][] board, String word, List<List<Integer>> solution, int direction) {
        solution.remove(solution.size()-1);
    }
    
    // 根据方向，找到下一步的坐标。direction 1,2,3,4 分别代表上下左右
    private static List<Integer> getXYByDirection(List<List<Integer>> solution, int direction) {
        List<Integer> coordinate = new ArrayList<>(2);
        int x = solution.get(solution.size() - 1).get(0);
        int y = solution.get(solution.size() - 1).get(1);
        switch (direction) {
            case 1:
                x--;
                break;
            case 2:
                x++;
                break;
            case 3:
                y--;
                break;
            case 4:
                y++;
                break;
            default:
                throw new IllegalArgumentException("direction is " + direction);
        }
        coordinate.add(x);
        coordinate.add(y);
        return coordinate;
    }

    public static void main(String[] args) {
        char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
        String word1 = "ABCCED";
        String word2 = "SEE";
        String word3 = "ABCB";
        System.out.println(solution1(board, word1));
        System.out.println(solution1(board, word2));
        System.out.println(solution1(board, word3));
        char[][] board4 = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'E', 'S' }, { 'A', 'D', 'E', 'E' } };
        String word4 = "ABCESEE";
        System.out.println(solution1(board4, word4));
        char[][] board5 = { { 'b', 'b' }, { 'a', 'b' }, { 'b', 'a' } };
        String word5 = "a";
        System.out.println(solution1(board5, word5));
    }

}
