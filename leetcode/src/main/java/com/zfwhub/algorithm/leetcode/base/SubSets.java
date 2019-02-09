package com.zfwhub.algorithm.leetcode.base;
import java.util.*;
import com.zfwhub.algorithm.utils.ArrayUtil;
import com.zfwhub.algorithm.utils.CollectionUtil;

// https://leetcode.com/problems/subsets/
public class SubSets {

    // 回溯法，只有在arr中没有重复元素时正确。
    public static List<List<Integer>> solution1(int[] arr) {
        List<List<Integer>> solutionList = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        dfs(solutionList, solution, arr);
        return solutionList;
    }
    
    private static void dfs(List<List<Integer>> solutionList, List<Integer> solution, int[] arr) {
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
    
    private static boolean isASolution(List<List<Integer>> solutionList, List<Integer> solution) {
        // TODO SubSets 为什么不需要判断。
//      return !solutionList.contains(solution);
        return true;
    }

    private static void processSolution(List<List<Integer>> solutionList, List<Integer> solution) {
        solutionList.add(new ArrayList<>(solution));
    }
    
    private static boolean isValid(List<Integer> solution, int n) {
        if (solution.size() == 0) {
            return true;
        }
        return solution.get(solution.size()-1) < n;
    }
    
    private static void makeMove(List<Integer> solution, int n) {
        solution.add(n);
    }
    
    private static void unMakeMove(List<Integer> solution) {
        solution.remove(solution.size()-1);
    }
    
    // https://www.cnblogs.com/grandyang/p/4309345.html 解法一
    public static List<List<Integer>> solution2(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        // result初始化为空集
        result.add(new ArrayList<>());
        //循环arr，每次循环都扩充result一倍。
        for (int i = 0; i < arr.length; i++) {
            int size = result.size();
            for (int j = 0; j < size; j++) {
                // 选arr[i],或者不选，可以 扩充result一倍，
                List<Integer> list = new ArrayList<>(result.get(j));
                list.add(arr[i]);
                result.add(list);
            }
        }
        return result;
    }
    
    // https://www.cnblogs.com/grandyang/p/4309345.html 解法二  类比上面的回溯
    public static List<List<Integer>> solution3(int[] arr) {
        List<List<Integer>> solutionList = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        solution3Helper(solutionList, solution, arr, 0);
        return solutionList;
    }
    
    public static void solution3Helper(List<List<Integer>> solutionList, List<Integer> solution, int[] arr, int pos) {
        solutionList.add(new ArrayList<>(solution));
        for (int i = pos; i < arr.length; i++) {
            solution.add(arr[i]);
            solution3Helper(solutionList, solution, arr, i+1);
            solution.remove(solution.size()-1);
        }
    }
    
    /*
     * assume we have arr [1,2,3], try to get all of combinations
     * C(3,0)+C(3,1)+C(3,2)+C(3,3)=1+3+3+1=8=pow(2,3)
     * try to look the array elements as turn off switch, so we can have pow(2,3)
     */
    public static List<List<Integer>> solution4(int[] arr) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        int count = new Double(Math.pow(2.0, arr.length)).intValue();
        for (int i = 0; i < count; i++) {
            List<Integer> list1 = new ArrayList<Integer>();
            String binaryString = String.format("%3s", Integer.toBinaryString(i)).replace(' ', '0');
            char[] chars = binaryString.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == '1') {
                    list1.add(arr[j]);
                }
            }
            list.add(list1);
        }
        return list;
    }
    
    // CollectionUtil
    public static List<List<Integer>> solution5(int[] nums) {
        return CollectionUtil.subsets(ArrayUtil.toList(nums));
    }
    
    public static void main(String[] args) {
        int[] arr = new int[] {1,2,3};
        System.out.println(SubSets.solution1(arr));
        System.out.println(SubSets.solution2(arr));
        System.out.println(SubSets.solution3(arr));
    }
}
