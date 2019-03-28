package com.zfwhub.algorithm.codility.stacks_and_queues;

import java.util.*;

// https://app.codility.com/programmers/lessons/7-stacks_and_queues/fish/
public class Fish {

    // Performance 100%
    public static int solution1(int[] A, int[] B) {
        Stack<Integer> s = new Stack<Integer>();
        for (int i = 0; i < A.length; i++) {
            int size = A[i];
            int dir = B[i];
            if (s.empty()) {
                s.push(i);
            } else {
                while (!s.empty() && dir - B[s.peek()] == -1 && A[s.peek()] < size) {
                    s.pop();
                }
                if (!s.empty()) {
                    if (dir - B[s.peek()] != -1)
                        s.push(i);
                } else {
                    s.push(i);
                }
            }
        }
        return s.size();
    }

    public static void main(String[] args) {
        int[] A = new int[] { 4,3,2,1,5};
        int[] B = new int[] { 0,1,0,0,0 };
        System.out.println(solution1(A, B));
    }

}
