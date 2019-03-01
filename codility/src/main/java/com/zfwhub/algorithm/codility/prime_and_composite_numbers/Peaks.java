package com.zfwhub.algorithm.codility.prime_and_composite_numbers;

import java.util.*;

import com.zfwhub.algorithm.utils.CollectionUtil;

// https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/peaks/
public class Peaks {

    // brute force
    public static int solution1(int[] A) {
        Set<Integer> peaks = getPeaks(A);
        // 最多不可能超过peaks.size()，从大到小尝试。
        for (int i = peaks.size(); i > 0; i--) {
            // 如果能整除，则判断每个区间是否有peak
            if (A.length % i == 0) { // i表示当前分成多少份
                boolean flag = true; //是否每一份都有peak
                // 一份一份的检查 
                for (int j = 0; j < i; j++) { // j表示当前是第几份
                    Set<Integer> set = new HashSet<>();
                    // 生成当前份的索引
                    for (int k = j*(A.length/i); k < (j+1)*(A.length/i); k++) {
                        set.add(k);
                    }
                    if (!CollectionUtil.hasDuplicates(peaks, set)) {
                        // 只要有一份没有peak，退出此次i的检查。
                        flag = false;
                        break; // break掉j，进入下一个i的检查。
                    }
                }
                if (flag) {
                    return i;
                }
            }
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
        // 有几个peak则最多分成几块，将每个peak都尽量往右靠。如果没有重复的。
//      Set<Integer> set = new HashSet<>();
//      for (Integer peak : peaks) {
//          set.add(NumberUtil.closestMultiple(peak, A.length/i, ClosestMultipleFlag.CEIL)-1);
//      }
//      System.out.println(set);
//      if (set.size() == peaks.size()) {
//          return i;
//      }
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
