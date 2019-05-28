package com.zfwhub.algorithm.leetcode.dp;

import static org.junit.Assert.*;

import org.junit.Test;

// https://leetcode.com/problems/edit-distance/
public class EditDistanceTest {

    @Test
    public void test() {
        
        String word1 = "horse";
        String word2 = "ros";
        int expected1 = 3;
        
        String word12 = "intention";
        String word22 = "execution";
        int expected2 = 5;

        String word13 = "sea";
        String word23 = "eat";
        int expected3 = 2;

        String word14 = "";
        String word24 = "a";
        int expected4 = 1;
        
        assertEquals(expected1, EditDistance.solution2(word1, word2));
        assertEquals(expected2, EditDistance.solution2(word12, word22));
        assertEquals(expected3, EditDistance.solution2(word13, word23));
        assertEquals(expected4, EditDistance.solution2(word14, word24));
    }

}
