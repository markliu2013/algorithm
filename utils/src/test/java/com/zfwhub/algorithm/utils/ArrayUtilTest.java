package com.zfwhub.algorithm.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayUtilTest {

    @Test
    public void testMergeTwoSortedArray() {
        int[] nums11 = new int[] {1,3,5,7};
        int[] nums12 = new int[] {2,4,6,8};
        int[] expected1 = new int[] {1,2,3,4,5,6,7,8};
        int[] nums21 = new int[] {2};
        int[] nums22 = new int[] {};
        int[] expected2 = new int[] {2};
        int[] nums31 = new int[] {};
        int[] nums32 = new int[] {2};
        int[] expected3 = new int[] {2};
        int[] nums41 = new int[] {};
        int[] nums42 = new int[] {};
        int[] expected4 = new int[] {};
        int[] nums51 = new int[] {2};
        int[] nums52 = new int[] {1};
        int[] expected5 = new int[] {1,2};
        int[] nums61 = new int[] {3};
        int[] nums62 = new int[] {-2,-1};
        int[] expected6 = new int[] {-2,-1,3};
        assertArrayEquals(expected1, ArrayUtil.mergeTwoSortedArray(nums11, nums12));
        assertArrayEquals(expected2, ArrayUtil.mergeTwoSortedArray(nums21, nums22));
        assertArrayEquals(expected3, ArrayUtil.mergeTwoSortedArray(nums31, nums32));
        assertArrayEquals(expected4, ArrayUtil.mergeTwoSortedArray(nums41, nums42));
        assertArrayEquals(expected5, ArrayUtil.mergeTwoSortedArray(nums51, nums52));
        assertArrayEquals(expected6, ArrayUtil.mergeTwoSortedArray(nums61, nums62));
    }

}
