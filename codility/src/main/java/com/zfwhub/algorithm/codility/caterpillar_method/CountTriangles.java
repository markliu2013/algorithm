package com.zfwhub.algorithm.codility.caterpillar_method;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.zfwhub.algorithm.utils.ArrayUtil;
import com.zfwhub.algorithm.utils.CollectionUtil;

// https://app.codility.com/programmers/lessons/15-caterpillar_method/count_triangles/
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
                System.out.println(triple);
                count++;
            }
        }
        return count;
    }
    
    // TODO CountTriangles https://www.cnblogs.com/grandyang/p/7053730.html
    // 排序后，固定两个数，然后二分查找。
    public static int solution3(int[] A) {
        int count = 0;
        Arrays.sort(A);
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                Arrays.binarySearch(A, j+1, A.length, A[i]+A[j]);
                
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] A = ArrayUtil.newIntArray(1, 10, 1);
        System.out.println(solution2(A));
    }
}
