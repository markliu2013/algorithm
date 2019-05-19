package com.zfwhub.algorithm.leetcode.contest137;

// https://leetcode.com/contest/weekly-contest-137/problems/remove-all-adjacent-duplicates-in-string/
public class RemoveAllAdjacentDuplicatesInString {
    
    public static String solution1(String S) {
        StringBuffer sb = new StringBuffer(S);
        boolean flag = true;
        while (flag) {
            boolean flag2 = false;
            for (int i = 0; i < sb.length()-1; i++) {
                if (sb.charAt(i) == sb.charAt(i+1)) {
                    sb.delete(i, i+2);
                    flag2 = true;
                    continue;
                }
            }
            flag = flag2;
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        System.out.println(solution1("adsss"));
    }

}
