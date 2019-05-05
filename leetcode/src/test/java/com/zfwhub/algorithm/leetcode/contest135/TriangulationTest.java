package com.zfwhub.algorithm.leetcode.contest135;

import static org.junit.Assert.*;

import org.junit.Test;

public class TriangulationTest {

    @Test
    public void testSolution() {
        
        int[] A1 = new int[] {1,2,3};
        int expected1 = 6;
        
        int[] A2 = new int[] {3,7,4,5};
        int expected2 = 144;
        
        int[] A3 = new int[] {1,3,1,4,1,5};
        int expected3 = 13;
        
        int[] A4 = new int[] {3,1,4,5,4};
        int expected4 = 52;

        int[] A5 = new int[] {3,4,2,2,4,2};
        int expected5 = 60;
        
        assertEquals(expected1, Triangulation.solution1(A1));
        assertEquals(expected2, Triangulation.solution1(A2));
        assertEquals(expected3, Triangulation.solution1(A3));
        assertEquals(expected4, Triangulation.solution1(A4));
        assertEquals(expected5, Triangulation.solution1(A5));
    }

}
