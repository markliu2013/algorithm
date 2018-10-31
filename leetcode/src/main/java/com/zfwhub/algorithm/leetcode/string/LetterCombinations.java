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
    
    // work for 2 digits
    public static List<String> solution0(String digits) {
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            list1.add(map.get(digits.charAt(i)));
        }
        Set<String> set = new LinkedHashSet<>();
        for (int i = 0; i < list1.get(0).length(); i++) {
            for (int j = 0; j < list1.get(1).length(); j++) {
                StringBuilder sb = new StringBuilder();
                sb.append(list1.get(0).charAt(i));
                sb.append(list1.get(1).charAt(j));
                set.add(sb.toString());
            }
        }
        List<String> list = new ArrayList<>(set);
        return list;
    }
    
    public static List<String> solution(String digits) {
        List<String> list1 = new ArrayList<>();
        if (digits == null || digits.isEmpty() || digits.trim().isEmpty()) {
            return list1;
        }
        for (int i = 0; i < digits.length(); i++) {
            if (map.get(digits.charAt(i)) != null) {
                list1.add(map.get(digits.charAt(i)));
            }
        }
        Set<String> set = combinateList(list1);
        List<String> list = new ArrayList<>(set);
        return list;
    }
    
    private static Set<String> combinateList(List<String> list) {
        Set<String> set = new LinkedHashSet<>();
        LinkedList<Integer> stack = new LinkedList<>();
        combinateListHelper(list, set, 0, stack);
        return set;
    }
    
    // DynamicFor
    private static void combinateListHelper(List<String> list, Set<String> set, int x, LinkedList<Integer> stack) {
        if (x == list.size()) {
            StringBuilder sb = new StringBuilder();
            LinkedList<Integer> tempStack = new LinkedList<>(stack);
            for (int i = tempStack.size()-1; i >= 0; i--) {
                sb.append(list.get(tempStack.size()-1-i).charAt(tempStack.get(i)));
            }
            set.add(sb.toString());
            return;
        }
        x++;
        for (int i = 0; i < list.get(x-1).length(); i++) {
            stack.push(i);
            combinateListHelper(list, set, x, stack);
            stack.pop();
        }
    }
    
    // TODO LetterCombinations
    // https://www.cnblogs.com/grandyang/p/4452220.html
    // https://www.cnblogs.com/kepuCS/p/5271654.html
    
    public static void main(String[] args) {
        System.out.println(solution0("27"));
        System.out.println(solution("27"));
    }

}
