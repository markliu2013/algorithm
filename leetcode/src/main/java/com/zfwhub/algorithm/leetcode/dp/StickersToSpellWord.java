package com.zfwhub.algorithm.leetcode.dp;

import java.util.*;

// https://leetcode.com/problems/stickers-to-spell-word/
public class StickersToSpellWord {
    
    public static int minStickers(String[] stickers, String target) {
        // 先检查是否存在解
        if (!hasSolution(stickers, target)) {
            return -1;
        } else {
            return dp(parseStickers(stickers, target), targetToList(target), new HashMap<>());
        }
    }
    
    private static boolean hasSolution(String[] stickers, String target) {
        Set<Character> targetSet = new HashSet<>();
        for (int i = 0; i < target.length(); i++) {
            targetSet.add(target.charAt(i));
        }
        Set<Character> stickerSet = new HashSet<>();
        for (int i = 0; i < stickers.length; i++) {
            for (int j = 0; j < stickers[i].length(); j++) {
                stickerSet.add(stickers[i].charAt(j));
            }
        }
        return stickerSet.containsAll(targetSet);
    }
    
    private static List<List<Character>> parseStickers(String[] stickers, String target) {
        List<List<Character>> list = new ArrayList<>();
        Set<Character> targetSet = new HashSet<>();
        for (int i = 0; i < target.length(); i++) {
            targetSet.add(target.charAt(i));
        }
        for (int i = 0; i < stickers.length; i++) {
            List<Character> list2 = new ArrayList<>();
            for (int j = 0; j < stickers[i].length(); j++) {
                if (targetSet.contains(stickers[i].charAt(j))) {
                    list2.add(stickers[i].charAt(j));
                }
            }
            Collections.sort(list2);//必须排序之后再添加，以保证唯一。
            if (list2.size() > 0 && !list.contains(list2)) {
                list.add(list2);
            }
        }
        return list;
    }
    
    private static List<Character> targetToList(String target) {
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < target.length(); i++) {
            list.add(target.charAt(i));
        }
        return list;
    }
    
    private static int dp(List<List<Character>> stickers, List<Character> target, HashMap<StickersToSpellWord.DpMapKey, Integer> map) {
        if (target.size() == 0) {
            return 0;
        }
        if (stickers.size() == 0) {
            return -1;
        }
        List<List<Character>> subStickers = stickers.subList(0, stickers.size()-1);
        List<Character> lastSticker = stickers.get(stickers.size()-1);
        List<StickerResult> stickerResults = go(lastSticker, target);
        int minValue = Integer.MAX_VALUE-5;
        for (int i = 0; i < stickerResults.size(); i++) {
            StickerResult stickerResult = stickerResults.get(i);
            DpMapKey dpMapKey = new StickersToSpellWord.DpMapKey(subStickers, stickerResult.target);
            int value1 = -1;
            if (map.containsKey(dpMapKey)) {
                value1 = map.get(dpMapKey);
            } else {
                value1 = dp(subStickers, stickerResult.target, map);
                map.put(dpMapKey, value1);
            }
            if (value1 != -1) {
                int value = value1 + stickerResult.count;
                minValue = Math.min(minValue, value);                    
            }
        }
        return minValue;
    }
    
    private static List<StickerResult> go(List<Character> sticker, List<Character> target) {
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
            appendList(list2, sticker);
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
    
    private static void remove(List<Character> target, List<Character> list) {
        Iterator<Character> it = list.iterator();
        while (it.hasNext()) {
            Character c = it.next();
            if (target.contains(c)) {
                target.remove(c);
                it.remove();
            }
        }
    }
    
    private static void appendList(List<Character> list, List<Character> sticker) {
        for (int j = 0; j < sticker.size(); j++) {
            list.add(sticker.get(j));
        }
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
    
    static class DpMapKey {
        public List<List<Character>> stickers;
        public List<Character> target;
        
        public DpMapKey(List<List<Character>> stickers, List<Character> target) {
            this.stickers = stickers;
            this.target = target;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((stickers == null) ? 0 : stickers.hashCode());
            result = prime * result + ((target == null) ? 0 : target.hashCode());
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
            if (stickers == null) {
                if (other.stickers != null)
                    return false;
            } else if (!stickers.equals(other.stickers))
                return false;
            if (target == null) {
                if (other.target != null)
                    return false;
            } else if (!target.equals(other.target))
                return false;
            return true;
        }
    }
    
    public static void main(String[] args) {
        String[] stickers = new String[] {"own","tone","feel","there","invent","trade","follow","home","still","check","market","cotton","blow","use","afraid","indicate","support","sail","charge","been","children","how","behind","size","hat","match","count","notice","food","excite","felt","serve","guess","else","quick","student","consonant","strong","wait","ago","enemy","oh","industry","cover","cat","while","nose","wild","quite","shine"};
        String target = "donepower";
        System.out.println(minStickers(stickers, target));
    }
    
}



