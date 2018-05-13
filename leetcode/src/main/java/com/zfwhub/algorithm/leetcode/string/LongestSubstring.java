package com.zfwhub.algorithm.leetcode.string;

import java.util.HashSet;

/**
 * 找最长没有重复的子字符串
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 */
public class LongestSubstring {

    /**
     * straightforward, brute force
     */
    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j <= s.length(); j++) {
                String subStr = s.substring(i, j);
                if (isAllUnique(subStr)) {
                    maxLength = Math.max(maxLength, subStr.length());
                }
            }
        }
        return maxLength;
    }

    /**
     * 区间检查, 定义startIndex和endIndex的区间
     * 判断下一个元素是否与区间类的有重复
     * 有重复则区间变为从重复的索引下一个开始
     */
    public static int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return  0;
        }
        int startIndex = 0;
        int endIndex = 0;
        String subStr = s.substring(startIndex, endIndex + 1);
        int maxLength = subStr.length();
        while (endIndex != s.length() - 1) {
            endIndex++;
            char next = s.charAt(endIndex);
            int nextIndex = subStr.indexOf(next);
            if (nextIndex >= 0) {
                subStr = subStr.substring(nextIndex+1) + next;
            } else {
                subStr = subStr + next;
                if (maxLength < subStr.length()) {
                    maxLength = subStr.length();
                }
            }
        }
        return maxLength;
    }

    private static boolean isAllUnique(String s) {
        HashSet<Character> set = new HashSet<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                return false;
            } else {
                set.add(s.charAt(i));
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "bpfbhmipx";
        System.out.println(LongestSubstring.lengthOfLongestSubstring2(s));
    }


}
