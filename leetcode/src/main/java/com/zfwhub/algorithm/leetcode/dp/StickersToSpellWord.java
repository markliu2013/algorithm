package com.zfwhub.algorithm.leetcode.dp;

import java.util.*;

// https://leetcode.com/problems/stickers-to-spell-word/
// 动态规划，从上往下递归，一直超时
// https://leetcode.com/submissions/detail/200048701/
public class StickersToSpellWord {
    
    public static int minStickers(String[] stickers, String target) {
        // 先检查是否存在解
        if (!hasSolution(stickers, target)) {
            return -1;
        } else {
            List<Character> targetList = stringToList(target);
            Collections.sort(targetList);// 排序后增加map命中率
            return dp(parseStickers(stickers, target), targetList, new HashMap<>());
        }
    }
    
    // target的每个字符都要在stickers中找到，否则问题无解
    static boolean hasSolution(String[] stickers, String target) {
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
    
    // stickers 转为二维list，并且去掉不包含target字符的，因为去掉不影响问题的解
    static List<List<Character>> parseStickers(String[] stickers, String target) {
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
    
    static List<Character> stringToList(String target) {
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < target.length(); i++) {
            list.add(target.charAt(i));
        }
        return list;
    }
    
    static int dp(List<List<Character>> stickers, List<Character> target, HashMap<DpMapKey, Integer> map) {
        if (target.size() == 0) {
            return 0;
        }
        if (stickers.size() == 0) {
            return -1;
        }
        List<List<Character>> subStickers = stickers.subList(0, stickers.size()-1);
        List<Character> lastSticker = stickers.get(stickers.size()-1);
        List<StickerResult> stickerResults = go(lastSticker, target);
        int minValue = -1;
        for (int i = 0; i < stickerResults.size(); i++) {
            StickerResult stickerResult = stickerResults.get(i);
            DpMapKey dpMapKey = new DpMapKey(subStickers, stickerResult.target);
            int subMinValue = -1;
            if (map.containsKey(dpMapKey)) {
                subMinValue = map.get(dpMapKey);
            } else {
                subMinValue = dp(subStickers, stickerResult.target, map);
                map.put(dpMapKey, subMinValue);
            }
            if (subMinValue != -1) {
                subMinValue = subMinValue + stickerResult.count;
                if (minValue == -1) {
                    minValue = subMinValue;
                } else {
                    minValue = Math.min(minValue, subMinValue);
                }
            }
        }
        return minValue;
    }
    
    static List<StickerResult> go(List<Character> sticker, List<Character> target) {
        List<StickerResult> list = new ArrayList<>();
        List<Character> targetList = new ArrayList<>(target);
        // 0个
        StickerResult stickerResult0 = new StickerResult(0, new ArrayList<>(target));
        list.add(stickerResult0);
        boolean flag = true;
        int count = 0;
        while (flag) {
            count++;
            int oldSize = targetList.size();
            remove(targetList, sticker);
            StickerResult stickerResult = new StickerResult(count, new ArrayList<>(targetList));
            list.add(stickerResult);
            if (targetList.size() == 0 || targetList.size() == oldSize) {
                flag = false;
            }
        }
        return list;
    }
    
    static void remove(List<Character> target, List<Character> list) {
        for (int i = 0; i < list.size(); i++) {
            target.remove(list.get(i));
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