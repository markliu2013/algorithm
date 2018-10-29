package com.zfwhub.algorithm.leetcode.string;
import java.util.*;
/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/ 
 */
public class LetterCombinations {
    
    public static Map<String, String> map;
    
    static {
        map = new HashMap<>();
        map.put("2", "abc");
        map.put("3", "def");
        map.put("4", "ghi");
        map.put("5", "jkl");
        map.put("6", "mno");
        map.put("7", "pqrs");
        map.put("8", "tuv");
        map.put("9", "wxyz");
    }
    
    public static List<String> solution(String digits) {
        System.out.println(map);
        return null;
    }
    
    public static void main(String[] args) {
        System.out.println(solution("23"));
    }

}
