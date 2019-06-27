package com.zfwhub.algorithm.math.linearalgebra;

public class VectorUtil {
    
    /**
     * is a and b orthogonal ?
     * @param a
     * @param b
     * @return
     */
    public static boolean isOrthogonal(Vector a, Vector b) {
        return false;
    }
    
    /**
     * dot product
     * @param a
     * @param b
     * @return
     */
    public static double dotProducts(Vector a, Vector b) {
        // size must be consistent
        if (a.getData().length != b.getData().length) {
            throw new IllegalArgumentException("the length is not consistent");
        }
        double result = 0;
        for (int i = 0; i < a.getData().length; i++) {
            result += a.getData()[i] * b.getData()[i];
        }
        return result;
    }
    
    /**
     * cross product
     * @param a
     * @param b
     * @return
     */
    public static Vector crossProducts(Vector a, Vector b) {
        return null;
    }
    
    /**
     * a = a - b
     * @param a
     * @param b
     * @return
     */
    public static void minus(Vector a, Vector b) {
        // size must be consistent
        if (a.getData().length != b.getData().length) {
            throw new IllegalArgumentException("the length is not consistent");
        }
        for (int i = 0; i < a.getData().length; i++) {
            a.getData()[i] -= b.getData()[i];
        }
    }
    
    public static Vector scaleMultiply(Vector a, double scale) {
        Vector v = new Vector(a.getData());
        for (int i = 0; i < a.getData().length; i++) {
            a.getData()[i] *= scale;
        }
        return v;
    }

}
