package com.zfwhub.algorithm.leetcode.dp;

import static org.junit.Assert.*;

import org.junit.Test;

import com.zfwhub.algorithm.leetcode.dp.MinimumSwapsToMakeSequencesIncreasing;

public class MinimumSwapsToMakeSequencesIncreasingTest {

    @Test
    public void testMinSwap() {
        int[] A1 = new int[] {1,3,5,4};
        int[] B1 = new int[] {1,2,3,7};
        int result1 = 1;
        int[] A2 = new int[] {3,3,8,9,10};
        int[] B2 = new int[] {1,7,4,6,8};
        int result2 = 1;
        int[] A3 = new int[] {0,3,5,8,9};
        int[] B3 = new int[] {2,1,4,6,9};
        int result3 = 1;
        int[] A4 = new int[] {0,7,8,10,10,11,12,13,19,18};
        int[] B4 = new int[] {4,4,5, 7,11,14,15,16,17,20};
        int result4 = 4;
        int[] A5 = new int[] {0,4,4,5,9};
        int[] B5 = new int[] {0,1,6,8,10};
        int result5 = 1;
        assertEquals(result1, MinimumSwapsToMakeSequencesIncreasing.minSwap(A1, B1));
        assertEquals(result2, MinimumSwapsToMakeSequencesIncreasing.minSwap(A2, B2));
        assertEquals(result3, MinimumSwapsToMakeSequencesIncreasing.minSwap(A3, B3));
        assertEquals(result4, MinimumSwapsToMakeSequencesIncreasing.minSwap(A4, B4));
        assertEquals(result5, MinimumSwapsToMakeSequencesIncreasing.minSwap(A5, B5));
        assertEquals(result1, MinimumSwapsToMakeSequencesIncreasing.minSwap2(A1, B1));
        assertEquals(result2, MinimumSwapsToMakeSequencesIncreasing.minSwap2(A2, B2));
        assertEquals(result3, MinimumSwapsToMakeSequencesIncreasing.minSwap2(A3, B3));
        assertEquals(result4, MinimumSwapsToMakeSequencesIncreasing.minSwap2(A4, B4));
        assertEquals(result5, MinimumSwapsToMakeSequencesIncreasing.minSwap2(A5, B5));
        
    }

}
