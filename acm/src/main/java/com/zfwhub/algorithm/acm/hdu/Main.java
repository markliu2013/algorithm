package com.zfwhub.algorithm.acm.hdu;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nums = sc.nextInt();
        for (int i = 0; i < nums; i++) {
            int count = sc.nextInt();
            int weight = sc.nextInt();
            int[] weigh = new int[count];
            int[] value = new int[count];
            for (int j = 0; j < count; j++) {
                value[j] = sc.nextInt();
            }
            for (int j = 0; j < count; j++) {
                weigh[j] = sc.nextInt();
            }
            System.out.println(solution2(weigh, value, weight));
        }
    }

    public static int solution1(int[] volumns, int[] values, int capacity) {
        // 注意边界
        if (values == null || values.length == 0) {
            return 0;
        }
        int N = values.length;
        // N == 1 的边界可以省略。
        /**if (N == 1) {
            if (capacity < volumns[0]) {
                return 0;
            } else {
                return values[0];
            }
        }*/
        int[] subVolumns = Arrays.copyOf(volumns, N-1);
        int[] subValues = Arrays.copyOf(values, N-1);
        if (capacity < volumns[N-1]) {//最后一个物品装不下
            return solution1(subVolumns, subValues, capacity);
        } else {
            /** 
               1. 放弃最后一个物品
               2. 选择最后一个物品
                                      取其中的较大值
            */
            return Math.max(
                    solution1(subVolumns, subValues, capacity),
                    solution1(subVolumns, subValues, capacity-volumns[N-1]) + values[N-1]
            );
        }
    }
    
    public static int solution2(int[] volumns, int[] values, int capacity) {
        // 初始化表格，默认第一行全部是 0
        int[][] result = new int[volumns.length + 1][capacity + 1];
        for (int i = 0; i < volumns.length; i++) {
            // j必须从0开始，因为容量为0的包可以装体积为0的物品
            for (int j = 0; j <= capacity; j++) {
                if (volumns[i] > j) {//如果物品放不进背包
                    result[i+1][j] = result[i][j];
                } else {
                    result[i+1][j] = Math.max(result[i][j], result[i][j-volumns[i]] + values[i]);
                }
            }
        }
        return result[volumns.length][capacity];
    }
    
    // 优化存储空间
    public static int solution3(int[] volumns, int[] values, int capacity) {
        int N = values.length;
        int[] results = new int[capacity+1];
        int[] preResults = new int[capacity+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (volumns[i-1] > j) {
                    results[j] = preResults[j];
                } else {
                    results[j] = Math.max(preResults[j], preResults[j-volumns[i-1]]+values[i-1]);
                }
            }
            preResults = results;
        }
        return results[capacity];
    }
    
    public static int solution4(int[] weigh, int[] value, int weight) {
        int[] dp = new int[weight + 1];
        for (int i = 0; i < weigh.length; i++) {
            for (int j = weight; j >= weigh[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weigh[i]] + value[i]);
            }
        }
        return dp[weight];
    }
}
