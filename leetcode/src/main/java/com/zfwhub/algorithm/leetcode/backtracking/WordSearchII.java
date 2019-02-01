package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

public class WordSearchII {
    
    // TODO WordSearchII Time Limit Exceeded
    public static List<String> findWords(char[][] board, String[] words) {
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
    
    public static void main(String[] args) {
        char[][] board1 = { 
                { 'o', 'a' , 'a' , 'n' }, 
                { 'e', 't' , 'a' , 'e' }, 
                { 'i', 'h' , 'k' , 'r' },
                { 'i', 'f' , 'l' , 'v' }
        };
        String[] words1 = new String[] {"oath","pea","eat","rain"};
        System.out.println(findWords(board1, words1));
    }

}
