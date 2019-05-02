package com.zfwhub.algorithm.leetcode.dp;

import static org.junit.Assert.*;

import org.junit.Test;

public class UncrossedLinesTest {

    @Test
    public void testSolution3() {
        int[] A1 = new int[] {2,1};
        int[] B1 = new int[] {1,2,1,3};
        int expected1 = 2;
        
        int[] A2 = new int[] {2,5,1,2,5};
        int[] B2 = new int[] {10,5,2,1,5,2};
        int expected2 = 3;
        
        int[] A3 = new int[] {1,4,2};
        int[] B3 = new int[] {1,2,4};
        int expected3 = 2;
        
        int[] A4 = new int[] {1,3,7,1,7,5};
        int[] B4 = new int[] {1,9,2,5,1};
        int expected4 = 2;
        
        assertEquals(expected1, UncrossedLines.solution3(A1, B1));
        assertEquals(expected2, UncrossedLines.solution3(A2, B2));
        assertEquals(expected3, UncrossedLines.solution3(A3, B3));
        assertEquals(expected4, UncrossedLines.solution3(A4, B4));
    }

}
