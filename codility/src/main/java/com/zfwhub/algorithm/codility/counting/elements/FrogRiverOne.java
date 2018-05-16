package com.zfwhub.algorithm.codility.counting.elements;
import java.util.HashSet;

/**
 * give an array and X, find minimum index contains all 1 to X numbers.
 * https://app.codility.com/programmers/lessons/4-counting_elements/frog_river_one/
 */
public class FrogRiverOne {

    /**
     * HashSet can't add duplicated number.
     * add number to set one by one from array use loop.
     * when set size is X, return i
     * return -1 end loop
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
     * each element of array A is an integer within the range [1..X].
     * so we can use X size boolean array, index match the number in array.
     * the boolean only can be changed once,
     * every change step+1 until X, return i 
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
