package com.zfwhub.algorithm.codility.sorting;

import java.util.Arrays;

// https://app.codility.com/programmers/lessons/6-sorting/number_of_disc_intersections/
public class NumberOfDiscIntersections {
    
    // brute force, get all combinations of two, then check.
    public static int solution1(int[] A) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                int distance = j - i;//distance of this two points
                // Be careful, overflow
                if (A[i] >= distance - A[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    // TODO NumberOfDiscIntersections http://viowan.lofter.com/post/19fa66_48202ed
    public static int solution2(int[] A) {
        int count = 0;
        Arrays.sort(A);
        for (int i = 0; i < A.length; i++) {
            for (int j = A.length-1; j >= i+1; j--) {
                int distance = j - i;//distance of this two points
                // Be careful, overflow
                if (A[i] >= distance - A[j]) {
                    count++;
                }  else {
                    break;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(solution1(new int[] { 1, 10, 100, 1 }));
        System.out.println(solution2(new int[] { 1, 1, 10, 100}));
    }

}
