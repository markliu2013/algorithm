package com.zfwhub.algorithm.acm.hdu;

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nums = sc.nextInt();
        for (int i = 0; i < nums; i++) {
            int weight = sc.nextInt();
            int count = sc.nextInt();
            int[] weigh = new int[count];
            int[] value = new int[count];
            int[] quantities = new int[count];
            for (int j = 0; j < count; j++) {
                weigh[j] = sc.nextInt();
                value[j] = sc.nextInt();
                quantities[j] = sc.nextInt();
            }
            System.out.println(solution1(weigh, value, quantities, weight));
        }
    }
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
