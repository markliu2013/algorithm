package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

// https://leetcode.com/problems/additive-number/
// 题目的意思: 必须是前面两个数相加
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
        if (num.length() < 3) {
            return false;
        }
        // num的每个位置是否分割点（包括最后一个分割点，必须是分的）
        List<Boolean> solution = new ArrayList<>();
        boolean result = dfs(solution, num);
        System.out.println(splitNums(solution, num));
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
        return solution.size() == num.length();
    }

    public static boolean isValid(List<Boolean> solution, String num, int flag) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < solution.size(); i++) {
            if (solution.get(i)) {
                list.add(i);
            }
        }
        // 第一个点必须打在前半部分。
        int mid = num.length() / 2;
        if (list.size() == 0) {
            if (solution.size()+1 >= mid && flag == 0) {
                return false;
            }
            // 不能这样 "02"
            if (flag == 1) {
                String targetStr = num.substring(0, solution.size()+1);
                if (targetStr.startsWith("0") && targetStr.length() > 1) {
                    return false;
                }
            }
        }
        // 前面两个点位数相加不能大于后面
        if (list.size() == 1) {
            // 第一个分割的数是几位数
            int shift1 = list.get(0)+1;
            int shift2 = (solution.size()+1)-shift1;
            // 前面两个数相加的最多位数
            int maxShift = Math.max(shift1, shift2);
            // 后面剩余的位数
            int remainShift = num.length()-(solution.size()+1);
            if (maxShift > remainShift) {
                return false;
            }
            // 不能这样 "001"
            if (flag == 1) {
                String targetStr = num.substring(list.get(0)+1, solution.size()+1);
                if (targetStr.startsWith("0") && targetStr.length() > 1) {
                    return false;
                }
            }
        }
        // 最后一个点必须分
        if (solution.size() == num.length()-1 && flag == 0) {
            return false;
        }
        // 前面已经有两个以上分割点了
        if (list.size() >= 2 && flag == 1) {
            List<Long> numList = splitNums(solution, num);
            String targetStr = num.substring(list.get(list.size()-1)+1, solution.size()+1);
            if (targetStr.startsWith("0") && targetStr.length() > 1) {
                return false;
            }
            Long target = Long.valueOf(targetStr);
            Long pre1 = numList.get(numList.size()-1);
            Long pre2 = numList.get(numList.size()-2);
            return target == pre1 + pre2;
        }
        return true;
    }
    
    public static void makeMove(List<Boolean> solution, int i) {
        solution.add(i == 1);
    }
    
    public static void unMakeMove(List<Boolean> solution) {
        solution.remove(solution.size()-1);
    }
    
    public static List<Long> splitNums(List<Boolean> solution, String num) {
        List<Long> list = new ArrayList<>();
        int preTrueIndex = 0;
        for (int j = 0; j < solution.size(); j++) {
            if (solution.get(j) == true) {
                list.add(Long.valueOf(num.substring(preTrueIndex, j+1)));
                preTrueIndex = j+1;
            }
        }
        return list;
    }
    
    public static void main(String[] args) {
//        System.out.println(isAdditiveNumber("000"));
        System.out.println(isAdditiveNumber("120122436"));
    }
    
}
