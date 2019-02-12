package com.zfwhub.algorithm.codility.prime_and_composite_numbers;

import java.util.ArrayList;

// https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/flags/
public class Flags {
    
    // brute force, Performance 28%
    public static int solution1(int[] A) {
        ArrayList<Integer> peakIndexes = new ArrayList<Integer>();
        for (int i = 1; i < A.length - 1; i++) {
            if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                peakIndexes.add(i);
            }
        }
        if (peakIndexes.size() == 0 || peakIndexes.size() == 1) {
            return peakIndexes.size();
        }
        int maxFlags = 0;
        for (int i = 2; i <= peakIndexes.size(); i++) {
            int count = 1;
            int preIndex = 0;
            for (int j = 1; j < peakIndexes.size(); j++) {
                if (count == i) {
                    break;
                }
                if (peakIndexes.get(j) - peakIndexes.get(preIndex) >= i) {
                    count++;
                    preIndex = j;
                }
            }
            maxFlags = Math.max(maxFlags, count);
        }
        return maxFlags;
    }

    // brute force, return early. Performance 28%
    public static int solution2(int[] A) {
        ArrayList<Integer> peakIndexes = new ArrayList<Integer>();
        for (int i = 1; i < A.length - 1; i++) {
            if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                peakIndexes.add(i);
            }
        }
        if (peakIndexes.size() == 0 || peakIndexes.size() == 1) {
            return peakIndexes.size();
        }
        int maxFlags = 0;
        for (int i = peakIndexes.size(); i > 1; i--) {
            int count = 1;
            int preIndex = 0;
            for (int j = 1; j < peakIndexes.size(); j++) {
                if (count == i) {
                    break;
                }
                if (peakIndexes.get(j) - peakIndexes.get(preIndex) >= i) {
                    count++;
                    preIndex = j;
                }
            }
            if (count >= i - 1) {
                return count;
            }
            maxFlags = Math.max(maxFlags, count);
        }
        return maxFlags;
    }

    // TODO Flags https://codility.com/media/train/solution-flags.pdf
    public static int solution3(int[] A) {
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(Flags.solution1(new int[] { 1, 5, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2 }));
    }

}
