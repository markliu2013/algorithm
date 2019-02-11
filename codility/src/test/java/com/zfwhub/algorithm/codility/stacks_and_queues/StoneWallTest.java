package com.zfwhub.algorithm.codility.stacks_and_queues;

import static org.junit.Assert.*;

import org.junit.Test;

public class StoneWallTest {

    @Test
    public void testSolution() {
        int[] H1 = new int[] { 1,1 };
        int expected1 = 1;
        int[] H2 = new int[] { 1,2,1 };
        int expected2 = 2;
        int[] H9 = new int[] { 8,8,5,7,9,8,7,4,8 };
        int expected9 = 7;
        assertEquals(expected1, StoneWall.solution1(H1));
        assertEquals(expected2, StoneWall.solution1(H2));
        assertEquals(expected9, StoneWall.solution1(H9));
        assertEquals(expected1, StoneWall.solution2(H1));
        assertEquals(expected2, StoneWall.solution2(H2));
        assertEquals(expected9, StoneWall.solution2(H9));
    }

}
