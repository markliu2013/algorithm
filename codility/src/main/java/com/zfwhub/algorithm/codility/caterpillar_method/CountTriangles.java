package com.zfwhub.algorithm.codility.caterpillar_method;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.zfwhub.algorithm.utils.ArrayUtil;
import com.zfwhub.algorithm.utils.CollectionUtil;

// https://app.codility.com/programmers/lessons/15-caterpillar_method/count_triangles/
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
        List<List<Integer>> triples = CollectionUtil.combine(ArrayUtil.toList(A), 3);
        for (List<Integer> triple : triples) {
            Collections.sort(triple);
            if (triple.get(0) + triple.get(1) > triple.get(2)) {
                count++;
            }
        }
        return count;
    }
    
    // TODO CountTriangles https://www.cnblogs.com/grandyang/p/7053730.html
    public static int solution3(int[] A) {
        Arrays.sort(A);
        
        return 0;
    }

    public static void main(String[] args) {
        int[] A = new int[] {10,2,5,1,8,12};
        System.out.println(CountTriangles.solution2(A));
    }
}
