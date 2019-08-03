package com.zfwhub.algorithm.acm.kickstart2019;

import java.util.Scanner;

public class XWhat03 {
    
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
        int[] prefixXORs = new int[A.length];
        prefixXORs[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            prefixXORs[i] = prefixXORs[i - 1] ^ A[i];
        }
        int result = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i; j < A.length; j++) {
                int XORs = prefixXORs[j] ^ (i >= 1 ? prefixXORs[i-1] : 0);
                if (Integer.bitCount(XORs) % 2 == 0) {
                    result = Math.max(result, (j-i+1));
                }
            }
        }
        return result;
    }
    
}
