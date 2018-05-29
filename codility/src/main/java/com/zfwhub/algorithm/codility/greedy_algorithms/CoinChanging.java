package com.zfwhub.algorithm.codility.greedy_algorithms;

import java.util.LinkedHashMap;

/**
 * https://codility.com/media/train/14-GreedyAlgorithms.pdf
 */
public class CoinChanging {
	
	/**
	 * greedy, wrong. M = [1,3,4] k = 6
	 */
	public static LinkedHashMap<Integer, Integer> solution(int[] M, int k) {
		LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
		for (int i = M.length-1; i >= 0; i--) {
			map.put(M[i], k / M[i]);
			k = k % M[i];
		}
		return map;
	}
	
	// TODO CoinChanging
	public static LinkedHashMap<Integer, Integer> solution2(int[] M, int k) {
		return null;
	}
	
	public static void main(String[] args) {
		int[] M = new int[] {1,3,4};
		int k = 6;
		System.out.println(CoinChanging.solution(M, k));
	}
}
