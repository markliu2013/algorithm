package com.zfwhub.algorithm.leetcode.contest134;

import java.util.Arrays;

public class MovingStones {
    
    public static int[] solution1(int a, int b, int c) {
        int[] arr = new int[3];
        arr[0] = a;
        arr[1] = b;
        arr[2] = c;
        Arrays.sort(arr);
        int[] result = new int[2];
        if (arr[1] > arr[0] && arr[1] < arr[2]) {
            int min = 0;
            int max = 0;
            if (arr[1]-arr[0] != 1) {
                min++;
                max += arr[1]-arr[0]-1;
            }
            if (arr[2]-arr[1] != 1) {
                min++;
                max += arr[2]-arr[1]-1;
            }
            if (arr[1]-arr[0] == 2 || arr[2]-arr[1] == 2) {
                min = 1;
            }
            result[0] = min;
            result[1] = max;
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution1(2, 4, 1)));
    }
    
}
