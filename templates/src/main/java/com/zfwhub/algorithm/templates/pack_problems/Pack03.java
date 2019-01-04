package com.zfwhub.algorithm.templates.pack_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 多重背包问题
 * 跑代码：http://acm.hdu.edu.cn/showproblem.php?pid=2191
 */
public class Pack03 {
    
    public static int solution1(int[] volumns, int[] values, int[] quantities, int capacity) {
        if (values.length == 0) {
            return 0;
        }
        int[] subVolumns = Arrays.copyOf(volumns, volumns.length - 1);
        int[] subValues = Arrays.copyOf(values, values.length - 1);
        int[] subQuantities = Arrays.copyOf(quantities, quantities.length - 1);
        int lastVolumn = volumns[volumns.length-1];
        int lastValue = values[values.length-1];
        int lastQuantity = quantities[quantities.length-1];
        if (lastVolumn > capacity) {
            return solution1(subVolumns, subValues, subQuantities, capacity);
        } else {
            int maxValue = 0;
            int maxCount = capacity / lastVolumn;//最多选多少次
            maxCount = Math.min(maxCount, lastQuantity);
            for (int i = maxCount; i >= 0; i--) {
                int value = solution1(subVolumns, subValues, subQuantities, capacity-(lastVolumn*i)) + lastValue*i;
                maxValue = Math.max(value, maxValue);
            }
            return maxValue;
        }
    }
    
    public static int solution2(int[] volumns, int[] values, int[] quantities, int capacity) {
        List<Integer> volumnList = new ArrayList<>();
        List<Integer> valueList = new ArrayList<>();
        for (int i = 0; i < volumns.length; i++) {
            int count = Math.min(capacity / volumns[i], quantities[i]);
            for (int j = 0; j < count; j++) {
                volumnList.add(volumns[i]);
                valueList.add(values[i]);
            }
        }
        return solution2Helper(volumnList.toArray(new Integer[0]), valueList.toArray(new Integer[0]), capacity);
    }
    
    public static int solution2Helper(Integer[] weigh, Integer[] value, int weight) {
        int[] dp = new int[weight + 1];
        for (int i = 0; i < weigh.length; i++) {
            for (int j = weight; j >= weigh[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weigh[i]] + value[i]);
            }
        }
        return dp[weight];
    }
    
}
