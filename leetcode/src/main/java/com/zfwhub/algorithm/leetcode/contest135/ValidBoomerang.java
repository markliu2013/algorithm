package com.zfwhub.algorithm.leetcode.contest135;

import java.util.Arrays;

public class ValidBoomerang {
    
    public static boolean solution1(int[][] points) {
        // 计算斜率
        double slope = 0;
        for (int i = 1; i < points.length; i++) {
            if (!Arrays.equals(points[i], points[0])) {
                if ((points[i][0]-points[0][0]) == 0) {
                    slope = Double.MAX_VALUE;
                } else {
                    slope = (double)(points[i][1]-points[0][1])/(points[i][0]-points[0][0]);
                }
            }
        }
        for (int i = 1; i < points.length; i++) {
            // 两个点可能相等
            if (Arrays.equals(points[i], points[0])) {
                continue;
            }
            double slope1 = 0;
            if ((points[i][0]-points[0][0]) == 0) {
                slope1 = Double.MAX_VALUE;
            } else {
                slope1 = (double)(points[i][1]-points[0][1])/(points[i][0]-points[0][0]);
            }
            if (slope1 != slope) {
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        int[][] points = new int[][] {{1,1},{2,2},{3,3}};
        System.out.println(solution1(points));
    }
    
}
