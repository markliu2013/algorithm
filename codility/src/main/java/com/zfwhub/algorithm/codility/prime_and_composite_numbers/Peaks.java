package com.zfwhub.algorithm.codility.prime_and_composite_numbers;

import java.util.*;

import com.zfwhub.algorithm.utils.CollectionUtil;
import com.zfwhub.algorithm.utils.NumberUtil;

// https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/peaks/
public class Peaks {

    // brute force
    public static int solution1(int[] A) {
        Set<Integer> peaks = getPeaks(A);
        // 有几个peak则最多分成几块，将每个peak都尽量往右靠。如果没有重复的。
        for (int i = peaks.size(); i > 0; i--) {
            if (A.length % i == 0) {
                boolean flag = true;
                for (int j = 0; j < i; j++) {
                    Set<Integer> set = new HashSet<>();
                    for (int k = j*(A.length/i); k < (j+1)*(A.length/i); k++) {
                        set.add(k);
                    }
                    if (CollectionUtil.subtract(set, peaks).size() == set.size()) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    return i;
                }
            }
//            Set<Integer> set = new HashSet<>();
//            for (Integer peak : peaks) {
//                set.add(NumberUtil.closestMultiple(peak, A.length/i, ClosestMultipleFlag.CEIL)-1);
//            }
//            System.out.println(set);
//            if (set.size() == peaks.size()) {
//                return i;
//            }
        }
        return 0;
    }
    
    // TODO Peaks 优化
    public static int solution2(int[] A) {
        Set<Integer> peaks = getPeaks(A);
        int peakSize = peaks.size();
        int result = 0;
        if (peakSize == 0) {
            return 1;
        }
        List<Integer> primes = NumberUtil.getAllPrime(A.length);
        
        
        return result;
    }
    
    private static Set<Integer> getPeaks(int[] A) {
        Set<Integer> peaks = new HashSet<>();
        for (int i = 1; i < A.length-1; i++) {
            if (A[i] > A[i-1] && A[i] > A[i+1]) {
                peaks.add(i);
            }
        }
        return peaks;
    }
    
    public static void main(String[] args) {
        int[] A = new int[] {1,2,3,4,3,4,1,2,3,4,6,2};
//        int[] A = new int[] {0, 1, 0, 0, 1, 0, 0, 1, 0};
        System.out.println(solution1(A));
    }

}
