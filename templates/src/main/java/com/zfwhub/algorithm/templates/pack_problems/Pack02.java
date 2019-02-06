package com.zfwhub.algorithm.templates.pack_problems;
import java.util.*;

/**
 * 完全背包问题
 * 跑代码：@link com.zfwhub.algorithm.acm.hihocoder.no1043.Main
 */
public class Pack02 {
    
    // 递归
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
    
    public static int solution3(List<Pack> packs, int capacity) {
        // 初始化表格，默认第一行全部是 0
        int[][] results = new int[packs.size()+1][capacity+1];
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            // j必须从0开始，因为容量为0的包可以装体积为0的物品
            for (int j = 0; j <= capacity; j++) {
                if (p.weight > j) {//如果物品放不进背包
                    results[i+1][j] = results[i][j];
                } else {
                    int count = j / p.weight;
                    for (int k = 0; k <= count; k++) {
                        results[i+1][j] = Math.max(results[i+1][j], results[i][j - p.weight*k] + p.value * k);
                    }
                }
            }
        }
        return results[packs.size()][capacity];
    }
    
    // TODO solution4 Why wrong?
    public static int solution4(List<Pack> packs, int capacity) {
        int[] results = new int[capacity + 1];
        int[] preResults = new int[capacity + 1];
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            for (int j = 0; j <= capacity; j++) {
                if (p.weight > j) {
                    results[j] = preResults[j];
                } else {
                    int count = j / p.weight;
                    for (int k = 0; k <= count; k++) {
                        results[j] = Math.max(preResults[j], preResults[j - p.weight*k] + p.value * k);
                    }
                }
            }
            // 注意必须是深度复制
            preResults = results.clone();
        }
        return results[capacity];
    }
    
}
