package com.zfwhub.algorithm.leetcode.hashtable;

import java.util.*;

import com.zfwhub.algorithm.utils.ArrayUtil;
import com.zfwhub.algorithm.utils.CollectionUtil;
import com.zfwhub.algorithm.utils.StringUtil;

// https://leetcode.com/problems/substring-with-concatenation-of-all-words/
public class SubstringConcatenationWords {

    // https://leetcode.com/submissions/detail/228284223/
    // 将words所有排列情况列出来，然后去s中搜索。非常暴力。
    public static List<Integer> solution1(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        List<String> wordList = ArrayUtil.toList(words);
        List<List<String>> wordCombinations = CollectionUtil.permutate(wordList);
        Set<List<String>> wordCombinationsSet = new HashSet<>(wordCombinations);
        for (List<String> list : wordCombinationsSet) {
            StringBuffer sb = new StringBuffer();
            for (String string : list) {
                sb.append(string);
            }
            if (sb.length() != 0) {
                List<Integer> indices = StringUtil.findAllIndices(s, sb);
                result.addAll(indices);
            }
        }
        return result;
    }
    
    // TODO SubstringConcatenationWords
    
    public static void main(String[] args) {
        String s2 = "";
        String[] words2 = new String[] {};
        System.out.println(solution1(s2, words2));
    }

}
