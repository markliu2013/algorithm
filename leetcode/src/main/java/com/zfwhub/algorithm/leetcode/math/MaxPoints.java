package com.zfwhub.algorithm.leetcode.math;

import java.util.*;

import com.zfwhub.algorithm.utils.CollectionUtil;

// https://leetcode.com/problems/max-points-on-a-line/
public class MaxPoints {
    
    // 强攻超时 
    public static int solution1(int[][] points) {
        if (points.length < 3) {
            return points.length;
        }
        List<int[]> pointList = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            pointList.add(points[i]);
        }
        for (int i = points.length; i > 2; i--) {
            List<List<int[]>> pointsCom = CollectionUtil.combine(pointList, i);
            for (int j = 0; j < pointsCom.size(); j++) {
                if (check(pointsCom.get(j))) {
                    for (int j2 = 0; j2 < pointsCom.get(j).size(); j2++) {
                        System.out.println(Arrays.toString(pointsCom.get(j).get(j2)));
                    }
                    return pointsCom.get(j).size();
                }
            }
        }
        return 2;
    }
    
    public static boolean check(List<int[]> points) {
        // 计算斜率
        double slope = 0;
        for (int i = 1; i < points.size(); i++) {
            if (!Arrays.equals(points.get(i), points.get(0))) {
                if ((points.get(i)[0]-points.get(0)[0]) == 0) {
                    slope = Double.MAX_VALUE;
                } else {
                    slope = (double)(points.get(i)[1]-points.get(0)[1])/(points.get(i)[0]-points.get(0)[0]);
                }
            }
        }
        for (int i = 1; i < points.size(); i++) {
            // 两个点可能相等
            if (Arrays.equals(points.get(i), points.get(0))) {
                continue;
            }
            double slope1 = 0;
            if ((points.get(i)[0]-points.get(0)[0]) == 0) {
                slope1 = Double.MAX_VALUE;
            } else {
                slope1 = (double)(points.get(i)[1]-points.get(0)[1])/(points.get(i)[0]-points.get(0)[0]);
            }
            if (slope1 != slope) {
                return false;
            }
        }
        return true;
    }
    
    // TODO MaxPoints
    
    public static void main(String[] args) {
//        int[][] points = new int[][] {{1,1},{2,2},{3,3}};
        int[][] points = new int[][] {{0,9},{138,429},{115,359},{115,359},{-30,-102},{230,709},{-150,-686},{-135,-613},{-60,-248},{-161,-481},{207,639},{23,79},{-230,-691},{-115,-341},{92,289},{60,336},{-105,-467},{135,701},{-90,-394},{-184,-551},{150,774}};
        System.out.println(solution1(points));
    }
    
    
    
}
