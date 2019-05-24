package com.zfwhub.algorithm.leetcode.dp;

import java.util.*;

// https://leetcode.com/problems/maximal-rectangle/
public class MaximalRectangle {
    // 失败
    public static int solution1(char[][] matrix) {
        DPStatus dpStatus = new DPStatus();
        solution1DP(matrix, dpStatus);
        List<Rectangle> rectangles = dpStatus.rectangles;
        int maxArea = Integer.MIN_VALUE;
        for (Rectangle rectangle : rectangles) {
            maxArea = Math.max(maxArea, rectangle.area);
        }
        return maxArea;
    }
    
    public static void solution1DP(char[][] matrix, DPStatus dpStatus) {
        if (matrix.length == 1) {
            char[] row = matrix[0];
            int startIndex = 0;
            int currentCount = 0;
            for (int i = 0; i < row.length; i++) {
                if (row[i] == '1') {
                    currentCount++;
                } else {
                    if (currentCount != 0) {
                        Rectangle rectangle = new Rectangle();
                        rectangle.start = startIndex;
                        rectangle.end = startIndex + currentCount - 1;
                        rectangle.area = currentCount;
                        dpStatus.rectangles.add(rectangle);
                    }
                    startIndex = i+1;
                    currentCount = 0;
                }
            }
            if (currentCount != 0) {
                Rectangle rectangle = new Rectangle();
                rectangle.start = startIndex;
                rectangle.end = startIndex + currentCount - 1;
                rectangle.area = currentCount;
                dpStatus.rectangles.add(rectangle);
            }
            return;
        }
        char[] lastRow = matrix[matrix.length-1];
        char[][] subMatrix = Arrays.copyOfRange(matrix, 0, matrix.length-1);
        solution1DP(subMatrix, dpStatus);
        List<Rectangle> oldRects = dpStatus.rectangles;
        List<Rectangle> newRects = new ArrayList<>();
        int startIndex = 0;
        int currentCount = 0;
        for (int i = 0; i < lastRow.length; i++) {
            if (lastRow[i] == '1') {
                currentCount++;
            } else {
                if (currentCount != 0) {
                    //start = startIndex;
                    //end = startIndex + currentCount - 1;
                    for (int j = 0; j < oldRects.size(); j++) {
                        Rectangle rectangle = oldRects.get(j);
                        
                    }
                    
                }
                startIndex = i+1;
                currentCount = 0;
            }
        }
        if (currentCount != 0) {
            //start = startIndex;
            //end = startIndex + currentCount - 1;
        }
    }
    
    private static class DPStatus {
        public List<Rectangle> rectangles;
        public DPStatus() {
            rectangles = new ArrayList<>();
        }
        @Override
        public String toString() {
            return "DPStatus [rectangles=" + rectangles + "]";
        }
    }
    
    private static class Rectangle {
        public int start;
        public int end;
        public int area;
        @Override
        public String toString() {
            return "Rectangle [start=" + start + ", end=" + end + ", area=" + area + "]";
        }
        
    }
    
    public static void main(String[] args) {
        char[][] matrix = new char[][] {
            {'1','0','1'},
            {'1','0','1'},
            {'1','1','1'},
          };
        solution1(matrix);
    }

}
