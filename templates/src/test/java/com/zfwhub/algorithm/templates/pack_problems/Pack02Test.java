package com.zfwhub.algorithm.templates.pack_problems;


import static org.junit.Assert.*;

import org.junit.Test;

public class Pack02Test {
    
    int[] volumns1 = new int[] {144, 487, 210, 567, 1056};
    int[] values1 = new int[] {990, 436, 673, 58, 897};
    int capacity1 = 1000;
    int expected1 = 5940;

    @Test
    public void testSolution1() {
        assertEquals(expected1, Pack02.solution1(PackUtil.arrayToPackList(volumns1, values1), capacity1));
    }
    
    @Test
    public void testSolution2() {
        assertEquals(expected1, Pack02.solution2(PackUtil.arrayToPackList(volumns1, values1), capacity1));
    }

}
