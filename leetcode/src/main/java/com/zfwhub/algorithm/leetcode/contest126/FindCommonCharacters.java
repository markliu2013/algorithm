package com.zfwhub.algorithm.leetcode.contest126;

import java.util.*;
// https://leetcode.com/contest/weekly-contest-126
public class FindCommonCharacters {
    
    public static List<String> solution1(String[] A) {
        List<String> result = new ArrayList<>();
        String firstStr = A[0];
        for (int i = 0; i < firstStr.length(); i++) {
            boolean flag = true;
            char target = firstStr.charAt(i);
            List<Integer> indexes = new ArrayList<>();
            for (int j = 1; j < A.length; j++) {
                int indexOf = A[j].indexOf(new Character(target).toString());
                if (indexOf < 0) {
                    flag = false;
                    break;
                } else {
                    indexes.add(indexOf);
                }
            }
            if (flag) {
                result.add(new Character(target).toString());
                for (int j = 0; j < indexes.size(); j++) {
                    int index = indexes.get(j);
                    A[j+1] = A[j+1].substring(0, index) + A[j+1].substring(index+1);
                }
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        String[] A1 = new String[] {"bella","label","roller"};
        System.out.println(solution1(A1));
        String[] A2 = new String[] {"cool","lock","cook"};
        System.out.println(solution1(A2));
    }

}
