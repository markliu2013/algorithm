package com.zfwhub.algorithm.leetcode.string;

import static org.junit.Assert.*;

import org.junit.Test;

public class ZigZagTest {

    @Test
    public void testConvert() {
        String S1 = "PAYPALISHIRING";
        int numRows1 = 4;
        String expecteds1 = "PINALSIGYAHRPI";
        
        String S2 = "PAYPALISHIRING";
        int numRows2 = 3;
        String expecteds2 = "PAHNAPLSIIGYIR";
        
        String S3 = "A";
        int numRows3 = 1;
        String expecteds3 = "A";
        
        assertEquals(expecteds1, ZigZag.solution1(S1, numRows1));
        assertEquals(expecteds2, ZigZag.solution1(S2, numRows2));
        assertEquals(expecteds3, ZigZag.solution1(S3, numRows3));
        
    }

}
