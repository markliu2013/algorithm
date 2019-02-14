package com.zfwhub.algorithm.leetcode.backtracking;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.zfwhub.algorithm.utils.ArrayUtil;

public class SplitArrayFibonacciTest {

    @Test
    public void testSolution() {
        String s1 = "123456579";
        List<Integer> expected1 = ArrayUtil.toList(new int[] {123,456,579});
        String s2 = "11235813";
        List<Integer> expected2 = ArrayUtil.toList(new int[] {1,1,2,3,5,8,13});
        String s3 = "112358130";
        List<Integer> expected3 = new ArrayList<>();
        String s4 = "0123";
        List<Integer> expected4 = new ArrayList<>();
        String s5 = "1101111";
        List<Integer> expected5 = ArrayUtil.toList(new int[] {11, 0, 11, 11});
        String s6 = "000000000";
        List<Integer> expected6 = ArrayUtil.toList(new int[] {0,0,0,0,0,0,0,0,0});
        assertEquals(expected1, SplitArrayFibonacci.solution1(s1));
        assertEquals(expected2, SplitArrayFibonacci.solution1(s2));
        assertEquals(expected3, SplitArrayFibonacci.solution1(s3));
        assertEquals(expected4, SplitArrayFibonacci.solution1(s4));
        assertEquals(expected5, SplitArrayFibonacci.solution1(s5));
        assertEquals(expected6, SplitArrayFibonacci.solution1(s6));
        assertEquals(expected1, SplitArrayFibonacci.solution2(s1));
        assertEquals(expected2, SplitArrayFibonacci.solution2(s2));
        assertEquals(expected3, SplitArrayFibonacci.solution2(s3));
        assertEquals(expected4, SplitArrayFibonacci.solution2(s4));
        assertEquals(expected5, SplitArrayFibonacci.solution2(s5));
        assertEquals(expected6, SplitArrayFibonacci.solution2(s6));
    }

}
