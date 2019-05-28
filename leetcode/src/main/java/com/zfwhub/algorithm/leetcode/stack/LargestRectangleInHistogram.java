package com.zfwhub.algorithm.leetcode.stack;

import com.zfwhub.algorithm.utils.ArrayUtil;

// https://leetcode.com/problems/largest-rectangle-in-histogram/
public class LargestRectangleInHistogram {
    
    // 找每个柱子的水平线，返回最大的。
    // https://leetcode.com/submissions/detail/231221511/
    public static int solution1(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        int[] dp = new int[heights.length];
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
            dp[i] = heights[i]*count;
        }
        return ArrayUtil.max(dp);
    }
    
    // https://www.cnblogs.com/boring09/p/4231906.html
    
    public static void main(String[] args) {
        System.out.println(solution1(new int[] {2,1,5,6,2,3}));
        System.out.println(solution1(new int[] {1,1}));
    }

}
