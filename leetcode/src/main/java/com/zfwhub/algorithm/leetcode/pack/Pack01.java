package com.zfwhub.algorithm.leetcode.pack;

import java.util.Arrays;

// 01背包
// 贼，夜入豪宅，可偷之物甚多，而负重能力有限，偷哪些才更加不枉此行？
// https://zhuanlan.zhihu.com/p/35278858
// https://zhuanlan.zhihu.com/p/30959069
// https://www.cnblogs.com/bahcelor/p/6836695.html
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
        int N = values.length;
        // 初始化表格，默认第一行第一列全部是 0
        int[][] F = new int[N+1][capacity+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (volumns[i-1] > j) {//如果物品放不进背包
                    F[i][j] = F[i-1][j];
                } else {
                    F[i][j] = Math.max(F[i-1][j], F[i-1][j-volumns[i-1]]+values[i-1]);
                }
            }
        }
        return F[N][capacity];
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
    
    public static void main(String[] args) {
        /*int[] volumns = new int[] {5,5,3,4,3};
        int[] values = new int[] {400,500,200,300,350};
        int capacity = 10;*/
        int[] volumns = new int[] {2,3,4,5};
        int[] values = new int[] {3,4,5,6};
        int capacity = 8;
        System.out.println(solution2(volumns, values, capacity));
        System.out.println(solution3(volumns, values, capacity));
    }

}
