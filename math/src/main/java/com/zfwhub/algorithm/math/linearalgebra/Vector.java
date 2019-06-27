package com.zfwhub.algorithm.math.linearalgebra;

import java.util.Arrays;

public class Vector {
    
    private double[] data;

    public Vector(double[] data) {
        this.data = data;
    }
    
    public double[] getData() {
        return data;
    }
    
    @Override
    public String toString() {
        return Arrays.toString(data);
    }
    
    public double length() {
        int result = 0;
        for (double e : data) {
            result += e*e;
        }
        return Math.sqrt(result);
    }
    
    public void scaleMultiply(double scale) {
        for (int i = 0; i < data.length; i++) {
            data[i] *= scale;
        }
    }
    
    public void minus(Vector a) {
        // size must be consistent
        if (this.getData().length != a.getData().length) {
            throw new IllegalArgumentException("the length is not consistent");
        }
        for (int i = 0; i < this.getData().length; i++) {
            this.getData()[i] -= a.getData()[i];
        }
    }
    
    public void add(Vector a) {
        if (this.getData().length != a.getData().length) {
            throw new IllegalArgumentException("the length is not consistent");
        }
        for (int i = 0; i < this.getData().length; i++) {
            this.getData()[i] += a.getData()[i];
        }
    }
    
}
