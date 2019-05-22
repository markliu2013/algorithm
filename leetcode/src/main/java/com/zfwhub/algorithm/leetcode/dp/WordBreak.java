package com.zfwhub.algorithm.leetcode.dp;

import java.util.*;

// https://leetcode.com/problems/word-break/
public class WordBreak {
    
    // https://leetcode.com/submissions/detail/230135516/
    public static boolean solution1(String s, List<String> wordDict) {
        if (s.trim().length() == 0) {
            return true;
        }
        if (wordDict.size() == 0) {
            return false;
        }
        String lastWord = wordDict.get(wordDict.size()-1);
        List<String> subWordDict = wordDict.subList(0, wordDict.size()-1);
        if (solution1(s, subWordDict)) {
            return true;
        }
        int lastWordIndex = s.indexOf(lastWord);
        while (lastWordIndex >= 0) {
            s = s.replaceFirst(lastWord, " ");
            lastWordIndex = s.indexOf(lastWord);
            if (solution1(s, subWordDict)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean solution2(String s, List<String> wordDict) {
        return solution2DP(s, wordDict, new HashMap<>());
    }
    
    private static boolean solution2DP(String s, List<String> wordDict, Map<DPMapKey, Boolean> map) {
        if (s.trim().length() == 0) {
            return true;
        }
        if (wordDict.size() == 0) {
            return false;
        }
        String lastWord = wordDict.get(wordDict.size()-1);
        List<String> subWordDict = wordDict.subList(0, wordDict.size()-1);
        boolean result1 = false;
        DPMapKey key1 = new DPMapKey(s, subWordDict);
        if (map.containsKey(key1)) {
            result1 = map.get(key1);
        } else {
            result1 = solution2DP(s, subWordDict, map);
            map.put(key1, result1);
        }
        if (result1) {
            return true;
        }
        int lastWordIndex = s.indexOf(lastWord);
        while (lastWordIndex >= 0) {
            s = s.replaceFirst(lastWord, " ");
            lastWordIndex = s.indexOf(lastWord);
            boolean result2 = false;
            DPMapKey key2 = new DPMapKey(s, subWordDict);
            if (map.containsKey(key2)) {
                result2 = map.get(key2);
            } else {
                result2 = solution2DP(s, subWordDict, map);
                map.put(key2, result2);
            }
            if (result2) {
                return true;
            }
        }
        return false;
    }
    
    private static class DPMapKey {
        public String s;
        public List<String> wordDict;
        public DPMapKey(String s, List<String> wordDict) {
            this.s = s;
            this.wordDict = wordDict;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((s == null) ? 0 : s.hashCode());
            result = prime * result + ((wordDict == null) ? 0 : wordDict.hashCode());
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
            if (s == null) {
                if (other.s != null)
                    return false;
            } else if (!s.equals(other.s))
                return false;
            if (wordDict == null) {
                if (other.wordDict != null)
                    return false;
            } else if (!wordDict.equals(other.wordDict))
                return false;
            return true;
        }
    }
    
    // https://www.cnblogs.com/grandyang/p/4257740.html
    
    
    public static void main(String[] args) {
        String s = "ccbb";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("bc");wordDict.add("cb");
        System.out.println(solution2(s, wordDict));
    }

}
