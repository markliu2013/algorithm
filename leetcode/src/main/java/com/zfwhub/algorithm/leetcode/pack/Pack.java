package com.zfwhub.algorithm.leetcode.pack;

import java.util.*;

// 背包问题，背包实体类
public class Pack {
    
    public int w;
    public int v;
    
    public Pack() { }

    public Pack(int w, int v) {
        this.w = w;
        this.v = v;
    }
    
    public static List<Pack> arrayToPackList(int[] w, int[] v) {
        List<Pack> packs = new ArrayList<>();
        for (int i = 0; i < v.length; i++) {
            Pack p = new Pack(w[i], v[i]);
            packs.add(p);
        }
        return packs;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + v;
        result = prime * result + w;
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
        if (v != other.v)
            return false;
        if (w != other.w)
            return false;
        return true;
    }
    
}
