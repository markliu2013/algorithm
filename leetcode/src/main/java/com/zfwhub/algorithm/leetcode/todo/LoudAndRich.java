package com.zfwhub.algorithm.leetcode.todo;

import java.util.Arrays;

/**
 * https://leetcode.com/contest/weekly-contest-88/problems/loud-and-rich/
 */
public class LoudAndRich {

    // TODO LoudAndRich
    public static int[] solution(int[][] richer, int[] quiet) {

        return null;
    }

    public static void main(String[] args) {
        int[][] richer = new int[][] { { 1, 0 }, { 2, 1 }, { 3, 1 }, { 3, 7 }, { 4, 3 }, { 5, 3 }, { 6, 3 } };
        int[] quiet = new int[] { 3, 2, 5, 4, 6, 1, 7, 0 };
        int[] answer = LoudAndRich.solution(richer, quiet);
        System.out.println(Arrays.toString(answer));
    }
}
