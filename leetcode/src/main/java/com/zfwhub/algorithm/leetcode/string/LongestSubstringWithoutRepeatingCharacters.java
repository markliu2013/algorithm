package com.zfwhub.algorithm.leetcode.string;

import com.zfwhub.algorithm.utils.StringUtil;

// https://leetcode.com/problems/longest-substring-without-repeating-characters/
public class LongestSubstringWithoutRepeatingCharacters {
    
    // straightforward, brute force, Time Limit Exceeded
    public static int solution1(String s) {
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                if (StringUtil.isAllUnique(s, i, j)) {
                    maxLength = Math.max(maxLength, j-i);
                }
            }
        }
        return maxLength;
    }

    /*
     * check a range by range, range from startIndex to endIndex
     * check if there is a char exists in the range
     * if exists, startIndex should move to the exists index' next.
     */
    public static int solution2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
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
                subStr = subStr.substring(nextIndex + 1) + next;
            } else {
                subStr = subStr + next;
                if (maxLength < subStr.length()) {
                    maxLength = subStr.length();
                }
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(solution1(s));
        System.out.println(solution2(s));
    }

}
