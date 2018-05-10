package com.zfwhub.algorithm.codility.counting.elements;
import java.util.HashSet;

/**
 * 给定一个长度n的数组和小于n的数字X, 找最早出现1到X之间所有自然数的索引。
 * https://app.codility.com/programmers/lessons/4-counting_elements/frog_river_one/
 */
public class FrogRiverOne {

    /**
     * 利用set不能添加重复的数字
     * 循环数组往set中添加, 当set.size()等于X, 返回i
     * 循环结束返回-1
     */
    public int solution(int X, int[] A) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < A.length; i++) {
            set.add(A[i]);
            if (set.size() == X) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 定义长度为X的boolean数组, 索引对应数组中的数字。
     * 利用boolean只能改一次值的特点, 每改一次我们将step+1, 直到与X相等, 返回i。
     */
    public int solution2(int X, int[] A) {
        int steps = X;
        boolean[] bitmap = new boolean[steps+1];
        for(int i = 0; i < A.length; i++){
            if(!bitmap[A[i]]){
                bitmap[A[i]] = true;
                steps--;
            }
            if(steps == 0) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        FrogRiverOne fro = new FrogRiverOne();
        int X = 5;
        int[] A = new int[] {1, 3, 1, 4, 2, 3, 5, 4};
        System.out.println(fro.solution(X, A));
    }

}
