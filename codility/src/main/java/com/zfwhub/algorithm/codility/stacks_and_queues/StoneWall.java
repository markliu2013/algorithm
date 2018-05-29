package com.zfwhub.algorithm.codility.stacks_and_queues;

import java.util.HashSet;
import java.util.Iterator;

/**
 * https://app.codility.com/programmers/lessons/7-stacks_and_queues/stone_wall/
 */
public class StoneWall {

    /**
     * brute force
     */
    public static int solution(int[] H) {
        int count = 0;
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < H.length; i++) {
            if (set.contains(H[i])) {
                Iterator<Integer> iterator = set.iterator();
                while (iterator.hasNext()) {
                    Integer element = iterator.next();
                    if (element > H[i]) {
                        iterator.remove();
                    }
                }
            } else {
                set.add(H[i]);
                count++;
                Iterator<Integer> iterator = set.iterator();
                while (iterator.hasNext()) {
                    Integer element = iterator.next();
                    if (element > H[i]) {
                        iterator.remove();
                    }
                }
            }
        }
        return count;
    }

    /**
     * brute force
     */
    public static int solution2(int[] H) {
        int count = 0;
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < H.length; i++) {
            System.out.print(count + " ");
            if (!set.contains(H[i])) {
                set.add(H[i]);
                count++;
            }
            Iterator<Integer> iterator = set.iterator();
            while (iterator.hasNext()) {
                Integer element = iterator.next();
                if (element > H[i]) {
                    iterator.remove();
                }
            }
        }
        return count;
    }

    // TODO StoneWall
    public static int solution3(int[] H) {
        int count = 0;
        int metric = 0;
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < H.length; i++) {
            System.out.print(count + " ");
            if (set.contains(H[i]) && H[i] <= metric) {
            } else {
                set.add(H[i]);
                count++;
            }
            metric = H[i];
        }
        return count;
    }

    // TODO StoneWall stack or quene
    public static int solution6(int[] H) {
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(StoneWall.solution2(new int[] { 8, 8, 5, 7, 9, 8, 7, 4, 8 }));
        System.out.println(StoneWall.solution3(new int[] { 8, 8, 5, 7, 9, 8, 7, 4, 8 }));
        //        System.out.println(StoneWall.solution2(new int[] {1,2,3,1,2}));
        //        System.out.println(StoneWall.solution3(new int[] {1,2,3,1,2}));
    }
}
