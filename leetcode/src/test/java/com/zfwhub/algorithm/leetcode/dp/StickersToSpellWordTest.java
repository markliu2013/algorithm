package com.zfwhub.algorithm.leetcode.dp;

import static org.junit.Assert.*;

import org.junit.Test;

public class StickersToSpellWordTest {
    
    String[] stickers1 = new String[] {"a"};
    String target1 = "a";
    int expected1 = 1;
    
    String[] stickers2 = new String[] {"a", "b"};
    String target2 = "ab";
    int expected2 = 2;
    
    String[] stickers3 = new String[] {"ab", "b"};
    String target3 = "ab";
    int expected3 = 1;
    
    String[] stickers4 = new String[] {"ab", "c"};
    String target4 = "ab";
    int expected4 = 1;
    
    String[] stickers5 = new String[] {"aa", "baa", "ca"};
    String target5 = "aa";
    int expected5 = 1;
    
    String[] stickers21 = new String[] {"with", "example", "science"};
    String target21 = "thehat";
    int expected21 = 3;
    
    String[] stickers22 = new String[] {"notice", "possible"};
    String target22 = "basicbasic";
    int expected22 = -1;
    
    String[] stickers23 = new String[] {"fly","me","charge","mind","bottom"};
    String target23 = "centorder";
    int expected23 = 4;
    
    @Test
    public void testMinStickers() {
        assertEquals(expected1, StickersToSpellWord.minStickers(stickers1, target1));
        assertEquals(expected2, StickersToSpellWord.minStickers(stickers2, target2));
        assertEquals(expected3, StickersToSpellWord.minStickers(stickers3, target3));
        assertEquals(expected4, StickersToSpellWord.minStickers(stickers4, target4));
        assertEquals(expected5, StickersToSpellWord.minStickers(stickers5, target5));
        assertEquals(expected21, StickersToSpellWord.minStickers(stickers21, target21));
        assertEquals(expected22, StickersToSpellWord.minStickers(stickers22, target22));
        assertEquals(expected23, StickersToSpellWord.minStickers(stickers23, target23));
    }

}
