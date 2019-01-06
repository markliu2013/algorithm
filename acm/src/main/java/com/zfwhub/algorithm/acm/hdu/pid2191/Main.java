package com.zfwhub.algorithm.acm.hdu.pid2191;

import java.util.*;

// http://acm.hdu.edu.cn/showproblem.php?pid=2191
// 多重背包
public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            int nums = sc.nextInt();
            for (int i = 0; i < nums; i++) {
                int weight = sc.nextInt();
                int count = sc.nextInt();
                int[] weighs = new int[count];
                int[] values = new int[count];
                int[] quantities = new int[count];
                for (int j = 0; j < count; j++) {
                    weighs[j] = sc.nextInt();
                    values[j] = sc.nextInt();
                    quantities[j] = sc.nextInt();
                }
                System.out.println(solution2(Pack.arrayToPackList(weighs, values, quantities), weight));
            }
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
        maxCount = Math.min(maxCount, lastPack.quantity);
        for (int i = maxCount; i >= 0; i--) {
            int value = solution1(subPacks, capacity - (lastPack.weight * i)) + lastPack.value * i;
            maxValue = Math.max(value, maxValue);
        }
        return maxValue;
    }

    public static int solution2(List<Pack> packs, int capacity) {
        List<Pack> packs2 = new ArrayList<>();
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            int count = Math.min(capacity / p.weight, p.quantity);
            for (int j = 0; j < count; j++) {
                packs2.add(new Pack(p.weight, p.value));
            }
        }
        return Pack01.solution(packs2, capacity);
    }
}

class Pack {

    public int weight;
    public int value;
    public int quantity;

    public Pack(int weight, int value) {
        this.weight = weight;
        this.value = value;
        this.quantity = 1;
    }

    public Pack(int weight, int value, int quantity) {
        this.weight = weight;
        this.value = value;
        this.quantity = quantity;
    }

    public static List<Pack> arrayToPackList(int[] weights, int[] values) {
        List<Pack> packs = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            Pack p = new Pack(weights[i], values[i]);
            packs.add(p);
        }
        return packs;
    }

    public static List<Pack> arrayToPackList(int[] weights, int[] values, int[] quantities) {
        List<Pack> packs = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            Pack p = new Pack(weights[i], values[i], quantities[i]);
            packs.add(p);
        }
        return packs;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + quantity;
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
        if (quantity != other.quantity)
            return false;
        if (value != other.value)
            return false;
        if (weight != other.weight)
            return false;
        return true;
    }
}

class Pack01 {
    // 递减顺序推算
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
