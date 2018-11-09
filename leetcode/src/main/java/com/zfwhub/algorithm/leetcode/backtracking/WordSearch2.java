package com.zfwhub.algorithm.leetcode.backtracking;

// https://leetcode.com/problems/word-search/discuss/27658/Accepted-very-short-Java-solution.-No-additional-space.
public class WordSearch2 {
    public static boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (exist(board, y, x, w, 0))
                    return true;
            }
        }
        return false;
    }

    private static boolean exist(char[][] board, int y, int x, char[] word, int i) {
        if (i == word.length)
            return true;
        if (y < 0 || x < 0 || y == board.length || x == board[y].length)
            return false;
        if (board[y][x] != word[i])
            return false;
        board[y][x] ^= 256;
        boolean exist = exist(board, y, x + 1, word, i + 1) || exist(board, y, x - 1, word, i + 1) || exist(board, y + 1, x, word, i + 1) || exist(board, y - 1, x, word, i + 1);
        board[y][x] ^= 256;
        return exist;
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
