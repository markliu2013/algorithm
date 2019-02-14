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
    
    public static List<List<Integer>> solution2(int[] nums) {
        return subsetsWithDup(ArrayUtil.toList(nums));
    }
    
    private static <T extends Comparable<? super T>> List<List<T>> subsetsWithDup(List<T> list) {
        List<List<T>> solutionList = new ArrayList<>();
        Collections.sort(list); //必须加上排序，以方便去重。
        subsetsWithDupHelper(solutionList, new ArrayList<>(), list, 0);
        return solutionList;
    }
    
    private static <T extends Comparable<? super T>> void subsetsWithDupHelper(List<List<T>> solutionList, List<T> solution, List<T> list, int start) {
        solutionList.add(new ArrayList<>(solution));
        for (int i = start; i < list.size(); i++) {
            if(i > start && list.get(i) == list.get(i-1)) continue; // skip duplicates
            solution.add(list.get(i));
            subsetsWithDupHelper(solutionList, solution, list, i+1);
            solution.remove(solution.size()-1);
        }
    }
    
    public static List<List<Integer>> solution3(int[] nums) {
        return CollectionUtil.subsetsRemoveDup(ArrayUtil.toList(nums));
    }
    
    public static void main(String[] args) {
        int[] arr = new int[] { 1,2,2 };
        System.out.println(SubSetsII.solution1(arr));
        System.out.println(SubSetsII.solution2(arr));
        System.out.println(SubSetsII.solution3(arr));
    }
}
