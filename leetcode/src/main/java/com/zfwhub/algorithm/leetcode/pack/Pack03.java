package com.zfwhub.algorithm.leetcode.pack;

import java.util.Arrays;

//多重背包问题
//跑代码：http://acm.hdu.edu.cn/showproblem.php?pid=2191
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
    
}
