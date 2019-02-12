package com.zfwhub.algorithm.codility.stacks_and_queues;

import java.util.*;

// https://app.codility.com/programmers/lessons/7-stacks_and_queues/brackets/
public class Brackets {

    public static boolean solution1(String s) {
        char[] stack = new char[s.length()];
        int head = 0;
        for (char c : s.toCharArray()) {
            switch (c) {
                case '{':
                case '[':
                case '(':
                    stack[head++] = c;
                    break;
                case '}':
                    if (head == 0 || stack[--head] != '{')
                        return false;
                    break;
                case ')':
                    if (head == 0 || stack[--head] != '(')
                        return false;
                    break;
                case ']':
                    if (head == 0 || stack[--head] != '[')
                        return false;
                    break;
            }
        }
        return head == 0;

    }

    public static boolean solution2(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }

    public static boolean solution3(String s) {
        char[] chars = s.toCharArray();
        LinkedList<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < chars.length; i++) {
            if (stack.size() == 0) {
                stack.push(chars[i]);
                continue;
            }
            char c2 = stack.peek();
            if (match(c2, chars[i])) {
                stack.pop();
            } else {
                stack.push(chars[i]);
            }
        }
        return stack.size() == 0;
    }

    private static boolean match(char c1, char c2) {
        if ((c1 == '(' && c2 == ')') || (c1 == '[' && c2 == ']') || c1 == '{' && c2 == '}') {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(solution3("()[{}"));
    }
}
