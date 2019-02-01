package com.zfwhub.algorithm.leetcode.dp;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;  

@RunWith(Parameterized.class)  
public class StickersToSpellWordTest {
    
    private String[] stickers;
    private String target;
    private int expected;
    
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { new String[] {"a"}, "a", 1 },
                { new String[] {"a", "b"}, "ab", 2 },
                { new String[] {"ab", "b"}, "ab", 1 },
                { new String[] {"ab", "c"}, "ab", 1 },
                { new String[] {"a","b","bc"}, "abc", 2 },
                { new String[] {"ab"}, "aabba", 3 },
                { new String[] {"with", "example", "science"}, "thehat", 3 },
                { new String[] {"notice", "possible"}, "basicbasic", -1 },
                { new String[] {"fly","me","charge","mind","bottom"}, "centorder", 4 },
                { new String[] {"soil","since","lift","are","lot","twenty","put"}, "appearreason", 7 },
                { new String[] {"control","heart","interest","stream","sentence",
                        "soil","wonder","them","month","slip","table","miss","boat","speak",
                        "figure","no","perhaps","twenty","throw","rich","capital","save","method",
                        "store","meant","life","oil","string","song","food","am","who","fat","if",
                        "put","path","come","grow","box","great","word","object","stead","common",
                        "fresh","the","operate","where","road","mean"}, 
                    "stoodcrease", 3 }
        });
    }
    
    public StickersToSpellWordTest(String[] stickers, String target, int expected) {
        this.stickers = stickers;
        this.target = target;
        this.expected = expected;
    }

    @Test
    public void testMinStickers() {
        assertEquals(expected, StickersToSpellWord.solution1(stickers, target));
    }
    
    @Test
    public void testMinStickers2() {
        assertEquals(expected, StickersToSpellWord.solution2(stickers, target));
    }

}
