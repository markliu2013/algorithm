package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

// 运用 BacktrackingTemplate4
public class WordSearch2 {

    public static boolean exist(char[][] board, String word) {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (board[x][y] == word.charAt(0)) {//入口
                    // 每个solution包含x，y两个索引
                    List<List<Integer>> solution = new ArrayList<>();
                    List<Integer> coordinate = new ArrayList<>();
                    coordinate.add(x);
                    coordinate.add(y);
                    solution.add(coordinate);
                    if (dfs(board, word, x, y, solution)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // x和y是当前board的索引
    private static boolean dfs(char[][] board, String word, int x, int y, List<List<Integer>> solution) {
        if (isASolution(board, word, x, y, solution)) {
            return true;
        }
        // 每一个位置，都上下左右尝试。只要有一个位置合法
        for (int i = 1; i <= 4; i++) { //1,2,3,4 分别代表上下左右
            if (isValid(board, word, solution, i)) {
                makeMove(board, word, solution, i);
                if (dfs(board, word, x, y, solution)) {
                    return true;
                }
                unMakeMove(board, word, solution, i);
            }
        }
        return false;
    }

    private static boolean isASolution(char[][] board, String word, int x, int y, List<List<Integer>> solution) {
        return solution.size() == word.length();
    }

    // direction 1,2,3,4 分别代表上下左右
    private static boolean isValid(char[][] board, String word, List<List<Integer>> solution, int direction) {
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

    private static void makeMove(char[][] board, String word, List<List<Integer>> solution, int direction) {
        List<Integer> coordinate = getXYByDirection(solution, direction);
        solution.add(coordinate);
    }

    private static void unMakeMove(char[][] board, String word, List<List<Integer>> solution, int direction) {
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
