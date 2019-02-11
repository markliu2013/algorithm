package com.zfwhub.algorithm.leetcode.base;

import java.util.*;

import com.zfwhub.algorithm.utils.ArrayUtil;
import com.zfwhub.algorithm.utils.CollectionUtil;

// https://leetcode.com/problems/subsets-ii/
public class SubSetsII {
    
    public static List<List<Integer>> solution1(int[] nums) {
        List<List<Integer>> solutionList = new ArrayList<>();
        // solution存储的是nums的索引
        List<Integer> solution = new ArrayList<>();
        Arrays.sort(nums); //必须排序处理后
        dfs(solutionList, solution, nums);
        return solutionList;
    }

    private static void dfs(List<List<Integer>> solutionList, List<Integer> solution, int[] arr) {
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

    private static boolean isASolution(List<List<Integer>> solutionList, List<Integer> solution, int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < solution.size(); i++) {
            list.add(arr[solution.get(i)]);
        }
        return !solutionList.contains(list);
    }

    private static void processSolution(List<List<Integer>> solutionList, List<Integer> solution, int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < solution.size(); i++) {
            list.add(arr[solution.get(i)]);
        }
        solutionList.add(list);
    }

    private static boolean isValid(List<Integer> solution, int i) {
        if (solution.size() == 0) {
            return true;
        }
        return solution.get(solution.size() - 1) < i;
    }

    private static void makeMove(List<Integer> solution, int i) {
        solution.add(i);
    }

    private static void unMakeMove(List<Integer> solution) {
        solution.remove(solution.size() - 1);
    }
    
    // CollectionUtil
    public static List<List<Integer>> solution2(int[] nums) {
        return CollectionUtil.subsetsWithDup(ArrayUtil.toList(nums));
    }
    
    public static void main(String[] args) {
        int[] arr = new int[] { 1,2,2 };
        System.out.println(SubSetsII.solution1(arr));
        System.out.println(SubSetsII.solution2(arr));
    }
}
