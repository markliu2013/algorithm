package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

// https://leetcode.com/problems/subsets-ii/
public class SubSetsII {
    
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> solutionList = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        Arrays.sort(nums);
        dfs(solutionList, solution, nums);
        return solutionList;
    }

    public static void dfs(List<List<Integer>> solutionList, List<Integer> solution, int[] arr) {
        if (isASolution(solutionList, solution, arr)) {
            processSolution(solutionList, solution, arr);
        }
        for (int i = 0; i < arr.length; i++) {
            if (isValid(solution, i)) {
                makeMove(solution, i);
                dfs(solutionList, solution, arr);
                unMakeMove(solution);
            }
        }
    }

    public static boolean isASolution(List<List<Integer>> solutionList, List<Integer> solution, int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < solution.size(); i++) {
            list.add(arr[solution.get(i)]);
        }
        return !solutionList.contains(list);
    }

    public static void processSolution(List<List<Integer>> solutionList, List<Integer> solution, int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < solution.size(); i++) {
            list.add(arr[solution.get(i)]);
        }
        solutionList.add(list);
    }

    public static boolean isValid(List<Integer> solution, int i) {
        if (solution.size() == 0) {
            return true;
        }
        return solution.get(solution.size() - 1) < i;
    }

    public static void makeMove(List<Integer> solution, int i) {
        solution.add(i);
    }

    public static void unMakeMove(List<Integer> solution) {
        solution.remove(solution.size() - 1);
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 4, 4, 4, 1, 4 };
        System.out.println(SubSetsII.subsetsWithDup(arr));
    }
}
