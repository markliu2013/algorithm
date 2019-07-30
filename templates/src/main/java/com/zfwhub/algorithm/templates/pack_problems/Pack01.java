package com.zfwhub.algorithm.templates.pack_problems;
import java.util.*;

/**
 * 01背包
 * 跑代码：@link com.zfwhub.algorithm.acm.hdu.pid2602.Main
 * https://zhuanlan.zhihu.com/p/35278858
 * https://zhuanlan.zhihu.com/p/30959069
 * https://www.cnblogs.com/zlcxbb/p/5820666.html
 */
public class Pack01 {

    // 递归超时
    public static int solution1(List<Pack> packs, int capacity) {
        // capacity小于0，返回无穷小，下面我们就不用判断是否capacity足够，直接按方程式写代码。
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
        int[][] dp = new int[packs.size()+1][capacity+1];
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            // j必须从0开始，因为容量为0的包可以装体积为0的物品
            for (int j = 0; j <= capacity; j++) {
                if (p.weight > j) {//如果物品放不进背包
                    dp[i+1][j] = dp[i][j];
                } else {
                    dp[i+1][j] = Math.max(dp[i][j], dp[i][j-p.weight] + p.value);
                }
            }
        }
        return dp[packs.size()][capacity];
    }

    // 两个一维数组，优化存储空间。
    public static int solution3(List<Pack> packs, int capacity) {
        int[] dp = new int[capacity + 1];
        int[] preDp = new int[capacity + 1];
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            for (int j = 0; j <= capacity; j++) {
                if (p.weight > j) {
                    dp[j] = preDp[j];
                } else {
                    dp[j] = Math.max(preDp[j], preDp[j-p.weight] + p.value);
                }
            }
            // 注意必须是深度复制
            preDp = dp.clone();
        }
        return dp[capacity];
    }
    
    // 两个for循环，避免判断。
    public static int solution4(List<Pack> packs, int capacity) {
        int[] dp = new int[capacity + 1];
        int[] preDp = new int[capacity + 1];
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            // 加了这个就会Wrong Answer，如果p.weight > capacity，则数组越界。
            /*for (int j = 0; j < p.weight && j <= capacity; j++) {
                dp[j] = preDp[j];
            }*/
            for (int j = p.weight; j <= capacity; j++) {
                dp[j] = Math.max(preDp[j], preDp[j-p.weight] + p.value);
            }
            preDp = dp.clone();
        }
        return dp[capacity];
    }
    
    // 逆序递推，优化存储空间为一个一维数组。
    public static int solution5(List<Pack> packs, int capacity) {
        int[] dp = new int[capacity + 1];
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            for (int j = capacity; j >= 0; j--) {
                if (p.weight > j) {
                    dp[j] = dp[j];
                } else {
                    dp[j] = Math.max(dp[j], dp[j-p.weight] + p.value);
                }
            }
        }
        return dp[capacity];
    }
    
    // (两个for循环，避免判断) + 逆序递推 。 终极版。
    public static int solution6(List<Pack> packs, int capacity) {
        int[] dp = new int[capacity + 1];
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            for (int j = capacity; j >= p.weight; j--) {
                dp[j] = Math.max(dp[j], dp[j-p.weight] + p.value);
            }
        }
        return dp[capacity];
    }

    // 1.5 一个常数优化
    public static int solution7(List<Pack> packs, int capacity) {
        int[] dp = new int[capacity + 1];
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            int sum = 0;
            for (int j = i; j < packs.size(); j++) {
                sum += packs.get(j).value;
            }
            int max = Math.max(capacity-sum, p.weight);
            for (int j = capacity; j >= max; j--) {
                dp[j] = Math.max(dp[j], dp[j-p.weight] + p.value);
            }
        }
        return dp[capacity];
    }
    
    public static void main(String[] args) {
        int[] volumns = new int[] { 2,3,4,10 };
        int[] values = new int[] { 3,4,5,6 };
        int capacity = 8;
        System.out.println(solution3(PackUtil.arrayToPackList(volumns, values), capacity));
        System.out.println(solution4(PackUtil.arrayToPackList(volumns, values), capacity));
//        System.out.println(solution2(volumns, values, capacity));
//        System.out.println(solution4(volumns, values, capacity));
    }

}
