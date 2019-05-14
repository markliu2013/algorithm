package com.zfwhub.algorithm.leetcode.contest136;

import static org.junit.Assert.*;

import org.junit.Test;

public class FlowerPlantingWithNoAdjacentTest {

    @Test
    public void testSolution2() {
        int N1 = 3;
        int[][] paths1 = new int[][] {{1,2},{2,3},{3,1}};
        int[] expected1 = new int[] {1,2,3};
        int[] actual1 = FlowerPlantingWithNoAdjacent.solution2(N1, paths1);
        for (int i = 0; i < actual1.length; i++) {
            assertEquals(expected1[i], actual1[i]);
        }
        
        int N2 = 4;
        int[][] paths2 = new int[][] {{1,2},{3,4}};
        int[] expected2 = new int[] {1,2,1,2};
        int[] actual2 = FlowerPlantingWithNoAdjacent.solution2(N2, paths2);
        for (int i = 0; i < actual2.length; i++) {
            assertEquals(expected2[i], actual2[i]);
        }

        int N3 = 4;
        int[][] paths3 = new int[][] {{1,2},{2,3},{3,4},{4,1},{1,3},{2,4}};
        int[] expected3 = new int[] {1,2,3,4};
        int[] actual3 = FlowerPlantingWithNoAdjacent.solution2(N3, paths3);
        for (int i = 0; i < actual3.length; i++) {
            assertEquals(expected3[i], actual3[i]);
        }

        int N4 = 5;
        int[][] paths4 = new int[][] {{4,1},{4,2},{4,3},{2,5},{1,2},{1,5}};
        int[] expected4 = new int[] {1,2,1,3,3};
        int[] actual4 = FlowerPlantingWithNoAdjacent.solution2(N4, paths4);
        for (int i = 0; i < actual4.length; i++) {
            assertEquals(expected4[i], actual4[i]);
        }

        int N5 = 4;
        int[][] paths5 = new int[][] {{3,4},{4,2},{3,2},{1,3}};
        int[] expected5 = new int[] {1,1,2,3};
        int[] actual5 = FlowerPlantingWithNoAdjacent.solution2(N5, paths5);
        for (int i = 0; i < actual5.length; i++) {
            assertEquals(expected5[i], actual5[i]);
        }
        
        
        int N6 = 6;
        int[][] paths6 = new int[][] {{6,4},{6,1},{3,1},{4,5},{2,1},{5,6},{5,2}};
        int[] expected6 = new int[] {1,2,2,1,3,2};
        int[] actual6 = FlowerPlantingWithNoAdjacent.solution2(N6, paths6);
        for (int i = 0; i < actual6.length; i++) {
            assertEquals(expected6[i], actual6[i]);
        }
    }

}
