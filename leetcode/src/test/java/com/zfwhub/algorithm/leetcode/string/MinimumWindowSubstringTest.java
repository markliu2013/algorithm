package com.zfwhub.algorithm.leetcode.string;

import static org.junit.Assert.*;

import org.junit.Test;

public class MinimumWindowSubstringTest {

    @Test
    public void testSolution() {
        String s1 = "ADOBECODEBANC";
        String t1 = "ABC";
        String result1 = "BANC";
        String s2 = "a";
        String t2 = "aa";
        String result2 = "";
        String s3 = "ab";
        String t3 = "a";
        String result3 = "a";
        String s4 = "bbaa";
        String t4 = "aba";
        String result4 = "baa";
        assertEquals(MinimumWindowSubstring.solution(s1, t1), result1);
        assertEquals(MinimumWindowSubstring.solution(s2, t2), result2);
        assertEquals(MinimumWindowSubstring.solution(s3, t3), result3);
        assertEquals(MinimumWindowSubstring.solution(s4, t4), result4);
    }

}
