package com.zfwhub.algorithm.templates.pack_problems;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class Pack01Test {
    
    int[] volumns1 = new int[] {5, 5, 3, 4, 3};
    int[] values1 = new int[] {400, 500, 200, 300, 350};
    int capacity1 = 10;
    int expected1 = 900;
    
    int[] volumns2 = new int[] { 2, 3, 4, 5 };
    int[] values2 = new int[] { 3, 4, 5, 6 };
    int capacity2 = 8;
    int expected2 = 10;
    
    int[] volumns3 = new int[] {1, 2, 5, 6, 7};
    int[] values3 = new int[] {1, 6, 18, 22, 28};
    int capacity3 = 11;
    int expected3 = 40;
    
    int[] volumns4 = new int[] {1, 2, 3, 4, 5};
    int[] values4 = new int[] {5, 4, 3, 2, 1};
    int capacity4 = 10;
    int expected4 = 14;
    
    // 注意包的容量为0，物体的体积也是0
    int[] volumns5 = new int[] {0, 0, 1, 0, 0};
    int[] values5 = new int[] {2, 4, 1, 5, 1};
    int capacity5 = 0;
    int expected5 = 12;

    @Test
    public void testSolution1() {
        assertEquals(expected1, Pack01.solution1(PackUtil.arrayToPackList(volumns1, values1), capacity1));
        assertEquals(expected2, Pack01.solution1(PackUtil.arrayToPackList(volumns2, values2), capacity2));
        assertEquals(expected3, Pack01.solution1(PackUtil.arrayToPackList(volumns3, values3), capacity3));
        assertEquals(expected4, Pack01.solution1(PackUtil.arrayToPackList(volumns4, values4), capacity4));
        assertEquals(expected5, Pack01.solution1(PackUtil.arrayToPackList(volumns5, values5), capacity5));
        assertEquals(expected1, PackUtil.getValue(Pack01Solution.solution1(PackUtil.arrayToPackList(volumns1, values1), capacity1)));
        assertEquals(expected2, PackUtil.getValue(Pack01Solution.solution1(PackUtil.arrayToPackList(volumns2, values2), capacity2)));
        assertEquals(expected3, PackUtil.getValue(Pack01Solution.solution1(PackUtil.arrayToPackList(volumns3, values3), capacity3)));
        assertEquals(expected4, PackUtil.getValue(Pack01Solution.solution1(PackUtil.arrayToPackList(volumns4, values4), capacity4)));
        assertEquals(expected5, PackUtil.getValue(Pack01Solution.solution1(PackUtil.arrayToPackList(volumns5, values5), capacity5)));
        assertEquals(expected1, PackUtil.getValue(Pack01Solution.solution2(PackUtil.arrayToPackList(volumns1, values1), capacity1)));
        assertEquals(expected2, PackUtil.getValue(Pack01Solution.solution2(PackUtil.arrayToPackList(volumns2, values2), capacity2)));
        assertEquals(expected3, PackUtil.getValue(Pack01Solution.solution2(PackUtil.arrayToPackList(volumns3, values3), capacity3)));
        assertEquals(expected4, PackUtil.getValue(Pack01Solution.solution2(PackUtil.arrayToPackList(volumns4, values4), capacity4)));
        assertEquals(expected5, PackUtil.getValue(Pack01Solution.solution2(PackUtil.arrayToPackList(volumns5, values5), capacity5)));
    }
    
    @Test
    public void testSolution2() {
        assertEquals(expected1, Pack01.solution2(PackUtil.arrayToPackList(volumns1, values1), capacity1));
        assertEquals(expected2, Pack01.solution2(PackUtil.arrayToPackList(volumns2, values2), capacity2));
        assertEquals(expected3, Pack01.solution2(PackUtil.arrayToPackList(volumns3, values3), capacity3));
        assertEquals(expected4, Pack01.solution2(PackUtil.arrayToPackList(volumns4, values4), capacity4));
        assertEquals(expected5, Pack01.solution2(PackUtil.arrayToPackList(volumns5, values5), capacity5));
    }

    @Test
    public void testSolution3() {
        assertEquals(expected1, Pack01.solution3(PackUtil.arrayToPackList(volumns1, values1), capacity1));
        assertEquals(expected2, Pack01.solution3(PackUtil.arrayToPackList(volumns2, values2), capacity2));
        assertEquals(expected3, Pack01.solution3(PackUtil.arrayToPackList(volumns3, values3), capacity3));
        assertEquals(expected4, Pack01.solution3(PackUtil.arrayToPackList(volumns4, values4), capacity4));
        assertEquals(expected5, Pack01.solution3(PackUtil.arrayToPackList(volumns5, values5), capacity5));
    }
    
    @Test
    public void testSolution4() {
        assertEquals(expected1, Pack01.solution4(PackUtil.arrayToPackList(volumns1, values1), capacity1));
        assertEquals(expected2, Pack01.solution4(PackUtil.arrayToPackList(volumns2, values2), capacity2));
        assertEquals(expected3, Pack01.solution4(PackUtil.arrayToPackList(volumns3, values3), capacity3));
        assertEquals(expected4, Pack01.solution4(PackUtil.arrayToPackList(volumns4, values4), capacity4));
        assertEquals(expected5, Pack01.solution4(PackUtil.arrayToPackList(volumns5, values5), capacity5));
    }
    
    @Test
    public void testSolution5() {
        assertEquals(expected1, Pack01.solution5(PackUtil.arrayToPackList(volumns1, values1), capacity1));
        assertEquals(expected2, Pack01.solution5(PackUtil.arrayToPackList(volumns2, values2), capacity2));
        assertEquals(expected3, Pack01.solution5(PackUtil.arrayToPackList(volumns3, values3), capacity3));
        assertEquals(expected4, Pack01.solution5(PackUtil.arrayToPackList(volumns4, values4), capacity4));
        assertEquals(expected5, Pack01.solution5(PackUtil.arrayToPackList(volumns5, values5), capacity5));
    }
    
    @Test
    public void testSolution6() {
        assertEquals(expected1, Pack01.solution6(PackUtil.arrayToPackList(volumns1, values1), capacity1));
        assertEquals(expected2, Pack01.solution6(PackUtil.arrayToPackList(volumns2, values2), capacity2));
        assertEquals(expected3, Pack01.solution6(PackUtil.arrayToPackList(volumns3, values3), capacity3));
        assertEquals(expected4, Pack01.solution6(PackUtil.arrayToPackList(volumns4, values4), capacity4));
        assertEquals(expected5, Pack01.solution6(PackUtil.arrayToPackList(volumns5, values5), capacity5));
    }
}
