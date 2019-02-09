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
                System.out.println(Pack01.solution5(PackUtil.arrayToPackList(weigh, value), weight));
            }
        } finally {
            sc.close();
        }
    }
}

class Pack01 {
    public static int solution5(List<Pack> packs, int capacity) {
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

class PackUtil {

    public static List<Pack> arrayToPackList(int[] weights, int[] values) {
        List<Pack> packs = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            Pack p = new Pack(weights[i], values[i]);
            packs.add(p);
        }
        return packs;
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

    @Override
    public String toString() {
        return "[weight=" + weight + ", value=" + value + "]";
    }

}
