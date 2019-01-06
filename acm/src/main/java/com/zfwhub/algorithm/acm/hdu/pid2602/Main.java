package com.zfwhub.algorithm.acm.hdu.pid2602;

import java.util.*;

// http://acm.hdu.edu.cn/showproblem.php?pid=2602
// 0 1 背包
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
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
                System.out.println(solution6(Pack.arrayToPackList(weigh, value), weight));
            }
        } finally {
            sc.close();
        }
    }

    public static int solution1(List<Pack> packs, int capacity) {
        if (capacity < 0) {
            return Integer.MIN_VALUE;
        }
        // 注意边界
        if (packs.size() == 0) {
            return 0;
        }
        Pack lastPack = packs.get(packs.size() - 1);
        List<Pack> subPacks = packs.subList(0, packs.size() - 1);
        return Math.max(solution1(subPacks, capacity), solution1(subPacks, capacity - lastPack.weight) + lastPack.value);
    }

    public static int solution2(List<Pack> packs, int capacity) {
        // 初始化表格，默认第一行全部是 0
        int[][] results = new int[packs.size() + 1][capacity + 1];
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            // j必须从0开始，因为容量为0的包可以装体积为0的物品
            for (int j = 0; j <= capacity; j++) {
                if (p.weight > j) {//如果物品放不进背包
                    results[i + 1][j] = results[i][j];
                } else {
                    results[i + 1][j] = Math.max(results[i][j], results[i][j - p.weight] + p.value);
                }
            }
        }
        return results[packs.size()][capacity];
    }

    public static int solution3(List<Pack> packs, int capacity) {
        int[] results = new int[capacity + 1];
        int[] preResults = new int[capacity + 1];
        for (int i = 0; i < packs.size(); i++) {
            for (int j = 0; j <= capacity; j++) {
                Pack p = packs.get(i);
                if (p.weight > j) {
                    results[j] = preResults[j];
                } else {
                    results[j] = Math.max(preResults[j], preResults[j - p.weight] + p.value);
                }
            }
            // 注意必须是深度复制
            preResults = results.clone();
        }
        return results[capacity];
    }

    public static int solution4(List<Pack> packs, int capacity) {
        int[] results = new int[capacity + 1];
        for (int i = 0; i < packs.size(); i++) {
            for (int j = capacity; j >= 0; j--) {
                Pack p = packs.get(i);
                if (p.weight > j) {
                    results[j] = results[j];
                } else {
                    results[j] = Math.max(results[j], results[j - p.weight] + p.value);
                }
            }
        }
        return results[capacity];
    }

    public static int solution5(List<Pack> packs, int capacity) {
        int[][] results = new int[packs.size() + 1][capacity + 1];
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            for (int j = 0; j < p.weight; j++) {
                results[i + 1][j] = results[i][j];
            }
            for (int j = p.weight; j <= capacity; j++) {
                results[i + 1][j] = Math.max(results[i][j], results[i][j - p.weight] + p.value);
            }
        }
        return results[packs.size()][capacity];
    }

    public static int solution6(List<Pack> packs, int capacity) {
        int[] results = new int[capacity + 1];
        int[] preResults = new int[capacity + 1];
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            for (int j = 0; j < p.weight; j++) {
                results[j] = preResults[j];
            }
            for (int j = p.weight; j <= capacity; j++) {
                results[j] = Math.max(preResults[j], preResults[j - p.weight] + p.value);
            }
            preResults = results.clone();
        }
        return results[capacity];
    }
}

// @see com.zfwhub.algorithm.templates.pack_problems.Pack
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
