package com.zfwhub.algorithm.leetcode.hashtable;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.zfwhub.algorithm.utils.ArrayUtil;

public class SubstringConcatenationWordsTest {

    @Test
    public void testSolution1() {
        
        String s1 = "barfoothefoobarman";
        String[] words1 = new String[] {"foo","bar"};
        List<Integer> expected1 = ArrayUtil.toList(new int[] {0, 9});
        
        String s2 = "wordgoodgoodgoodbestword";
        String[] words2 = new String[] {"word","good","best","word"};
        List<Integer> expected2 = ArrayUtil.toList(new int[] {});

        String s3 = "";
        String[] words3 = new String[] {};
        List<Integer> expected3 = ArrayUtil.toList(new int[] {});
        
        String s4 = "wordgoodgoodgoodbestword";
        String[] words4 = new String[] {"word","good","best","good"};
        List<Integer> expected4 = ArrayUtil.toList(new int[] {8});
        
        String s5 = "foobarfoobar";
        String[] words5 = new String[] {"foo","bar"};
        List<Integer> expected5 = ArrayUtil.toList(new int[] {3,0,6});
        
        assertEquals(expected1, SubstringConcatenationWords.solution1(s1, words1));
        assertEquals(expected2, SubstringConcatenationWords.solution1(s2, words2));
        assertEquals(expected3, SubstringConcatenationWords.solution1(s3, words3));
        assertEquals(expected4, SubstringConcatenationWords.solution1(s4, words4));
        assertEquals(expected5, SubstringConcatenationWords.solution1(s5, words5));
    }

}
