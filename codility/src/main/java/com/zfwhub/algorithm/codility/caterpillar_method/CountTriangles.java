package com.zfwhub.algorithm.codility.caterpillar_method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.zfwhub.algorithm.utils.ArrayUtil;
import com.zfwhub.algorithm.utils.CollectionUtil;

// https://app.codility.com/programmers/lessons/15-caterpillar_method/count_triangles/
// https://leetcode.com/problems/valid-triangle-number/
// https://www.cnblogs.com/grandyang/p/7053730.html
public class CountTriangles {

    // brute force
    public static int solution1(int[] A) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                for (int k = j + 1; k < A.length; k++) {
                    if (A[i] + A[j] > A[k] && A[i] + A[k] > A[j] && A[j] + A[k] > A[i]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    // collection util
    public static int solution2(int[] A) {
        int count = 0;
        if (A.length < 3) {
            return count;
        }
        List<List<Integer>> triples = CollectionUtil.combine(ArrayUtil.toList(A), 3);
        for (List<Integer> triple : triples) {
            Collections.sort(triple);
            if (triple.get(0) + triple.get(1) > triple.get(2)) {
                count++;
            }
        }
        return count;
    }
    
    // 排序后，固定两个数，然后二分查找。 Performance 100%
    public static int solution3(int[] A) {
        int count = 0;
        Arrays.sort(A);
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                // 要找一个位置，满足 x >= value 的最小x
                count += ArrayUtil.lowerBound(A, j+1, A.length, A[i]+A[j]) - (j+1);
            }
        }
        return count;
    }
    
    // 参考three sum，排序后，固定一个点，然后two point
    public static int solution4(int[] A) {
        int count = 0;
        Arrays.sort(A);
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < A.length-2; i++) {
            int leftIndex = i+1;
            int rightIndex = A.length-1;
            while (leftIndex < rightIndex) {
                if (A[i] + A[leftIndex] > A[rightIndex]) {
                    count = count + (rightIndex-leftIndex);
                    for (int j = leftIndex; j <= rightIndex; j++) {
                        for (int k = j+1; k <= rightIndex; k++) {
                            list.add(Arrays.asList(A[i], A[j], A[k]));
                        }
                    }
                    break;
                }
                
                for (int j = leftIndex+1; j < rightIndex; j++) {
                    if (A[i] + A[j] > A[rightIndex]) {
                        //for (int k = j; k < rightIndex; k++) {
                            list.add(Arrays.asList(A[i], A[j], A[rightIndex]));
                        //}
                    }
                }
                rightIndex--;
            }
        }
        return list.size();
    }
    
    // 同solution4，只计算count
    public static int solution5(int[] A) {
        int count = 0;
        Arrays.sort(A);
        for (int i = 0; i < A.length-2; i++) {
            int leftIndex = i+1;
            int rightIndex = A.length-1;
            while (leftIndex < rightIndex) {
                if (A[i] + A[leftIndex] > A[rightIndex]) {
//                    count += MathUtil.combine(rightIndex-leftIndex+1, 2).intValue();
                    // 中间这一块做2的组合数，全部是问题的解。
                    count += (rightIndex-leftIndex+1)*(rightIndex-leftIndex)/2;
                    break;
                }
                // 固定rightIndex，可优化为二分查找。
                for (int j = leftIndex+1; j < rightIndex; j++) {
                    if (A[i] + A[j] > A[rightIndex]) {
                        count += rightIndex-j;
                        break;
                    }
                }
                rightIndex--;
            }
        }
        return count;
    }
    
    // +二分查找
    public static int solution6(int[] A) {
        int count = 0;
        Arrays.sort(A);
        for (int i = 0; i < A.length-2; i++) {
            int leftIndex = i+1;
            int rightIndex = A.length-1;
            while (leftIndex < rightIndex) {
                if (A[i] + A[leftIndex] > A[rightIndex]) {
//                    count += MathUtil.combine(rightIndex-leftIndex+1, 2).intValue();
                    // 中间这一块做2的组合数，全部是问题的解。
                    count += (rightIndex-leftIndex+1)*(rightIndex-leftIndex)/2;
                    break;
                }
                // 固定rightIndex，可优化为二分查找。
                count += rightIndex-ArrayUtil.upperBound(A, leftIndex+1, rightIndex, A[rightIndex]-A[i]);
                rightIndex--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] A = new int[] {10,12,13,21,22};
        System.out.println(solution1(A));
        //System.out.println(solution2(A));
//        System.out.println(solution3(A));
        System.out.println(solution6(A));
    }
}
