package com.zfwhub.algorithm.codility.time.complexity;

/**
 *
 */
public class FrogJmp {

    public int solution(int X, int Y, int D) {
        return (int) Math.ceil((Y - X) / (float)D);
    }

    public static void main(String[] args) {
        FrogJmp fj = new FrogJmp();
        System.out.println(fj.solution(10, 85, 30));
    }

}
