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
		// then count
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
		// get smallest prime from 1 to N
		int[] F = new int[N + 1];
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
		// prefix sums
		int[] prefixSums = new int[N+1];
		for (int j = 4; j <= N; j++) {//1,2,3 is not Semiprime
			if (F[j] != 0) {
				int a = j / F[j];
				if (F[a] == 0) {
					prefixSums[j] = prefixSums[j-1] + 1;
				} else {
					prefixSums[j] = prefixSums[j-1];
				}
			} else {
				prefixSums[j] = prefixSums[j-1];				
			}
		}
		// count
		int[] result = new int[P.length];
		for (int j = 0; j < P.length; j++) {
			result[j] = (Q[j] == 0 ? 0 : prefixSums[Q[j]]) - (P[j] == 0 ? 0 : prefixSums[P[j]-1]);
		}
		return result;
	}
	
	public static void main(String[] args) {
		int N = 10000000;
		int[] P = new int[] {1,4,16,0};
		int[] Q = new int[] {26,10,20,10000000};
		System.out.println(Arrays.toString(CountSemiprimes.solution(N, P, Q)));
		System.out.println(Arrays.toString(CountSemiprimes.solution2(N, P, Q)));
	}
	
}
