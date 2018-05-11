package com.zfwhub.algorithm.math.pac;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合
 */
public class Combination {

    /**
     * assume we have arr [1,2,3], try to get all of combinations
     * C(3,0)+C(3,1)+C(3,2)+C(3,3)=1+3+3+1=8=pow(2,3)
     * try to look the array elements as turn off switch, so we can have pow(2,3)
     */
    public static List<List<Integer>> getAllCombination(int[] arr) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        int count = new Double(Math.pow(2.0, arr.length)).intValue();
        for (int i = 0; i < count; i++) {
            List<Integer> list1 = new ArrayList<Integer>();
            String binaryString = String.format("%3s", Integer.toBinaryString(i)).replace(' ', '0');
            char[] chars = binaryString.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == '1') {
                    list1.add(arr[j]);
                }
            }
            list.add(list1);
        }
        return list;
    }

    // TODO
    public static List<List<Integer>> getCombination(int[] arr, int n) {
        return null;
    }

}
