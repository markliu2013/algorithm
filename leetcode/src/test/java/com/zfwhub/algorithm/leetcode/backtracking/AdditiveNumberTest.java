package com.zfwhub.algorithm.leetcode.backtracking;

import static org.junit.Assert.*;

import org.junit.Test;

public class AdditiveNumberTest {
    
    @Test
    public void testIsAdditiveNumber() {
        String num1 = "000";
        boolean expected1 = true;
        String num2 = "0235813";
        boolean expected2 = false;
        String num3 = "0";
        boolean expected3 = false;
        String num4 = "1203";
        boolean expected4 = false;
        String num5 = "221474836472147483649";
        boolean expected5 = true;
        String num6 = "199001200";
        boolean expected6 = false;
        String num7 = "120122436";
        boolean expected7 = false;
        assertEquals(expected1, AdditiveNumber.solution1(num1));
        assertEquals(expected2, AdditiveNumber.solution1(num2));
        assertEquals(expected3, AdditiveNumber.solution1(num3));
        assertEquals(expected4, AdditiveNumber.solution1(num4));
        assertEquals(expected5, AdditiveNumber.solution1(num5));
        assertEquals(expected6, AdditiveNumber.solution1(num6));
        assertEquals(expected7, AdditiveNumber.solution1(num7));
    }

}
