package com.zfwhub.algorithm.codility.stacks_and_queues;

import java.util.HashSet;

import com.zfwhub.algorithm.utils.CollectionUtil;

// https://app.codility.com/programmers/lessons/7-stacks_and_queues/stone_wall/
public class StoneWall {

    // brute force, Performance bad
    public static int solution1(int[] H) {
        int count = 0;
        // set保存后面可以复用的stone
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < H.length; i++) {
            if (!set.contains(H[i])) {
                set.add(H[i]);
                count++;
            }
            // 删除set中大于H[i]，所有大于当前stone的都无法复用了。
            CollectionUtil.remove(set, new Integer(H[i]), (a, b) -> a.compareTo(b));
        }
        return count;
    }

    // TODO StoneWall stack or quene
    public static int solution2(int[] H) {
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(solution1(new int[] { 8, 8, 5, 7, 9, 8 }));
        System.out.println(solution2(new int[] { 8, 8, 5, 7, 9, 8 }));
        //        System.out.println(solution2(new int[] {1,2,3,1,2}));
        //        System.out.println(solution3(new int[] {1,2,3,1,2}));
    }
}
