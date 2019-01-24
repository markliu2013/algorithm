package com.zfwhub.algorithm.utils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 字符串工具类
 */

public class StringUtil {
    
    private StringUtil() {}
    
    /**
     * 将CharSequence转为List。
     * @param target 要转换的CharSequence
     * @return 转换后的list
     */
    public static List<Character> charSeqToList(CharSequence target) {
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < target.length(); i++) {
            list.add(target.charAt(i));
        }
        return list;
    }
    
    /**
     * 将一个CharSequence类型的数组合并为一个StringBuilder，并去掉其中重复的字符。
     * @param targets 需要合并的数组
     * @return 合并后的StringBuilder，不保证targets中的顺序。
     */
    public static <T extends CharSequence> StringBuilder removeDuplicates(T[] targets) {
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
    
    /**
     * 检查字符序列cs中是否存在chars中的每一个字符。
     * @param cs 
     * @param chars
     * @return {@code true} 如果每个字符都存在，{@code false} 只要有一个字符不存在
     */
    public static boolean containsAllChar(CharSequence cs, CharSequence chars) {
        for (int i = 0; i < chars.length(); i++) {
            char c = chars.charAt(i);
            for (int j = 0; j < cs.length(); j++) {
                if (cs.charAt(j) == c) {
                    return true;
                }
            }
        }
        return false;
    }
    
}
