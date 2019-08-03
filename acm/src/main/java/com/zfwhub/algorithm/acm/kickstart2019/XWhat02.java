package com.zfwhub.algorithm.acm.kickstart2019;

import java.util.Scanner;

public class XWhat02 {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            int T = sc.nextInt();
            for (int i = 0; i < T; i++) {
                int N = sc.nextInt();
                int Q = sc.nextInt();
                int[] A = new int[N];
                for (int j = 0; j < N; j++) {
                    A[j] = sc.nextInt();
                }
                System.out.print("Case #"+(i+1)+": ");
                for (int j = 0; j < Q; j++) {
                    int index = sc.nextInt();
                    int value = sc.nextInt();
                    A[index] = value;
                    System.out.print(largestXOREvenSubinterval(A) + " ");
                }
                System.out.print("\n");
            }
        } finally {
            sc.close();
        }
    }
    
    public static int largestXOREvenSubinterval(int[] A) {
        int result = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i; j < A.length; j++) {
                int xorSum = 0;
                for (int k = i; k <= j; k++) {
                    xorSum ^= A[k];
                }
                if (Integer.bitCount(xorSum) % 2 == 0) {
                    result = Math.max(result, (j-i+1));
                }
            }
        }
        return result;
    }
}
