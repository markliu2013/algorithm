package com.zfwhub.algorithm.utils;
import java.util.ArrayList;
import java.util.List;

/**
 * 字符串工具类
 * @author Mark Liu
 *
 */

public class StringUtils {
    
    private StringUtils() {}
    
    /**
     * 将String转为List
     * @param target 要转换的string
     * @return 转换后的list
     */
    public static List<Character> stringToList(String target) {
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < target.length(); i++) {
            list.add(target.charAt(i));
        }
        return list;
    }
    
}
