package com.zfwhub.algorithm.leetcode.string;
/**
 * https://leetcode.com/problems/minimum-window-substring/description/
 */
public class MinimumWindowSubstring {
    
    public static String solution(String s, String t) {
        int minLength = Integer.MAX_VALUE;
        String minSub = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j < s.length()+1; j++) {
                String subStr = s.substring(i, j);
                if (contains(s, subStr)) {
                    if (minLength > j-i) {
                        minSub = subStr;
                    }
                }
            }
        }
        return minSub;
    }
    
    public static boolean contains(String str, String sub) {
        char[] charArr = sub.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            if (str.indexOf(charArr[i]) < 0) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(MinimumWindowSubstring.solution(s, t));
    }
}
