package com.zfwhub.algorithm.codility.leader;

import java.util.Arrays;

/**
 * https://app.codility.com/programmers/lessons/8-leader/equi_leader/
 */
public class EquiLeader {

    /**
     * count all index
     */
    public static int solution(int[] A) {
        int count = 0;
        for (int i = 1; i < A.length; i++) {
            int[] arr1 = Arrays.copyOfRange(A, 0, i);
            int[] arr2 = Arrays.copyOfRange(A, i, A.length);
            int leader1 = Leader.goldenLeader(arr1);
            int leader2 = Leader.goldenLeader(arr2);
            if ((leader1 != -1) && (leader1 == leader2)) {
                count++;
            }
        }
        return count;
    }

    /**
     * similar TapeEquilibrium
     */
    public static int solution2(int[] A) {
        int size = 0;
        int value = 0;
        for (int i = 0; i < A.length; i++) {
            if (size == 0) {
                size++;
                value = A[i];
            } else {
                if (value != A[i]) {
                    size--;
                } else {
                    size++;
                }
            }
        }
        int candidate = -1;
        if (size > 0) {
            candidate = value;
        }
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == candidate) {
                count++;
            }
        }
        int countEquiLeader = 0;
        if (count > A.length / 2) {
            int leftLeaderCount = 0;
            int rightLeaderCount = count;
            for (int i = 0; i < A.length - 1; i++) {
                if (A[i] == candidate) {
                    leftLeaderCount++;
                    rightLeaderCount--;
                }
                if (leftLeaderCount > (i + 1) / 2 && rightLeaderCount > (A.length - (i + 1)) / 2) {
                    countEquiLeader++;
                }
            }
            return countEquiLeader;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(EquiLeader.solution2(new int[] { 2, 2, 2, 2, 1 }));
    }

}
