package com.zfwhub.algorithm.codility.sieve_of_eratosthenes;

import java.util.HashSet;

public class Eratosthenes {
	
	/**
	 * get all prime numbers from 1 to n
	 */
	public static HashSet<Integer> sieve(int n) {
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 2; i <= n; i++) {
			set.add(i);
		}
		int i = 2;
		while (i * i <= n) {
			int multiplicator = 2;
			while (i * multiplicator <= n) {
				set.remove(i * multiplicator);
				multiplicator++;
			}
			i++;
		}
		return set;
	}
	
	public static HashSet<Integer> sieve2(int n) {
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 2; i <= n; i++) {
			set.add(i);
		}
		int i = 2;
		while (i * i <= n) {
			int k = i * i;
			while (k <= n) {
				set.remove(k);
				k += i;
			}
			i++;
		}
		return set;
	}
	
	public static HashSet<Integer> factorization(int n) {
		
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(Eratosthenes.sieve(100));
		System.out.println(Eratosthenes.sieve2(100));
	}
	
}
