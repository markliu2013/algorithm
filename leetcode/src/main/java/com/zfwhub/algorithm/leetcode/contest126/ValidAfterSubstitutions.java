package com.zfwhub.algorithm.leetcode.contest126;

import java.util.LinkedList;

public class ValidAfterSubstitutions {
    
    public static boolean solution1(String S) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == 'a') {
                stack.push('a');
            } else if (S.charAt(i) == 'b') {
                if (stack.peek() == null || stack.peek() != 'a') {
                    return false;
                } else {
                    stack.push('b');
                }
            } else if (S.charAt(i) == 'c') {
                if (stack.peek() == null || stack.peek() != 'b') {
                    return false;
                } else {
                    stack.pop();
                    stack.pop();
                }
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }
    
    public static void main(String[] args) {
        String S = "abcabcababcc";
        System.out.println(solution1(S));
    }
    
}
