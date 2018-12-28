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
        if (stickers.length == 0) {
            return 0;
        }
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
        } else { // lastSticker可以选多少个？一个个的尝试最优解
            List<StickerResult> stickerResults = go(lastSticker, target);
            int minValue = Integer.MAX_VALUE;
            for (int i = 0; i < stickerResults.size(); i++) {
                StickerResult stickerResult = stickerResults.get(i);
                int value = dp(subStickers, stickerResult.target) + stickerResult.count;
                minValue = Math.min(minValue, value);
            }
            return minValue;
        }
    }
    
    // sticker是否包含target中的某个字符
    public static boolean contains(String sticker, List<Character> target) {
        for (int i = 0; i < target.size(); i++) {
            if (sticker.indexOf(target.get(i)) >= 0) {
                return true;
            }
        }
        return false;
    }
    
    public static List<StickerResult> go(String sticker, List<Character> target) {
        List<StickerResult> list = new ArrayList<>();
        boolean flag = true;
        int count = 0;
        while (flag) {
            count++;
            List<Character> list2 = getList(sticker, count);
            List<Character> list3 = remove(target, list2);
            if (list3.size() == target.size()) {
                flag = false;
            } else {
                StickerResult stickerResult = new StickersToSpellWord.StickerResult(count, list3);
                list.add(stickerResult);
                target = list3;
            }
        }
        return list;
    }
    
    public static List<Character> remove(List<Character> target, List<Character> list) {
        List<Character> result = new ArrayList<>(target);
        for (int i = 0; i < list.size(); i++) {
            result.remove(list.get(i));
        }
        return result;
    }
    
    public static List<Character> getList(String sticker, int count) {
        List<Character> result = new ArrayList<>();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                for (int j = 0; j < sticker.length(); j++) {
                    result.add(sticker.charAt(j));
                }
            }
        }
        return result;
    }
    
    static class StickerResult {
        public int count;
        public List<Character> target;
        public StickerResult() {
        }
        public StickerResult(int count, List<Character> target) {
            this.count = count;
            this.target = target;
        }
    }
    
    public static void main(String[] args) {
        String[] stickers = new String[] {"with", "example", "science"};
        String target = "thehat";
        System.out.println(minStickers(stickers, target));
    }
    
}



