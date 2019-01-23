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
        DpResult dpResult = solution2DP(packs, capacity, new HashMap<>());
        return dpResult.packs;
    }
    
    private static DpResult solution2DP(List<Pack> packs, int capacity, HashMap<DpMapKey, DpResult> map) {
        if (packs == null || packs.size() == 0) {
            return new DpResult(new ArrayList<>(), 0);
        }
        Pack lastPack = packs.get(packs.size()-1);
        List<Pack> subPacks = packs.subList(0, packs.size()-1);
        // 不选
        DpResult dpResult01 = null;
        DpMapKey dpMapKey01 = new DpMapKey(subPacks, capacity);
        if (map.containsKey(dpMapKey01)) {
            dpResult01 = map.get(dpMapKey01);
        } else {
            dpResult01 = solution2DP(subPacks, capacity, map);
            // 不需要缓存packs.size=0的情况，能省则省。
            if (dpMapKey01.packs.size() != 0) {
                map.put(dpMapKey01, dpResult01.clone());
            }
        }
        if (lastPack.weight > capacity) {
            return dpResult01;
        }
        // 选
        DpResult dpResult02 = null;
        DpMapKey dpMapKey02 = new DpMapKey(subPacks, capacity - lastPack.weight);
        if (map.containsKey(dpMapKey02)) {
            dpResult02 = map.get(dpMapKey02);
        } else {
            dpResult02 = solution2DP(subPacks, capacity - lastPack.weight, map);
            if (dpMapKey02.packs.size() != 0) {
                map.put(dpMapKey02, dpResult02.clone());
            }
        }
        dpResult02.packs.add(lastPack);
        dpResult02.value += lastPack.value;
        return dpResult01.compareTo(dpResult02) > 0 ? dpResult01 : dpResult02;
    }
    
    // 对应Pack01.solution2
    public static List<Pack> solution3(List<Pack> packs, int capacity) {
        DpResult[][] results = new DpResult[packs.size()+1][capacity+1];
        // 默认第一行
        for (int j = 0; j <= capacity; j++) {
            results[0][j] = new DpResult(new ArrayList<>(), 0);
        }
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            for (int j = 0; j <= capacity; j++) {
                if (p.weight > j) {
                    results[i+1][j] = results[i][j];
                } else {
                    DpResult dpResult01 = results[i][j].clone();
                    DpResult dpResult02 = results[i][j-p.weight].clone();
                    dpResult02.packs.add(p);
                    dpResult02.value += p.value;
                    results[i+1][j] = dpResult01.compareTo(dpResult02) > 0 ? dpResult01 : dpResult02;
                }
            }
        }
        return results[packs.size()][capacity].packs;
    }
    
    // 对应Pack01.solution3
    public static List<Pack> solution4(List<Pack> packs, int capacity) {
        DpResult[] results = new DpResult[capacity + 1];
        DpResult[] preResults = new DpResult[capacity + 1];
        // 默认第一行
        for (int j = 0; j <= capacity; j++) {
            preResults[j] = new DpResult(new ArrayList<>(), 0);
        }
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            for (int j = 0; j <= capacity; j++) {
                if (p.weight > j) {
                    results[j] = preResults[j].clone();
                } else {
                    DpResult dpResult01 = preResults[j].clone();
                    DpResult dpResult02 = preResults[j-p.weight].clone();
                    dpResult02.packs.add(p);
                    dpResult02.value += p.value;
                    results[j] = dpResult01.compareTo(dpResult02) > 0 ? dpResult01 : dpResult02;
                }
            }
            // 注意必须是深度复制
            preResults = results.clone();
        }
        return results[capacity].packs;
    }
    
    // https://zhuanlan.zhihu.com/p/35278858
    public static List<Pack> solution5(List<Pack> packs, int capacity) {
        List<Pack> solutionPackList = new ArrayList<>();
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
        return solutionPackList;
    }
    
    private static void solution5Backtrack(List<Pack> packs, List<Pack> solutionPackList) {
        
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
    
    // TODO 深入了解 Cloneable Comparable Serializable
    static class DpResult implements Comparable<DpResult>, Cloneable {
        
        public List<Pack> packs;
        public Integer value;//避免每次需要循环，value在添加或删除pack时手动维护。
        
        public DpResult(List<Pack> packs, Integer value) {
            this.packs = packs;
            this.value = value;
        }
        
        // 判断value是否和packs一致
        public boolean checkValue() {
            return value.equals(PackUtil.getValue(packs));
        }

        @Override
        public int compareTo(DpResult o) {
            return value.compareTo(o.value);
        }
        
        @Override
        public DpResult clone() {
            try {
                DpResult dpResult = (DpResult) super.clone();
                dpResult.packs = new ArrayList<>(packs);
                return dpResult;
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
