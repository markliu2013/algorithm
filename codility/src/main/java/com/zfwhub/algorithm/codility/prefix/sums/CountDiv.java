package com.zfwhub.algorithm.codility.prefix.sums;

/**
 * count the numbers divisible by K between A and B.
 * https://app.codility.com/programmers/lessons/5-prefix_sums/count_div/
 */
public class CountDiv {
    
    /**
     * count 1 by 1...
     * you can know the numbers exactly.
     */
    public int solution(int A, int B, int K) {
        int count = 0;
        for (int i = A; i <= B; i++) {
            if (i % K ==0) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * use the difference between A and B to count.
     * You can't know the numbers, the optimization way will lost information?
     * You should check the first number before
     */
    public int solution2(int A, int B, int K) {
        int count = 0;
        if (A % K == 0) count++;
        count += (int) Math.floor((B - A) / (float)K);
        return count;
    }
    
    public static void main(String[] args) {
        CountDiv cd = new CountDiv();
        System.out.println(cd.solution2(6, 11, 2));
        System.out.println(cd.solution2(10, 10, 5));
    }
    
}
