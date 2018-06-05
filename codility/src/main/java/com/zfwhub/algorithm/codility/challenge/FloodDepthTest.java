package com.zfwhub.algorithm.codility.challenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class FloodDepthTest {

    @Test
    public void testSolution() {
        int[] A1 = new int[] {1,3,2,1,2,1,5,3,3,4,2};
        int result1 = 2;
        int[] A2 = new int[] {5,8};
        int result2 = 0;
        assertEquals(FloodDepth.solution(A1), result1);
        assertEquals(FloodDepth.solution(A2), result2);
    }

}
