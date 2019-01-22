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
            map.put(dpMapKey01, new DpResult(new ArrayList<>(dpResult01.packs), dpResult01.value));
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
            if (dpMapKey02.size()) {
                
            }
            map.put(dpMapKey02, new DpResult(new ArrayList<>(dpResult02.packs), dpResult02.value));
        }
        dpResult02.packs.add(lastPack);
        dpResult02.value += lastPack.value;
        return dpResult01.compareTo(dpResult02) > 0 ? dpResult01 : dpResult02;
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
    
    static class DpResult implements Comparable<DpResult> {
        
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
        public String toString() {
            return packs.toString() + ", totalValue="+value+".";
        }
        
    }
    
    public static void main(String[] args) {
        int[] volumns = new int[] { 5, 5, 3};
        int[] values = new int[] { 400, 500, 200};
        int capacity = 10;
        List<Pack> packs = solution2(PackUtil.arrayToPackList(volumns, values), capacity);
        
        System.out.println(PackUtil.getValue(packs));
//        System.out.println(solution2(volumns, values, capacity));
//        System.out.println(solution4(volumns, values, capacity));
    }
    
}
