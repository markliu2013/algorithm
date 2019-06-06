package com.zfwhub.algorithm.leetcode.dp;

// https://leetcode.com/problems/palindromic-substrings/
public class PalindromicSubstrings {
    
    public static int solution1(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (check(s.substring(i, j+1))) {
                    count++;
                }
            }
        }
        return count;
    }
    
    private static boolean check(String s) {
        for (int i = 0; i < (s.length())/2; i++) {
            if (s.charAt(i) != s.charAt(s.length()-1-i)) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println(solution1("aaa"));
    }

}
