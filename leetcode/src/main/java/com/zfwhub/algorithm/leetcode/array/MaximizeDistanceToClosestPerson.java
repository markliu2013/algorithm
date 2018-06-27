package com.zfwhub.algorithm.leetcode.array;

/**
 * https://leetcode.com/contest/weekly-contest-88/problems/maximize-distance-to-closest-person/
 */
public class MaximizeDistanceToClosestPerson {

    public static int solution(int[] seats) {
        int maxDistToClosest = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 0) {
                // check left
                int leftDistToClosest = 0;
                boolean hasLeft = false;
                for (int j = i - 1; j >= 0; j--) {
                    if (seats[j] == 0) {
                        leftDistToClosest++;
                    } else if (seats[j] == 1) {
                        leftDistToClosest++;
                        hasLeft = true;
                        break;
                    }
                }
                // check right
                int rightDistToClosest = 0;
                boolean hasRight = false;
                for (int j = i + 1; j < seats.length; j++) {
                    if (seats[j] == 0) {
                        rightDistToClosest++;
                    } else if (seats[j] == 1) {
                        rightDistToClosest++;
                        hasRight = true;
                        break;
                    }
                }
                int distToClosest = 0;
                if (hasRight && hasLeft) {
                    distToClosest = Math.min(leftDistToClosest, rightDistToClosest);
                } else {
                    if (!hasLeft) {
                        distToClosest = rightDistToClosest;
                    }
                    if (!hasRight) {
                        distToClosest = leftDistToClosest;
                    }
                }
                maxDistToClosest = Math.max(maxDistToClosest, distToClosest);
            }
        }
        return maxDistToClosest;
    }

    public static void main(String[] args) {
        int[] seats1 = new int[] { 1, 0, 0, 0, 1, 0, 1 };
        int[] seats2 = new int[] { 1, 0, 0, 0 };
        System.out.println(MaximizeDistanceToClosestPerson.solution(seats1));
        System.out.println(MaximizeDistanceToClosestPerson.solution(seats2));
    }
}
