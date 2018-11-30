package com.zfwhub.algorithm.leetcode.backtracking;

// https://leetcode.com/problems/additive-number/
public class AdditiveNumber {
    
    // Wrong Answer
    public static boolean isAdditiveNumber(String num) {
        int[] numArr = new int[num.length()];
        for (int i = 0; i < num.length(); i++) {
            numArr[i] = Character.getNumericValue(num.charAt(i));
        }
        for (int i = 2; i < numArr.length; i++) {
            boolean flag = false;  
            checkIndex:
            for (int j = 0; j < i; j++) {
                for (int k = j+1; k < i; k++) {
                    if (numArr[j] + numArr[k] == numArr[i]) {
                        flag = true;
                        break checkIndex;
                    }
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
//        System.out.println(isAdditiveNumber("112358"));
        System.out.println(isAdditiveNumber("199100199"));
    }
    
}
