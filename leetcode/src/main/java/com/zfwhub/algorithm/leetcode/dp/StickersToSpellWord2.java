package com.zfwhub.algorithm.leetcode.dp;

import java.util.*;

import com.zfwhub.algorithm.utils.CollectionUtil;
import com.zfwhub.algorithm.utils.StringUtil;
// 动态规划，从下往上递推。
// https://leetcode.com/submissions/detail/200349923/
public class StickersToSpellWord2 {
    
    final static int INFINITE = Integer.MAX_VALUE - 10; // 模拟无穷大
    
    public static int minStickers(String[] stickers, String target) {
        List<Character> targetList = StringUtil.charSeqToList(target);
        List<List<Character>> stickerList = compress(stickers, targetList);
        int result = dp(stickerList, targetList);
        return result >= INFINITE ? -1 : result;
    }
    
    private static List<List<Character>> compress(String[] stickers, List<Character> target) {
        List<List<Character>> result = new ArrayList<>();
        for (int i = 0; i < stickers.length; i++) {
            List<Character> sticker = StringUtil.charSeqToList(stickers[i]);
            List<Character> targetList = new ArrayList<>(target);
            boolean flag = true;
            while (flag) {
                int oldSize = targetList.size();
                CollectionUtil.subtract(targetList, sticker);
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

    static int dp(List<List<Character>> stickers, List<Character> target) {
        List<List<Character>> targetSubsetsList = new ArrayList<>(CollectionUtil.subsetsWithDup(target));
        List<Map<List<Character>, Integer>> results = new ArrayList<>();//类似Pack01.solution02中的result
        Map<List<Character>, Integer> map = new HashMap<>();
        for (int i = 0; i < targetSubsetsList.size(); i++) {
            List<Character> targetList = targetSubsetsList.get(i);
            if (targetList.size() == 0) {
                map.put(targetList, 0);
            } else {
                map.put(targetList, INFINITE);
            }
        }
        results.add(map);
        for (int i = 0; i < stickers.size(); i++) {
            List<Character> sticker = stickers.get(i);
            Map<List<Character>, Integer> rowMap = new HashMap<>();//表格中每一行result对应的map
            for (int j = 0; j < targetSubsetsList.size(); j++) {
                List<Character> targetSubsets = targetSubsetsList.get(j);//result中当前行的每一格
                List<Character> targetList = new ArrayList<>(targetSubsets);
                // 选sticker
                CollectionUtil.subtract(targetList, sticker);
                int cost1 = results.get(i).get(targetList) + 1;
                // 不选sticker
                int cost2 = results.get(i).get(targetSubsets);
                rowMap.put(targetSubsets, Math.min(cost1, cost2));                    
            }
            results.add(rowMap);
        }
        return results.get(results.size()-1).get(target);
    }
    
    public static void main(String[] args) {
        String[] stickers = new String[] {"a", "b"};
        String target = "abc";
        System.out.println(minStickers(stickers, target));
    }
    
}



