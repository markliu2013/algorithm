package com.zfwhub.algorithm.codility.prime_and_composite_numbers;
/**
 * https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/count_factors/ 
 */
public class CountFactors {
    
    /**
     * count 1 by 1  Integer.MAX_VALUE wrong
     */
    public static int solution(int N) {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (N % i == 0) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Don't need count from 1 to n, just need from 1 to n's square root
     * Will overflow
     */
    public static int solution2(int N) {
        int count = 0;
        int i = 1;
        for (;i * i < N; i++) {
            if (N % i == 0) {
                count = count + 2;
            }
        }
        if (i * i == N) {
			count++;
		}
        return count;
    }
    
    public static int solution3(int N) {
        int count = 0;
        int i = 1;
        while (i * i < N) {
			if (N % i == 0) {
			    count = count + 2;
			}
        	i++;
		}
        if (i * i == N) {
			count++;
		}
        return count;
    }
    
    /**
     * fix overflow  Integer.MAX_VALUE wrong
     */
    public static int solution4(int N) {
    	int count = 0;
        int i = 1;
        while (i < Math.sqrt(N)) {
			if (N % i == 0) {
			    count = count + 2;
			}
        	i++;
		}
        if (i == Math.sqrt(N)) {
			count++;
		}
        return count;
    }
    
    // TODO CountFactors fix Integer.MAX_VALUE
    /**
     * fix overflow
     */
    public static int solution5(long N) {
        int count = 0;
        long i = 1L;
        while (i * i < N) {
			if (N % i == 0) {
			    count = count + 2;
			}
        	i++;
		}
        if (i * i == N) {
			count++;
		}
        return count;
    }
    
    public static void main(String[] args) {
//        System.out.println(CountFactors.solution(49));
//        System.out.println(CountFactors.solution3(49));
//        System.out.println(CountFactors.solution3(Integer.MAX_VALUE));
        System.out.println(CountFactors.solution4(49));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(CountFactors.solution4(Integer.MAX_VALUE));
        System.out.println(CountFactors.solution5(2147483649L));
        
    }

}
