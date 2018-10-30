package com.zfwhub.algorithm.leetcode.string;
import java.util.*;
/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/ 
 */
public class LetterCombinations {
    
    public static Map<Character, String> map;
    
    static {
        map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
    }
    
    public static List<String> solution(String digits) {
        Set<String> set = new HashSet<>();
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            list1.add(map.get(digits.charAt(i)));
        }
        
        List<String> list = new ArrayList<>(set);
        return list;
    }
    
    public static void main(String[] args) {
        System.out.println(solution("23"));
    }

}
