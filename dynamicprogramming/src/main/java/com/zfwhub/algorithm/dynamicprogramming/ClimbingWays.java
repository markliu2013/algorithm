package com.zfwhub.algorithm.dynamicprogramming;

import java.util.HashMap;

/**
 * 爬楼梯有几种爬法?
 * 对应斐波那契数列
 */
public class ClimbingWays {

    // TODO 暴力解法
    public static int getClimbingWays(int n) {
        return 0;
    }

    /**
     * 自顶向下
     */
    public static int getClimbingWays1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return getClimbingWays1(n-1) + getClimbingWays1(n-2);
    }

    /**
     * 备忘录
     */
    public static int getClimbingWays2(int n, HashMap<Integer, Integer> map) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        } else {
            int value = getClimbingWays2(n-1, map) + getClimbingWays2(n-2, map);
            map.put(n, value);
            return value;
        }
    }

    /**
     * 自底向上
     */
    public static int getClimbingWays3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int a = 1;
        int b = 2;
        int temp = 0;
        for (int i = 3; i <= n; i++) {
            temp = a + b;
            a = b;
            b = temp;
        }
        return temp;
    }


    public static void main(String[] args) {
        System.out.println(ClimbingWays.getClimbingWays1(10));
    }


}
