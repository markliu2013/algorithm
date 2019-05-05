package com.zfwhub.algorithm.leetcode.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MaxPointsTest {

    @Test
    public void testSolution() {
        int[][] points1 = new int[][] {{1,1},{2,2},{3,3}};
        int expected1 = 3;
        
        int[][] points2 = new int[][] {{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}};
        int expected2 = 4;

        int[][] points3 = new int[][] {{0,0},{1,1},{0,0}};
        int expected3 = 3;
        
        int[][] points4 = new int[][] {};
        int expected4 = 0;
        
        int[][] points5 = new int[][] {{4,0},{4,-1},{4,5}};
        int expected5 = 3;
        
        int[][] points6 = new int[][] {{1,1},{1,1},{2,3}};
        int expected6 = 3;
        
        int[][] points7 = new int[][] {{0,9},{138,429},{115,359},{115,359},{-30,-102},{230,709},{-150,-686},{-135,-613},{-60,-248},{-161,-481},{207,639},{23,79},{-230,-691},{-115,-341},{92,289},{60,336},{-105,-467},{135,701},{-90,-394},{-184,-551},{150,774}};
        int expected7 = 12;
        
        assertEquals(expected1, MaxPoints.solution1(points1));
        assertEquals(expected2, MaxPoints.solution1(points2));
        assertEquals(expected3, MaxPoints.solution1(points3));
        assertEquals(expected4, MaxPoints.solution1(points4));
        assertEquals(expected5, MaxPoints.solution1(points5));
        assertEquals(expected6, MaxPoints.solution1(points6));
        assertEquals(expected7, MaxPoints.solution1(points7));
        
    }

}
