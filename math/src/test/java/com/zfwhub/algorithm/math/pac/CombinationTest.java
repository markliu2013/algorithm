package com.zfwhub.algorithm.math.pac;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CombinationTest {
    @Test
    public void testGetAllCombination() throws Exception {
        int[] spam = new int[] { 1, 2, 3 };
        System.out.println(Combination.getAllCombination(spam));
    }

    @Test
    public void testGetCombination() throws Exception {
        Integer[] spam = new Integer[] { 1, 2, 3, 4 };
        List<Integer> list = Arrays.asList(spam);
        System.out.println(Combination.getCombination(list, 2));
    }

    @Test
    public void testGetCombination1() throws Exception {

    }

}