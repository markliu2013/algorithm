package com.zfwhub.algorithm.leetcode.dp;
import java.util.*;

// https://leetcode.com/problems/regular-expression-matching/
// https://leetcode.com/submissions/detail/200680154/
public class RegularExpressionMatching {
    
    static final Character ASTERISK_CHAR = '*';
    static final Character POINT_CHAR = '.';
    static final String ASTERISK_STRING = "*";

    // TODO RegularExpressionMatching
    public static boolean isMatch(String s, String p) {
        if (p.length() == 0) {
            if (s.length() == 0) {
                return true;
            } else {
                return false;
            }
        }
        List<CharPattern> patterns = new ArrayList<>();
        for (int i = 0; i < p.length()-1; i++) {
            CharPattern pattern = new CharPattern(p.charAt(i+1) == ASTERISK_CHAR, p.charAt(i));
            patterns.add(pattern);
            if (p.charAt(i+1) == ASTERISK_CHAR) {
                i++;
            }
        }
        if (p.charAt(p.length()-1) != ASTERISK_CHAR) {
            patterns.add(new CharPattern(false, p.charAt(p.length()-1)));
        }
        return dp(s, patterns);
    }
    
    static boolean dp(String s, List<CharPattern> patterns) {
        if (s.length() == 0 && patterns.size() == 0) {
            return true;
        }
        if (s.length() == 0) {
            for (int i = 0; i < patterns.size(); i++) {
                CharPattern pattern = patterns.get(i);
                if (!pattern.isAsterisk) {
                    return false;
                }
            }
            return true;
        }
        if (patterns.size() == 0) {
            return false;
        }
        CharPattern lastPattern = patterns.get(patterns.size()-1);
        List<CharPattern> subPatterns = patterns.subList(0, patterns.size()-1);
        if (!lastPattern.isAsterisk) {
            if (lastPattern.matches(s.charAt(s.length()-1))) {
                return dp(s.substring(0, s.length()-1), subPatterns);
            } else {
                return false;
            }
        } else {
            if (lastPattern.matches(s.charAt(s.length()-1))) {
                // 是星号，且匹配上了，可以匹配多个，需要对string的最后匹配几个进行轮询
                int count = 0;
                if (lastPattern.isPoint()) {
                    count = s.length();
                } else {
                    count = lastPattern.checkLastCount(s);
                }
                for (int i = 0; i <= count; i++) {
                    if (dp(s.substring(0, s.length()-i), subPatterns)) {
                        return true;
                    }
                }
                return false;
            } else {
                return dp(s, subPatterns);
            }
        }
    }
    
    static class CharPattern {
        
        public boolean isAsterisk;
        public Character character;
        
        public CharPattern() { }
        
        public int checkLastCount(String s) {
            int count = 0;
            for (int i = s.length()-1; i >= 0; i--) {
                if (matches(s.charAt(i))) {
                    count++;
                } else {
                    break;
                }
            }
            return count;
        }

        public CharPattern(boolean isAsterisk, Character character) {
            this.isAsterisk = isAsterisk;
            this.character = character;
        }
        
        public boolean isPoint() {
            return character == POINT_CHAR;
        }
        
        public boolean matches(Character c) {
            if (character == '.') {
                return true;
            }
            return character == c;
        }
        
        @Override
        public String toString() {
            return character + (isAsterisk ? ASTERISK_STRING : "");
        }
        
    }

    public static void main(String[] args) {
        String s = "";
        String p = "a*a*";
        System.out.println(isMatch(s, p));
    }

}
