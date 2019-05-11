package com.zfwhub.algorithm.leetcode.hashtable;

import java.util.*;

// https://leetcode.com/problems/group-anagrams/
public class GroupAnagrams {
    
    public static List<List<String>> solution1(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] charArr = strs[i].toCharArray();
            Arrays.sort(charArr);
            String keyString = String.valueOf(charArr);
            if (map.containsKey(keyString)) {
                map.get(keyString).add(strs[i]);
            } else {
                List<String> list = new ArrayList<>();
                list.add(strs[i]);
                map.put(keyString, list);
            }
        }
        return new ArrayList<>(map.values());
    }
    
    public static void main(String[] args) {
        System.out.println(solution1(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
    
}
