package com.zfwhub.algorithm.leetcode.string;
/**
 * https://leetcode.com/problems/longest-common-prefix/description/
 */
public class LongestCommonPrefix {
    
    public static String solution(String[] strs) {
        if (strs == null || strs.length == 0 || strs[0] == null || strs[0].length() == 0) {
            return "";
        }
        String result = "";
        String firstStr = strs[0];
        for (int i = 0; i < firstStr.length(); i++) {
            boolean flag = true;
            for (int j = 1; j < strs.length; j++) {
                try {
                    if (firstStr.charAt(i) == strs[j].charAt(i)) {
                    } else {
                        return result;
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    return result;
                }
            }
            if (flag) {
                result += firstStr.charAt(i);
            }
        }
        return result;
    }
    
    //https://www.geeksforgeeks.org/longest-common-prefix-using-binary-search/
    // TODO LongestCommonPrefix
    public static String solution2(String[] strs) {
        return "";
    }
    
    public static void main(String[] args) {
        String[] strs = new String[] {"aa","a"};
        System.out.println(LongestCommonPrefix.solution(strs));
    }

}
