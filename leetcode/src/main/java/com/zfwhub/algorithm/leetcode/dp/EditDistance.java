package com.zfwhub.algorithm.leetcode.dp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfwhub.algorithm.utils.StringUtil;

// https://leetcode.com/problems/edit-distance/
public class EditDistance {
    
    // 失败
    public static int solution1(String word1, String word2) {
        List<Character> list1 = StringUtil.charSeqToList(word1);
        List<Character> list2 = StringUtil.charSeqToList(word2);
        return solution1DP(list1, list2);
    }
    public static int solution1DP(List<Character> list1, List<Character> list2) {
        if (list1.size() == 0) {
            return list2.size();
        }
        Character lastItem = list1.get(list1.size()-1);
        List<Character> subList1 = list1.subList(0, list1.size()-1);
        int lastItemIndex = list2.indexOf(lastItem);
        if (lastItemIndex < 0) {
            // list1和list2的长度一样，则替换。
            // 如果不一样则删除。
            if (list1.size() == list2.size()) {
                return solution1DP(subList1, list2.subList(0, list2.size()-1)) + 1;
            } else {
                return solution1DP(subList1, list2) + 1;
            }
        } else {
            List<Character> subList2 = list2.subList(0, lastItemIndex);
            int result = solution1DP(subList1, subList2) + (list2.size()-1-lastItemIndex);
            return result;
        }
    }
    
    public static int solution2(String word1, String word2) {
        if (word1.length() == 0 && word2.length() == 0) {
            return 0;
        }
        if (word1.length() == 0) {
            return word2.length();
        }
        if (word2.length() == 0) {
            return word1.length();
        }
        int result1 = solution2(word1, word2.substring(0, word2.length()-1))+1;
        int result2 = solution2(word1.substring(0, word1.length()-1), word2)+1;
        int result3 = solution2(word1.substring(0, word1.length()-1), word2.substring(0, word2.length()-1));
        if (word1.charAt(word1.length()-1) != word2.charAt(word2.length()-1)) {
            result3++;
        }
        return Math.min(Math.min(result1, result2), result3);
    }
    
    // https://leetcode.com/problems/edit-distance/submissions/
    public static int solution3(String word1, String word2) {
        return solution3DP(word1, word2, new HashMap<>());
    }
    public static int solution3DP(String word1, String word2, Map<DPMapKey, Integer> map) {
        if (word1.length() == 0 && word2.length() == 0) {
            return 0;
        }
        if (word1.length() == 0) {
            return word2.length();
        }
        if (word2.length() == 0) {
            return word1.length();
        }
        String subWord1 = word1.substring(0, word1.length()-1);
        String subWord2 = word2.substring(0, word2.length()-1);
        int result1 = 0;
        DPMapKey key1 = new DPMapKey(word1, subWord2);
        if (map.containsKey(key1)) {
            result1 = map.get(key1)+1;
        } else {
            result1 = solution3DP(key1.word1, key1.word2, map);
            map.put(key1, result1);
            result1++;
        }
        int result2 = 0;
        DPMapKey key2 = new DPMapKey(subWord1, word2);
        if (map.containsKey(key2)) {
            result2 = map.get(key2)+1;
        } else {
            result2 = solution3DP(key2.word1, key2.word2, map);
            map.put(key2, result2);
            result2++;
        }
        int result3 = 0;
        DPMapKey key3 = new DPMapKey(subWord1, subWord2);
        if (map.containsKey(key3)) {
            result3 = map.get(key3);
        } else {
            result3 = solution3DP(key3.word1, key3.word2, map);
            map.put(key3, result3);
        }
        if (word1.charAt(word1.length()-1) != word2.charAt(word2.length()-1)) {
            result3++;
        }
        return Math.min(Math.min(result1, result2), result3);
    }
    private static class DPMapKey {
        public String word1;
        public String word2;
        public DPMapKey(String word1, String word2) {
            this.word1 = word1;
            this.word2 = word2;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((word1 == null) ? 0 : word1.hashCode());
            result = prime * result + ((word2 == null) ? 0 : word2.hashCode());
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
            DPMapKey other = (DPMapKey) obj;
            if (word1 == null) {
                if (other.word1 != null)
                    return false;
            } else if (!word1.equals(other.word1))
                return false;
            if (word2 == null) {
                if (other.word2 != null)
                    return false;
            } else if (!word2.equals(other.word2))
                return false;
            return true;
        }
        
    }
    
    public static void main(String[] args) {
        System.out.println(solution3("horse", "ros"));
//        System.out.println(solution1("intention", "execution"));
//        System.out.println(solution1("", "a"));
//        System.out.println(solution1("sea", "eat"));
    }

}
