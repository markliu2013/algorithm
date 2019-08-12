package com.zfwhub.ml.mock;

import com.zfwhub.algorithm.math.linearalgebra.Vector;

// https://www.zybuluo.com/hanbingtao/note/433855
public class Perceptron {
    
    private Vector weights;
    private double bias;
    
    public Perceptron(int inputNum) {
        weights = new Vector(new double[inputNum]);
        bias = 0.0;
    }
    
    public boolean active(int x) {
        return false;
    }
    

}
