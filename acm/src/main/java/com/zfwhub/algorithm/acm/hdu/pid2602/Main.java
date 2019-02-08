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

    // 超时
    public static int solution1(List<Pack> packs, int capacity) {
        if (capacity < 0) {
            return Integer.MIN_VALUE;
        }
        // 注意边界
        if (packs.size() == 0) {
            return 0;
        }
        Pack lastPack = packs.get(packs.size()-1);
        List<Pack> subPacks = packs.subList(0, packs.size()-1);
        return Math.max(solution1(subPacks, capacity), solution1(subPacks, capacity - lastPack.weight) + lastPack.value);
    }

    public static int solution2(List<Pack> packs, int capacity) {
        // 初始化表格，默认第一行全部是 0
        int[][] results = new int[packs.size()+1][capacity+1];
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            // j必须从0开始，因为容量为0的包可以装体积为0的物品
            for (int j = 0; j <= capacity; j++) {
                if (p.weight > j) {//如果物品放不进背包
                    results[i+1][j] = results[i][j];
                } else {
                    results[i+1][j] = Math.max(results[i][j], results[i][j-p.weight] + p.value);
                }
            }
        }
        return results[packs.size()][capacity];
    }

    // 优化存储空间
    public static int solution3(List<Pack> packs, int capacity) {
        int[] results = new int[capacity + 1];
        int[] preResults = new int[capacity + 1];
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            for (int j = 0; j <= capacity; j++) {
                if (p.weight > j) {
                    results[j] = preResults[j];
                } else {
                    results[j] = Math.max(preResults[j], preResults[j-p.weight] + p.value);
                }
            }
            // 注意必须是深度复制
            preResults = results.clone();
        }
        return results[capacity];
    }
    
    // 递减顺序推算
    public static int solution4(List<Pack> packs, int capacity) {
        int[] results = new int[capacity + 1];
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            for (int j = capacity; j >= 0; j--) {
                if (p.weight > j) {
                    results[j] = results[j];
                } else {
                    results[j] = Math.max(results[j], results[j-p.weight] + p.value);
                }
            }
        }
        return results[capacity];
    }
    
    // TODO solution5 Why wrong?
    public static int solution5(List<Pack> packs, int capacity) {
        int[][] results = new int[packs.size()+1][capacity+1];
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            for (int j = 0; j < p.weight; j++) {
                results[i+1][j] = results[i][j];
            }
            for (int j = p.weight; j <= capacity; j++) {
                results[i+1][j] = Math.max(results[i][j], results[i][j-p.weight] + p.value);
            }
        }
        return results[packs.size()][capacity];
    }
    
    // TODO Why wrong?
    // @link com.zfwhub.algorithm.acm.hihocoder.no1043.Main use solution6 Accepted
    // @link com.zfwhub.algorithm.acm.hdu.pid2191.Main use solution6 Accepted
    public static int solution6(List<Pack> packs, int capacity) {
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
    
    // https://blog.csdn.net/luming_xml/article/details/71922365
    public static int solution9(int[] weigh, int[] value, int weight) {
        int[] dp = new int[weight + 1];
        for (int i = 0; i < weigh.length; i++) {
            for (int j = weight; j >= weigh[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weigh[i]] + value[i]);
            }
        }
        return dp[weight];
    }

    public static void main(String[] args) {
        int[] volumns = new int[] { 2, 3};
        int[] values = new int[] { 3, 4};
        int capacity = 8;
        
        System.out.println(solution1(PackUtil.arrayToPackList(volumns, values), capacity));
//        System.out.println(solution2(volumns, values, capacity));
//        System.out.println(solution4(volumns, values, capacity));
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

    public static List<Pack> arrayToPackList(int[] weights, int[] values, int[] quantities) {
        List<Pack> packs = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            Pack p = new Pack(weights[i], values[i], quantities[i]);
            packs.add(p);
        }
        return packs;
    }

    public static int getValue(List<Pack> packs) {
        int value = 0;
        for (Pack pack : packs) {
            value += pack.value;
        }
        return value;
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
