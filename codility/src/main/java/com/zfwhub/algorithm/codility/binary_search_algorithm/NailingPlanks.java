package com.zfwhub.algorithm.codility.binary_search_algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class NailingPlanks {

    /**
     * brute force
     */
    public static int solution(int[] A, int[] B, int[] C) {
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

    /**
     * brute force, remove early
     */
    // TODO wrong NailingPlanks
    public static int solution4(int[] A, int[] B, int[] C) {
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        for (int i = 0; i < A.length; i++) {
            list1.add(A[i]);
        }
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        for (int i = 0; i < B.length; i++) {
            list2.add(B[i]);
        }
        ArrayList<Integer> list3 = new ArrayList<Integer>();
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < list1.size(); j++) {
                if (C[i] >= list1.get(j) && C[i] <= list2.get(j)) {
                    list3.add(j);
                }
            }
            if (list3.size() == A.length) {
                return i + 1;
            }
            for (Integer integer : list3) {
                list1.remove(integer);
                list2.remove(integer);
            }
        }
        return -1;
    }

    /**
     * binary search
     */
    // TODO wrong.
    public static int solution2(int[] A, int[] B, int[] C) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < C.length; i++) {
            int begin1 = 0;
            int end1 = A.length - 1;
            while (begin1 <= end1) {
                int mid = (begin1 + end1) / 2;
                if (A[mid] < C[i]) {
                    begin1 = mid + 1;
                } else if (A[mid] > C[i]) {
                    end1 = mid - 1;
                } else {
                    end1 = mid;
                    break;
                }
            }
            int begin2 = 0;
            int end2 = B.length - 1;
            while (begin2 <= end2) {
                int mid = (begin2 + end2) / 2;
                if (B[mid] < C[i]) {
                    begin2 = mid + 1;
                } else if (B[mid] > C[i]) {
                    end2 = mid - 1;
                } else {
                    begin2 = mid;
                    break;
                }
            }
            // 0 to end1, begin2 to list2.size
            for (int j = begin2; j <= end1; j++) {
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

    // TODO list, check and remove
    public static int solution3(int[] A, int[] B, int[] C) {
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        for (int i = 0; i < A.length; i++) {
            list1.add(A[i]);
        }
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        for (int i = 0; i < B.length; i++) {
            list2.add(B[i]);
        }
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < C.length; i++) {
            int begin1 = 0;
            int end1 = list1.size() - 1;
            while (begin1 <= end1) {
                int mid = (begin1 + end1) / 2;
                if (list1.get(mid) < C[i]) {
                    begin1 = mid + 1;
                } else if (list1.get(mid) > C[i]) {
                    end1 = mid - 1;
                } else {
                    end1 = mid;
                    break;
                }
            }
            int begin2 = 0;
            int end2 = list2.size() - 1;
            while (begin2 <= end2) {
                int mid = (begin2 + end2) / 2;
                if (list2.get(mid) < C[i]) {
                    begin2 = mid + 1;
                } else if (list2.get(mid) > C[i]) {
                    end2 = mid - 1;
                } else {
                    end2 = mid;
                    break;
                }
            }
            // 0 to end1, begin2 to list2.size
            if (end1 >= begin2) {
                for (int j = begin2; j <= end1; j++) {
                    if (C[i] >= list1.get(j) && C[i] <= list2.get(j)) {
                        set.add(j);
                        if (set.size() == A.length) {
                            return i + 1;
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //        int[] A = new int[]{1,4,5,8};
        //        int[] B = new int[]{4,5,9,10};
        //        int[] C = new int[]{4,5,5,5};
        int[] A = new int[] { 3, 4, 5, 6, 9 };
        int[] B = new int[] { 16, 17, 18, 19, 20 };
        int[] C = new int[] { 19, 17, 7 };
        System.out.println(NailingPlanks.solution(A, B, C));
        System.out.println(NailingPlanks.solution2(A, B, C));
    }

}
