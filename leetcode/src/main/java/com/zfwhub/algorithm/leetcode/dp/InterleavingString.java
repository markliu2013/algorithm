package com.zfwhub.algorithm.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/interleaving-string/
public class InterleavingString {
    
    // https://leetcode.com/submissions/detail/230509037/
    public static boolean solution1(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        if (s1.length() == 0 && s2.length() == 0 && s3.length() == 0) {
            return true;
        }
        if (s1.length() == 0) {
            return s2.equals(s3);
        }
        if (s2.length() == 0) {
            return s1.equals(s3);
        }
        Character lastChar = s3.charAt(s3.length()-1);
        String subS3 = s3.substring(0, s3.length()-1);
        boolean result1 = false;
        if (lastChar == s1.charAt(s1.length()-1)) {
            result1 = solution1(s1.substring(0, s1.length()-1), s2, subS3);
        }
        boolean result2 = false;
        if (lastChar == s2.charAt(s2.length()-1)) {
            result2 = solution1(s1, s2.substring(0, s2.length()-1), subS3);
        }
        return result1 || result2;
    }
    
    // https://leetcode.com/submissions/detail/230510927/
    public static boolean solution2(String s1, String s2, String s3) {
        return solution2DP(s1, s2, s3, new HashMap<>());
    }
    
    public static boolean solution2DP(String s1, String s2, String s3, Map<DPMapKey, Boolean> map) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        if (s1.length() == 0 && s2.length() == 0 && s3.length() == 0) {
            return true;
        }
        if (s1.length() == 0) {
            return s2.equals(s3);
        }
        if (s2.length() == 0) {
            return s1.equals(s3);
        }
        Character lastChar = s3.charAt(s3.length()-1);
        String subS3 = s3.substring(0, s3.length()-1);
        boolean result1 = false;
        if (lastChar == s1.charAt(s1.length()-1)) {
            String subS1 = s1.substring(0, s1.length()-1);
            DPMapKey key = new DPMapKey(subS1, s2, subS3);
            if (map.containsKey(key)) {
                result1 = map.get(key);
            } else {
                result1 = solution2DP(subS1, s2, subS3, map);
                map.put(key, result1);
            }
        }
        boolean result2 = false;
        if (lastChar == s2.charAt(s2.length()-1)) {
            String subS2 = s2.substring(0, s2.length()-1);
            DPMapKey key = new DPMapKey(s1, subS2, subS3);
            if (map.containsKey(key)) {
                result2 = map.get(key);
            } else {
                result2 = solution2DP(s1, subS2, subS3, map);
                map.put(key, result2);
            }
        }
        return result1 || result2;
    }
    
    private static class DPMapKey {
        public String s1;
        public String s2;
        public String s3;
        public DPMapKey(String s1, String s2, String s3) {
            this.s1 = s1;
            this.s2 = s2;
            this.s3 = s3;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((s1 == null) ? 0 : s1.hashCode());
            result = prime * result + ((s2 == null) ? 0 : s2.hashCode());
            result = prime * result + ((s3 == null) ? 0 : s3.hashCode());
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
            if (s1 == null) {
                if (other.s1 != null)
                    return false;
            } else if (!s1.equals(other.s1))
                return false;
            if (s2 == null) {
                if (other.s2 != null)
                    return false;
            } else if (!s2.equals(other.s2))
                return false;
            if (s3 == null) {
                if (other.s3 != null)
                    return false;
            } else if (!s3.equals(other.s3))
                return false;
            return true;
        }
    }
    
    // TODO InterleavingString solution3
    
    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbbaccc";
        System.out.println(solution1(s1, s2, s3));
    }

}
