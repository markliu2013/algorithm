package com.zfwhub.algorithm.leetcode.number;

import static org.junit.Assert.*;

import org.junit.Test;

public class IntegerToRomanTest {

    @Test
    public void testSolution() {
        int num1 = 3;
        String str1 = "III";
        int num2 = 4;
        String str2 = "IV";
        int num3 = 9;
        String str3 = "IX";
        int num4 = 58;
        String str4 = "LVIII";
        int num5 = 1994;
        String str5 = "MCMXCIV";
        assertEquals(IntegerToRoman.solution(num1), str1);
        assertEquals(IntegerToRoman.solution(num2), str2);
        assertEquals(IntegerToRoman.solution(num3), str3);
        assertEquals(IntegerToRoman.solution(num4), str4);
        assertEquals(IntegerToRoman.solution(num5), str5);
    }

}
