package com.zfwhub.algorithm.leetcode.string;
/**
 * https://leetcode.com/problems/minimum-window-substring/description/
 */
public class MinimumWindowSubstring {
    
    public static String solution(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }
        int minLength = Integer.MAX_VALUE;
        String minSub = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j < s.length()+1; j++) {
                String subStr = s.substring(i, j);
                if (contains(subStr, t)) {
                    if (minLength > j-i) {
                        minSub = subStr;
                        minLength = j-i;
                    }
                }
            }
        }
        return minSub;
    }
    
    public static boolean contains(String str, String sub) {
        if (sub.length() > str.length()) {
            return false;
        }
        char[] charArr = sub.toCharArray();
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < charArr.length; i++) {
            int index = sb.indexOf(String.valueOf(charArr[i]));
            if (index < 0) {
                return false;
            } else {
                sb.deleteCharAt(index);
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        String s = "bbaa";
        String t = "aba";
        System.out.println(MinimumWindowSubstring.solution(s, t));
    }
}
