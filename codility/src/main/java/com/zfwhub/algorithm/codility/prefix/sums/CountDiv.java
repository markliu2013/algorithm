package com.zfwhub.algorithm.codility.prefix.sums;

/**
 * 找A, B之前能被K整除的整数的个数。
 * https://app.codility.com/programmers/lessons/5-prefix_sums/count_div/
 */
public class CountDiv {
    
    /**
     * count 1 by 1...
     * 可以知道哪些具体的数字
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
     * 利用差值直接计算，不能知道具体的数字，优化的方法是否都是丢失了信息？
     * 先判断第一个数是否符合，后面根据差值累加。
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
