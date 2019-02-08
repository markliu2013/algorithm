package com.zfwhub.algorithm.templates.pack_problems;
import java.util.*;

/**
 * 01背包
 * 跑代码：@link com.zfwhub.algorithm.acm.hdu.pid2602.Main
 * https://zhuanlan.zhihu.com/p/35278858
 * https://zhuanlan.zhihu.com/p/30959069
 * https://www.cnblogs.com/bahcelor/p/6836695.html
 * https://www.cnblogs.com/zlcxbb/p/5820666.html
 */
public class Pack01 {

    // 递归超时
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

    // 二维表格递推
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

    // 两个一维数组，优化存储空间。
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
    
    // TODO Why wrong?
    // @link com.zfwhub.algorithm.acm.hihocoder.no1043.Main use solution4 Accepted
    // @link com.zfwhub.algorithm.acm.hdu.pid2191.Main use solution4 Accepted
    // 两个for循环，避免判断。
    public static int solution4(List<Pack> packs, int capacity) {
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
    
    // 逆序递推，优化存储空间为一个一维数组。
    public static int solution5(List<Pack> packs, int capacity) {
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
