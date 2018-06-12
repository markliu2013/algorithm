package com.zfwhub.algorithm.acm.array;

import static org.junit.Assert.*;

import org.junit.Test;

public class MakingTheGradeTest {

    @Test
    public void testMakeGrade() {
        int[] roads1 = new int[] { 1, 2, 3 };
        int result1 = 0;
        int[] roads2 = new int[] { 95, 96, 97, 2 };
        int result2 = 2;
        int[] roads3 = new int[] { 95, 96, 97, 2, 1000 };
        int result3 = 95;
        int[] roads4 = new int[] { 95, 94, 94, 95, 95, 95, 95, 95, 97, 97, 97, 95, 97, 98, 99, 95, 95, 95, 95, 95 };
        int result4 = 16;
        assertEquals(result1, MakingTheGrade.solution(roads1));
        assertEquals(result2, MakingTheGrade.solution(roads2));
        assertEquals(result3, MakingTheGrade.solution(roads3));
        assertEquals(result4, MakingTheGrade.solution(roads4));
    }

}
