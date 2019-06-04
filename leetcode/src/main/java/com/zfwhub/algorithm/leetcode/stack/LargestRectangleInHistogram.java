package com.zfwhub.algorithm.leetcode.stack;

import java.util.Stack;

// https://leetcode.com/problems/largest-rectangle-in-histogram/
public class LargestRectangleInHistogram {
    
    // 找每个柱子的水平线，返回最大的。
    public static int solution1(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            int count = 1;
            for (int j = i-1; j >= 0; j--) {
                if (heights[j] >= heights[i]) {
                    count++;
                } else {
                    break;
                }
            }
            for (int j = i+1; j < heights.length; j++) {
                if (heights[j] >= heights[i]) {
                    count++;
                } else {
                    break;
                }
            }
            result = Math.max(result, heights[i]*count);
        }
        return result;
    }
    
    public static int solution2(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        if (heights.length == 1) {
            return heights[0];
        }
        int[] leftMaxs = new int[heights.length]; //每一个点可以往左边最远走多远。
        leftMaxs[0] = 1; //第一个肯定为1
        for (int i = 1; i < heights.length; i++) {
            // 计算leftMaxs[i]
            if (heights[i] > heights[i-1]) { // 如果后面一个比前面高，则一步都不能走。
                leftMaxs[i] = 1;
            } else {
                int j = i-1;
                leftMaxs[i] = 1;
                while (j >= 0 && heights[j] >= heights[i]) {
                    leftMaxs[i] = leftMaxs[i] + leftMaxs[j];
                    j = j - leftMaxs[j];
                }
            }
        }
        int[] rightMaxs = new int[heights.length]; //每一个点可以往右边最远走多远。
        rightMaxs[heights.length-1] = 1; // 最后一个只能为1
        for (int i = heights.length-2; i >= 0; i--) {
            // 计算rightMaxs[i];
            if (heights[i] > heights[i+1]) {
                rightMaxs[i] = 1;
            } else {
                int j = i+1;
                rightMaxs[i] = 1;
                while (j < heights.length && heights[j] >= heights[i]) {
                    rightMaxs[i] = rightMaxs[i] + rightMaxs[j];
                    j = j + rightMaxs[j];
                }
            }
        }
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            result = Math.max(result, heights[i]*(leftMaxs[i]+rightMaxs[i]-1));
        }
        return result;
    }
    
    // https://www.cnblogs.com/boring09/p/4231906.html
    // TODO LargestRectangleInHistogram
    public static int solution3(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        int result = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();//单调递增栈
        stack.push(0);
        for (int i = 1; i < heights.length; i++) {
            if (!stack.isEmpty() && heights[stack.peek()] > heights[i]) { //递增被破坏
                Integer integer = 0;
                int count = 0;
                while (!stack.isEmpty() && (heights[integer = stack.pop()]) > heights[i]) {
                    count++;
                    result = Math.max(result, heights[integer]*(count+integer));
                }
            }
            stack.push(i);
        }
        System.out.println(stack);
        for (Integer i : stack) {
            result = Math.max(result, heights[i]*(heights.length-i));
        }
        return result;
    }
    
    
    public static void main(String[] args) {
//        System.out.println(solution1(new int[] {2,1,5,6,2,3}));
        System.out.println(solution2(new int[] {2,1,5,6,2,3}));
//        System.out.println(solution1(new int[] {1,2,3,4}));
//        System.out.println(solution2(new int[] {1,2,3,4}));
        System.out.println(solution2(new int[] {2,1,2}));
    }

}
