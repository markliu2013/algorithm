package com.zfwhub.algorithm.leetcode.dp;

import static org.junit.Assert.*;

import org.junit.Test;

public class InterleavingStringTest {

    @Test
    public void test() {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        boolean expected1 = true;
        String s12 = "aabcc";
        String s22= "dbbca";
        String s32 = "aadbbbaccc";
        boolean expected2 = false;
        assertEquals(expected1, InterleavingString.solution1(s1, s2, s3));
        assertEquals(expected2, InterleavingString.solution1(s12, s22, s32));
    }

}
