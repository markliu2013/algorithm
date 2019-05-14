package com.zfwhub.algorithm.leetcode.contest136;

import java.util.*;

// https://leetcode.com/contest/weekly-contest-136/problems/robot-bounded-in-circle/
public class RobotBoundedInCircle {
    
    public static boolean solution1(String instructions) {
        if (instructions.indexOf('G') < 0) {
            return true;
        }
        Map<Point, Integer> pathMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            sb.append(instructions);
        }
        int currentD = 1; //north
        Point currentP = new Point(0, 0);
        for (int i = 0; i < sb.length(); i++) {
            char instruction = sb.charAt(i);
            switch (instruction) {
                case 'G':
                    switch (currentD) {
                        case 1:
                            currentP.y++;
                            break;
                        case 2:
                            currentP.x--;
                            break;
                        case 3:
                            currentP.y--;
                            break;
                        case 4:
                            currentP.x++;
                            break;
                        default:
                            break;
                    }
                    if (pathMap.containsKey(currentP)) {
                        pathMap.put(new Point(currentP.x, currentP.y), pathMap.get(currentP)+1);
                    } else {
                        pathMap.put(new Point(currentP.x, currentP.y), 1);
                    }
                    break;
                case 'L':
                    if (currentD == 4) {
                        currentD = 1;
                    } else {
                        currentD++;
                    }
                    break;
                case 'R':
                    if (currentD == 1) {
                        currentD = 4;
                    } else {
                        currentD--;
                    }
                    break;
                default:
                    break;
            }
        }
        List<Integer> list = new ArrayList<>(pathMap.values());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < 2) {
                return false;
            }
        }
        return true;
    }
    
    public static class Point {
        
        public int x;
        public int y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Point other = (Point) obj;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            return true;
        }
        @Override
        public String toString() {
            return "Point [x=" + x + ", y=" + y + "]";
        }
        
    }
    
    public static void main(String[] args) {
        System.out.println(solution1("GGLLGG")); // true
        System.out.println(solution1("GG")); // false
        System.out.println(solution1("GL")); // true
        System.out.println(solution1("LRRRRLLLRL")); // true
        System.out.println(solution1("RRGRRGLLLRLGGLGLLGRLRLGLRLRRGLGGLLRRRLRLRLLGRGLGRRRGRLG"));//false
    }
    
}
