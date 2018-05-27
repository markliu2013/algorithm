package com.zfwhub.algorithm.codility.binary_search_algorithm;

public class BinarySearch {
    
    public static int solution(int[] A, int x) {
        return binarySearchHelp(A, 0, A.length, x);
    }
    
    public static int binarySearchHelp(int[] A, int start, int end, int x) {
        if (start == A.length) {
            return -1;
        }
        int mid = (end - start) / 2 + start;
        if (A[mid] == x) {
            return mid;
        }
        if (end == start) {
            return -1;
        }
        if (A[mid] < x) {
            return binarySearchHelp(A, mid+1, end, x);
        }
        if (A[mid] > x) {
            return binarySearchHelp(A, start, mid, x);
        }
        return -1;
    }
    
    public static int solution2(int[] A, int x) {
        int begin = 0;
        int end = A.length - 1;
        while (begin <= end) {
            int mid = (begin + end) / 2;
            if (A[mid] == x) {
                return mid;
            }
            if (A[mid] < x) {
                begin = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        int[] A = new int[]{1,2,3,4,5,6};
        for (int i = 0; i < A.length; i++) {
            System.out.print(BinarySearch.solution(A, i+1) + " ");
        }
        System.out.println();
        for (int i = 0; i < A.length; i++) {
            System.out.print(BinarySearch.solution2(A, i+1) + " ");
        }
        System.out.println();
        System.out.println(BinarySearch.solution(A, 0));
        System.out.println(BinarySearch.solution2(A, 0));
        System.out.println(BinarySearch.solution(A, 8));
        System.out.println(BinarySearch.solution2(A, 8));
    }

}
