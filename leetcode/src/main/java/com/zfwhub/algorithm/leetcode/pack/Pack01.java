package com.zfwhub.algorithm.leetcode.pack;

import java.util.*;

/**
 01背包
  跑代码：http://acm.hdu.edu.cn/showproblem.php?pid=2602
 https://zhuanlan.zhihu.com/p/35278858
 https://zhuanlan.zhihu.com/p/30959069
 https://www.cnblogs.com/bahcelor/p/6836695.html
*/
public class Pack01 {

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
        int[] subVolumns = Arrays.copyOf(volumns, N - 1);
        int[] subValues = Arrays.copyOf(values, N - 1);
        if (capacity < volumns[N - 1]) {//最后一个物品装不下
            return solution1(subVolumns, subValues, capacity);
        } else {
            /** 
               1. 放弃最后一个物品
               2. 选择最后一个物品
                                      取其中的较大值
            */
            return Math.max(solution1(subVolumns, subValues, capacity), solution1(subVolumns, subValues, capacity - volumns[N - 1]) + values[N - 1]);
        }
    }

    public static int solution2(int[] volumns, int[] values, int capacity) {
        // 初始化表格，默认第一行全部是 0
        int[][] results = new int[volumns.length + 1][capacity + 1];
        for (int i = 0; i < volumns.length; i++) {
            // j必须从0开始，因为容量为0的包可以装体积为0的物品
            for (int j = 0; j <= capacity; j++) {
                if (volumns[i] > j) {//如果物品放不进背包
                    results[i+1][j] = results[i][j];
                } else {
                    results[i+1][j] = Math.max(results[i][j], results[i][j-volumns[i]] + values[i]);
                }
            }
        }
        return results[volumns.length][capacity];
    }

    // 优化存储空间
    public static int solution3(int[] volumns, int[] values, int capacity) {
        int[] results = new int[capacity + 1];
        int[] preResults = new int[capacity + 1];
        for (int i = 0; i < volumns.length; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (volumns[i] > j) {
                    results[j] = preResults[j];
                } else {
                    results[j] = Math.max(preResults[j], preResults[j-volumns[i]] + values[i]);
                }
            }
            // 注意必须是深度复制
            preResults = results.clone();
        }
        return results[capacity];
    }

    // https://blog.csdn.net/luming_xml/article/details/71922365
    public static int solution4(int[] weigh, int[] value, int weight) {
        int[] dp = new int[weight + 1];
        for (int i = 0; i < weigh.length; i++) {
            for (int j = weight; j >= weigh[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weigh[i]] + value[i]);
            }
        }
        return dp[weight];
    }

    public static void main(String[] args) {
        int[] volumns = new int[] { 2, 3, 4, 5 };
        int[] values = new int[] { 3, 4, 5, 6 };
        int capacity = 8;
        
//        System.out.println(solution1(volumns, values, capacity));
        System.out.println(solution2(volumns, values, capacity));
        System.out.println(solution3(volumns, values, capacity));
    }

}
