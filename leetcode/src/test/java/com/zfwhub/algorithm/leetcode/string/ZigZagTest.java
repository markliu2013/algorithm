package com.zfwhub.algorithm.leetcode.string;

import static org.junit.Assert.*;

import org.junit.Test;

public class ZigZagTest {

    @Test
    public void testConvert() {
        String S1 = "PAYPALISHIRING";
        int numRows1 = 4;
        String result1 = "PINALSIGYAHRPI";
        String S2 = "PAYPALISHIRING";
        int numRows2 = 3;
        String result2 = "PAHNAPLSIIGYIR";
        String S3 = "A";
        int numRows3 = 1;
        String result3 = "A";
        assertEquals(ZigZag.convert(S1, numRows1), result1);
        assertEquals(ZigZag.convert(S2, numRows2), result2);
        assertEquals(ZigZag.convert(S3, numRows3), result3);
    }

}
