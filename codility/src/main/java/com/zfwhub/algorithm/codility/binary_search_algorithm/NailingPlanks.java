package com.zfwhub.algorithm.codility.binary_search_algorithm;

import java.util.*;

import com.zfwhub.algorithm.utils.ArrayUtil;

// https://app.codility.com/programmers/lessons/14-binary_search_algorithm/nailing_planks/
// http://codility-lessons.blogspot.com/2015/03/lesson-11-nailingplanks-nailing-planks.html
public class NailingPlanks {

    // brute force，必须是前面的所有钉子都使用。
    public static int solution1(int[] A, int[] B, int[] C) {
        // 把nailed的index放到set
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (C[i] >= A[j] && C[i] <= B[j]) {
                    set.add(j);
                }
            }
            if (set.size() == A.length) {
                return i + 1;
            }
        }
        return -1;
    }

    // 删除A和B中已经nailed，后续搜索提高效率。
    public static int solution2(int[] A, int[] B, int[] C) {
        List<Integer> listA = ArrayUtil.toList(A);
        List<Integer> listB = ArrayUtil.toList(B);
        for (int i = 0; i < C.length; i++) {
            List<Integer> listAtoRemove = new ArrayList<>();
            List<Integer> listBtoRemove = new ArrayList<>();
            for (int j = 0; j < A.length; j++) {
                if (C[i] >= A[j] && C[i] <= B[j]) {
                    listAtoRemove.add(A[j]);
                    listBtoRemove.add(B[j]);
                }
            }
            listA.removeAll(listAtoRemove);
            listB.removeAll(listBtoRemove);
            if (listA.size() == 0 && listB.size() == 0) {
                return i + 1;
            }
        }
        return -1;
    }

    // TODO NailingPlanks. binary search
    // binary search
    public static int solution3(int[] A, int[] B, int[] C) {
        HashSet<Integer> set = new HashSet<Integer>();
        Arrays.sort(A);
        Arrays.sort(B);
        int indexA = 0;//从左往右挤
        int indexB = B.length-1;//从右往左挤
        for (int i = 0; i < C.length; i++) {
            indexA = Arrays.binarySearch(A, indexA, A.length, C[i]);
            //if (indexA < 0) {
                indexA = Math.abs(indexA+1);
            //}
            indexB = Arrays.binarySearch(B, 0, indexB+1, C[i]);
            if (indexB < 0) {
                indexB = Math.abs(indexB+1);
            }
            for (int j = indexB; j < indexA; j++) {
                set.add(j);
            }
            if (set.size() == A.length) {
                return i + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//                int[] A = new int[]{1,4,5,8};
//                int[] B = new int[]{4,5,9,10};
//                int[] C = new int[]{4,6,7,10,2};
        int[] A = new int[] { 3, 4, 4, 6, 9 };
        int[] B = new int[] { 16, 17, 17, 19, 20 };
        int[] C = new int[] { 19, 17, 7 };
        System.out.println(solution1(A, B, C));
//        System.out.println(solution2(A, B, C));
        System.out.println(solution3(A, B, C));
    }

}
