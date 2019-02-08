package com.zfwhub.algorithm.leetcode.string;

import java.util.HashSet;

/*
 * Given a string, find the length of the longest substring without repeating characters.
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 */
public class LongestSubstringWithoutRepeatingCharacters {

    // straightforward, brute force
    public static int solution1(String s) {
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String subStr = s.substring(i, j);
                if (isAllUnique(subStr)) {
                    maxLength = Math.max(maxLength, subStr.length());
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
        System.out.println(LongestSubstringWithoutRepeatingCharacters.solution2(s));
    }

}
