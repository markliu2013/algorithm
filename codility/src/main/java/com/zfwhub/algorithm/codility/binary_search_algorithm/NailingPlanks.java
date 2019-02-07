package com.zfwhub.algorithm.codility.binary_search_algorithm;

import java.util.*;

import com.zfwhub.algorithm.utils.ArrayUtil;

// https://app.codility.com/programmers/lessons/14-binary_search_algorithm/nailing_planks/
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

    /**
     * binary search
     */
    // TODO wrong.
    public static int solution3(int[] A, int[] B, int[] C) {
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
    public static int solution4(int[] A, int[] B, int[] C) {
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
        System.out.println(NailingPlanks.solution1(A, B, C));
        System.out.println(NailingPlanks.solution2(A, B, C));
        System.out.println(NailingPlanks.solution3(A, B, C));
    }

}
