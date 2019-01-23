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
                System.out.println(PackUtil.getValue(Pack01Solution.solution5(PackUtil.arrayToPackList(weigh, value), weight)));
            }
        } finally {
            sc.close();
        }
    }
}

class Pack01Solution {

    // 一般递归
    public static List<Pack> solution1(List<Pack> packs, int capacity) {
        if (packs == null || packs.size() == 0) {
            return new ArrayList<>();
        }
        Pack lastPack = packs.get(packs.size() - 1);
        List<Pack> subPacks = packs.subList(0, packs.size() - 1);
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
        Pack lastPack = packs.get(packs.size() - 1);
        List<Pack> subPacks = packs.subList(0, packs.size() - 1);
        // 不选
        DpResult dpResult01 = null;
        DpMapKey dpMapKey01 = new DpMapKey(subPacks, capacity);
        if (map.containsKey(dpMapKey01)) {
            dpResult01 = map.get(dpMapKey01);
        } else {
            dpResult01 = solution2DP(subPacks, capacity, map);
            // 不需要缓存packs.size=0的情况，能省则省。
            if (dpMapKey01.packs.size() != 0) {
                map.put(dpMapKey01, new DpResult(new ArrayList<>(dpResult01.packs), dpResult01.value));
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
                map.put(dpMapKey02, new DpResult(new ArrayList<>(dpResult02.packs), dpResult02.value));
            }
        }
        dpResult02.packs.add(lastPack);
        dpResult02.value += lastPack.value;
        return dpResult01.compareTo(dpResult02) > 0 ? dpResult01 : dpResult02;
    }
    
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
                    results[i+1][j] = new DpResult(new ArrayList<>(results[i][j].packs), results[i][j].value);
                } else {
                    DpResult dpResult01 = new DpResult(new ArrayList<>(results[i][j].packs), results[i][j].value);
                    DpResult dpResult02 = new DpResult(new ArrayList<>(results[i][j-p.weight].packs), results[i][j-p.weight].value);
                    dpResult02.packs.add(p);
                    dpResult02.value += p.value;
                    results[i+1][j] = dpResult01.compareTo(dpResult02) > 0 ? dpResult01 : dpResult02;
                }
            }
        }
        return results[packs.size()][capacity].packs;
    }
    
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
        solution5Backtrack(packs, solutionPackList, results, packs.size(), capacity);
        return solutionPackList;
    }
    
    private static void solution5Backtrack(List<Pack> packs, List<Pack> solutionPackList, int[][] results, int i, int j) {
        if (i > 0) {
            Pack p = packs.get(i-1);
            if (results[i][j] == results[i-1][j]) {
                solution5Backtrack(packs, solutionPackList, results, i-1, j);
            } else {
                solutionPackList.add(p);
                solution5Backtrack(packs, solutionPackList, results, i-1, j-p.weight);
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
            return packs.toString() + ", capacity=" + capacity + ".";
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
        int[] volumns = new int[] { 5, 5, 3 };
        int[] values = new int[] { 400, 500, 200 };
        int capacity = 10;
        List<Pack> packs = solution2(PackUtil.arrayToPackList(volumns, values), capacity);

        System.out.println(PackUtil.getValue(packs));
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
