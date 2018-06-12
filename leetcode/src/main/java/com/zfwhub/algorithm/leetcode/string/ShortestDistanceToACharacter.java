package com.zfwhub.algorithm.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/shortest-distance-to-a-character/description/
 */
public class ShortestDistanceToACharacter {

    public static int[] solution(String S, char C) {
        int[] result = new int[S.length()];
        List<Integer> list1 = new ArrayList<Integer>();
        int index = S.indexOf(C);
        while (index >= 0) {
            list1.add(index);
            index = S.indexOf(C, index + 1);
        }
        for (int i = 0; i < S.length(); i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < list1.size(); j++) {
                int d = Math.abs(list1.get(j) - i);
                min = Math.min(d, min);
            }
            result[i] = min;
        }
        return result;
    }
    
    public static void main(String[] args) {
        String S = "loveleetcode";
        char C = 'e';
        int[] result = ShortestDistanceToACharacter.solution(S, C);
        System.out.println(Arrays.toString(result));
    }

}
