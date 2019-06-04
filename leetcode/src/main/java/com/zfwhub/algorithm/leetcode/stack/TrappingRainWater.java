package com.zfwhub.algorithm.leetcode.stack;

import java.util.Stack;

// https://leetcode.com/problems/trapping-rain-water/
public class TrappingRainWater {
    
    public static int solution1(int[] height) {
        int count = 0;
        // 一个一个的检查，左边和右边是否有比自己高的。
        for (int i = 1; i < height.length; i++) {
            int leftMax = Integer.MIN_VALUE;
            for (int j = i-1; j >= 0; j--) {
                if (height[j] > height[i]) {
                    leftMax = Math.max(leftMax, height[j]);
                }
            }
            int rightMax = Integer.MIN_VALUE;
            for (int j = i+1; j < height.length; j++) {
                if (height[j] > height[i]) {
                    rightMax = Math.max(rightMax, height[j]);
                }
            }
            if (leftMax != Integer.MIN_VALUE && rightMax != Integer.MIN_VALUE) {
                count += Math.min(leftMax, rightMax) - height[i];
            }
        }
        return count;
    }
    
    // https://leetcode.windliang.cc/leetCode-42-Trapping-Rain-Water.html
    public static int solution2(int[] height) {
        if (height.length == 0) return 0;
        int[] leftMaxs = new int[height.length];
        leftMaxs[0] = 0;
        for (int i = 1; i < height.length; i++) {
            leftMaxs[i] = Math.max(leftMaxs[i-1], height[i-1]);
        }
        int[] rightMaxs = new int[height.length];
        rightMaxs[rightMaxs.length-1] = 0;
        for (int i = height.length-2; i >= 0; i--) {
            rightMaxs[i] = Math.max(rightMaxs[i+1], height[i+1]);
        }
        int count = 0;
        for (int i = 1; i < height.length; i++) {
            int leftMax = leftMaxs[i] > height[i] ? leftMaxs[i] : Integer.MIN_VALUE;
            int rightMax = rightMaxs[i] > height[i] ? rightMaxs[i] : Integer.MIN_VALUE;
            if (leftMax != Integer.MIN_VALUE && rightMax != Integer.MIN_VALUE) {
                count += Math.min(leftMax, rightMax) - height[i];
            }
        }
        return count;
    }
    
    // 单调递减栈 TODO
    public static int solution3(int[] height) {
        int count = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            if (height[i] < height[stack.peek()]) {
                stack.push(i);
            }
        }
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println(solution1(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(solution2(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
    }

}
