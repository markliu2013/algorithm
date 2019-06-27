package com.zfwhub.algorithm.math.linearalgebra;

import java.util.Arrays;

public class Matrix {
    
    private double[][] data;

    public Matrix(double[][] data) {
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
    
    /**
     * 是否方阵
     * @return
     */
    public boolean isSquare() {
        int m = data.length;
        int n = data[0].length;
        return m == n;
    }
    
    /**
     * 是否对称阵
     * @return
     */
    public boolean isSymmetric() {
        // A为方形矩阵是A为对称矩阵的必要条件。
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
    
    /**
     * 是否可逆
     * @return
     */
    public boolean isInvertible() {
        return false;
    }
    
    /**
     * 求秩
     * @return
     */
    public int rank() {
        return 0;
    }
    
    /**
     * 求行列式
     * @return
     */
    public double determinant() {
        // 只有方阵才能求行列式。
        return 0;
    }
    
    /**
     * 转置矩阵
     */
    public void transpose() {
        
    }
    
    /**
     * 逆矩阵
     */
    public void inverse() {
        
    }
    
    @Override
    public String toString() {
        return Arrays.deepToString(data);
    }
}
