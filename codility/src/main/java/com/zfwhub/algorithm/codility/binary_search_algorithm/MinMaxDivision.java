package com.zfwhub.algorithm.codility.binary_search_algorithm;

import java.util.*;

// https://app.codility.com/programmers/lessons/14-binary_search_algorithm/min_max_division/
public class MinMaxDivision {

    /**
     * brute force
     */
    public static int solution(int K, int M, int[] A) {
        if (K == 1) {
            return Arrays.stream(A).sum();
        }
        List<List<List<Integer>>> blocks = splitArray(A, K);
        int minLargeSum = Integer.MAX_VALUE;
        for (int i = 0; i < blocks.size(); i++) {
            List<List<Integer>> list = blocks.get(i);
            int largeSum = Integer.MIN_VALUE;
            for (int j = 0; j < list.size(); j++) {
                List<Integer> list2 = list.get(j);
                int sum = 0;
                for (int k = 0; k < list2.size(); k++) {
                    sum += list2.get(k);
                }
                largeSum = Math.max(largeSum, sum);
            }
            minLargeSum = Math.min(minLargeSum, largeSum);
        }
        return minLargeSum;
    }

    /**
     * split array to k blocks
     */
    public static List<List<List<Integer>>> splitArray(int[] arr, int k) {
        int[] arr1 = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr1[i] = i;
        }
        LinkedList<Integer> stack = new LinkedList<Integer>();
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        List<List<List<Integer>>> result = new ArrayList<List<List<Integer>>>();
        splitArrayHelper(arr1, 1, k, stack, list);
        for (int i = 0; i < list.size(); i++) {
            List<Integer> list2 = list.get(i);
            List<List<Integer>> list4 = new ArrayList<List<Integer>>();
            int start = 0;
            for (int j = 0; j < list2.size(); j++) {
                List<Integer> list3 = new ArrayList<Integer>();
                for (int j2 = start; j2 < list2.get(j); j2++) {
                    list3.add(arr[j2]);
                }
                list4.add(list3);
                start = list2.get(j);
            }
            if (list2.size() > 0) {
                start = list2.get(list2.size() - 1);
                List<Integer> list3 = new ArrayList<Integer>();
                for (int j2 = start; j2 < arr.length; j2++) {
                    list3.add(arr[j2]);
                }
                list4.add(list3);
            }
            result.add(list4);
        }
        return result;
    }

    /*
     * dynamic nested for loops
     * k is how many blocks,
     * n is array's length
     */
    private static void splitArrayHelper(int[] arr, int x, int n, LinkedList<Integer> stack, List<List<Integer>> list) {
        if (n == 1) {
            ArrayList<Integer> list2 = new ArrayList<Integer>();
            ArrayList<Integer> tempStack = new ArrayList<Integer>(stack);
            for (int i = tempStack.size() - 1; i >= 0; i--) {
                list2.add(arr[tempStack.get(i) - 1]);
            }
            list.add(list2);
            return;
        }
        for (int i = x; i <= arr.length; i++) {
            n--;
            stack.push(i);
            splitArrayHelper(arr, i, n, stack, list);
            stack.pop();
            n++;
        }
    }

    public static int solutionMock(int K, int M, int[] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = i; j < A.length; j++) {
                for (int k = j; k < A.length; k++) {
                    int[] arr1 = Arrays.copyOfRange(A, 0, i);
                    int[] arr2 = Arrays.copyOfRange(A, i, j);
                    int[] arr3 = Arrays.copyOfRange(A, j, k);
                    int[] arr4 = Arrays.copyOfRange(A, k, A.length);
                    System.out.println(Arrays.toString(arr1));
                    System.out.println(Arrays.toString(arr2));
                    System.out.println(Arrays.toString(arr3));
                    System.out.println(Arrays.toString(arr4));
                    System.out.println("----");
                    System.out.println("[" + i + ", " + j + ", " + k + "]");
                }
            }
        }
        return 0;
    }
    
    // TODO MinMaxDivision
    public static int solution2(int K, int M, int[] A) {
        return 0;
    }

    public static void main(String[] args) {
        int K = 3;
        int M = 5;
        int[] A = new int[] {2,1,5,1,2,2,2};
        System.out.println(MinMaxDivision.solution(K, M, A));
        System.out.println(Arrays.stream(A).sum());
    }

}
