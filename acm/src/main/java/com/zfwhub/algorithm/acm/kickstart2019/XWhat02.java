package com.zfwhub.algorithm.acm.kickstart2019;

import java.util.Scanner;

import com.zfwhub.algorithm.utils.ArrayUtil;

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
                    System.out.print(largestXOREvenSubinterval4(A) + " ");
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
    
    public static int largestXOREvenSubinterval2(int[] A) {
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
    
    // 参考MaximumSubarray.solution4_3
    public static int largestXOREvenSubinterval3(int[] A) {
        int[] dp = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            int xors = 0;
            int max = 0;
            for (int j = i; j >= 0; j--) {
                xors ^= A[j];
                if (Integer.bitCount(xors) % 2 == 0) {
                    max = i - j + 1;
                }
            }
            dp[i] = max;
        }
        return ArrayUtil.max(dp);
    }
    
    // 参考MaximumSubarray.solution6_1
    public static int largestXOREvenSubinterval4(int[] A) {
        int[] dp = new int[A.length];
        int[] xorDp2 = new int[A.length];
        int[] xorDp = new int[A.length];
        xorDp2[0] = A[0];
        if (Integer.bitCount(A[0]) % 2 == 0) {
            dp[0] = 1;
            xorDp[0] = A[0];
        } else {
            dp[0] = 0;
            xorDp[0] = 0;
        }
        for (int i = 1; i < A.length; i++) {
            if (Integer.bitCount(xorDp[i-1] ^ A[i]) % 2 == 0) {
                dp[i] = dp[i-1] + 1;
                xorDp[i] = xorDp[i-1] ^ A[i];
            } else {
                dp[i] = i + 1;
                xorDp[i] = xorDp2[i-1] ^ A[i];
            }
            xorDp2[i] = xorDp2[i-1] ^ A[i];
        }
        return ArrayUtil.max(dp);
    }
    
    
}
