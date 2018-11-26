package com.zfwhub.algorithm.acm.hihocoder;

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {
    
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
                int value1 = solution1(subVolumns, subValues, capacity-(lastVolumn*i)) + lastValue*i;
                maxValue = Math.max(value1, maxValue);
            }
            return maxValue;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] costs = new int[n];
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            costs[i] = sc.nextInt();
            values[i] = sc.nextInt();
        }
        System.out.println(solution1(costs, values, m));
    }
}
