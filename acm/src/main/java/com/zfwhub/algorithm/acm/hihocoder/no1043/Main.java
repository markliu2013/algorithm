package com.zfwhub.algorithm.acm.hihocoder.no1043;

import java.util.*;

// https://hihocoder.com/problemset/problem/1043
// 完全背包问题
public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[] costs = new int[n];
            int[] values = new int[n];
            for (int i = 0; i < n; i++) {
                costs[i] = sc.nextInt();
                values[i] = sc.nextInt();
            }
            System.out.println(solution3(Pack.arrayToPackList(costs, values), m));
        } finally {
            sc.close();
        }
    }
    
    public static int solution1(List<Pack> packs, int capacity) {
        if (capacity < 0) {
            return Integer.MIN_VALUE;
        }
        if (packs.size() == 0) {
            return 0;
        }
        Pack lastPack = packs.get(packs.size() - 1);
        List<Pack> subPacks = packs.subList(0, packs.size() - 1);
        int maxValue = 0;
        int maxCount = capacity / lastPack.weight;//最多选多少次
        for (int i = maxCount; i >= 0; i--) {
            int value = solution1(subPacks, capacity - (lastPack.weight * i)) + lastPack.value * i;
            maxValue = Math.max(value, maxValue);
        }
        return maxValue;
    }

    // 转换为01背包。
    public static int solution2(List<Pack> packs, int capacity) {
        List<Pack> packs2 = new ArrayList<>();
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            int count = capacity / p.weight;
            for (int j = 0; j < count; j++) {
                packs2.add(new Pack(p.weight, p.value));
            }
        }
        return Pack01.solution(packs2, capacity);
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
}

//@see com.zfwhub.algorithm.templates.pack_problems.Pack
class Pack {

    public int weight;
    public int value;

    public Pack() {
    }

    public Pack(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public static List<Pack> arrayToPackList(int[] weights, int[] values) {
        List<Pack> packs = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            Pack p = new Pack(weights[i], values[i]);
            packs.add(p);
        }
        return packs;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + value;
        result = prime * result + weight;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pack other = (Pack) obj;
        if (value != other.value)
            return false;
        if (weight != other.weight)
            return false;
        return true;
    }
}

class Pack01 {
    public static int solution(List<Pack> packs, int capacity) {
        int[] results = new int[capacity + 1];
        int[] preResults = new int[capacity + 1];
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            for (int j = 0; j < p.weight; j++) {
                results[j] = preResults[j];
            }
            for (int j = p.weight; j <= capacity; j++) {
                results[j] = Math.max(preResults[j], preResults[j-p.weight] + p.value);
            }
            preResults = results.clone();
        }
        return results[capacity];
    }
}
