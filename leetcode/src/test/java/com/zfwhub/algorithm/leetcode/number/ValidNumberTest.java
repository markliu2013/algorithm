package com.zfwhub.algorithm.leetcode.number;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidNumberTest {

    @Test
    public void testSolution() {
        
        String s1 = "0";
        boolean expected1 = true;
        
        String s2 = " 0.1 ";
        boolean expected2 = true;
        
        String s3 = "abc";
        boolean expected3 = false;
        
        String s4 = "1 a";
        boolean expected4 = false;
        
        String s5 = "2e10";
        boolean expected5 = true;
        
        String s6 = " -90e3   ";
        boolean expected6 = true;
        
        String s7 = " 1e";
        boolean expected7 = false;
        
        String s8 = "e3";
        boolean expected8 = false;
        
        String s9 = " 6e-1";
        boolean expected9 = true;

        String s10 = " 99e2.5 ";
        boolean expected10 = false;

        String s11 = "53.5e93";
        boolean expected11 = true;

        String s12 = " --6 ";
        boolean expected12 = false;

        String s13 = "-+3";
        boolean expected13 = false;

        String s14 = "95a54e53";
        boolean expected14 = false;

        String s15 = "008";
        boolean expected15 = true;

        String s16 = "0.";
        boolean expected16 = true;
        
        String s17 = "078332e437";
        boolean expected17 = true;
        
        String s18 = "438.4e0197";
        boolean expected18 = true;
        
        String s19 = "959440.94f";
        boolean expected19 = false;
        
        String s20 = "+0619";
        boolean expected20 = true;
        
        assertEquals(expected1, ValidNumber.solution1(s1));
        assertEquals(expected2, ValidNumber.solution1(s2));
        assertEquals(expected3, ValidNumber.solution1(s3));
        assertEquals(expected4, ValidNumber.solution1(s4));
        assertEquals(expected5, ValidNumber.solution1(s5));
        assertEquals(expected6, ValidNumber.solution1(s6));
        assertEquals(expected7, ValidNumber.solution1(s7));
        assertEquals(expected8, ValidNumber.solution1(s8));
        assertEquals(expected9, ValidNumber.solution1(s9));
        assertEquals(expected10, ValidNumber.solution1(s10));
        assertEquals(expected11, ValidNumber.solution1(s11));
        assertEquals(expected12, ValidNumber.solution1(s12));
        assertEquals(expected13, ValidNumber.solution1(s13));
        assertEquals(expected14, ValidNumber.solution1(s14));
        assertEquals(expected15, ValidNumber.solution1(s15));
        assertEquals(expected16, ValidNumber.solution1(s16));
        assertEquals(expected17, ValidNumber.solution1(s17));
        assertEquals(expected18, ValidNumber.solution1(s18));
        assertEquals(expected19, ValidNumber.solution1(s19));
        assertEquals(expected20, ValidNumber.solution1(s20));
        
    }

}
