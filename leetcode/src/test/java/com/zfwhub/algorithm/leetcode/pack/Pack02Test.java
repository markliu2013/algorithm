package com.zfwhub.algorithm.leetcode.pack;

import static org.junit.Assert.*;

import org.junit.Test;

public class Pack02Test {
    
    int[] volumns1 = new int[] {144, 487, 210, 567, 1056};
    int[] values1 = new int[] {990, 436, 673, 58, 897};
    int capacity1 = 1000;
    int expected1 = 5940;

    @Test
    public void testSolution1() {
        assertEquals(Pack02.solution1(volumns1, values1, capacity1), expected1);
    }

}