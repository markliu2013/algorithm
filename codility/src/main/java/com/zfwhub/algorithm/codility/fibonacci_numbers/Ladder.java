package com.zfwhub.algorithm.codility.fibonacci_numbers;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * https://app.codility.com/programmers/lessons/13-fibonacci_numbers/ladder/
 */
public class Ladder {
    
	/**
	 * brute force, use BigInteger fix overflow.
	 */
    public static int[] solution(int[] A, int[] B) {
        int[] result = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            BigInteger a = fibonacciDynamic(A[i]);
            BigInteger b = new BigInteger("2").pow(B[i]);
            result[i] = a.divideAndRemainder(b)[1].intValue();
        }
        return result;
    }
    
    public static BigInteger fibonacciDynamic(int n) {
        BigInteger[] fibArr = new BigInteger[n+2];
        fibArr[0] = new BigInteger("0");
        fibArr[1] = new BigInteger("1");
        for (int i = 2; i <= n+1; i++) {
            fibArr[i] = fibArr[i-1].add(fibArr[i-2]);
        }
        return fibArr[fibArr.length-1];
    }
    
    /**
     * 334 % 10 = 4
     * 12345 % 10 = 5
     * 334 % 100 = 34
     * 12345 % 100 = 45
     * &0xff?
     */
    // TODO ladder
    public static int[] solution2(int[] A, int[] B) {
        return null;
    }
    
    public static void main(String[] args) {
        int[] A = new int[] {5000, 4, 5, 5, 1};
        int[] B = new int[] {10, 2, 4, 3, 1};
        System.out.println(Arrays.toString(Ladder.solution(A, B)));
        System.out.println(Arrays.toString(Ladder.solution2(A, B)));
    }

}
