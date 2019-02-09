package com.zfwhub.algorithm.templates.pack_problems;
import java.util.*;

/**
 * 完全背包问题
 * 跑代码：@link com.zfwhub.algorithm.acm.hihocoder.no1043.Main
 */
public class Pack02 {
    
    // 递归超时
    public static int solution1(List<Pack> packs, int capacity) {
        if (capacity < 0) {
            return Integer.MIN_VALUE;
        }
        if (packs.size() == 0) {
            return 0;
        }
        Pack lastPack = packs.get(packs.size()-1);
        List<Pack> subPacks = packs.subList(0, packs.size()-1);
        int maxValue = 0;
        int maxCount = capacity / lastPack.weight;//最多选多少次
        for (int i = maxCount; i >= 0; i--) {
            int value = solution1(subPacks, capacity-(lastPack.weight*i)) + lastPack.value*i;
            maxValue = Math.max(value, maxValue);
        }
        return maxValue;
    }
    
    // 转为01背包。
    public static int solution2(List<Pack> packs, int capacity) {
        List<Pack> packs2 = new ArrayList<>();
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            int count = capacity / p.weight;
            for (int j = 0; j < count; j++) {
                packs2.add(new Pack(p.weight, p.value));
            }
        }
        return Pack01.solution4(packs2, capacity);
    }
    
    // 二维表格递推。对应Pack01.solution2
    public static int solution3(List<Pack> packs, int capacity) {
        // 初始化表格，默认第一行全部是 0
        int[][] dp = new int[packs.size()+1][capacity+1];
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            // j必须从0开始，因为容量为0的包可以装体积为0的物品
            for (int j = 0; j <= capacity; j++) {
                if (p.weight > j) {//如果物品放不进背包
                    dp[i+1][j] = dp[i][j];
                } else {
                    int maxAmount = j / p.weight; // 可以选择的最大数量
                    dp[i+1][j] = dp[i][j];//从0开始尝试
                    for (int k = 1; k <= maxAmount; k++) {//从1开始比较
                        dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j - p.weight*k] + p.value * k);
                    }
                }
            }
        }
        return dp[packs.size()][capacity];
    }
    
    // 两个一维数组，优化存储空间。对应Pack01.solution3
    public static int solution4(List<Pack> packs, int capacity) {
        int[] dp = new int[capacity + 1];
        int[] preDp = new int[capacity + 1];
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            for (int j = 0; j <= capacity; j++) {
                if (p.weight > j) {
                    dp[j] = preDp[j];
                } else {
                    int maxAmount = j / p.weight;
                    dp[j] = preDp[j];
                    for (int k = 1; k <= maxAmount; k++) {
                        dp[j] = Math.max(dp[j], preDp[j - p.weight*k] + p.value * k);
                    }
                }
            }
            // 注意必须是深度复制
            preDp = dp.clone();
        }
        return dp[capacity];
    }
    
    // 对应Pack01.solution6
    public static int solution5(List<Pack> packs, int capacity) {
        int[] dp = new int[capacity + 1];
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            for (int j = capacity; j >= p.weight; j--) {
                int maxAmount = j / p.weight;
                for (int k = 1; k <= maxAmount; k++) {
                    // k=1时，0和1比较。
                    // k=2时，2和max(0,1)比较。
                    dp[j] = Math.max(dp[j], dp[j-p.weight*k] + p.value*k);
                }
            }
        }
        return dp[capacity];
    }
    
}
