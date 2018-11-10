package com.zfwhub.algorithm.leetcode.backtracking;
import java.util.*;

// https://leetcode.com/problems/restore-ip-addresses/
public class RestoreIpAddresses {
    
    public static List<String> restoreIpAddresses(String s) {
        List<String> solutionList = new ArrayList<>();
        if (s.length() > 12) {
            return solutionList;
        }
        List<Integer> solution = new ArrayList<>();//每一个点都在第几个
        dfs(solutionList, solution, s);
        return solutionList;
    }
    
    public static void dfs(List<String> solutionList, List<Integer> solution, String s) {
        if (isASolution(solution)) {
            processSolution(solutionList, solution, s);
        } else {
            for (int i = 0; i <= s.length(); i++) {//每一个点都一个一个位置的尝试
                if (isValid(solution, i, s)) {
                    makeMove(solution, i);
                    dfs(solutionList, solution, s);
                    unMakeMove(solution);
                }
            }
        }
    }
    public static boolean isASolution(List<Integer> solution) {
        return solution.size() == 3;
    }
    public static void processSolution(List<String> solutionList, List<Integer> solution, String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < solution.size(); i++) {
            sb.insert(solution.get(i), ".");
        }
        solutionList.add(sb.toString());
    }
    public static boolean isValid(List<Integer> solution, int i, String s) {
        // 验证，将字符串分割成4个部分
        if (solution.contains(i)) {//两个点不能同一个位置
            return false;
        }
        if (i == 0) {//不能在起始位置
            return false;
        }
        if (i == s.length()) {// 不能在末尾
            return false;
        }
        // 是组合数，不是排列，去重的思想
        if (solution.size() > 0 && i >= solution.get(solution.size()-1)) {
            return false;
        }
        for (int j = 0; j < solution.size(); j++) {
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
        }
        return true;
    }
    public static void unMakeMove(List<Integer> solution) {
        solution.remove(solution.size()-1);
    }

    public static void makeMove(List<Integer> solution, int i) {
        solution.add(i);
    }
    
    public static boolean validateIPBit(String str) {
        if (str.length() > 3) {
            return false;
        }
        if (str.startsWith("0") && str.length() > 1) {
            return false;
        }
        if (Integer.parseInt(str) > 255) {
            return false;
        }
        return true;
    }
    
    // https://leetcode.com/problems/restore-ip-addresses/discuss/30949/My-code-in-Java
    public static List<String> restoreIpAddresses2(String s) {
        List<String> res = new ArrayList<String>();
        int len = s.length();
        for(int i = 0; i<4 && i<len-2; i++){
            for(int j = i+1; j<i+4 && j<len-1; j++){
                for(int k = j+1; k<j+4 && k<len; k++){
                    String s1 = s.substring(0,i), s2 = s.substring(i,j), s3 = s.substring(j,k), s4 = s.substring(k,len);
                    if(isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4)){
                        res.add(s1+"."+s2+"."+s3+"."+s4);
                   }
                }
            }
        }
        return res;
    }
    public static boolean isValid(String s){
        if(s.length()>3 || s.length()==0 || (s.charAt(0)=='0' && s.length()>1) || Integer.parseInt(s)>255)
            return false;
        return true;
    }
    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("121232121"));
        System.out.println(Integer.parseInt("010"));
    }
    
    
}
