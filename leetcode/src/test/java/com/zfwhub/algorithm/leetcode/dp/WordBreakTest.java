package com.zfwhub.algorithm.leetcode.dp;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class WordBreakTest {

    @Test
    public void test() {
        String s1 = "leetcode";
        List<String> wordDict1 = new ArrayList<>();
        wordDict1.add("leet");wordDict1.add("code");
        boolean expected1 = true;
        
        String s2 = "applepenapple";
        List<String> wordDict2 = new ArrayList<>();
        wordDict2.add("apple");wordDict2.add("pen");
        boolean expected2 = true;
        
        String s3 = "catsandog";
        List<String> wordDict3 = new ArrayList<>();
        wordDict3.add("cats");wordDict3.add("dog");wordDict3.add("sand");wordDict3.add("and");
        boolean expected3 = false;
        
        String s4 = "aaaaaaa";
        List<String> wordDict4 = new ArrayList<>();
        wordDict4.add("aaaa");wordDict4.add("aaa");
        boolean expected4 = true;

        String s5 = "cars";
        List<String> wordDict5 = new ArrayList<>();
        wordDict5.add("car");wordDict5.add("ca");wordDict5.add("rs");
        boolean expected5 = true;

        String s6 = "dogs";
        List<String> wordDict6 = new ArrayList<>();
        wordDict6.add("dog");wordDict6.add("s");wordDict6.add("gs");
        boolean expected6 = true;

        String s7 = "ccbb";
        List<String> wordDict7 = new ArrayList<>();
        wordDict7.add("bc");wordDict7.add("cb");
        boolean expected7 = false;
        
        assertEquals(expected1, WordBreak.solution1(s1, wordDict1));
        assertEquals(expected2, WordBreak.solution1(s2, wordDict2));
        assertEquals(expected3, WordBreak.solution1(s3, wordDict3));
        assertEquals(expected4, WordBreak.solution1(s4, wordDict4));
        assertEquals(expected5, WordBreak.solution1(s5, wordDict5));
        assertEquals(expected6, WordBreak.solution1(s6, wordDict6));
        assertEquals(expected7, WordBreak.solution1(s7, wordDict7));
    }

}
