package com.zfwhub.algorithm.templates.pack_problems;
import java.util.*;

/**
 * 多重背包问题
 * 跑代码：@link com.zfwhub.algorithm.acm.hdu.pid2191.Main
 * https://www.jianshu.com/p/c68589311976
 */
public class Pack03 {
    // 递归
    public static int solution1(List<Pack> packs, int capacity) {
        if (capacity < 0) {
            return Integer.MIN_VALUE;
        }
        if (packs.size() == 0) {
            return 0;
        }
        Pack lastPack = packs.get(packs.size()-1);
        List<Pack> subPacks = packs.subList(0, packs.size()-1);
        int maxValue = 0;
        int maxCount = capacity / lastPack.weight;//最多选多少次
        maxCount = Math.min(maxCount, lastPack.quantity);
        for (int i = maxCount; i >= 0; i--) {
            int value = solution1(subPacks, capacity-(lastPack.weight * i)) + lastPack.value * i;
            maxValue = Math.max(value, maxValue);
        }
        return maxValue;
    }
    
    // 转为01背包。
    public static int solution2(List<Pack> packs, int capacity) {
        List<Pack> packs2 = new ArrayList<>();
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            int count = Math.min(capacity / p.weight, p.quantity);
            for (int j = 0; j < count; j++) {
                packs2.add(new Pack(p.weight, p.value));
            }
        }
        return Pack01.solution4(packs2, capacity);
    }
    
    // 3.3 转化为01背包 + 二进制思想
    public static int solution3(List<Pack> packs, int capacity) {
        List<Pack> packs2 = new ArrayList<>();
        for (int i = 0; i < packs.size(); i++) {
            Pack p = packs.get(i);
            int k = 1;
            int M = p.quantity;
            while (k < M) {
                packs2.add(new Pack(p.weight * k, p.value * k));
                M = M - k;
                k = 2*k;
            }
            packs2.add(new Pack(p.weight * M, p.value * M));
        }
        return Pack01.solution6(packs2, capacity);
    }
    
    public static void main(String[] args) {
        int M = 13;
        int k = 1;
        while (k < M) {
            System.out.println(k);
            M = M - k;
            k = 2*k;
        }
        System.out.println(M);
    }
    
}
