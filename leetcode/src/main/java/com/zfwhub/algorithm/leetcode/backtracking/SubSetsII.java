package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

// https://leetcode.com/problems/subsets-ii/
public class SubSetsII {
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> solutionList = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        dfs(solutionList, solution, nums);
        return solutionList;
    }
    public static void dfs(List<List<Integer>> solutionList, List<Integer> solution, int[] arr) {
        if (isASolution(solutionList, solution)) {
            processSolution(solutionList, solution);
        }
        for (int i = 0; i < arr.length; i++) {
            if (isValid(solution, arr[i])) {
                makeMove(solution, arr[i]);
                dfs(solutionList, solution, arr);
                unMakeMove(solution);
            }
        }
        
    }
    
    public static boolean isASolution(List<List<Integer>> solutionList, List<Integer> solution) {
        return true;
    }

    public static void processSolution(List<List<Integer>> solutionList, List<Integer> solution) {
        solutionList.add(new ArrayList<>(solution));
    }
    
    public static boolean isValid(List<Integer> solution, int n) {
        if (solution.size() == 0) {
            return true;
        }
        return solution.get(solution.size()-1) < n;
    }
    
    public static void makeMove(List<Integer> solution, int n) {
        solution.add(n);
    }
    
    public static void unMakeMove(List<Integer> solution) {
        solution.remove(solution.size()-1);
    }
    public static void main(String[] args) {
        int[] arr = new int[] {1,2,2};
        System.out.println(SubSetsII.subsetsWithDup(arr));
    }
}
