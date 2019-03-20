package com.zfwhub.algorithm.codility.caterpillar_method;

import java.util.HashSet;

// https://app.codility.com/programmers/lessons/15-caterpillar_method/abs_distinct/
public class AbsDistinct {
    
    // 使用hashset，performance 100%
    public static int solution1(int[] A) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < A.length; i++) {
            if (!set.contains(Math.abs(A[i]))) {
                set.add(Math.abs(A[i]));
            }
        }
        return set.size();
    }
    
    // two pointer 算法。
    public static int solution2(int[] A) {
        int count = 0;
        int leftIndex = 0;
        int rightIndex = A.length-1;
        while (leftIndex <= rightIndex) {
            if (Math.abs((long)A[leftIndex]) > Math.abs((long)A[rightIndex])) {
                // 一直移动到下一个不相等的点
                int newLeftIndex = leftIndex+1;
                while (newLeftIndex <= rightIndex) {
                    if (A[newLeftIndex] != A[leftIndex]) {
                        break;
                    }
                    newLeftIndex++;
                }
                leftIndex = newLeftIndex;
                count++;
            } else if (Math.abs((long)A[leftIndex]) < Math.abs((long)A[rightIndex])) {
                int newRightIndex = rightIndex-1;
                while (newRightIndex >= leftIndex) {
                    if (A[newRightIndex] != A[rightIndex]) {
                        break;
                    }
                    newRightIndex--;
                }
                rightIndex = newRightIndex;
                count++;
            } else {
                int newLeftIndex = leftIndex+1;
                while (newLeftIndex <= rightIndex) {
                    if (A[newLeftIndex] != A[leftIndex]) {
                        break;
                    }
                    newLeftIndex++;
                }
                leftIndex = newLeftIndex;
                int newRightIndex = rightIndex-1;
                while (newRightIndex >= leftIndex) {
                    if (A[newRightIndex] != A[rightIndex]) {
                        break;
                    }
                    newRightIndex--;
                }
                rightIndex = newRightIndex;
                count++;
            }
        }
        return count;
    }
    
    public static void main(String[] args) {
        int[] A = new int[] {-5,-3,-1,0,3,6};
        System.out.println(AbsDistinct.solution1(A));
        System.out.println(AbsDistinct.solution2(A));
    }

}
