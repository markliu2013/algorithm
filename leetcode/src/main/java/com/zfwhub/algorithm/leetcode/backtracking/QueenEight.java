package com.zfwhub.algorithm.leetcode.backtracking;

import java.util.*;

public class QueenEight {
    
    // 暴力解法，列出所有的组合，然后判断每一列，每一条斜线。
    // https://www.cnblogs.com/houkai/p/3480940.html
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
    
    // 提前排除那些没有前途的状态，会节约时间——回溯法，体现了“回溯”
    public static int solution2() {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int[][] arr1 = new int[8][8];
                arr1[0][i] = 1;
                arr1[1][j] = 1;
                if (!check(arr1)) {
                    continue;
                }
                for (int k = 0; k < 8; k++) {
                    int[][] arr2 = new int[8][8];
                    arr2[0][i] = 1;
                    arr2[1][j] = 1;
                    arr2[2][k] = 1;
                    if (!check(arr2)) {
                        continue;
                    }
                    for (int l = 0; l < 8; l++) {
                        int[][] arr3 = new int[8][8];
                        arr3[0][i] = 1;
                        arr3[1][j] = 1;
                        arr3[2][k] = 1;
                        arr3[3][l] = 1;
                        if (!check(arr3)) {
                            continue;
                        }
                        for (int m = 0; m < 8; m++) {
                            int[][] arr4 = new int[8][8];
                            arr4[0][i] = 1;
                            arr4[1][j] = 1;
                            arr4[2][k] = 1;
                            arr4[3][l] = 1;
                            arr4[4][m] = 1;
                            if (!check(arr4)) {
                                continue;
                            }
                            for (int n = 0; n < 8; n++) {
                                int[][] arr5 = new int[8][8];
                                arr5[0][i] = 1;
                                arr5[1][j] = 1;
                                arr5[2][k] = 1;
                                arr5[3][l] = 1;
                                arr5[4][m] = 1;
                                arr5[5][n] = 1;
                                if (!check(arr5)) {
                                    continue;
                                }
                                for (int o = 0; o < 8; o++) {
                                    int[][] arr6 = new int[8][8];
                                    arr6[0][i] = 1;
                                    arr6[1][j] = 1;
                                    arr6[2][k] = 1;
                                    arr6[3][l] = 1;
                                    arr6[4][m] = 1;
                                    arr6[5][n] = 1;
                                    arr6[6][o] = 1;
                                    if (!check(arr6)) {
                                        continue;
                                    }
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
    private static boolean check(int[][] arr) {
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
    
    public static int solution3() {
        List<List<Integer>> list = new ArrayList<>();//存放解，存储cols
        List<Integer> cols = new ArrayList<>();//每一列第几行放置皇后
        solution3_backtrack(list, cols);
        return list.size();
    }
    
    private static void solution3_backtrack(List<List<Integer>> list, List<Integer> cols) {
        if (cols.size() == 8) { //8列都放好了，表示找到解了。
            list.add(new ArrayList<Integer>(cols));//注意cols是引用类型，必须clone一份。
        } else {
            for (int i = 0; i < 8; i++) {//每一列都一行一行的尝试
                if (!solution3_backtrack_check(cols, i)) {//不符合，继续当前列下一行
                    continue;
                }
                cols.add(i);//make_move，移动到下一列。
                solution3_backtrack(list, cols);
                cols.remove(cols.size()-1);//unmake_move
            }
        }
    }
    
    //i是行数，在第cols.size列，尝试第i行
    private static boolean solution3_backtrack_check(List<Integer> cols, int i) {
        if (cols.contains(i)) {//同一行
            return false;
        } else {//不在同一行，检查对角线
            for (int j = 0; j < cols.size(); j++) {
                if (i == cols.get(j)+(cols.size()-j)) {
                    return false;
                }
                if (i == cols.get(j)-(cols.size()-j)) {
                    return false;
                }
            }
            return true;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(solution3());
    }

}
