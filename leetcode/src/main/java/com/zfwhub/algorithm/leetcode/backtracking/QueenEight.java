package com.zfwhub.algorithm.leetcode.backtracking;

public class QueenEight {
    
    /**
             * 暴力解法，列出所有的组合，然后判断每一列，每一条斜线。
     */
    public static int solution() {
        int count = 0;
        //不在同一行上, 所有可能。
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    for (int l = 0; l < 8; l++) {
                        for (int m = 0; m < 8; m++) {
                            for (int n = 0; n < 8; n++) {
                                for (int o = 0; o < 8; o++) {
                                    for (int p = 0; p < 8; p++) {
                                        int[][] arr = new int[8][8];
                                        arr[0][i] = 1;
                                        arr[1][j] = 1;
                                        arr[2][k] = 1;
                                        arr[3][l] = 1;
                                        arr[4][m] = 1;
                                        arr[5][n] = 1;
                                        arr[6][o] = 1;
                                        arr[7][p] = 1;
                                        if (check(arr)) {
                                            count++;
                                            System.out.println("第"+count+"种解法：");
                                            for (int r = 0; r < 8; r++) {
                                                for (int s = 0; s < 8; s++) {
                                                    System.out.print(arr[r][s] + " ");
                                                }
                                                System.out.println("");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
    
    //检查是否满足
    public static boolean check(int[][] arr) {
        // 不在同一列上
        for (int i = 0; i < 8; i++) {
            int count = 0;
            for (int j = 0; j < 8; j++) {
                if (arr[j][i] == 1) {
                    count++;
                    if (count > 1) {
                        return false;
                    }
                }
            }
        }
        // 不在同一斜线上 /
        for (int i = 0; i < 8; i++) {
            int count1 = 0;
            for (int j = 0; j <= i ; j++) {
                if (arr[i-j][j] == 1) {
                    count1++;
                    if (count1 > 1) {
                        return false;
                    }
                }
            }
            int count2 = 0;
            for (int j = 0; j <= i ; j++) {
                if (arr[7-(i-j)][7-j] == 1) {
                    count2++;
                    if (count2 > 1) {
                        return false;
                    }
                }
            }
        }
        // 不在同一反斜线上 \
        for (int i = 0; i < 8; i++) {
            int count1 = 0;
            for (int j = 0; j <= i ; j++) {
                if (arr[i-j][7-j] == 1) {
                    count1++;
                    if (count1 > 1) {
                        return false;
                    }
                }
            }
            int count2 = 0;
            for (int j = 0; j <= i ; j++) {
                if (arr[7-(i-j)][j] == 1) {
                    count2++;
                    if (count2 > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println(solution());
    }

}
