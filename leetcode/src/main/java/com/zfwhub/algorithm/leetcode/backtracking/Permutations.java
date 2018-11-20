package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

// https://leetcode.com/problems/permutations/
public class Permutations {
    
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> solutionList = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        dfs(solutionList, solution, nums);
        return solutionList;
    }

    public static void dfs(List<List<Integer>> solutionList, List<Integer> solution, int[] nums) {
        if (isASolution(solution, nums)) {
            processSolution(solutionList, solution);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (isValid(solution, nums[i])) {
                makeMove(solution, nums[i]);
                dfs(solutionList, solution, nums);
                unMakeMove(solution);
            }
        }
    }

    public static boolean isASolution(List<Integer> solution, int[] nums) {
        return solution.size() == nums.length;
    }

    public static void processSolution(List<List<Integer>> solutionList, List<Integer> solution) {
        solutionList.add(new ArrayList<>(solution));
    }

    public static boolean isValid(List<Integer> solution, int n) {
        return !solution.contains(n);
    }

    public static void makeMove(List<Integer> solution, int n) {
        solution.add(n);
    }

    public static void unMakeMove(List<Integer> solution) {
        solution.remove(solution.size() - 1);
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2, 3 };
        System.out.println(Permutations.permute(nums));
    }
}
