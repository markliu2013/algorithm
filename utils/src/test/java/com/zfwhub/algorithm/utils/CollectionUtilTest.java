package com.zfwhub.algorithm.utils;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class CollectionUtilTest {

    @Test
    public void testSubsetsListOfTInt() {
        List<Integer> list1 = CollectionUtil.newIntList(4);
        List<List<Integer>> list2 = CollectionUtil.subsets(list1, 3);
        System.out.println(list2);
    }

}
