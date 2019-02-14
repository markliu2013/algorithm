package com.zfwhub.algorithm.leetcode.backtracking;
import java.math.BigDecimal;
import java.util.*;

import com.zfwhub.algorithm.utils.CollectionUtil;

// https://leetcode.com/problems/split-array-into-fibonacci-sequence/
public class SplitArrayFibonacci {
    
    // Memory Limit Exceeded
    public static List<Integer> solution1(String S) {
        // 尝试分割string
        List<List<Integer>> list = CollectionUtil.subsets(CollectionUtil.newIntList(1, S.length()-1));
        for (int i = 0; i < list.size(); i++) {
            List<Integer> splitList = list.get(i);
            if (splitList.size() >= 2) {
                List<Integer> splitResult = new ArrayList<>();
                int preIndex = 0;
                boolean splitValid = true;
                for (int j = 0; j < splitList.size(); j++) {
                    String splitBitString = S.substring(preIndex, splitList.get(j));
                    if (splitBitString.startsWith("0") && splitBitString.length() > 1) {
                        splitValid = false;
                        break;
                    } else {
                        splitResult.add(Integer.valueOf(splitBitString));    
                    }
                    preIndex = splitList.get(j);
                }
                if (splitValid) {
                    String splitBitString = S.substring(preIndex);
                    if (splitBitString.startsWith("0") && splitBitString.length() > 1) {
                        splitValid = false;
                    } else {
                        splitResult.add(Integer.valueOf(splitBitString));
                    }
                }
                // 检查分割结果
                if (splitValid) {
                    boolean checkResult = true;
                    for (int j = 2; j < splitResult.size(); j++) {
                        if (splitResult.get(j) != splitResult.get(j-1) + splitResult.get(j-2)) {
                            checkResult = false;
                            break;
                        }
                    }
                    if (checkResult && splitResult.size() > 2) {
                        return splitResult;
                    }
                }
            }
        }
        return new ArrayList<>();
    }
    
    public static List<Integer> solution2(String num) {
        if (num.length() < 3) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        // num的每个位置是否分割点（包括最后一个分割点，必须是分的）
        List<Boolean> solution = new ArrayList<>();
        dfs(result, solution, num);
        return result;
    }
    
    private static boolean dfs(List<Integer> result, List<Boolean> solution, String num) {
        if (isASolution(solution, num)) {
            processSolution(result, solution, num);
            return true;
        } else {
            // num的每一个位置都尝试分割或不分割
            // 0 不分割；1 分割
            for (int i = 0; i < 2; i++) {
                if (isValid(solution, num, i)) {
                    makeMove(solution, i);
                    if (dfs(result, solution, num)) {
                        return true;
                    }
                    unMakeMove(solution);
                }
            }
            return false;
        }
    }
    
    private static void processSolution(List<Integer> result, List<Boolean> solution, String num) {
        result.addAll(splitNums(solution, num));
    }

    private static boolean isASolution(List<Boolean> solution, String num) {
        return solution.size() == num.length();
    }

    private static boolean isValid(List<Boolean> solution, String num, int flag) {
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
            if (flag == 1) {
                String targetStr = num.substring(0, solution.size()+1);
                // 不能这样 "02"
                if (targetStr.startsWith("0") && targetStr.length() > 1) {
                    return false;
                }
                // 不能超出integer范围
                if (!isInRange(targetStr)) {
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
                // 不能超出integer范围
                if (!isInRange(targetStr)) {
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
            String targetStr = num.substring(list.get(list.size()-1)+1, solution.size()+1);
            if (targetStr.startsWith("0") && targetStr.length() > 1) {
                return false;
            }
            // 不能超出integer范围
            if (!isInRange(targetStr)) {
                return false;
            }
            List<Integer> numList = splitNums(solution, num);
            Integer target = Integer.valueOf(targetStr);
            Integer pre1 = numList.get(numList.size()-1);
            Integer pre2 = numList.get(numList.size()-2);
            return target == pre1 + pre2;
        }
        return true;
    }
    
    private static void makeMove(List<Boolean> solution, int i) {
        solution.add(i == 1);
    }
    
    private static void unMakeMove(List<Boolean> solution) {
        solution.remove(solution.size()-1);
    }
    
    private static List<Integer> splitNums(List<Boolean> solution, String num) {
        List<Integer> list = new ArrayList<>();
        int preTrueIndex = 0;
        for (int j = 0; j < solution.size(); j++) {
            if (solution.get(j) == true) {
                list.add(Integer.valueOf(num.substring(preTrueIndex, j+1)));
                preTrueIndex = j+1;
            }
        }
        return list;
    }
    
    // 检查是否在integer范围内
    private static boolean isInRange(String target) {
        return new BigDecimal(target).compareTo(new BigDecimal(Integer.MAX_VALUE)) <= 0;
    }

    public static void main(String[] args) {
        System.out.println(solution2("539834657215398346785398346991079669377161950407626991734534318677529701785098211336528511"));
    }
    
}
