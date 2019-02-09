package com.zfwhub.algorithm.templates.pack_problems;
import java.util.*;

// 01背包，求出Solution
public class Pack01Solution {
    
    // 一般递归
    public static List<Pack> solution1(List<Pack> packs, int capacity) {
        if (packs == null || packs.size() == 0) {
            return new ArrayList<>();
        }
        Pack lastPack = packs.get(packs.size()-1);
        List<Pack> subPacks = packs.subList(0, packs.size()-1);
        // 不选
        List<Pack> packs01 = solution1(subPacks, capacity);
        if (lastPack.weight > capacity) {
            return packs01;
        }
        // 选
        List<Pack> packs02 = solution1(subPacks, capacity - lastPack.weight);
        packs02.add(lastPack);
        if (PackUtil.getValue(packs01) > PackUtil.getValue(packs02)) {
            return packs01;
        } else {
            return packs02;
        }
    }
    
    // 递归 + 备忘录
    public static List<Pack> solution2(List<Pack> packs, int capacity) {
        DPStatus dpStatus = solution2DP(packs, capacity, new HashMap<>());
        return dpStatus.packs;
    }
    
    private static DPStatus solution2DP(List<Pack> packs, int capacity, HashMap<DpMapKey, DPStatus> map) {
        if (packs == null || packs.size() == 0) {
            return new DPStatus(new ArrayList<>(), 0);
        }
        Pack lastPack = packs.get(packs.size()-1);
        List<Pack> subPacks = packs.subList(0, packs.size()-1);
        // 不选
        DPStatus dpStatus01 = null;
        DpMapKey dpMapKey01 = new DpMapKey(subPacks, capacity);
        if (map.containsKey(dpMapKey01)) {
            dpStatus01 = map.get(dpMapKey01);
        } else {
            dpStatus01 = solution2DP(subPacks, capacity, map);
            // 不需要缓存packs.size=0的情况，能省则省。
            if (dpMapKey01.packs.size() != 0) {
                map.put(dpMapKey01, dpStatus01.clone());
            }
        }
        if (lastPack.weight > capacity) {
            return dpStatus01;
        }
        // 选
        DPStatus dpStatus02 = null;
        DpMapKey dpMapKey02 = new DpMapKey(subPacks, capacity - lastPack.weight);
        if (map.containsKey(dpMapKey02)) {
            dpStatus02 = map.get(dpMapKey02);
        } else {
            dpStatus02 = solution2DP(subPacks, capacity - lastPack.weight, map);
            if (dpMapKey02.packs.size() != 0) {
                map.put(dpMapKey02, dpStatus02.clone());
            }
        }
        dpStatus02.packs.add(lastPack);
        dpStatus02.value += lastPack.value;
        return dpStatus01.compareTo(dpStatus02) > 0 ? dpStatus01 : dpStatus02;
    }
    
