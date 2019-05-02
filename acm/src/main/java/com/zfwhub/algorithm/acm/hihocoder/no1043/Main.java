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
            System.out.println(solution6(Pack.arrayToPackList(costs, values), m));
        } finally {
            sc.close();
        }
    }

    // 递归超时
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
        return Pack01.solution(packs2, capacity);
    }

    // 二维表格递推
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
    
    public static int solution6(List<Pack> packs, int capacity) {
        List<Pack> packs2 = new ArrayList<>();
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            int k = 0;
            while ((int)Math.pow(2, k) * p.weight <= capacity) {
                packs2.add(new Pack(p.weight * (int)Math.pow(2, k), p.value * (int)Math.pow(2, k)));
                k++;
            }
        }
        return Pack01.solution(packs2, capacity);
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
        int[] dp = new int[capacity + 1];
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            for (int j = capacity; j >= p.weight; j--) {
                dp[j] = Math.max(dp[j], dp[j-p.weight] + p.value);
            }
        }
        return dp[capacity];
    }
}
