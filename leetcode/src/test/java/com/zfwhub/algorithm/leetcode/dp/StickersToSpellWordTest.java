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
    
    String[] stickers5 = new String[] {"a","b","bc"};
    String target5 = "abc";
    int expected5 = 2;
    
    String[] stickers6 = new String[] {"ab"};
    String target6 = "aabba";
    int expected6 = 3;
    
    String[] stickers21 = new String[] {"with", "example", "science"};
    String target21 = "thehat";
    int expected21 = 3;
    
    String[] stickers22 = new String[] {"notice", "possible"};
    String target22 = "basicbasic";
    int expected22 = -1;
    
    String[] stickers23 = new String[] {"fly","me","charge","mind","bottom"};
    String target23 = "centorder";
    int expected23 = 4;
    
    String[] stickers24 = new String[] {"soil","since","lift","are","lot","twenty","put"};
    String target24 = "appearreason";
    int expected24 = 7;
    
    String[] stickers25 = new String[] {"control","heart","interest","stream","sentence",
            "soil","wonder","them","month","slip","table","miss","boat","speak",
            "figure","no","perhaps","twenty","throw","rich","capital","save","method",
            "store","meant","life","oil","string","song","food","am","who","fat","if",
            "put","path","come","grow","box","great","word","object","stead","common",
            "fresh","the","operate","where","road","mean"};
    String target25 = "stoodcrease";
    int expected25 = 3;
    
            
    String[] stickers26 = new String[] {"own","tone","feel","there","invent","trade","follow","home","still","check","market","cotton","blow","use","afraid","indicate","support","sail","charge","been","children","how","behind","size","hat","match","count","notice","food","excite","felt","serve","guess","else","quick","student","consonant","strong","wait","ago","enemy","oh","industry","cover","cat","while","nose","wild","quite","shine"};
    String target26 = "stoodcrease";
    int expected26 = 3;
    
    @Test
    public void testMinStickers() {
        assertEquals(expected1, StickersToSpellWord.minStickers(stickers1, target1));
        assertEquals(expected2, StickersToSpellWord.minStickers(stickers2, target2));
        assertEquals(expected3, StickersToSpellWord.minStickers(stickers3, target3));
        assertEquals(expected4, StickersToSpellWord.minStickers(stickers4, target4));
        assertEquals(expected5, StickersToSpellWord.minStickers(stickers5, target5));
        assertEquals(expected6, StickersToSpellWord.minStickers(stickers6, target6));
        assertEquals(expected21, StickersToSpellWord.minStickers(stickers21, target21));
        assertEquals(expected22, StickersToSpellWord.minStickers(stickers22, target22));
        assertEquals(expected23, StickersToSpellWord.minStickers(stickers23, target23));
        assertEquals(expected24, StickersToSpellWord.minStickers(stickers24, target24));
        assertEquals(expected25, StickersToSpellWord.minStickers(stickers25, target25));
    }
    
    @Test
    public void testMinStickers2() {
        assertEquals(expected1, StickersToSpellWord2.minStickers(stickers1, target1));
        assertEquals(expected2, StickersToSpellWord2.minStickers(stickers2, target2));
        assertEquals(expected3, StickersToSpellWord2.minStickers(stickers3, target3));
        assertEquals(expected4, StickersToSpellWord2.minStickers(stickers4, target4));
        assertEquals(expected5, StickersToSpellWord2.minStickers(stickers5, target5));
        assertEquals(expected6, StickersToSpellWord2.minStickers(stickers6, target6));
        assertEquals(expected21, StickersToSpellWord2.minStickers(stickers21, target21));
        assertEquals(expected22, StickersToSpellWord2.minStickers(stickers22, target22));
        assertEquals(expected23, StickersToSpellWord2.minStickers(stickers23, target23));
        assertEquals(expected24, StickersToSpellWord2.minStickers(stickers24, target24));
        assertEquals(expected25, StickersToSpellWord2.minStickers(stickers25, target25));
    }

}
