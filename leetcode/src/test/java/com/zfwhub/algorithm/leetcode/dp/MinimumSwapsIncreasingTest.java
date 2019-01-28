package com.zfwhub.algorithm.leetcode.dp;

import static org.junit.Assert.*;

import org.junit.Test;

import com.zfwhub.algorithm.leetcode.dp.MinimumSwapsIncreasing;

public class MinimumSwapsIncreasingTest {

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
        int[] A6 = new int[] {2,1,6,7,8,13,15,11,18,13,20,24,17,28,22,23,36,37,39,34,43,38,48,41,46,48,49,50,56,55,59,60,62,64,66,75,69,70,71,74,87,78,95,97,81,99,85,101,90,91,93,95,107,109,101,111,106,114,115,117,118,115,121,122,123,124,125,126,134,131,133,136,142,149,151,152,145,156,158,150,162,159,161,165,169,170,169,174,172,176,177,181,183,192,186,188,189,196,198,200};
        int[] B6 = new int[] {0,4,10,11,12,9,10,16,12,19,15,16,25,20,33,34,27,29,32,40,35,45,40,50,51,52,53,55,52,58,58,61,62,66,71,68,78,81,83,84,75,91,79,80,98,83,100,89,102,103,105,106,96,98,110,105,113,109,110,111,112,120,116,118,126,130,131,133,129,137,138,140,137,138,140,142,154,147,149,159,152,163,164,163,166,168,171,170,175,176,177,181,186,184,193,194,195,190,195,200};
        assertEquals(result1, MinimumSwapsIncreasing.minSwap(A1, B1));
        assertEquals(result2, MinimumSwapsIncreasing.minSwap(A2, B2));
        assertEquals(result3, MinimumSwapsIncreasing.minSwap(A3, B3));
        assertEquals(result4, MinimumSwapsIncreasing.minSwap(A4, B4));
        assertEquals(result5, MinimumSwapsIncreasing.minSwap(A5, B5));
        assertEquals(result1, MinimumSwapsIncreasing.minSwap2(A1, B1));
        assertEquals(result2, MinimumSwapsIncreasing.minSwap2(A2, B2));
        assertEquals(result3, MinimumSwapsIncreasing.minSwap2(A3, B3));
        assertEquals(result4, MinimumSwapsIncreasing.minSwap2(A4, B4));
        assertEquals(result5, MinimumSwapsIncreasing.minSwap2(A5, B5));
        assertEquals(result1, MinimumSwapsIncreasing.minSwap3(A1, B1));
        assertEquals(result2, MinimumSwapsIncreasing.minSwap3(A2, B2));
        assertEquals(result3, MinimumSwapsIncreasing.minSwap3(A3, B3));
        assertEquals(result4, MinimumSwapsIncreasing.minSwap3(A4, B4));
        assertEquals(result5, MinimumSwapsIncreasing.minSwap3(A5, B5));
        assertEquals(result1, MinimumSwapsIncreasing.minSwap4(A1, B1));
        assertEquals(result2, MinimumSwapsIncreasing.minSwap4(A2, B2));
        assertEquals(result3, MinimumSwapsIncreasing.minSwap4(A3, B3));
        assertEquals(result4, MinimumSwapsIncreasing.minSwap4(A4, B4));
        assertEquals(result5, MinimumSwapsIncreasing.minSwap4(A5, B5));
    }

}
