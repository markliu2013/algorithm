package com.zfwhub.algorithm.leetcode.pack;

import java.util.*;

/**
 * 完全背包问题
 * 跑代码：https://hihocoder.com/problemset/problem/1043
 */
public class Pack02 {
    
    public static int solution1(int[] volumns, int[] values, int capacity) {
        if (values.length == 0) {
            return 0;
        }
        int[] subVolumns = Arrays.copyOf(volumns, volumns.length - 1);
        int[] subValues = Arrays.copyOf(values, values.length - 1);
        int lastVolumn = volumns[volumns.length-1];
        int lastValue = values[values.length-1];
        if (lastVolumn > capacity) {
            return solution1(subVolumns, subValues, capacity);
        } else {
            int maxValue = 0;
            int maxCount = capacity / lastVolumn;//最多选多少次
            for (int i = maxCount; i >= 0; i--) {
                int value = solution1(subVolumns, subValues, capacity-(lastVolumn*i)) + lastValue*i;
                maxValue = Math.max(value, maxValue);
            }
            return maxValue;
        }
    }
    
}
