package com.zfwhub.algorithm.codility.stacks_and_queues;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

import com.zfwhub.algorithm.utils.CollectionUtil;

// https://app.codility.com/programmers/lessons/7-stacks_and_queues/stone_wall/
public class StoneWall {

    // brute force, Performance 44%
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

    // Performance 88%
    public static int solution2(int[] H) {
        // list中永远都是从小到大
        LinkedList<Integer> list = new LinkedList<>();
        // 默认先把第一个放进list
        list.add(H[0]);
        int count = 1;
        for (int i = 1; i < H.length; i++) {
            if (H[i] <= list.get(list.size()-1)) { // 当前的比list最后一个小
                int index = Collections.binarySearch(list, H[i]);
                if (index < 0) { // list中没有
                    index = Math.abs(index+1);
                    count++;
                }
                // 清空在index之后的
                list.subList(index, list.size()).clear();
                list.add(H[i]);
            } else {
                count++;
                list.add(H[i]);
            }
        }
        return count;
    }
    
    // StoneWall https://codility.com/media/train/solution-stone-wall.pdf
    
    public static void main(String[] args) {
//        int[] H = new int[] { 8, 8, 5, 7, 9, 8, 7,4,8 };
        int[] H = new int[] { 1,2,1};
        System.out.println(solution1(H));
        System.out.println(solution2(H));
    }
}
