package com.zfwhub.algorithm.templates.pack_problems;

import java.util.*;

// 背包问题，背包实体类
public class Pack {
    
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
}
