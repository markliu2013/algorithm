package com.zfwhub.algorithm.leetcode.string;

/*
 * https://leetcode.com/contest/weekly-contest-76/problems/similar-rgb-color/
 * leetcode 800
 * http://guoyc.com/en/post/leetcode_weekly_contest_76/
 */
public class SimilarRGBColor {
    
    public static String solution(String color) {
        String r=color.substring(1, 3);
        String g=color.substring(3, 5);
        String b=color.substring(5, 7);
        return "#"+findSim(r)+findSim(g)+findSim(b);
    }
    
    public static String findSim(String color) {
        Character c1 = color.charAt(0);
        Integer integer=Integer.parseInt(color, 16);
        if (c1=='0') {
            Integer i1=Integer.parseInt("11", 16);
            if (integer>i1-integer) {
                return "11";
            } else {
                return "00";
            }
        } else if (c1=='f') {
            Integer i1=Integer.parseInt("ff", 16);
            Integer i2=Integer.parseInt("ee", 16);
            if (i1-integer>integer-i2) {
                return "ee";
            } else {
                return "ff";
            }
        } else {
            String str1=c1.toString()+c1.toString();//66
            Integer i2=Integer.valueOf(c1.toString(), 16)-1;
            String str2=Integer.toHexString(i2)+Integer.toHexString(i2);//55
            Integer i3=Integer.valueOf(c1.toString(), 16)+1;
            String str3=Integer.toHexString(i3)+Integer.toHexString(i3);//77
            if (Integer.parseInt(str1, 16)>integer) {
                int gap1=Integer.parseInt(str1, 16)-integer;
                int gap2=integer-Integer.parseInt(str2, 16);
                if (gap1>gap2) {
                    return str2;
                } else {
                    return str1;
                }
            } else {
                int gap1=integer-Integer.parseInt(str1, 16);
                int gap2=Integer.parseInt(str3, 16)-integer;
                if (gap1>gap2) {
                    return str3;
                } else {
                    return str1;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println(SimilarRGBColor.solution("#09f166"));
    }

}
