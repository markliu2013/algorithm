package com.zfwhub.algorithm.templates.pack_problems;

import java.util.ArrayList;
import java.util.List;

public class PackUtil {
    
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
