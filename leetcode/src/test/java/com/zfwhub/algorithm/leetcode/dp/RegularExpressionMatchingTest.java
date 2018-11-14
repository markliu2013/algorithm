package com.zfwhub.algorithm.leetcode.dp;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegularExpressionMatchingTest {

    @Test
    public void testSolution() {
        String s1 = "aa";
        String p1 = "a";
        boolean result1 = false;
        String s2 = "aa";
        String p2 = "a*";
        boolean result2 = true;
        String s3 = "ab";
        String p3 = ".*";
        boolean result3 = true;
        String s4 = "aab";
        String p4 = "c*a*b";
        boolean result4 = true;
        String s5 = "mississippi";
        String p5 = "mis*is*p*.";
        boolean result5 = false;
        assertEquals(result1, RegularExpressionMatching.solution(s1, p1));
        assertEquals(result2, RegularExpressionMatching.solution(s2, p2));
        assertEquals(result3, RegularExpressionMatching.solution(s3, p3));
        assertEquals(result4, RegularExpressionMatching.solution(s4, p4));
        assertEquals(result5, RegularExpressionMatching.solution(s5, p5));
    }

}
