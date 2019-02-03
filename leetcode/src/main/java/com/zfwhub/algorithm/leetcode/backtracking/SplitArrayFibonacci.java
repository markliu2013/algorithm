package com.zfwhub.algorithm.leetcode.backtracking;
import java.util.*;

import com.zfwhub.algorithm.utils.CollectionUtil;

// https://leetcode.com/problems/split-array-into-fibonacci-sequence/
public class SplitArrayFibonacci {
    
    // Memory Limit Exceeded
    public static List<Integer> solution1(String S) {
        // 尝试分割string
        List<List<Integer>> list = CollectionUtil.subsets(CollectionUtil.newIntList(S.length()-1));
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
    
    public static List<Integer> solution2(String S) {
        List<Integer> result = new ArrayList<>();
        List<Boolean> solution = new ArrayList<>();//每个位置是否打点
        dfs(result, solution, S);
        return result;
    }

    private static void dfs(List<Integer> result, List<Boolean> solution, String s) {
        // TODO Auto-generated method stub
        
    }

    public static void main(String[] args) {
        System.out.println(solution2("214748364721474836422147483641"));
    }
    
}
