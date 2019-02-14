package com.zfwhub.algorithm.leetcode.base;

import java.util.*;

// https://leetcode.com/problems/permutations/
// https://www.quora.com/How-does-recursion-work-inside-a-for-loop-Most-importantly-how-does-it-flow
public class Permutations {
    
    //想一下for循环算阶层的办法。
    public static List<List<Integer>> permute(int[] nums) {
        if (nums.length == 1) {
            List<List<Integer>> list = new ArrayList<List<Integer>>();
            List<Integer> list2 = new ArrayList<Integer>();
            list2.add(nums[0]);
            list.add(list2);
            return list;
        } else {
            List<List<Integer>> list3 = new ArrayList<List<Integer>>();
            for (int i = 0; i < nums.length; i++) {
                int[] dest = new int[nums.length - 1];
                System.arraycopy(nums, 0, dest, 0, i);
                System.arraycopy(nums, i + 1, dest, i, nums.length - 1 - i);
                List<List<Integer>> list4 = permute(dest);
                for (int k = 0; k < list4.size(); k++) {
                    List<Integer> list5 = list4.get(k);
                    list5.add(nums[i]);
                    list3.add(list5);
                }
            }
            return list3;
        }
    }
    
    public static List<List<Integer>> permute2(int[] nums) {
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
