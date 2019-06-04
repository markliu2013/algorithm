package com.zfwhub.algorithm.leetcode.hashtable;

import java.util.*;

import org.apache.commons.lang3.ObjectUtils.Null;

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
    
    public static List<List<String>> solution2(String[] strs) {
        AnagramsString[] myStrs = new AnagramsString[strs.length];
        for (int i = 0; i < strs.length; i++) {
            myStrs[i] = new AnagramsString(strs[i]);
        }
        Map<AnagramsString, List<String>> map = new HashMap<>();
        for (int i = 0; i < myStrs.length; i++) {
            if (map.containsKey(myStrs[i])) {
                map.get(myStrs[i]).add(strs[i]);
            } else {
                List<String> list = new ArrayList<>();
                list.add(strs[i]);
                map.put(myStrs[i], list);
            }
        }
        return new ArrayList<>(map.values());
    }
    
    private static class AnagramsString {
        
        public String value;

        public AnagramsString(String value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            int h = 0;
            for (int i = 0; i < value.length(); i++) {
                h += value.charAt(i);
            }
            return h;
        }
        
        public int hashCode2() {
            int h = 1;
            for (int i = 0; i < value.length(); i++) {
                h *= value.charAt(i);
            }
            System.out.println(h);
            return h;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            AnagramsString other = (AnagramsString) obj;
            if (value == null) {
                if (other.value != null)
                    return false;
            } else if (hashCode2() != other.hashCode2())
                return false;
            return true;
        }

        @Override
        public String toString() {
            return value.toString();
        }
        
    }
    
    public static List<List<String>> solution3(String[] strs) {
        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};//最多10609个z
        List<List<String>> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (String s : strs) {
            int key = 1;
            for (char c : s.toCharArray()) {
                key *= prime[c - 'a'];
            }
            List<String> t;
            if (map.containsKey(key)) {
                t = res.get(map.get(key));
            } else {
                t = new ArrayList<>();
                res.add(t);
                map.put(key, res.size() - 1);
            }
            t.add(s);
        }
        return res;
    }
    
    public static void main(String[] args) {
//        System.out.println(solution2(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"}));
//        System.out.println(solution2(new String[] {"cab","tin","pew","duh","may","ill","buy","bar","max","doc"}));
        System.out.println(solution1(new String[] {"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"}));
        System.out.println(solution3(new String[] {"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"}));
    }
    
}
