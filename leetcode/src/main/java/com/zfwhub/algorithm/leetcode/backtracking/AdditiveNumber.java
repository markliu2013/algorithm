package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

// https://leetcode.com/problems/additive-number/
public class AdditiveNumber {
    
    // Wrong Answer
    public static boolean isAdditiveNumber0(String num) {
        int[] numArr = new int[num.length()];
        for (int i = 0; i < num.length(); i++) {
            numArr[i] = Character.getNumericValue(num.charAt(i));
        }
        for (int i = 2; i < numArr.length; i++) {
            boolean flag = false;  
            checkIndex:
            for (int j = 0; j < i; j++) {
                for (int k = j+1; k < i; k++) {
                    if (numArr[j] + numArr[k] == numArr[i]) {
                        flag = true;
                        break checkIndex;
                    }
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isAdditiveNumber(String num) {
        // num的每个位置是否分割点
        List<Boolean> solution = new ArrayList<>();
        boolean result = dfs(solution, num);
        System.out.println(solution);
        return result;
    }
    
    public static boolean dfs(List<Boolean> solution, String num) {
        if (isASolution(solution, num)) {
            return true;
        } else {
            // num的每一个位置都尝试分割或不分割
            // 0 不分割；1 分割
            for (int i = 0; i < 2; i++) {
                if (isValid(solution, num, i)) {
                    makeMove(solution, i);
                    if (dfs(solution, num)) {
                        return true;
                    }
                    unMakeMove(solution);
                }
            }
            return false;
        }
    }
    
    public static boolean isASolution(List<Boolean> solution, String num) {
        return solution.size() == num.length() - 1;
    }

    public static boolean isValid(List<Boolean> solution, String num, int flag) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < solution.size(); i++) {
            if (solution.get(i)) {
                list.add(i);
            }
        }
        // 第一个点必须打在前半部分，
        // 前面两个点位数相加不能大于后面
        int mid = num.length() / 2;
        
        return true;
    }
    
    public static void makeMove(List<Boolean> solution, int i) {
        solution.add(i == 1);
    }
    
    public static void unMakeMove(List<Boolean> solution) {
        solution.remove(solution.size()-1);
    }
    
    public static List<Integer> splitNums(List<Boolean> solution, String num) {
        List<Integer> list = new ArrayList<>();
        int preTrueIndex = 0;
        for (int j = 0; j < solution.size(); j++) {
            if (solution.get(j) == true) {
                list.add(Integer.valueOf(num.substring(preTrueIndex, j+1)));
                preTrueIndex = j;
            }
        }
        list.add(preTrueIndex);
        return list;
    }
    
    public static void main(String[] args) {
//        System.out.println(isAdditiveNumber("112358"));
        System.out.println(isAdditiveNumber("199100199"));
    }
    
}
