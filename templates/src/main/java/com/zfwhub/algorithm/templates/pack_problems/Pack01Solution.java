package com.zfwhub.algorithm.templates.pack_problems;
import java.util.*;

// 01背包，求出Solution
public class Pack01Solution {
    
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
    
    public static void main(String[] args) {
        int[] volumns = new int[] { 5, 5, 3, 4, 3};
        int[] values = new int[] { 400, 500, 200, 300, 350};
        int capacity = 10;
        List<Pack> packs = solution1(PackUtil.arrayToPackList(volumns, values), capacity);
        
        System.out.println(PackUtil.getValue(packs));
//        System.out.println(solution2(volumns, values, capacity));
//        System.out.println(solution4(volumns, values, capacity));
    }
    
}
