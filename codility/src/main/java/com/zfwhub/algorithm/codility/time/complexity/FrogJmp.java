package com.zfwhub.algorithm.codility.time.complexity;

/**
 * https://app.codility.com/programmers/lessons/3-time_complexity/frog_jmp/
 */
public class FrogJmp {

    /**
     * 两个整数相除, 要找结果的向上取整。
     */
    public int solution(int X, int Y, int D) {
        return (int) Math.ceil((Y - X) / (float) D);
    }

    public static void main(String[] args) {
        FrogJmp fj = new FrogJmp();
        System.out.println(fj.solution(10, 85, 30));
    }

}
