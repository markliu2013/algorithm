package com.zfwhub.algorithm.leetcode.string;

import java.util.Arrays;

// https://leetcode.com/problems/longest-common-prefix/
public class LongestCommonPrefix {
    
    public static String solution1(String[] strs) {
        // 第一个字符串为空，则肯定结果是空
        if (strs == null || strs.length == 0 || strs[0] == null || strs[0].length() == 0) {
            return "";
        }
        String result = "";
        String firstStr = strs[0];
        // 第一个字符串的每一个字符都跟后面的字符串做比较
        for (int i = 0; i < firstStr.length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                // 尽可能的去匹配，一旦没匹配上则返回。
                if (i >= strs[j].length() || firstStr.charAt(i) != strs[j].charAt(i)) {
                    return result;
                }
            }
            // 所以为i的字符匹配上了。
            result += firstStr.charAt(i);
        }
        return result;
    }
    
    // 动态规划
    public static String solution2(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String lastItem = strs[strs.length-1];
        String[] subStrs = Arrays.copyOfRange(strs, 0, strs.length-1);
        String str = solution2(subStrs);
        // 返回lastItem和str的公共前缀。
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (i >= lastItem.length()) {
                break;
            }
            if (str.charAt(i) == lastItem.charAt(i)) {
                result.append(str.charAt(i));
            } else {
                break;
            }
        }
        return result.toString();
    }
    
    public static void main(String[] args) {
//        String[] strs = new String[] {"aa","a"};
        String[] strs = new String[] {"flower","flow","flight"};
//        System.out.println(solution1(strs));
        System.out.println(solution2(strs));
    }

}