    // 二维表格递推, 对应Pack01.solution2
    public static List<Pack> solution3(List<Pack> packs, int capacity) {
        DPStatus[][] dp = new DPStatus[packs.size()+1][capacity+1];
        // 默认第一行
        for (int j = 0; j <= capacity; j++) {
            dp[0][j] = new DPStatus(new ArrayList<>(), 0);
        }
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            for (int j = 0; j <= capacity; j++) {
                if (p.weight > j) {
                    dp[i+1][j] = dp[i][j];
                } else {
                    DPStatus dpStatus01 = dp[i][j].clone();
                    DPStatus dpStatus02 = dp[i][j-p.weight].clone();
                    dpStatus02.packs.add(p);
                    dpStatus02.value += p.value;
                    dp[i+1][j] = dpStatus01.compareTo(dpStatus02) > 0 ? dpStatus01 : dpStatus02;
                }
            }
        }
        return dp[packs.size()][capacity].packs;
    }
    
    // 两个一维数组，优化存储空间。对应Pack01.solution3
    public static List<Pack> solution4(List<Pack> packs, int capacity) {
        DPStatus[] dp = new DPStatus[capacity + 1];
        DPStatus[] preDp = new DPStatus[capacity + 1];
        // 默认第一行
        for (int j = 0; j <= capacity; j++) {
            preDp[j] = new DPStatus(new ArrayList<>(), 0);
        }
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            for (int j = 0; j <= capacity; j++) {
                if (p.weight > j) {
                    dp[j] = preDp[j].clone();
                } else {
                    DPStatus dpStatus01 = preDp[j].clone();
                    DPStatus dpStatus02 = preDp[j-p.weight].clone();
                    dpStatus02.packs.add(p);
                    dpStatus02.value += p.value;
                    dp[j] = dpStatus01.compareTo(dpStatus02) > 0 ? dpStatus01 : dpStatus02;
                }
            }
            // 注意必须是深度复制
            preDp = dp.clone();
        }
        return dp[capacity].packs;
    }
    
    // https://zhuanlan.zhihu.com/p/35278858, Accepted
    public static List<Pack> solution5(List<Pack> packs, int capacity) {
        List<Pack> solutionPackList = new ArrayList<>();
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
        solution5Backtrack(packs, solutionPackList, dp, packs.size(), capacity);
        return solutionPackList;
    }
    
    private static void solution5Backtrack(List<Pack> packs, List<Pack> solutionPackList, int[][] dp, int i, int j) {
        if (i > 0) {
            Pack p = packs.get(i-1);
            if (dp[i][j] == dp[i-1][j]) {
                solution5Backtrack(packs, solutionPackList, dp, i-1, j);
            } else {
                solutionPackList.add(p);
                solution5Backtrack(packs, solutionPackList, dp, i-1, j-p.weight);
            }
        }
    }
    
    // 为了优化DP，map缓存的key
    static class DpMapKey {
        
        public List<Pack> packs;
        public int capacity;
        
        public DpMapKey(List<Pack> packs, int capacity) {
            this.packs = packs;
            this.capacity = capacity;
        }
        @Override
        public String toString() {
            return packs.toString() + ", capacity="+capacity+".";
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + capacity;
            result = prime * result + ((packs == null) ? 0 : packs.hashCode());
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
            DpMapKey other = (DpMapKey) obj;
            if (capacity != other.capacity)
                return false;
            if (packs == null) {
                if (other.packs != null)
                    return false;
            } else if (!packs.equals(other.packs))
                return false;
            return true;
        }
        
    }
    
    static class DPStatus implements Comparable<DPStatus>, Cloneable {
        
        public List<Pack> packs;
        public Integer value;//避免每次需要循环，value在添加或删除pack时手动维护。
        
        public DPStatus(List<Pack> packs, Integer value) {
            this.packs = packs;
            this.value = value;
        }
        
        // 判断value是否和packs一致
        public boolean checkValue() {
            return value.equals(PackUtil.getValue(packs));
        }

        @Override
        public int compareTo(DPStatus o) {
            return value.compareTo(o.value);
        }
        
        @Override
        public DPStatus clone() {
            try {
                DPStatus dpStatus = (DPStatus) super.clone();
                dpStatus.packs = new ArrayList<>(packs);
                return dpStatus;
            } catch (CloneNotSupportedException e) {
                // this shouldn't happen, since we are Cloneable
                throw new InternalError();
            }
        }
        
        @Override
        public String toString() {
            return packs.toString() + ", totalValue="+value+".";
        }
        
    }
    
    public static void main(String[] args) {
        int[] volumns = new int[] { 5, 5, 3};
        int[] values = new int[] { 400, 500, 200};
        int capacity = 10;
        List<Pack> packs = solution3(PackUtil.arrayToPackList(volumns, values), capacity);
        List<Pack> packs2 = solution5(PackUtil.arrayToPackList(volumns, values), capacity);
        
        System.out.println(packs);
        System.out.println(packs2);
//        System.out.println(solution2(volumns, values, capacity));
//        System.out.println(solution4(volumns, values, capacity));
    }
    
}
