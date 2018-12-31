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
            // TODO 去掉没有作用的
            return dp(stickers, list1);
        }
    }
    
    public static int dp(String[] stickers, List<Character> target) {
        if (target.size() == 0) {
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
        if (stickers.length == 1) {
            List<StickerResult> stickerResults = go(stickers[0], target);
            StickerResult stickerResult = stickerResults.get(stickerResults.size()-1);
            if (stickerResult.target.size() == 0) {
                return stickerResult.count;
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
            int minValue = Integer.MAX_VALUE-5;
            for (int i = 0; i < stickerResults.size(); i++) {
                StickerResult stickerResult = stickerResults.get(i);
                int value1 = dp(subStickers, stickerResult.target);
                if (value1 != -1) {
                    int value = value1 + stickerResult.count;
                    minValue = Math.min(minValue, value);                    
                }
            }
            if (minValue == Integer.MAX_VALUE - 5) {
                
            }
            return minValue;
        }
    }
    
    // sticker是否包含target中的某个字符
    public static boolean contains(String sticker, List<Character> target) {
        for (int i = 0; i < sticker.length(); i++) {
            if (target.contains(sticker.charAt(i))) {
                return true;
            }
        }
        return false;
    }
    
    public static List<StickerResult> go(String sticker, List<Character> target) {
        List<StickerResult> list = new ArrayList<>();
        List<Character> targetList = new ArrayList<>(target);
        // 0个
        StickerResult stickerResult0 = new StickersToSpellWord.StickerResult(0, new ArrayList<>(target));
        list.add(stickerResult0);
        boolean flag = true;
        int count = 0;
        List<Character> list2 = new ArrayList<>();
        while (flag) {
            count++;
            list2 = getList(sticker, list2);
            List<Character> list3 = new ArrayList<>(targetList);
            remove(targetList, list2);
            StickerResult stickerResult = new StickersToSpellWord.StickerResult(count, new ArrayList<>(targetList));
            list.add(stickerResult);
            if (targetList.size() == 0 || targetList.size() == list3.size()) {
                flag = false;
            }
        }
        return list;
    }
    
    public static void remove(List<Character> target, List<Character> list) {
        Iterator<Character> it = list.iterator();
        while (it.hasNext()) {
            Character c = it.next();
            if (target.contains(c)) {
                target.remove(c);
                it.remove();
            }
        }
    }
    
    public static List<Character> getList(String sticker, List<Character> list) {
        List<Character> result = new ArrayList<>(list);
        for (int j = 0; j < sticker.length(); j++) {
            result.add(sticker.charAt(j));
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
        String[] stickers = new String[] {"control","heart","interest","stream","sentence",
                "soil","wonder","them","month","slip","table","miss","boat","speak",
                "figure","no","perhaps","twenty","throw","rich","capital","save","method",
                "store","meant","life","oil","string","song","food","am","who","fat","if",
                "put","path","come","grow","box","great","word","object","stead","common",
                "fresh","the","operate","where","road","mean"};
        String target = "stoodcrease";
        System.out.println(minStickers(stickers, target));
    }
    
}



