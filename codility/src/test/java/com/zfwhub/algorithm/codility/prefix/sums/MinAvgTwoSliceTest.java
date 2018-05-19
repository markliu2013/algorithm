package com.zfwhub.algorithm.codility.prefix.sums;

import static org.junit.Assert.*;

import org.junit.*;

import com.zfwhub.algorithm.codility.prefix_sums.MinAvgTwoSlice;

public class MinAvgTwoSliceTest {

    private static MinAvgTwoSlice minAvgTwoSlice;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        minAvgTwoSlice = new MinAvgTwoSlice();
    }

    @Test
    public void testSolution() {
        int result = minAvgTwoSlice.solution(new int[] {4,2,2,5,1,5,8});
        assertEquals(1, result);
    }
    
    @Test
    public void testSolution2() {
        int result = minAvgTwoSlice.solution2(new int[] {4,2,2,5,1,5,8});
        assertEquals(1, result);
    }
    
    @Test
    public void testSolution3() {
        int result = minAvgTwoSlice.solution3(new int[] {4,2,2,5,1,5,8});
        assertEquals(1, result);
    }

}
