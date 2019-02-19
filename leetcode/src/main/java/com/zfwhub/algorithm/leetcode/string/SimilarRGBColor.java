package com.zfwhub.algorithm.leetcode.string;

import com.zfwhub.algorithm.utils.NumberUtil;
import com.zfwhub.algorithm.utils.NumberUtil.ClosestMultipleFlag;

// http://guoyc.com/en/post/leetcode_weekly_contest_76/#800-similar-rgb-color
public class SimilarRGBColor {
    
    // Accepted
    public static String solution1(String color) {
        // 分成三个颜色部分，分别找similarity 
        String r=color.substring(1, 3);
        String g=color.substring(3, 5);
        String b=color.substring(5, 7);
        return "#"+findSim(r)+findSim(g)+findSim(b);
    }
    
    // wait to test
    public static String solution2(String color) {
        String result = "#";
        for (int i = 1; i < 6; i+=2) {
            result += Integer.toHexString(NumberUtil.closestMultiple(Integer.parseInt(color.substring(i, i+2), 16), 17, ClosestMultipleFlag.ROUND));
        }
        return result;
    }
    
    private static String findSim(String color) {
        Character c1 = color.charAt(0);
        Integer integer=Integer.parseInt(color, 16);
        if (c1=='0') {
            // 如果是0开头，则要么是11，要么是00
            Integer i1=Integer.parseInt("11", 16);
            if (integer>i1-integer) {
                return "11";
            } else {
                return "00";
            }
        } else if (c1=='f') {
            // 如果是f开头，则要么是ff，要么是ee
            Integer i1=Integer.parseInt("ff", 16);
            Integer i2=Integer.parseInt("ee", 16);
            if (i1-integer>integer-i2) {
                return "ee";
            } else {
                return "ff";
            }
        } else {
            // 假设是6开头，则比较三个数，66 55 77
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
        System.out.println(solution1("#09f166"));
        System.out.println(solution2("#09f166"));
        System.out.println(solution1("#12ab56"));
        System.out.println(solution2("#12ab56"));
    }

}
