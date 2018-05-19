package com.zfwhub.algorithm.codility.prime_and_composite_numbers;

import java.util.ArrayList;

public class Flags {
    
    public static int solution(int[] A) {
        ArrayList<Integer> peakIndexes = new ArrayList<Integer>();
        for (int i = 1; i < A.length - 1; i++) {
            if (A[i] > A[i-1] && A[i] > A[i+1]) {
                peakIndexes.add(i);
            }
        }
        if (peakIndexes.size() == 0) {
            return 0;
        }
        if (peakIndexes.size() == 1) {
            return 1;
        }
        int maxFlags = 0;
        for (int i = 2; i <= peakIndexes.size(); i++) {
            int count = 1;
            int preIndex = 0;
            for (int j = 1; j < i; j++) {
                if (peakIndexes.get(j) - peakIndexes.get(preIndex) >= i) {
                    count++;
                    preIndex = j;
                }
            }
            maxFlags = Math.max(maxFlags, count);
        }
        return maxFlags;
    }
    
    public static void main(String[] args) {
        System.out.println(Flags.solution(new int[] {1,5,3,4,3,4,1,2,3,4,6,2}));
    }

}
