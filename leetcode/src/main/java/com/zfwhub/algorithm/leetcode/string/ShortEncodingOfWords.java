package com.zfwhub.algorithm.leetcode.string;

import java.util.*;

// https://leetcode.com/problems/short-encoding-of-words/description/
public class ShortEncodingOfWords {

    public static int solution1(String[] words) {
        // 先长到短排序，保证后面短的被长的包含了。
        Arrays.sort(words, (a, b)->Integer.compare(b.length(), a.length()));
        Set<String> set = new HashSet<>();
        int result = 0;
        for (String word : words) {
            // 只要set中没有包含，则把所有的子字符串全部添加。
            if (!set.contains(word)) {
                for (int i = 0; i < word.length(); i++) {
                    set.add(word.substring(i));
                }
                result += word.length();
                result++; // 添加 #
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        String[] words = new String[] { "time", "me", "bell" };
        System.out.println(solution1(words));
    }
}
