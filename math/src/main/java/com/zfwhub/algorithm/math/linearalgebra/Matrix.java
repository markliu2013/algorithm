package com.zfwhub.algorithm.math.linearalgebra;

public class Matrix {
    
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
        return false;
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
    
    public static void main(String[] args) {
        int[][] data = new int[][] {{1,2},{1,3}};
        Matrix matrix = new Matrix(data);
        System.out.println(matrix.isSquare());
    }

}
