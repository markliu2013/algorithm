package com.zfwhub.algorithm.leetcode.string;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShiftingLettersTest {

    @Test
    public void testSolution() {
        String S1 = "abc";
        int[] shifts1 = new int[] {3,5,9};
        String result1 = "rpl";
        String S2 = "ruu";
        int[] shifts2 = new int[] {26,9,17};
        String result2 = "rul";
        String S3 = "mkgfzkkuxownxvfvxasy";
        int[] shifts3 = new int[] {505870226,437526072,266740649,224336793,532917782,311122363,567754492,595798950,81520022,684110326,137742843,275267355,856903962,148291585,919054234,467541837,622939912,116899933,983296461,536563513};
        String result3 = "wqqwlcjnkphhsyvrkdod";
        assertEquals(ShiftingLetters.solution(S1, shifts1), result1);
        assertEquals(ShiftingLetters.solution(S2, shifts2), result2);
        assertEquals(ShiftingLetters.solution(S3, shifts3), result3);
    }

}
