package com.zfwhub.algorithm.acm.hdu;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nums = sc.nextInt();
        for (int i = 0; i < nums; i++) {
            int count = sc.nextInt();
            int weight = sc.nextInt();
            int[] weigh = new int[count];
            int[] value = new int[count];
            for (int j = 0; j < count; j++) {
                value[j] = sc.nextInt();
            }
            for (int j = 0; j < count; j++) {
                weigh[j] = sc.nextInt();
            }
            System.out.println(solution(weigh, value, weight));
        }
    }
    public static int solution(int[] volumns, int[] values, int capacity) {
        int[] results = new int[capacity + 1];
        for (int i = 0; i < volumns.length; i++) {
            for (int j = capacity; j >= 0; j--) {
                if (volumns[i] > j) {
                    results[j] = results[j];
                } else {
                    results[j] = Math.max(results[j], results[j-volumns[i]] + values[i]);
                }
            }
        }
        return results[capacity];
    }
}
