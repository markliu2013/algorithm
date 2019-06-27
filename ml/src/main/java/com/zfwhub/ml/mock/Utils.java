package com.zfwhub.ml.mock;

import java.util.Arrays;

public class Utils {
    
    // add bias, then convert to dot products
    public static double[][] addBias(double[][] X) {
        double[][] result = new double[X.length][X[0].length+1];
        for (int i = 0; i < X.length; i++) {
            double[] temp = new double[X[i].length+1];
            System.arraycopy(X[i], 0, temp, 1, X[i].length);
            temp[0] = 1;
            result[i] = temp;
        }
        return result;
    }
    
    public static void main(String[] args) {
        double[][] X = new double[][] {{1,2},{3,4}};
        System.out.println(Arrays.deepToString(addBias(X)));
    }

}
