package com.zfwhub.algorithm.acm.sdut.pid1522;

import java.util.Scanner;
// http://acm.sdut.edu.cn/onlinejudge2/index.php/Home/Index/problemdetail/pid/1522.html
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            while (sc.hasNextLine()) {
                int m = sc.nextInt();
                if (m == 0) {
                    break;
                }
                int[][] data = new int[m][m];
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < m; j++) {
                        data[i][j] = sc.nextInt();
                    }
                }
                Matrix matrix = new Matrix(data);
                System.out.println(matrix.isSymmetric() ? "yes" : "no");
            }
        } finally {
            sc.close();
        }
    }
}
 class Matrix {
    
    private int[][] data;

    public Matrix(int[][] data) {
        if (data == null) {
            throw new IllegalArgumentException("data is null");
        }
        if (data.length == 0 || data[0] == null || data[0].length == 0) {
            throw new IllegalArgumentException("data is empty");
        }
        int n = data[0].length;
        for (int i = 1; i < data.length; i++) {
            if (data[i].length != n) {
                throw new IllegalArgumentException("data is not match m*n");
            }
        }
        this.data = data;
    }
    public boolean isSquare() {
        int m = data.length;
        int n = data[0].length;
        return m == n;
    }
    public boolean isSymmetric() {
        if (!isSquare()) {
            return false;
        }
        for (int i = 0; i < data.length; i++) {
            for (int j = i+1; j < data.length; j++) {
                if (data[i][j] != data[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
