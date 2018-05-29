package com.zfwhub.algorithm.codility.time.complexity;

/**
 * 将数组切一刀, 求左右两边的和的差值最小的那一刀在哪里。
 * https://app.codility.com/programmers/lessons/3-time_complexity/tape_equilibrium
 */
public class TapeEquilibrium {

    /**
     * 1. 先求出整个数组的和 sum
     * 2. 左边的和leftSum初始为0, 右边的和rightSum初始为sum
     * 3. 将刀开始从第一个数往右边滑动, 每滑一位, leftSum加一位, rightSum减一位
     * 4. 循环中计算左右的差值, 选择最小值。
     */
    public int solution(int[] A) {
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
        }
        int leftSum = 0;
        int rightSum = sum;
        int gap = Integer.MAX_VALUE;
        for (int i = 0; i < A.length - 1; i++) {
            leftSum += A[i];
            rightSum -= A[i];
            gap = Math.min(Math.abs(leftSum - rightSum), gap);
        }
        return gap;
    }

    public static void main(String[] args) {
        TapeEquilibrium te = new TapeEquilibrium();
        int[] A = new int[] { 0, 2000 };
        System.out.println(te.solution(A));
    }

}
