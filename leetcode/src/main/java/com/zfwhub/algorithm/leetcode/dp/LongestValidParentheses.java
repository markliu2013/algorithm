package com.zfwhub.algorithm.leetcode.dp;

import com.zfwhub.algorithm.leetcode.stack.ValidParentheses;
import com.zfwhub.algorithm.utils.ArrayUtil;

// https://leetcode.com/problems/longest-valid-parentheses/
public class LongestValidParentheses {
    
    public static int solution1(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int[] dp = new int[s.length()]; // 以k结尾的最大valid串
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                    String subStr = s.substring(j, i+1);
                    if (ValidParentheses.solution2(subStr)) {
                        dp[i] = subStr.length();
                        break;
                }
            }
        }
        return ArrayUtil.max(dp);
    }
    
    // TODO LongestValidParentheses 
    
    public static void main(String[] args) {
        System.out.println(solution1(")()())"));
    }

}
