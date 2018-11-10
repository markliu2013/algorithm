package com.zfwhub.algorithm.leetcode.backtracking;
import java.util.*;

// Backtrack template for only one solution
public class Backtrack2 {
    public static List<List<Object>> backtrack(int[] A) {
        List<List<Object>> result = new LinkedList<List<Object>>();
        if (A == null || A.length == 0) {
            return result;
        }
        List<Object> solution = new LinkedList<Object>();
        dfs(result, solution, A, 0);
        return result;
    }

    private static boolean dfs(List<List<Object>> result, List<Object> solution, int[] A, int pos) {
        if (isASolution(A, pos)) {
            processSolution(result, solution);
            return true;
        }
        for (int i = pos; i < A.length; i++) {
            if (!isValid(A[i])) continue;
            makeMove(solution, A[i]);
            if (dfs(result, solution, A, i + 1)) {
                return true;
            }
            unmakeMove(solution, A[i]);
        }
        return false;
    }

    private static void unmakeMove(List<Object> solution, int i) {
        solution.remove(solution.size()-1);
    }

    private static void makeMove(List<Object> solution, int i) {
        solution.add(i);
    }

    private static boolean isValid(int i) {
        return true;
    }

    private static void processSolution(List<List<Object>> result, List<Object> solution) {
        result.add(new ArrayList<>(solution));
    }

    private static boolean isASolution(int[] a, int pos) {
        return pos == a.length;//输出的是最后一个元素必须选择的子集。
    }
    
    public static void main(String[] args) {
        int[] A = new int[] {1,2,3,4};
        System.out.println(backtrack(A));
    }
}
