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
        if (stickers.length == 1) {
            Iterator<Character> targetIt = target.iterator();
            String lastSticker = stickers[0];
            List<Character> list2 = new ArrayList<>();
            for (int i = 0; i < lastSticker.length(); i++) {
                list2.add(lastSticker.charAt(i));
            }
            while (targetIt.hasNext()) {
                Character c = targetIt.next();
                if (list2.contains(c)) {
                    targetIt.remove();
                    list2.remove(c);
                }
            }
            if (target.size() == 0) {
                return 1;
            } else {
                return -1;
            }
        }
        String[] subStickers = Arrays.copyOf(stickers, stickers.length-1);
        String lastSticker = stickers[stickers.length-1];
        List<Character> list2 = new ArrayList<>();
        for (int i = 0; i < lastSticker.length(); i++) {
            list2.add(lastSticker.charAt(i));
        }
        boolean flag1 = false;
        for (int i = 0; i < list1.size(); i++) {
            if (list2.contains(list1.get(i))) {
                flag1 = true;
                break;
            }
        }
        if (!flag1) {
            return minStickers(subStickers, target);
        }
        boolean flag2 = true;
        List<Character> list3 = new ArrayList<>();
        int count = 0; // 记录最后一个用多少次
        int minCount = Integer.MAX_VALUE;
        while (flag2) {
            list3.addAll(list2);
            Iterator<Character> targetIt = list1.iterator();
            boolean flag3 = true;
            while (targetIt.hasNext()) {
                Character c = targetIt.next();
                if (list3.contains(c)) {
                    targetIt.remove();
                    list3.remove(c);
                    flag3 = false;
                    count++;
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < list1.size(); i++) {
                        sb.append(list1.get(i));
                    }
                    int count1 = dp(subStickers, sb.toString());
                    minCount = Math.min(minCount, count+count1);
                }
            }
            if (flag3) {
                flag2 = false; 
            }
        }
        return minCount;
    }
    
    public static void main(String[] args) {
        String[] stickers = new String[] {"with", "example", "science"};
        String target = "thehat";
        System.out.println(minStickers(stickers, target));
    }
    
}
