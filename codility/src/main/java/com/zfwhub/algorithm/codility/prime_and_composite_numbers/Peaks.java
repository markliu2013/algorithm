package com.zfwhub.algorithm.codility.prime_and_composite_numbers;

import java.util.*;
import com.zfwhub.algorithm.utils.*;
import com.zfwhub.algorithm.utils.NumberUtil.ClosestMultipleFlag;

// https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/peaks/
public class Peaks {

    // brute force, Performance 0%
    public static int solution1(int[] A) {
        List<Integer> peaks = getPeaks(A);
        // 最多不可能超过peaks.size()，从大到小尝试。
        for (int i = peaks.size(); i > 0; i--) {
            // 如果能整除，则判断每个区间是否有peak
            if (A.length % i == 0) { // i表示当前分成多少份
                boolean flag = true; //是否每一份都有peak
                // 一份一份的检查 
                for (int j = 0; j < i; j++) { // j表示当前是第几份
                    Set<Integer> set = new HashSet<>();
                    // 生成当前份的索引
                    for (int k = j * (A.length / i); k < (j + 1) * (A.length / i); k++) {
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
        // A中没有peak，返回0
        return 0;
    }

    // 效率比solution1高很多 Performance 80%
    public static int solution2(int[] A) {
        List<Integer> peaks = getPeaks(A);
        for (int i = peaks.size(); i > 0; i--) {
            if (A.length % i == 0) {
                // 将每个peak都尽量往右靠。
                Set<Integer> set = new HashSet<>();
                for (Integer peak : peaks) {
                    set.add(NumberUtil.closestMultiple(peak + 1, A.length / i, ClosestMultipleFlag.CEIL));
                }
                if (set.size() == i) {
                    return i;
                }
            }
        }
        return 0;
    }

    // Performance 100%
    public static int solution3(int[] A) {
        int[] peaks = ArrayUtil.newIntArray(getPeaks(A));
        for (int i = peaks.length; i > 0; i--) {
            if (A.length % i == 0) {
                boolean flag = true; //是否每一份都有peak
                // 不需要每个peak都判断，peak和A同时判断，实现peak跳跃。
                int peakIndex = 0;
                // peaks是排序好的，可二分查找
                for (int j = 0; j < i; j++) { // j表示当前是第几份
                    int k = (j + 1) * (A.length / i)-1; // 当前份最右边的那个数
                    int peakBound = ArrayUtil.upperBound(peaks, peakIndex, peaks.length, k);
                    if (peakBound == peakIndex) {
                        flag = false;
                        break;
                    } else {
                        peakIndex = peakBound;
                    }
                }
                if (flag) {
                    return i;
                }
            }
        }
        return 0;
    }

    private static List<Integer> getPeaks(int[] A) {
        List<Integer> peaks = new ArrayList<>();
        for (int i = 1; i < A.length - 1; i++) {
            if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                peaks.add(i);
            }
        }
        return peaks;
    }

    public static void main(String[] args) {
        //        int[] A = new int[] {1,2,3,4,3,4,1,2,3,4,6,2};
        //        int[] A = new int[] {0, 1, 0, 0, 1, 0, 0, 1, 0};
        int[] A = new int[] { 1,1,1,3,1,3,2,5,3,10,2};
        System.out.println(solution1(A));
        System.out.println(solution2(A));
        System.out.println(solution3(A));
    }

}
