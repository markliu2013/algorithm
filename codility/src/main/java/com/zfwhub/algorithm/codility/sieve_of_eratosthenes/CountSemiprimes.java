package com.zfwhub.algorithm.codility.sieve_of_eratosthenes;

import java.util.Arrays;

public class CountSemiprimes {
	
	/**
	 * Sieve of Eratosthenes
	 */
	public static int[] solution(int N, int[] P, int[] Q) {
		// get smallest prime from 1 to N
		int[] F = new int[N+1];
		int i = 2;
		while (i * i <= N) {
			if (F[i] == 0) {
				int k = i * i;
				while (k <= N) {
					if (F[k] == 0) {
                        F[k] = i;
                    }
                    k += i;
				}
			}
			i++;
		}
		int[] result = new int[P.length];
		for (int j = 0; j < P.length; j++) {
			int count = 0;
			for (int k = P[j]; k <= Q[j]; k++) {
				if (F[k] != 0) {
					int a = k / F[k];
					if (F[a] == 0) {
						count++;
					}
				}
			}
			result[j] = count;
		}
		return result;
	}
	
	// TODO CountSemiprimes
	public static int[] solution2(int N, int[] P, int[] Q) {
		return null;
	}
	
	public static void main(String[] args) {
		int N = 26;
		int[] P = new int[] {1,4,16};
		int[] Q = new int[] {26,10,20};
		System.out.println(Arrays.toString(CountSemiprimes.solution(N, P, Q)));
	}
	
}
