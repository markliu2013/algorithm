package com.zfwhub.algorithm.utils;
import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

public class CollectionUtilTest {

    @Test
    public void testCombine() {
        List<List<Integer>> actual1 = CollectionUtil.combine(CollectionUtil.newIntList(1, 5), 2);
        List<List<Integer>> expected1 = ArrayUtil.twoDArrayToList(new int[][] { 
            {1, 2},
            {1, 3},
            {1, 4},
            {2, 3},
            {2, 4},
            {3, 4}
        });
        assertEquals(true, CollectionUtil.isEqualCollection(actual1, expected1));
    }
    
    @Test
    public void testIsEqualCollection() {
        List<Integer> a1 = ArrayUtil.toList(new int[] {1,2,3});
        List<Integer> b1 = ArrayUtil.toList(new int[] {1,3,2});
        assertEquals(true, CollectionUtil.isEqualCollection(a1, b1));
        List<Integer> a2 = ArrayUtil.toList(new int[] {1,2,2});
        List<Integer> b2 = ArrayUtil.toList(new int[] {2,1,1});
        assertEquals(false, CollectionUtil.isEqualCollection(a2, b2));
        List<Integer> a3 = ArrayUtil.toList(new int[] {1,2,3});
        List<Integer> b3 = ArrayUtil.toList(new int[] {1,2,4});
        assertEquals(false, CollectionUtil.isEqualCollection(a3, b3));
    }
    
    @Test
    public void testSubtract() {
        List<Integer> a1 = ArrayUtil.toList(new int[] {1,2,3});
        List<Integer> b1 = ArrayUtil.toList(new int[] {1,3,2});
        assertEquals(0, CollectionUtil.subtract(a1, b1).size());
        List<Integer> a2 = new ArrayList<>();
        a2.add(null);
        a2.add(null);
        List<Integer> b2 = new ArrayList<>();
        b2.add(null);
        List<Integer> e2 = new ArrayList<>();
        b2.add(null);
        assertEquals(e2, CollectionUtil.subtract(a2, b2));
    }
    
}
