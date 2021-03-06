package com.zfwhub.algorithm.leetcode.dp;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegularExpressionMatchingTest {
    
    @Test
    public void testSolution() {
        String s1 = "aa";
        String p1 = "a";
        boolean expected1 = false;
        
        String s2 = "aa";
        String p2 = "a*";
        boolean expected2 = true;
        
        String s3 = "ab";
        String p3 = ".*";
        boolean expected3 = true;
        
        String s4 = "aab";
        String p4 = "c*a*b";
        boolean expected4 = true;
        
        String s5 = "mississippi";
        String p5 = "mis*is*p*.";
        boolean expected5 = false;
        
        String s6 = "a";
        String p6 = "";
        boolean expected6 = false;
        
        String s7 = "a";
        String p7 = "aa";
        boolean expected7 = false;
        
        String s8 = "a";
        String p8 = ".*..a*";
        boolean expected8 = false;
        
        String s9 = "";
        String p9 = "";
        boolean expected9 = true;
        
        String s10 = "a";
        String p10 = "a.*";
        boolean expected10 = true;
        
        String s11 = "";
        String p11 = "a*a*";
        boolean expected11 = true;

        String s12 = "mississippi";
        String p12 = "mis*is*ip*.";
        boolean expected12 = true;
        
        assertEquals(expected1, RegularExpressionMatching.solution1(s1, p1));
        assertEquals(expected2, RegularExpressionMatching.solution1(s2, p2));
        assertEquals(expected3, RegularExpressionMatching.solution1(s3, p3));
        assertEquals(expected4, RegularExpressionMatching.solution1(s4, p4));
        assertEquals(expected5, RegularExpressionMatching.solution1(s5, p5));
        assertEquals(expected6, RegularExpressionMatching.solution1(s6, p6));
        assertEquals(expected7, RegularExpressionMatching.solution1(s7, p7));
        assertEquals(expected8, RegularExpressionMatching.solution1(s8, p8));
        assertEquals(expected9, RegularExpressionMatching.solution1(s9, p9));
        assertEquals(expected10, RegularExpressionMatching.solution1(s10, p10));
        assertEquals(expected11, RegularExpressionMatching.solution1(s11, p11));
        
        assertEquals(expected1, RegularExpressionMatching.solution2(s1, p1));
        assertEquals(expected2, RegularExpressionMatching.solution2(s2, p2));
        assertEquals(expected3, RegularExpressionMatching.solution2(s3, p3));
        assertEquals(expected4, RegularExpressionMatching.solution2(s4, p4));
        assertEquals(expected5, RegularExpressionMatching.solution2(s5, p5));
        assertEquals(expected6, RegularExpressionMatching.solution2(s6, p6));
        assertEquals(expected7, RegularExpressionMatching.solution2(s7, p7));
        assertEquals(expected8, RegularExpressionMatching.solution2(s8, p8));
        assertEquals(expected9, RegularExpressionMatching.solution2(s9, p9));
        assertEquals(expected10, RegularExpressionMatching.solution2(s10, p10));
        assertEquals(expected11, RegularExpressionMatching.solution2(s11, p11));
        assertEquals(expected12, RegularExpressionMatching.solution2(s12, p12));
    }

}
