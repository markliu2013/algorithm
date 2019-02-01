package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

// https://leetcode.com/problems/restore-ip-addresses/
public class RestoreIpAddresses {

    public static List<String> solution1(String s) {
        List<String> solutionList = new ArrayList<>();//存储s打上正确的点的string
        if (s.length() > 12) {//大于12，那么有一个肯定超过三位数，所以绝对无解。
            return solutionList;
        }
        // 在s的每个位置都可以选择打点，solution记录每个点都在第几个位置。
        // 不包括开头和结尾处，总共的位置数为s的长度-1
        List<Integer> solution = new ArrayList<>();
        dfs(solutionList, solution, s);
        return solutionList;
    }
    
    private static void dfs(List<String> solutionList, List<Integer> solution, String s) {
        if (isASolution(solution, s)) {
            processSolution(solutionList, solution, s);
        } else {
            // 每一个点都一个位置一个位置的尝试，不包含开头和起始位置。
            for (int i = 1; i < s.length(); i++) {
                if (isValid(solution, i, s)) {
                    makeMove(solution, i);
                    dfs(solutionList, solution, s);
                    unMakeMove(solution);
                }
            }
        }
    }
    
    private static boolean isASolution(List<Integer> solution, String s) {
        // 3个点都打在了正确的位置, 需要判断分成的ip位是否合法。
        if (solution.size() == 3) {
            if (validateIPBit(s.substring(0, solution.get(0))) &&
                validateIPBit(s.substring(solution.get(0), solution.get(1))) &&
                validateIPBit(s.substring(solution.get(1), solution.get(2))) &&
                validateIPBit(s.substring(solution.get(2)))
                ) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    private static void processSolution(List<String> solutionList, List<Integer> solution, String s) {
        StringBuilder sb = new StringBuilder(s);
        //solution逆序循环，则可以在s中按索引从后面开始加点，不影响前面的索引。
        for (int i = solution.size()-1; i >= 0; i--) {
            sb.insert(solution.get(i), ".");
        }
        solutionList.add(sb.toString());
    }
    
    // 验证，将字符串分割成4个部分。i是下一个点的位置。
    private static boolean isValid(List<Integer> solution, int i, String s) {
        // 类似 BacktrackingTemplate1.isValid
        if (solution.size() == 0) {
            return true;
        }
        return solution.get(solution.size()-1) < i;
        
        // 两个点不能同一个位置。（如果下一个位置小于前一个，则可省略）
        /*if (solution.contains(i)) {
            return false;
        }*/
        
        // 下面的判断逻辑都移到了isASolution。
        
        // 下一个点的位置和solution中最后一个点的位置，隔出的必须符合ip位
        // 如果solution当前为空，则判断s的结尾处。
        
        /*int prePos = solution.size() > 0 ? solution.get(solution.size()-1) : s.length();
        if (!validateIPBit(s.substring(i, prePos))) {
            return false; 
        }*/
        
        /*for (int j = 0; j < solution.size(); j++) {
            String str = "";
            if (j > 0) {
                str = s.substring(solution.get(j), solution.get(j-1));
            } else {
                str = s.substring(solution.get(j));
            }
            if (!validateIPBit(str)) {
                return false;
            }
        }
        if (solution.size() > 0) {
            String str = s.substring(i, solution.get(solution.size()-1));
            if (!validateIPBit(str)) {
                return false;
            }
        }
        if (solution.size() == 2) {
            String str = s.substring(0, i);
            if (!validateIPBit(str)) {
                return false;
            }
        }*/
    }
    
    private static void unMakeMove(List<Integer> solution) {
        solution.remove(solution.size()-1);
    }

    private static void makeMove(List<Integer> solution, int i) {
        solution.add(i);
    }
    
    // 判断单个的ip位，单个的ip位必须小于255
    private static boolean validateIPBit(String str) {
        if (str.length() > 3) {
            return false;
        }
        // 0开头是不合法的
        if (str.startsWith("0") && str.length() > 1) {
            return false;
        }
        if (Integer.parseInt(str) > 255) {
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println(solution1("123456"));
    }

}
