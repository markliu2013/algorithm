package com.zfwhub.algorithm.codility.euclidean_algorithm;

/**
 * https://app.codility.com/programmers/lessons/12-euclidean_algorithm/chocolates_by_numbers/
 */
public class ChocolatesByNumbers {

    public static int solution(int N, int M) {
        int count = 0;
        boolean[] isEmptyArr = new boolean[N];
        int i = 0;
        while (isEmptyArr[i] != true) {
            isEmptyArr[i] = true;
            i = (i + M) % N;
            count++;
        }
        isEmptyArr[0] = true;
        return count;
    }

    public static int solution2(int N, int M) {
        int gcd = GreatestCommonDivisor.gcd3(N, M);
        return N / gcd;
    }

    public static void main(String[] args) {
        System.out.println(ChocolatesByNumbers.solution(10, 5));
        System.out.println(ChocolatesByNumbers.solution2(10, 5));
    }

}
