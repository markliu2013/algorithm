package com.zfwhub.algorithm.leetcode.dp;

import java.util.*;

import com.zfwhub.algorithm.utils.CollectionUtil;
import com.zfwhub.algorithm.utils.StringUtil;

// https://leetcode.com/problems/stickers-to-spell-word/
public class StickersToSpellWord {
    
    // 动态规划，从上往下递归
    public static int solution1(String[] stickers, String target) {
        // 先检查是否存在解
        if (!hasSolution(stickers, target)) {
            return -1;
        } else {
            List<Character> targetList = StringUtil.charSeqToList(target);
            Collections.sort(targetList);// 排序后增加map命中率
            return dp(parseStickers(stickers, target), targetList, new HashMap<>());
        }
    }
    
    // target的每个字符都要在stickers中找到，否则问题无解
    private static boolean hasSolution(String[] stickers, String target) {
        CharSequence stickerChars = removeDuplicates(stickers);
        return StringUtil.containsAllChar(stickerChars, target);
    }
    
    // stickers 转为二维list，并且去掉不包含target字符的，因为去掉不影响问题的解
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
    
    // 递归，从上往下。
    private static int dp(List<List<Character>> stickers, List<Character> target, HashMap<DpMapKey, Integer> map) {
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
            // 利用StickersToSpellWord2中无穷大的思想，此处代码可以写的更优雅。
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
    
    private static List<StickerResult> go(List<Character> sticker, List<Character> target) {
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
            CollectionUtil.remove(targetList, sticker);
            StickerResult stickerResult = new StickerResult(count, new ArrayList<>(targetList));
            list.add(stickerResult);
            if (targetList.size() == 0 || targetList.size() == oldSize) {
                flag = false;
            }
        }
        return list;
    }
    
    
    /**
     * 将一个CharSequence类型的数组合并为一个StringBuilder，并去掉其中重复的字符。
     * @param targets 需要合并的数组
     * @return 合并后的StringBuilder，不保证targets中的顺序。
     */
    private static <T extends CharSequence> StringBuilder removeDuplicates(T[] targets) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < targets.length; i++) {
            for (int j = 0; j < targets[i].length(); j++) {
                set.add(targets[i].charAt(j));
            }
        }
        StringBuilder sb = new StringBuilder(set.size());
        for (Character c : set) {
            sb.append(c);
        }
        return sb;
    }
    
    // 每选择一个sticker，对应的target都会改变，StickerResult封装选择的sticker个数和target的关系
    private static class StickerResult {
        public int count;
        public List<Character> target;
        
        public StickerResult(int count, List<Character> target) {
            this.count = count;
            this.target = target;
        }
    }
    
    private static class DpMapKey {
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
    
    private final static int INFINITE = Integer.MAX_VALUE - 10; // 模拟无穷大
    // 动态规划，从下往上递推。
    public static int solution2(String[] stickers, String target) {
        List<Character> targetList = StringUtil.charSeqToList(target);
        List<List<Character>> stickerList = compress(stickers, targetList);
        int result = dp(stickerList, targetList);
        return result >= INFINITE ? -1 : result;
    }
    
    // 将此问题转化为0 1型的背包问题
    private static List<List<Character>> compress(String[] stickers, List<Character> target) {
        List<List<Character>> result = new ArrayList<>();
        for (int i = 0; i < stickers.length; i++) {
            List<Character> sticker = StringUtil.charSeqToList(stickers[i]);
            List<Character> targetList = new ArrayList<>(target);
            boolean flag = true;
            while (flag) {
                int oldSize = targetList.size();
                CollectionUtil.remove(targetList, sticker);
                if (targetList.size() != oldSize) {
                    result.add(new ArrayList<>(sticker));
                }
                if (targetList.size() == 0 || targetList.size() == oldSize) {
                    flag = false;
                }
            }
        }
        return result;
    }

    // 递推，从下往上。
    private static int dp(List<List<Character>> stickers, List<Character> target) {
        List<List<Character>> targetSubsetsList = CollectionUtil.subsetsWithDup(target);
        Map<List<Character>, Integer> results = new HashMap<>();//类似Pack01.solution3中的result
        Map<List<Character>, Integer> preResults = new HashMap<>();
        for (int i = 0; i < targetSubsetsList.size(); i++) {
            List<Character> targetList = targetSubsetsList.get(i);
            if (targetList.size() == 0) {
                preResults.put(targetList, 0);
            } else {
                preResults.put(targetList, INFINITE);
            }
        }
        for (int i = 0; i < stickers.size(); i++) {
            List<Character> sticker = stickers.get(i);
            for (int j = 0; j < targetSubsetsList.size(); j++) {
                List<Character> targetSubsets = targetSubsetsList.get(j);//result中当前行的每一格
                List<Character> targetList = new ArrayList<>(targetSubsets);
                // 选sticker
                CollectionUtil.remove(targetList, sticker);
                int cost1 = preResults.get(targetList) + 1;
                // 不选sticker
                int cost2 = preResults.get(targetSubsets);
                results.put(targetSubsets, Math.min(cost1, cost2));                    
            }
            preResults = results;
        }
        return results.get(target);
    }
    
    public static void main(String[] args) {
        String[] stickers = new String[] {"own","tone","feel","there","invent","trade","follow","home","still","check","market","cotton","blow","use","afraid","indicate","support","sail","charge","been","children","how","behind","size","hat","match","count","notice","food","excite","felt","serve","guess","else","quick","student","consonant","strong","wait","ago","enemy","oh","industry","cover","cat","while","nose","wild","quite","shine"};
        String target = "donepower";
        System.out.println(solution1(stickers, target));
    }
    
}