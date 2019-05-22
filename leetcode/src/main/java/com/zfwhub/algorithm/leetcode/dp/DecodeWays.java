package com.zfwhub.algorithm.leetcode.dp;

// https://leetcode.com/problems/decode-ways/
public class DecodeWays {
    
    public static int solution1(String s) {
        if (s.startsWith("0")) {
            return 0;
        }
        if (s.length() == 1) {
            if (s.charAt(0) == '0') {
                return 0;
            }
            return 1;
        }
        Character lastItem = s.charAt(s.length()-1);
        String newS = s.substring(0, s.length()-1);
        Character newSlast = newS.charAt(newS.length()-1);
        int result = solution1(newS);
        if (lastItem == '0') {
            if (newSlast == '0') {
                return 0;
            }
            return result;
        }
        if (newSlast == '0') {
            return result;
        }
        StringBuffer string = new StringBuffer(newSlast.toString());
        string.append(lastItem);
        String str2 = string.toString();
        if (Integer.valueOf(str2) <= 26) {
            return result+1;
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(solution1("12")); //2
        System.out.println(solution1("226")); //3
        System.out.println(solution1("0")); //0
        System.out.println(solution1("10")); //1
        System.out.println(solution1("27")); //1
        System.out.println(solution1("01")); //0
        System.out.println(solution1("100")); //0
        System.out.println(solution1("101")); //1
        
    }

}
