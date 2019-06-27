package com.zfwhub.ml.mock;

import java.util.Arrays;

import com.zfwhub.algorithm.math.linearalgebra.Vector;
import com.zfwhub.algorithm.math.linearalgebra.VectorUtil;

/**
 * mock sklearn.linear_model.LinearRegression
 */
public class LinearRegression {
    
    public Vector theta;
    public double alpha = 0.01; // learning rate
    
    
    public void fit(double[][] X, double[] y) {
        if (X.length != y.length) {
            throw new IllegalArgumentException("Found input variables with inconsistent numbers of samples");
        }
        double[][] biasX = Utils.addBias(X);
        double[] thetaArray = new double[biasX[0].length];
        for (int i = 0; i < thetaArray.length; i++) {
            thetaArray[i] = 1;
        }
        theta = new Vector(thetaArray);
        for (int i = 0; i < 10000; i++) {
            // System.out.println("theta:" + theta);
            // System.out.println("loss: " + getLoss(X, y));
            bgd(X, y);
        }
    }
    
    // Batch gradient descent
    public void bgd(double[][] X, double[] y) {
        double[][] biasX = Utils.addBias(X);
        // gradient
        Vector gradient = null;
        for (int i = 0; i < X.length; i++) {
            Vector vXi = new Vector(biasX[i]);
            double loss = VectorUtil.dotProducts(theta, vXi) - y[i];
            Vector v = VectorUtil.scaleMultiply(vXi, loss);
            if (gradient == null) {
                gradient = v;
            } else {
                gradient.add(v);
            }
        }
        theta.minus(VectorUtil.scaleMultiply(gradient, alpha / X.length));
    }
    
    public int getLoss(double[][] X, double[] y) {
        double[] preResult = predict(X);
        int loss = 0;
        for (int i = 0; i < y.length; i++) {
            loss += Math.sqrt(y[i] - preResult[i]);
        }
        return loss;
    }
    
    public double[] predict(double[][] X) {
        double[][] biasX = Utils.addBias(X);
        double[] result = new double[X.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = VectorUtil.dotProducts(theta, new Vector(biasX[i]));
        }
        return result;
    }
    
    public static void main(String[] args) {
        LinearRegression reg = new LinearRegression();
        double[][] X = new double[][] {{1}, {2}, {3}, {4}, {5}};
        double[] y = new double[] {2, 4, 6, 8, 10};
        reg.fit(X, y);
        double[] result = reg.predict(new double[][] {{9}, {12}});
        System.out.println(Arrays.toString(result));
        
//        double[][] X = new double[][] {{1, 1}, {1, 2}, {2, 2}, {2, 3}};
//        double[] y = new double[] {6, 8, 9, 11};
//        reg.fit(X, y);
//        double[] result = reg.predict(new double[][] {{3, 5}});
//        System.out.println(Arrays.toString(result));
    }
    
}
