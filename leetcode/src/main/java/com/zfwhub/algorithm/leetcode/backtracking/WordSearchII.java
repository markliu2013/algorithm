package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

// https://leetcode.com/problems/word-search-ii/
public class WordSearchII {
    
    public static List<String> solution1(char[][] board, String[] words) {
        List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (String word : words) {
            set.add(word);
        }
        for (String word : set) {
            if (WordSearch.solution2(board, word)) {
                list.add(word);
            }
        }
        return list;
    }
    
    // TODO WordSearchII
    public static List<String> solution2(char[][] board, String[] words) {
        Set<String> solution = new HashSet<>();
        
        return new ArrayList<>(solution);
    }
    
    public static void main(String[] args) {
        char[][] board1 = { 
                { 'o', 'a' , 'a' , 'n' }, 
                { 'e', 't' , 'a' , 'e' }, 
                { 'i', 'h' , 'k' , 'r' },
                { 'i', 'f' , 'l' , 'v' }
        };
        String[] words1 = new String[] {"oath","pea","eat","rain"};
        System.out.println(solution1(board1, words1));
    }

}
