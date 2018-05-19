package com.zfwhub.algorithm.codility.stacks_and_queues;

import java.util.HashSet;

/**
 * https://app.codility.com/programmers/lessons/7-stacks_and_queues/stone_wall/
 */
public class StoneWall {
    // TODO
    public static int solution(int[] H) {
        int count = 0;
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < H.length; i++) {
            if (set.contains(H[i])) {
                for (int j = 0; j > H[i]; j++) {
                    set.remove(j);
                }
            } else {
                set.add(H[i]);
                count++;
                for (int j = 0; j < H[i]; j++) {
                    set.remove(j);
                }
            }
        }
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println(StoneWall.solution(new int[] {8,8,5,7,9,8,7,4,8}));
    }
}
