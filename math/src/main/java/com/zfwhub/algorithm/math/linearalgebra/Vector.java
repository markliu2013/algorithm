package com.zfwhub.algorithm.math.linearalgebra;

public class Vector {
    
    private int[] data;

    public Vector(int[] data) {
        this.data = data;
    }
    
    public double length() {
        int result = 0;
        for (int i : data) {
            result += i*i;
        }
        return Math.sqrt(result);
    }

}
