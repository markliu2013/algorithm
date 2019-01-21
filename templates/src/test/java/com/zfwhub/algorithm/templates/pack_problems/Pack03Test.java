package com.zfwhub.algorithm.templates.pack_problems;


import static org.junit.Assert.*;

import org.junit.Test;

public class Pack03Test {

    int[] volumns1 = new int[] {2, 4};
    int[] values1 = new int[] {100, 100};
    int[] quantities1 = new int[] {4, 2};
    int capacity1 = 8;
    int expected1 = 400;

    @Test
    public void testSolution1() {
        assertEquals(expected1, Pack03.solution1(PackUtil.arrayToPackList(volumns1, values1, quantities1), capacity1));
    }
    
    @Test
    public void testSolution2() {
        assertEquals(expected1, Pack03.solution2(PackUtil.arrayToPackList(volumns1, values1, quantities1), capacity1));
    }

}
