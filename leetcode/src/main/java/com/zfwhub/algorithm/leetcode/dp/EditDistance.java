package com.zfwhub.algorithm.leetcode.dp;

import java.util.List;

import com.zfwhub.algorithm.utils.StringUtil;

// https://leetcode.com/problems/edit-distance/
public class EditDistance {
    
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
    
    
    public static void main(String[] args) {
        System.out.println(solution1("horse", "ros"));
//        System.out.println(solution1("intention", "execution"));
//        System.out.println(solution1("", "a"));
//        System.out.println(solution1("sea", "eat"));
    }

}
