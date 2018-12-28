package com.zfwhub.algorithm.leetcode.dp;

import static org.junit.Assert.*;

import org.junit.Test;

public class StickersToSpellWordTest {
    
    String[] stickers1 = new String[] {"with", "example", "science"};
    String target1 = "thehat";
    int expected1 = 3;
    
    String[] stickers2 = new String[] {"notice", "possible"};
    String target2 = "basicbasic";
    int expected2 = -1;
    
    String[] stickers3 = new String[] {"a"};
    String target3 = "a";
    int expected3 = 1;
    
    String[] stickers4 = new String[] {"a", "b"};
    String target4 = "ab";
    int expected4 = 2;
    
    String[] stickers5 = new String[] {"fly","me","charge","mind","bottom"};
    String target5 = "centorder";
    int expected5 = 4;
    
    @Test
    public void testMinStickers() {
        assertEquals(expected1, StickersToSpellWord.minStickers(stickers1, target1));
        assertEquals(expected2, StickersToSpellWord.minStickers(stickers2, target2));
        assertEquals(expected3, StickersToSpellWord.minStickers(stickers3, target3));
        assertEquals(expected4, StickersToSpellWord.minStickers(stickers4, target4));
        assertEquals(expected5, StickersToSpellWord.minStickers(stickers5, target5));
    }

}
