package com.zfwhub.algorithm.leetcode.dp;

// https://leetcode.com/problems/longest-palindromic-substring/
public class LongestPalindromicSubstring {
    
    // https://leetcode.com/submissions/detail/229887911/
    public static String solution1(String s) {
        if (s.length() == 0) {
            return "";
        }
        String[] dp = new String[s.length()]; // 以k结尾的最大回文串
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    String subStr = s.substring(j, i+1);
                    if (isPalindromic(subStr)) {
                        dp[i] = subStr;
                        break;
                    }
                }
            }
        }
        int maxLength = dp[0].length();
        String maxStr = dp[0];
        for (int i = 1; i < dp.length; i++) {
            if (maxLength < dp[i].length()) {
                maxLength = dp[i].length();
                maxStr = dp[i];
            }
        }
        return maxStr;
    }
    
    private static boolean isPalindromic(String s) {
        int mid = s.length() / 2;
        for (int i = 0; i < mid; i++) {
            if (s.charAt(i) != s.charAt(s.length()-1-i)) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println(solution1("cbbd"));
    }
    
}
