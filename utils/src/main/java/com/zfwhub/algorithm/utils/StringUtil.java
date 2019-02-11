package com.zfwhub.algorithm.utils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringUtil {
    
    private StringUtil() {}
    
    /**
     * 将CharSequence转为List。
     * @param cs 要转换的CharSequence
     * @return 转换后的list
     */
    public static List<Character> charSeqToList(CharSequence cs) {
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < cs.length(); i++) {
            list.add(cs.charAt(i));
        }
        return list;
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
    
    /**
     * 检查字符序列cs指定范围中，是否不包括重复字符。
     * @param cs
     * @param beginIndex the beginning index, inclusive.
     * @param endIndex the ending index, exclusive.
     * @return {@code true} 如果不存在重复字符，{@code false} 存在重复字符
     */
    public static boolean isAllUnique(CharSequence cs, int beginIndex, int endIndex) {
        Set<Character> set = new HashSet<>();
        for (int i = beginIndex; i < endIndex; i++) {
            Character ch = cs.charAt(i);
            if (set.contains(ch)) {
                return false;
            } else {
                set.add(ch);
            }
        }
        return true;
    }
    
}
