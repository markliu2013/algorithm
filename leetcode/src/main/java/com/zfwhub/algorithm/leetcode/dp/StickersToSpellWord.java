package com.zfwhub.algorithm.leetcode.dp;

import java.util.*;

// https://leetcode.com/problems/stickers-to-spell-word/
public class StickersToSpellWord {
    
    public static int minStickers(String[] stickers, String target) {
        // 先检查是否存在解
        Set<Character> set1 = new HashSet<>();
        for (int i = 0; i < target.length(); i++) {
            set1.add(target.charAt(i));
        }
        Set<Character> set2 = new HashSet<>();
        for (int i = 0; i < stickers.length; i++) {
            for (int j = 0; j < stickers[i].length(); j++) {
                set2.add(stickers[i].charAt(j));
            }
        }
        if (!set2.containsAll(set1)) {
            return -1;
        } else {
            List<Character> list1 = new ArrayList<>();
            for (int i = 0; i < target.length(); i++) {
                list1.add(target.charAt(i));
            }
            return dp(stickers, list1);
        }
    }
    
    public static int dp(String[] stickers, List<Character> target) {
        if (target.size() == 1) {
            Set<Character> set2 = new HashSet<>();
            for (int i = 0; i < stickers.length; i++) {
                for (int j = 0; j < stickers[i].length(); j++) {
                    set2.add(stickers[i].charAt(j));
                }
            }
            if (set2.contains(target.get(0))) {
                return 1;
            } else {
                return -1;
            }
        }
        String[] subStickers = Arrays.copyOf(stickers, stickers.length-1);
        String lastSticker = stickers[stickers.length-1];
        // lastSticker不包含target中任何一个
        if (!contains(lastSticker, target)) {
            return dp(subStickers, target);
        } else {
            
        }
        return 0;
    }
    
    // sticker是否包含target中的某个字符
    public static boolean contains(String sticker, List<Character> target) {
        for (int i = 0; i < target.size(); i++) {
            if (sticker.indexOf(target.get(i)) < 0) {
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        String[] stickers = new String[] {"with", "example", "science"};
        String target = "thehat";
        System.out.println(minStickers(stickers, target));
    }
    
}
