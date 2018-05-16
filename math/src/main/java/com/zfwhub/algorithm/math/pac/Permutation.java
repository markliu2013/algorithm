package com.zfwhub.algorithm.math.pac;

import java.util.ArrayList;
import java.util.List;

/**
 * Permutation
 * https://www.quora.com/How-does-recursion-work-inside-a-for-loop-Most-importantly-how-does-it-flow
 */

public class Permutation {

    public static void main(String[] args) {
        permute("", "abc");
    }

    // TODO
    private static void permute(String soFar, String rest) {
        if (rest.equals("")) {
            System.out.println(soFar);
            return;
        }
        for (int i = 0; i < rest.length(); i++) {
            String next = soFar + rest.charAt(i);
            String remaining = rest.substring(0, i)
                    + rest.substring(i + 1, rest.length());
            permute(next, remaining);
        }
    }

    /**
     * It is like for loop to get Factorial
     */
    public static List<List<Integer>> permutation(int[] arr) {
        if (arr.length == 1) {
            List<List<Integer>> list = new ArrayList<List<Integer>>();
            List<Integer> list2 = new ArrayList<Integer>();
            list2.add(arr[0]);
            list.add(list2);
            return list;
        } else {
            List<List<Integer>> list3 = new ArrayList<List<Integer>>();
            for (int i=0; i < arr.length; i++) {
                int[] dest = new int[arr.length-1];
                System.arraycopy(arr, 0, dest, 0, i);
                System.arraycopy(arr, i+1, dest, i, arr.length-1-i);
                List<List<Integer>> list4 = permutation(dest);
                for (int k=0; k<list4.size(); k++) {
                    List<Integer> list5 = list4.get(k);
                    list5.add(arr[i]);
                    list3.add(list5);
                }
            }
            return list3;
        }
    }

}
